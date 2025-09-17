package com.cdac.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BusinessDetailReqDto {

	@NotBlank
	private String shopName;

	@NotBlank
	
	private String gstin;

	@NotBlank
	private String address;

	@NotBlank
	private String city;

	@NotNull
	
	private int pinCode;

	// Getters and Setters

}
