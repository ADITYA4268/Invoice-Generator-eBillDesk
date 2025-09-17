package com.cdac.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.dto.BusinessDetailReqDto;
import com.cdac.dto.BusinessDetailRespDto;
import com.cdac.service.BusinessService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/businessDetails")
@AllArgsConstructor
public class BusinessController {

	private BusinessService businessService;
	
	@PostMapping("/{id}")
    public ResponseEntity<BusinessDetailRespDto> createBusiness(@RequestBody @Valid BusinessDetailReqDto dto, @PathVariable Long id) {
        BusinessDetailRespDto created = businessService.createBusinessDetail(dto,id);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<BusinessDetailRespDto> getBusinessById(@PathVariable Long id) {
		
        BusinessDetailRespDto response = businessService.getBusinessDetail(id);
        return ResponseEntity.ok(response);
    }
	
//	@GetMapping("/user/{userId}")
//    public ResponseEntity<BusinessDetailRespDto> getBusinessDetailByUserId(@PathVariable Long userId) {
//        BusinessDetailRespDto detail = businessService.getBusinessDetailByUserId(userId);
//        return ResponseEntity.ok(detail);
//    }
	
	
}
