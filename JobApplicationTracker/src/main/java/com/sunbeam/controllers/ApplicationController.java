package com.sunbeam.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.dto.ApplicationDTO;
import com.sunbeam.service.ApplicationService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/application")
public class ApplicationController {
	private final ApplicationService appService;
	
	// create a new job application
	@PostMapping("/create/{id}")
	public ResponseEntity<?> createApplication(@PathVariable Long id,@RequestBody ApplicationDTO dto){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(appService.createApplication(id,dto));
	}
	
	//get all application
	@GetMapping("/allapplication/{userId}")
	public ResponseEntity<?> getAllApplication(@PathVariable Long userId){
		return ResponseEntity.ok(appService.getAllApplication(userId));
	}
	//update application
	//delete application
	//get application by id
	
}
