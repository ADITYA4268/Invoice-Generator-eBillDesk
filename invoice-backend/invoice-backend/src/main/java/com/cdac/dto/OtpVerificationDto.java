package com.cdac.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OtpVerificationDto {

	private String email;
    private String otp;
	
}
