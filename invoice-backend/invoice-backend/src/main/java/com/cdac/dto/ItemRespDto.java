package com.cdac.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemRespDto extends BaseDto{

	private Long itemId;
	private String productName;   // mapped from Product
    private Double productPrice;  // mapped from Product
	private Integer quantity;
	private Long billId;

}
