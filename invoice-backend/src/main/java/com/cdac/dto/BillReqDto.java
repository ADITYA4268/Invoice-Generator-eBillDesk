package com.cdac.dto;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BillReqDto {

	private String invoiceNo;
	private Date date;
	private String customerName;
	private List<ItemReqDto> items;

}
