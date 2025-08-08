package com.sunbeam.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.config.JwtUtil;
import com.sunbeam.dto.UserDTO;
import com.sunbeam.dto.UserLoginDTO;
import com.sunbeam.dto.updatePasswordDTO;
import com.sunbeam.dto.updateUserDTO;
import com.sunbeam.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
	private final UserService userService;
	private final AuthenticationManager authManager;
	private final JwtUtil jwtUtil;
	
	//now here we will be writing all the apis related to user
	
	//register user
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody UserDTO dto){
		return ResponseEntity.accepted().body(userService.registerUser(dto));
	}
	
	//login user
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody UserLoginDTO dto){
		Authentication auth = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
		auth = authManager.authenticate(auth);
		String token =jwtUtil.createToken(auth);
		return ResponseEntity.ok(token);
	}
	//get user profile
	@GetMapping("/profile")
	public ResponseEntity<?> getProfile(HttpServletRequest request){
		String token = jwtUtil.extractTokenFromRequest(request);
		Long id =jwtUtil.extractId(token);
		System.out.println("UserId : " + id);
		return ResponseEntity.ok(userService.getProfile(id));
	}
	
	//update user
	@PutMapping("/updateProfile")
	public ResponseEntity<?> updateProfile(HttpServletRequest request, @RequestBody updateUserDTO dto){
		String token = jwtUtil.extractTokenFromRequest(request);
		Long id =jwtUtil.extractId(token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.updateProfile(id,dto));
	}
	
	
	//change password
	@PutMapping("/updatePassword")
	public ResponseEntity<?> updatePassword(HttpServletRequest request, @RequestBody updatePasswordDTO dto){
		String token = jwtUtil.extractTokenFromRequest(request);
		Long id =jwtUtil.extractId(token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.updatePassword(id,dto));
	}
	
}
