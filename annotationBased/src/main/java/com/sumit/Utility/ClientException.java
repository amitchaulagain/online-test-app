package com.sumit.Utility;

import java.io.Serializable;


public class ClientException extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;
	private String message;

	public ClientException() {

	}

	public ClientException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}