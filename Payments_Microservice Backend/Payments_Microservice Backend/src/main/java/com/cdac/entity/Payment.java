package com.cdac.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Payments")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Payment extends BaseEntity {

	@Column(name = "BillId", nullable = false, unique = true)
	private Long billId;
	@Column(name = "Amount", nullable = false)
	private Double amount;

	@Enumerated(EnumType.STRING)
	@Column(name = "Payment Method", nullable = false)
	private PaymentMethod method; // CARD, UPI, NETBANKING

	@Column(name = "Payment Status", nullable = false)
	private String status; // SUCCESS, FAILED, PENDING

}
