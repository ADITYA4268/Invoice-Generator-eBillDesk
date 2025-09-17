package com.cdac.util;

import java.util.Random;

public class OTPUtil {

//	private static final String CHARACTERS = "0123456789";
//    private static final int OTP_LENGTH = 6;
//    private static final SecureRandom random = new SecureRandom();
//
//    public static String generateOTP() {
//        StringBuilder sb = new StringBuilder(OTP_LENGTH);
//        for (int i = 0; i < OTP_LENGTH; i++) {
//            int index = random.nextInt(CHARACTERS.length());
//            sb.append(CHARACTERS.charAt(index));
//        }
//        return sb.toString();
//    }
	
	public static String generateOtp() {
        return String.format("%06d", new Random().nextInt(999999));
    }
}
