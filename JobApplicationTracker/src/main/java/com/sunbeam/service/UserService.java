package com.sunbeam.service;

import com.sunbeam.dto.ApiResponse;
import com.sunbeam.dto.UserDTO;
import com.sunbeam.dto.UserLoginDTO;
import com.sunbeam.dto.updatePasswordDTO;
import com.sunbeam.dto.updateUserDTO;

public interface UserService {

	ApiResponse registerUser(UserDTO dto);

	ApiResponse loginUser(UserLoginDTO dto);

	UserDTO getProfile(Long id);

	ApiResponse updatePassword(Long id, updatePasswordDTO dto);

	ApiResponse updateProfile(Long id, updateUserDTO dto);

}
