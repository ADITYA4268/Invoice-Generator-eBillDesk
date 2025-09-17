package com.cdac.service;

import com.cdac.dto.AuthReq;
import com.cdac.dto.UserReqDto;
import com.cdac.dto.UserRespDto;

public interface UserService {


	UserRespDto authenticate(AuthReq dto);

	UserRespDto signUp(UserReqDto dto);
	
	UserRespDto verifyOtp(String email, String otp);
}
