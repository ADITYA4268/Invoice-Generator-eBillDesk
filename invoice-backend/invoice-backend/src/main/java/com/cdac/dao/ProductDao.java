package com.cdac.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entities.Product;

public interface ProductDao extends JpaRepository<Product, Long>{
	
	List<Product> findByUser_Id(Long id);
	
	

}
