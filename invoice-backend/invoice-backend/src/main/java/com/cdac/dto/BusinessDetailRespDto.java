package com.cdac.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BusinessDetailRespDto extends BaseDto{

	    
	    private String shopName;
	    private String gstin;
	    private String address;
	    private String city;
	    private int pinCode;

	    // Constructor, Getters, Setters
}
