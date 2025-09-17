package com.cdac.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRespDto extends BaseDto {

	private String firstName;

	private String lastName;

	private String email;

	private Long phoneNo;
}
