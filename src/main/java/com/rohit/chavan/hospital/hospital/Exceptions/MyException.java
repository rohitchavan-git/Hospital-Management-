package com.rohit.chavan.hospital.hospital.Exceptions;

public class MyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyException(Exception e) {
		super(e);
	}

	public MyException(String e) {
		super(e);
	}

	public MyException(String e, Throwable resion) {
		super(e, resion);
	}

}
