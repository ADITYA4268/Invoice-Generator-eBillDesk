package com.cdac.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.dto.BillReqDto;
import com.cdac.dto.BillRespDto;
import com.cdac.dto.PaymentReqDto;
import com.cdac.dto.PaymentRespDto;
import com.cdac.entities.Bill;
import com.cdac.service.BillService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/bill")
@AllArgsConstructor
public class BillController {

	 private BillService billService;
	
	 
	  @PostMapping("/user/{userId}")
	    public ResponseEntity<BillRespDto> createBillByUserId(@RequestBody @Valid BillReqDto dto, @PathVariable Long userId) {
		  BillRespDto response = billService.createBillByUserId(dto, userId);
	        return ResponseEntity.status(HttpStatus.CREATED).body(response);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<BillRespDto> getBill(@PathVariable Long id) {
	    	BillRespDto response = billService.getBillById(id);
	        return ResponseEntity.ok(response);
	    }
	    														
	    @GetMapping
	    public ResponseEntity<List<BillRespDto>> getAllBills() {
	        return ResponseEntity.ok(billService.getAllBills());
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteBill(@PathVariable Long id) {
	        billService.deleteBill(id);
	        return ResponseEntity.noContent().build();
	    }
	    
	    @GetMapping("/user/{userId}")
	    public ResponseEntity<List<BillRespDto>> getBillsByUserId(@PathVariable Long userId) {
	        List<BillRespDto> bills = billService.getBillsByUserId(userId);
	        return ResponseEntity.ok(bills);
	    }
	    
	    @PostMapping
	    public ResponseEntity<Bill> createBill(@RequestBody Bill bill) {
	        Bill savedBill = billService.createBill(bill);
	        return ResponseEntity.ok(savedBill);
	    }

	    @PostMapping("/{billId}/pay")
	    public ResponseEntity<?> payBill(
	            @PathVariable Long billId,
	            @RequestBody PaymentReqDto paymentRequest) {
	        PaymentRespDto response = billService.payBill(billId, paymentRequest);
	        return ResponseEntity.ok(response);
	    }
	    
	    @PostMapping("/{billId}/createPayment")
	    public PaymentRespDto createPayment(@PathVariable Long billId) {
	        return billService.generatePayment(billId);
	    }
	 
}
