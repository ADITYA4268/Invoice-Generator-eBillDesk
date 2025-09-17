package com.cdac.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cdac.dao.PaymentDao;
import com.cdac.entity.Payment;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {
	
	private PaymentDao paymentDao;
	@Override
	public Payment makePayment(Payment payment) {
		payment.setStatus("SUCCESS"); // just simulating success
        return paymentDao.save(payment);
	}

	@Override
	public Optional<Payment> getPaymentByBillId(Long billId) {
		return paymentDao.findByBillId(billId);
	}

}
