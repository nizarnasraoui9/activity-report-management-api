package com.emainfo.cra.exception;

public class CraException extends RuntimeException {

	public CraException(final String message) {
		super(message);
	}

	public CraException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
