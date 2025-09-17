package com.cdac.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entities.Item;


public interface ItemDao extends JpaRepository<Item, Long>{
	
	Optional<Item>findById(Long id);
	
	List<Item> findByBill_Id(Long billId);
	
	List<Item> findByUserIdAndBillIsNull(Long userId);
}
