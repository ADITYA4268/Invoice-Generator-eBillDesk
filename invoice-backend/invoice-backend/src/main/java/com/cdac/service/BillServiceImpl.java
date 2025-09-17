package com.cdac.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cdac.client.PaymentClient;
import com.cdac.dao.BillDao;
import com.cdac.dao.ItemDao;
import com.cdac.dao.UserDao;
import com.cdac.dto.BillReqDto;
import com.cdac.dto.BillRespDto;
import com.cdac.dto.PaymentReqDto;
import com.cdac.dto.PaymentRespDto;
import com.cdac.entities.Bill;
import com.cdac.entities.Item;
import com.cdac.entities.User;
import com.cdac.exceptions.BillNotFoundException;
import com.cdac.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class BillServiceImpl implements BillService {

	@Autowired
	private BillDao billDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private ModelMapper modelMapper;

	private ItemDao itemDao;

	private RestTemplate restTemplate;

	private PaymentClient paymentClient;

	@Override
	public BillRespDto createBillByUserId(BillReqDto dto, Long userId) {
		User user = userDao.findById(userId).orElseThrow(() -> new BillNotFoundException("User not found"));

		Bill bill = modelMapper.map(dto, Bill.class);
		bill.setUser(user);
		bill.setDate(new Date()); // Set current date

		Bill savedBill = billDao.save(bill);
		return modelMapper.map(savedBill, BillRespDto.class);
	}

	@Override
	public BillRespDto getBillById(Long id) {
		Bill bill = billDao.findById(id).orElseThrow(() -> new BillNotFoundException("Bill not found"));
		return modelMapper.map(bill, BillRespDto.class);
	}

	@Override
	public List<BillRespDto> getAllBills() {
		return billDao.findAll().stream().map(bill -> modelMapper.map(bill, BillRespDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public void deleteBill(Long id) {
		if (!billDao.existsById(id)) {
			throw new BillNotFoundException("Bill not found");
		}
		billDao.deleteById(id);

	}

	@Override
	public List<BillRespDto> getBillsByUserId(Long userId) {
		List<Bill> bills = billDao.findByUserId(userId);
		return bills.stream().map(bill -> modelMapper.map(bill, BillRespDto.class)).collect(Collectors.toList());
	}

	// Here logic for total bill is added that is generate bill
	@Override
	public BillRespDto generateBill(Long userId, BillReqDto dto) {
		// Step 1: Get user
		User user = userDao.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));

		// Step 2: Fetch all cart items (not assigned to any bill yet)
		List<Item> cartItems = itemDao.findByUserIdAndBillIsNull(userId);
		if (cartItems.isEmpty()) {
			throw new RuntimeException("Cart is empty. Cannot generate bill.");
		}

		// Step 3: Create new Bill
		Bill bill = new Bill();
		bill.setInvoiceNo(dto.getInvoiceNo());
		bill.setDate(dto.getDate());
		bill.setCustomerName(dto.getCustomerName());
		bill.setUser(user);

		// Step 4: Calculate total
		double totalAmount = 0.0;
		for (Item item : cartItems) {
			double itemTotal = item.getQuantity() * item.getProduct().getSellingPrice();
			totalAmount += itemTotal;
			item.setBill(bill); // Assign this item to bill
		}

		bill.setAmount(totalAmount);

		// Step 5: Save bill + items
		Bill savedBill = billDao.save(bill);

		// Items already managed by persistence context, so no explicit save needed
		// but we can flush
		itemDao.saveAll(cartItems);

		// Step 6: Return response
		BillRespDto resp = modelMapper.map(savedBill, BillRespDto.class);
		return resp;
	}

	@Override
	public Bill createBill(Bill bill) {
		return billDao.save(bill);
	}

	@Override
	public PaymentRespDto payBill(Long billId, PaymentReqDto paymentRequest) {
		Optional<Bill> billOpt = billDao.findById(billId);
		if (billOpt.isEmpty()) {
			throw new RuntimeException("Bill not found with id: " + billId);
		}

		Bill bill = billOpt.get();

		// Call Payment Microservice
		String url = "http://localhost:8081/payments"; // ✅ Payment microservice endpoint
		PaymentRespDto paymentResponse = restTemplate.postForObject(url, paymentRequest, PaymentRespDto.class);

		// Update bill status if payment successful
		if (paymentResponse != null && "SUCCESS".equalsIgnoreCase(paymentResponse.getStatus())) {
			bill.setStatus("PAID");
			billDao.save(bill);
		}

		return paymentResponse;
	}

	@Override
	public PaymentRespDto generatePayment(Long billId) {
		Bill bill = billDao.findById(billId).orElseThrow(() -> new RuntimeException("Bill not found"));

		// Build PaymentRequest
		PaymentReqDto request = PaymentReqDto.builder().billId(bill.getId()).amount((double) (bill.getAmount() * 100)) // Razorpay works in paise
				.currency("INR").paymentMethod("UPI").build();

		// Call Payment Microservice
		return paymentClient.createPayment(request);
	}
}
