package com.sunbeam.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginDTO {
	private String email;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
}
