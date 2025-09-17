package com.cdac.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemReqDto {

	private int quantity;
	private Long productId; // to link product
	private Long billId; // to link bill
}
