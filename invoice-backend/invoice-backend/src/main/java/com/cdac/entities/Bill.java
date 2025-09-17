package com.cdac.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "bill")
public class Bill extends BaseEntity {

	@Column(nullable = false, unique = true)
	private String invoiceNo;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Column(nullable = false)
	private String customerName;

	@Column(nullable = false)
	private int quantity;

	@Column(nullable = false)
	private double amount;

	@Column(name = "status", nullable = false)
	private String status;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

}