package com.sunbeam.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.sunbeam.entities.User;


public interface UserDao extends JpaRepository<User, Long> {
	User findByEmail(String email);

	boolean existsByEmail(String email);
}