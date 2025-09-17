package com.cdac.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entities.User;

public interface UserDao extends JpaRepository<User, Long> {

	Optional<User> findById(Long id);

	boolean existsByEmail(String email);

	Optional<User> findByEmail(String email);

}
