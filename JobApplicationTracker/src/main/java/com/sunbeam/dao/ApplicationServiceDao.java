package com.sunbeam.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunbeam.entities.Application;

public interface ApplicationServiceDao extends JpaRepository<Application, Long>{

}
