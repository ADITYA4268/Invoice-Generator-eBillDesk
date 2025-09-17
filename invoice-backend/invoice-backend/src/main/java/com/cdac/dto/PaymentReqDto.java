package com.cdac.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentReqDto {

	private Long billId;
	private Double amount;
	private String currency;
    private String paymentMethod;
    private String paymentStatus;
}
