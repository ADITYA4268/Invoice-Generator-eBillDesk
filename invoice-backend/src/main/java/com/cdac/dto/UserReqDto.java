package com.cdac.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserReqDto {

	@NotBlank
	private String firstName;
	
	@NotBlank
	private String lastName;
	
	@Email
	@NotBlank
	private String email;
	
	@Size(min = 8, max = 12)
	@NotBlank
	private String password;
	
	@NotNull
	@Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
	private String phoneNo;
}
