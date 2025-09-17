package com.cdac.service;

import java.util.Optional;

import com.cdac.entity.Payment;

public interface PaymentService {

	public Payment makePayment(Payment payment);
	
	public Optional<Payment> getPaymentByBillId(Long billId);
}
