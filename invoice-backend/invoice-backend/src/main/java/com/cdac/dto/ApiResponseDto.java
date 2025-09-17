package com.cdac.dto;

import java.time.LocalDateTime;

public class ApiResponseDto {

	private LocalDateTime timestamp;
	private String message;

	public ApiResponseDto(LocalDateTime timestamp, String message) {
		super();
		this.timestamp = LocalDateTime.now();
		this.message = message;
	}

}
