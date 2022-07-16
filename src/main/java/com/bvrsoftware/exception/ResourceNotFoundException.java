package com.bvrsoftware.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String resourceName;
	String fieldName;
	long fieldId;
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldId) {
		super(String.format("%s not found with %s: %s ", resourceName, fieldName, fieldId));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldId = fieldId;
	}
	
}
