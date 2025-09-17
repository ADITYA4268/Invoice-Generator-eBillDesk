package com.cdac.service;

import com.cdac.dto.BusinessDetailReqDto;
import com.cdac.dto.BusinessDetailRespDto;

public interface BusinessService {

	public BusinessDetailRespDto getBusinessDetail(Long id);

	public BusinessDetailRespDto createBusinessDetail(BusinessDetailReqDto dto, Long id);

//	public BusinessDetailRespDto getBusinessDetailByUserId(Long userId);
}
