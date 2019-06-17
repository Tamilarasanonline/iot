package com.tamil.iot.crowedanalytical.entity;

public class PhotoStatus {
	
	private String name; 
	private String error;
	private String message;

	public PhotoStatus() {
		
	}

	/**
	 * @param name
	 * @param error
	 * @param message
	 */
	public PhotoStatus(String name, String error, String message) {
		super();
		this.name = name;
		this.error = error;
		this.message = message;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
