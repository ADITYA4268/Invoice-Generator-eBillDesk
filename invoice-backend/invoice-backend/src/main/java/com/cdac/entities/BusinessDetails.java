package com.cdac.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Business_Details")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class BusinessDetails extends BaseEntity {

	@Column(name = "Shop Name", length = 30, nullable = false)
	private String shopName;
	@Column(name = "GSTIN", length = 15, nullable = false, unique = true)
	private String gstin;
	@Column(name = "Address", length = 30, nullable = false)
	private String address;
	@Column(name = "City", length = 20, nullable = false)
	private String city;
	@Column(name = "PIN Code", length = 6, nullable = false)
	private int pinCode;

	
}
