package com.sunbeam.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.sunbeam.customs_exception.ResourceNotFoundException;
import com.sunbeam.dao.ApplicationServiceDao;
import com.sunbeam.dao.UserDao;
import com.sunbeam.dto.ApiResponse;
import com.sunbeam.dto.ApplicationDTO;
import com.sunbeam.dto.UpdateApplicationDTO;
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
				.orElseThrow(()-> new ResourceNotFoundException("User not found"));
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

	@Override
	public ApiResponse updateApplication(Long applicationId, UpdateApplicationDTO dto) {
		
		Application app = appDao.findById(applicationId)
				.orElseThrow(()-> new ResourceNotFoundException("Application does not exist"));		
		if (dto.getCompanyName() != null) app.setCompanyName(dto.getCompanyName());
		if (dto.getProfile() != null) app.setProfile(dto.getProfile());
		if (dto.getStatus() != null) app.setStatus(dto.getStatus());
		if (dto.getJdPath() != null) app.setJdPath(dto.getJdPath());
		if (dto.getResumePath() != null) app.setResumePath(dto.getResumePath());
		appDao.save(app);
		return new ApiResponse("Application updated successfully");
	}

	@Override
	public ApiResponse deleteApplication(Long id,Long applicationId) {
		User entity = userDao.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("User not found"));
		Application app = appDao.findById(applicationId)
				.orElseThrow(()-> new ResourceNotFoundException("Application does not exist"));
				entity.deleteApp(applicationId);
		return new ApiResponse("Application delete successfully");
	}

}
