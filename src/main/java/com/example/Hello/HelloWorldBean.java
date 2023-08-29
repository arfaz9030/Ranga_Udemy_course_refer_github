package com.example.Hello;


public class HelloWorldBean {

	private String message;

	public HelloWorldBean(String message) {
		this.message = message;
		System.out.println(this.message+"get");
	}

	public String getMessage() {
		return message;  // this returns to object below setter not necessary 
	}

	/*
	 * public void setMessage(String message) { this.message = message;
	 * System.out.println(this.message+"set");
	 * 
	 * }
	 */

	/*
	 * @Override public String toString() { return "HelloWorldBean [message=" +
	 * message + "]"; }
	 */
}