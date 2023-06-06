package com.springboot.blog.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
//responseStatus annotation is cause springboot to respond with the specified Http status code whenever this exception is thrown from your controller.
public class ResourceNotFoundException  extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String resourcename;
	
	private String fieldname;
	
	private long fieldvalue;

	public ResourceNotFoundException(String resourcename, String fieldname, long fieldvalue) {
		super(String.format("%s is not found with %s:%s" ,resourcename,fieldname,fieldvalue));
		this.resourcename = resourcename;
		this.fieldname = fieldname;
		this.fieldvalue = fieldvalue;
	}
	
	public String getResourcename() {
		return resourcename;
	}
	
	public String getFieldname() {
		return fieldname;
	}
	
	public long getFieldvalue() {
		return fieldvalue;
	}

}
