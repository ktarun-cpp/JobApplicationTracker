package com.sunbeam.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class updatePasswordDTO {
	private String oldPassword;
	private String newPassword;
}
