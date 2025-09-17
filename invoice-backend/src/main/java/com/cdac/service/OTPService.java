package com.cdac.service;

public interface OTPService {

	//public String sendOtpEmail(String email);
	
	public String generateOtp();
	public String sendOtpEmail(String email, String otp);
	boolean verifyOtp(String email, String otp);
}
