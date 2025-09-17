package com.cdac.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PaymentRespDto extends BaseDto{

	private String razorpayOrderId;
    private String currency;
    private Long amount;
    private String status;
}
