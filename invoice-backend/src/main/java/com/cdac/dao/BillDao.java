package com.cdac.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entities.Bill;

public interface BillDao extends JpaRepository<Bill, Long> {
	Optional<Bill> findByInvoiceNo(String invoiceNo);

	Optional<Bill> findById(Long id);

	List<Bill> findByUserId(Long userId);
	

}
