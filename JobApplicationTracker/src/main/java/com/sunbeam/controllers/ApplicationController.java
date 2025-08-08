package com.sunbeam.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.config.JwtUtil;
import com.sunbeam.dto.ApplicationDTO;
import com.sunbeam.dto.UpdateApplicationDTO;
import com.sunbeam.service.ApplicationService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/application")
public class ApplicationController {
	private final ApplicationService appService;
	private final JwtUtil jwtUtil;
	// create a new job application
	@PostMapping("/create")
	public ResponseEntity<?> createApplication(HttpServletRequest request,@RequestBody ApplicationDTO dto){
		String token = jwtUtil.extractTokenFromRequest(request);
		Long id =jwtUtil.extractId(token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(appService.createApplication(id,dto));
	}
	
	//get all application
	@GetMapping("/all")
	public ResponseEntity<?> getAllApplication(HttpServletRequest request){
		String token = jwtUtil.extractTokenFromRequest(request);
		Long id =jwtUtil.extractId(token);
		return ResponseEntity.ok(appService.getAllApplication(id));
	}
	//update application
	@PostMapping("/update/{applicationId}")
	public ResponseEntity<?> updateApplication(@PathVariable Long applicationId,@RequestBody UpdateApplicationDTO dto){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(appService.updateApplication(applicationId,dto));
	}
	//delete application
	@DeleteMapping("/delete/{applicationId}")
	public ResponseEntity<?> deleteApplication(HttpServletRequest request,@PathVariable Long applicationId){
		String token = jwtUtil.extractTokenFromRequest(request);
		Long id =jwtUtil.extractId(token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(appService.deleteApplication(id,applicationId));
	}
}
