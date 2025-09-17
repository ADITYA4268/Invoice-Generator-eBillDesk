package com.cdac.service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class OTPServiceImpl implements OTPService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	private Map<String, String> otpStore = new ConcurrentHashMap<>();

	public String generateOtp() {
		return String.valueOf(100000 + new Random().nextInt(900000));
	}

	@Override
	public String sendOtpEmail(String email, String otp) {
        // Save OTP
		otpStore.put(email, otp);

        // Send email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your OTP for Login");
        message.setText("Your OTP is: " + otp);
        javaMailSender.send(message);

        return "OTP sent successfully";
    }

	@Override
	public boolean verifyOtp(String email, String otp) {
		String storedOtp = otpStore.get(email);

        if (storedOtp != null && storedOtp.equals(otp)) {
        	otpStore.remove(email); // OTP is single-use
            return true;
        }
        return false;
    }
}

//	@Override
//	public String sendOtpEmail(String email) {
//		String otp = OTPUtil.generateOTP();
//
//		try {
//			MimeMessage message = javaMailSender.createMimeMessage();
//			MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//			helper.setTo(email);
//			helper.setSubject("Your OTP for Invoice Verification");
//			helper.setText("Your OTP is: " + otp + "\nIt will expire in 5 minutes.", true);
//
//			javaMailSender.send(message);
//		} catch (MessagingException e) {
//			throw new RuntimeException("Failed to send email", e);
//		}
//
//		return otp; // for testing; in production, do NOT return OTP
//	}


