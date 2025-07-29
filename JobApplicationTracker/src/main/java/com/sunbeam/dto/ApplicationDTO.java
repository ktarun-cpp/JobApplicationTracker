package com.sunbeam.dto;

import com.sunbeam.entities.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationDTO {
	private String companyName;

	private String profile;
	private Status status;
	private String resumePath;
	private String jdPath;
}
