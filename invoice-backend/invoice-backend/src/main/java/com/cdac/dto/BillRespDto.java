package com.cdac.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BillRespDto extends BaseDto {

	private Long id;
    private String invoiceNo;
    private Date date;
    private String customerName;
    private Double totalAmount;

}
