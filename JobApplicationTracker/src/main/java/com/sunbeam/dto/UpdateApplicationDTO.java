package com.sunbeam.dto;

import com.sunbeam.entities.Status;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateApplicationDTO {
	private String companyName;
	private String profile;
	private Status status;
	private String resumePath;
	private String jdPath;
}
