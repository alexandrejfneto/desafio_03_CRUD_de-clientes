package com.alejfneto.desafio_03.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	
	public ResourceNotFoundException (String message) {
		super(message);
	}

}
