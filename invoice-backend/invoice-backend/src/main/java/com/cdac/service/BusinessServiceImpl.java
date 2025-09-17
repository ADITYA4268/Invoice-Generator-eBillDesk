package com.cdac.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.cdac.dao.BusinessDao;
import com.cdac.dao.UserDao;
import com.cdac.dto.BusinessDetailReqDto;
import com.cdac.dto.BusinessDetailRespDto;
import com.cdac.entities.BusinessDetails;
import com.cdac.entities.User;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class BusinessServiceImpl implements BusinessService {

	private final BusinessDao businessDao;
	private final UserDao userDao;
	private ModelMapper modelMapper;

	@Override
	public BusinessDetailRespDto getBusinessDetail(Long id) {
		User user = userDao.findById(id).orElseThrow(() -> new RuntimeException("user not found with ID: " + id));

		return modelMapper.map(user.getBusinessDetail(), BusinessDetailRespDto.class);
	}

	@Override
	public BusinessDetailRespDto createBusinessDetail(BusinessDetailReqDto dto, Long id) {
		User user = userDao.findById(id).orElseThrow(() -> new RuntimeException("user not found with ID: " + id));

		if (businessDao.findByGstin(dto.getGstin()).isPresent()) {
			throw new IllegalArgumentException("GSTIN already exists");
		}

		// Convert DTO to Entity
		BusinessDetails businessDetail = modelMapper.map(dto, BusinessDetails.class);

		// Save to DB
		user.setBusinessDetail(businessDetail);
		user = userDao.save(user);

		// Convert saved Entity to Response DTO
		return modelMapper.map(user.getBusinessDetail(), BusinessDetailRespDto.class);
	}

//	@Override
//	public BusinessDetailRespDto getBusinessDetailByUserId(Long userId) {
//		BusinessDetails detail = businessDao.findByUserId(userId)
//				.orElseThrow(() -> new IllegalArgumentException("BusinessDetail not found for user id: " + userId));
//		return modelMapper.map(detail, BusinessDetailRespDto.class);
//	}

}
