package com.cdac.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.dao.UserDao;
import com.cdac.dto.AuthReq;
import com.cdac.dto.UserReqDto;
import com.cdac.dto.UserRespDto;
import com.cdac.entities.User;
import com.cdac.exceptions.ApiException;
import com.cdac.exceptions.AuthenticationException;
import com.cdac.util.OTPUtil;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	private OTPService otpService;
	private ModelMapper modelMapper;
	private PasswordEncoder passwordEncoder;

	@Override
	public UserRespDto authenticate(AuthReq dto) {
		// invoke dao's method
		User entity = userDao.findByEmail(dto.getEmail())
				.orElseThrow(() -> new AuthenticationException("Invalid login !!!!!"));

		if (!passwordEncoder.matches(dto.getPassword(), entity.getPassword())) {
	        throw new AuthenticationException("Invalid login !!!!!");
	    }
		
		String otp = OTPUtil.generateOtp();
		// map entity -> dto
		otpService.sendOtpEmail(dto.getEmail(), otp);
		throw new ApiException("OTP sent to email. Please verify.");
		// return modelMapper.map(entity, UserRespDto.class);
	}

	@Override
	public UserRespDto signUp(UserReqDto dto) {
		// 1. check for dup email
		if (userDao.existsByEmail(dto.getEmail()))
			throw new ApiException("Dup Email detected - User exists already!!!!");
		// 2. dto -> entity

		User entity = modelMapper.map(dto, User.class);
		// 3. save the entity n map persistent entity -> resp dto
		return modelMapper.map(userDao.save(entity), UserRespDto.class);
	}

	@Override
	public UserRespDto verifyOtp(String email, String otp) {
	    boolean isValid = otpService.verifyOtp(email, otp);

	    if (!isValid)
	        throw new AuthenticationException("Invalid or expired OTP.");

	    User user = userDao.findByEmail(email)
	            .orElseThrow(() -> new AuthenticationException("User not found."));

	    return modelMapper.map(user, UserRespDto.class);
	}
}
