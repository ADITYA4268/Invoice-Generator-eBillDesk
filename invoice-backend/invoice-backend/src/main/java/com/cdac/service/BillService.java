package com.cdac.service;

import java.util.List;

import com.cdac.dto.BillReqDto;
import com.cdac.dto.BillRespDto;
import com.cdac.dto.PaymentReqDto;
import com.cdac.dto.PaymentRespDto;
import com.cdac.entities.Bill;

public interface BillService {

	BillRespDto createBillByUserId(BillReqDto dto, Long userId);

	Bill createBill(Bill bill);

	PaymentRespDto payBill(Long billId, PaymentReqDto paymentRequest);

	BillRespDto getBillById(Long id);

	BillRespDto generateBill(Long userId, BillReqDto dto);

	List<BillRespDto> getAllBills();

	List<BillRespDto> getBillsByUserId(Long userId);
	
	PaymentRespDto generatePayment(Long billId);

	void deleteBill(Long id);

}
