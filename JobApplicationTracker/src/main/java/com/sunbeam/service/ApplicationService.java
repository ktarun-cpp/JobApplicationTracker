package com.sunbeam.service;

import java.util.List;


import com.sunbeam.dto.ApiResponse;
import com.sunbeam.dto.ApplicationDTO;
import com.sunbeam.dto.UpdateApplicationDTO;
import com.sunbeam.entities.Application;

public interface ApplicationService {

	ApiResponse createApplication(Long id, ApplicationDTO dto);

	List<ApplicationDTO> getAllApplication(Long userId);

	ApiResponse updateApplication( Long applicationId, UpdateApplicationDTO dto);

	ApiResponse deleteApplication(Long id, Long applicationId);

}
