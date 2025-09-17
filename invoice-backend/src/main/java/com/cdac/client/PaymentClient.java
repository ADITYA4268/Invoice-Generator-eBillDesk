package com.cdac.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cdac.dto.PaymentReqDto;
import com.cdac.dto.PaymentRespDto;

@Component
public class PaymentClient {

	private static final String PAYMENT_SERVICE_URL = "http://localhost:8082/payments"; 
    // Change 8082 to the port where your Payment Microservice runs

    @Autowired
    private RestTemplate restTemplate;

    public PaymentRespDto createPayment(PaymentReqDto request) {
        return restTemplate.postForObject(PAYMENT_SERVICE_URL + "/create-order", request, PaymentRespDto.class);
    }
}
