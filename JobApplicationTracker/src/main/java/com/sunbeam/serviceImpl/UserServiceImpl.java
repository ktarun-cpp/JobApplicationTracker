package com.sunbeam.serviceImpl;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sunbeam.customs_exception.ResourceNotFoundException;
import com.sunbeam.dao.UserDao;
import com.sunbeam.dto.ApiResponse;
import com.sunbeam.dto.UserDTO;
import com.sunbeam.dto.UserLoginDTO;
import com.sunbeam.dto.UserResponseDTO;
import com.sunbeam.dto.updatePasswordDTO;
import com.sunbeam.dto.updateUserDTO;
import com.sunbeam.entities.User;
import com.sunbeam.service.UserService;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class UserServiceImpl implements UserService,UserDetailsService{
	private UserDao userDao;
	private ModelMapper map;
	private PasswordEncoder passwordEncoder;
	public UserServiceImpl(UserDao userDao,ModelMapper map,@Lazy PasswordEncoder passwordEncoder) {
		this.map=map;
		this.passwordEncoder=passwordEncoder;
		this.userDao=userDao;
	}

	@Override
	public ApiResponse registerUser(UserDTO dto) {
		boolean status =userDao.existsByEmail(dto.getEmail());
		if(status==true) {
			return new ApiResponse(false,"User Already exist, Login Please");
		}
		else {
		User entity = map.map(dto, User.class);
		entity.setPassword(passwordEncoder.encode(dto.getPassword()));
		userDao.save(entity);
		return new ApiResponse(true,"User registered successfully");
		}
	}


	@Override
	public ApiResponse loginUser(UserLoginDTO dto) {
	User entity = userDao.findByEmail(dto.getEmail());
		if(entity==null) {
			return new ApiResponse(false,"User Not Found, Register First");
		}
		
		if(!entity.getPassword().equals(dto.getPassword())) {
			return new ApiResponse(false, "Invalid Password, Try Again");
		}
		UserResponseDTO user = new UserResponseDTO(
				entity.getId(),
				entity.getFirstName(),
				entity.getLastName(),
				entity.getEmail(),
				entity.getPhoneNo()
				);
				
		return new ApiResponse("User Logged in successfully", true, user);
	}


	@Override
	public UserDTO getProfile(Long id) {
		User entity =userDao.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("User not found id : "+ id));
		return map.map(entity, UserDTO.class);
	}


	@Override
	public ApiResponse updatePassword(Long id, updatePasswordDTO dto) {
		User entity = userDao.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("User Not Found"));
		if(passwordEncoder.matches(dto.getOldPassword(), entity.getPassword())) {
			if(passwordEncoder.matches(dto.getNewPassword(), entity.getPassword())) {
				return new ApiResponse(false, "New and Old Password can't be same");
			}
			
				entity.setPassword(passwordEncoder.encode(dto.getNewPassword()));
				userDao.save(entity);
				return new ApiResponse(true, "Password Changed Successfully");
		}
		else
			return new ApiResponse(false, "Enter correct old Password");
			
		}


	@Override
	public ApiResponse updateProfile(Long id, updateUserDTO dto) {
		User entity = userDao.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("User Not Found"));
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setPhoneNo(dto.getPhoneNo());
		entity.setEmail(dto.getEmail());
		userDao.save(entity);
		return new ApiResponse(true, "User Updated");
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByEmail(username);
		if(user==null)
			throw new UsernameNotFoundException("User not found : "+username);
		return user;
	}
	}


