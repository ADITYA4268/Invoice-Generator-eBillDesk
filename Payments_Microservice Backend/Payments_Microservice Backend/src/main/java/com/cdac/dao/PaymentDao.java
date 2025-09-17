package com.cdac.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entity.Payment;

public interface PaymentDao extends JpaRepository<Payment, Long>{

	Optional<Payment> findByBillId(Long billId);
}
