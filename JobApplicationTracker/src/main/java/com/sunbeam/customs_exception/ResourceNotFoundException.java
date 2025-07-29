package com.sunbeam.customs_exception;


public class ResourceNotFoundException extends RuntimeException{
	private String msg;
	public ResourceNotFoundException(String msg) {
	super(msg);
	}

}
