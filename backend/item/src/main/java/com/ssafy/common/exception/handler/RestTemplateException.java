package com.ssafy.common.exception.handler;

public class RestTemplateException extends RuntimeException{
	String serverUrl;
	Integer statusCode;
	public RestTemplateException(String serverUrl, String msg, Integer statusCode) {
		super(msg);
		this.serverUrl = serverUrl;
		this.statusCode = statusCode;
	}
}
