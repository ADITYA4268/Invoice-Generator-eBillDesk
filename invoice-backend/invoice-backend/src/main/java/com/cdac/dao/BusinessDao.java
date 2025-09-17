package com.cdac.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.entities.BusinessDetails;

@Repository
public interface BusinessDao extends JpaRepository<BusinessDetails, Long>{

	Optional<BusinessDetails> findByGstin(String gstin);
	
	//Optional<BusinessDetails> findByUserId(Long userId);
}
