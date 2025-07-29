package com.sunbeam.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.sunbeam.customs_exception.ResourceNotFoundException;
import com.sunbeam.dao.ApplicationServiceDao;
import com.sunbeam.dao.UserDao;
import com.sunbeam.dto.ApiResponse;
import com.sunbeam.dto.ApplicationDTO;
import com.sunbeam.entities.Application;
import com.sunbeam.entities.User;
import com.sunbeam.service.ApplicationService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ApplicationServiceImpl implements ApplicationService{
	private final ApplicationServiceDao appDao;
	private final UserDao userDao;
	private final ModelMapper map;

	@Override
	public ApiResponse createApplication(Long id,ApplicationDTO dto) {
		User entity = userDao.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("USer not found"));
		Application appEntity = map.map(dto, Application.class);
		entity.addApp(appEntity);
		return new ApiResponse(true, "Application added");
	}

	@Override
	public List<ApplicationDTO> getAllApplication(Long userId) {
		User entity = userDao.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User not found"));
		List<ApplicationDTO> dto = entity.getApplication()
									.stream()
									.map(application -> map.map(application, ApplicationDTO.class))
									.toList();
		return dto;
	}

}
