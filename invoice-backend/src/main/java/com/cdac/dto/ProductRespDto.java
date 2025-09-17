package com.cdac.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductRespDto extends BaseDto {

	private String name;
	private String category;
	private Double sellingPrice;
	private Double costPrice;
	private Integer inStock;
	private Double discount;
	private String description;
	private Double gst;

}
