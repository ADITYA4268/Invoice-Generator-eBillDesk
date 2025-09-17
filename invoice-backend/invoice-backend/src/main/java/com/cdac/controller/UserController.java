package com.cdac.controller;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.dao.UserDao;
import com.cdac.dto.AuthReq;
import com.cdac.dto.OtpVerificationDto;
import com.cdac.dto.SignUpReqDto;
import com.cdac.entities.User;
import com.cdac.service.OTPService;
import com.cdac.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

	private UserService userService;
	private OTPService otpService;
	private UserDao userDao;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

//	@PostMapping("/signin")
//	public ResponseEntity<?> userSignIn(@RequestBody AuthReq dto) {
//		return ResponseEntity.ok(userService.authenticate(dto));
//	}

//	@PostMapping("/signup")
//	public ResponseEntity<?> userSignUp(@RequestBody @Valid UserReqDto dto) {
//		System.out.println("in user signup " + dto);
//		return ResponseEntity.status(HttpStatus.CREATED).body(userService.signUp(dto));
//	}

    private final Map<String, String> otpStorage = new HashMap<>();
    private final Map<String, SignUpReqDto> tempUserStorage = new HashMap<>();
	
	@PostMapping("/signup")
	public ResponseEntity<String> initiateSignup(@RequestBody SignUpReqDto signUpDto) {
	    // 1. Generate OTP
	    String otp = otpService.generateOtp();
	    otpService.sendOtpEmail(signUpDto.getEmail(), otp);

	    // 2. Store OTP and user temp data in memory
	    otpStorage.put(signUpDto.getEmail(), otp);
	    tempUserStorage.put(signUpDto.getEmail(), signUpDto);

	    return ResponseEntity.ok("OTP sent to your email. Please verify to complete registration.");
	}
	
	
	// ✅ SIGN-UP STEP 2: Verify OTP & Save User
    @PostMapping("/verify-signup-otp")
    public ResponseEntity<String> verifySignupOtp(@RequestBody OtpVerificationDto dto) {
        String storedOtp = otpStorage.get(dto.getEmail());

        if (storedOtp == null || !storedOtp.equals(dto.getOtp())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired OTP.");
        }

        SignUpReqDto tempUser = tempUserStorage.get(dto.getEmail());
        if (tempUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No signup data found.");
        }

        User user = modelMapper.map(tempUser, User.class);
        user.setPassword(passwordEncoder.encode(tempUser.getPassword()));
        userDao.save(user);

        otpStorage.remove(dto.getEmail());
        tempUserStorage.remove(dto.getEmail());

        return ResponseEntity.ok("User registered successfully.");
    }
    
    
	// ========== LOGIN STEP 1: CREDENTIALS & OTP ==========
	@PostMapping("/signin")
	public ResponseEntity<?> login(@RequestBody AuthReq dto) {
		try {
			userService.authenticate(dto); // This sends OTP and throws ApiException
			return ResponseEntity.ok("OTP sent to email");
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
		}
	}

	// ========== LOGIN STEP 2: VERIFY OTP ==========
	@PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        boolean isValid = otpService.verifyOtp(email, otp);
        if (isValid) {
            // ✅ You may generate JWT here
            return ResponseEntity.ok("OTP Verified. Login successful.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired OTP.");
        }
	}
}
