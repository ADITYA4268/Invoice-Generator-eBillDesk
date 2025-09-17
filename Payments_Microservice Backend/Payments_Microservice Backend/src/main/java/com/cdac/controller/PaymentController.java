package com.cdac.controller;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.entity.Payment;
import com.cdac.service.PaymentService;

import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/payment")
@NoArgsConstructor
public class PaymentController {

	private PaymentService paymentService;
	private ModelMapper modelMapper;

	// Create payment
	@PostMapping
	public ResponseEntity<?> makePayment(@RequestBody Payment payment) {
		Payment saved = paymentService.makePayment(payment);
		return ResponseEntity.ok(saved);
	}

	// Get payment by billId
	@GetMapping("/{billId}")
	public ResponseEntity<Payment> getPayment(@PathVariable Long billId) {
		Optional<Payment> payment = paymentService.getPaymentByBillId(billId);
		return payment.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
}
