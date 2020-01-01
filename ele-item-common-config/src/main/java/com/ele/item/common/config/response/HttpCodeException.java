package com.ele.item.common.config.response;

/**
 * 
 * @author nec
 * @DESC 定义 错误码 ,一般http请求返回给前端 
 *
 */
public enum HttpCodeException implements Status {

	OK(10001, "OK"),
	FAIL(10002, "Bad Request"),
	PARAM_INVALID(10003, "Parameter invalid"),
	TOKEN_DECFAIL(10004,"token decfail");

	private Long code;
	private String message;

	HttpCodeException(long code, String message) {
		this.code = code;
		this.message = message;
	}

	@Override
	public Long code() {
		return this.code;
	}

	@Override
	public String message() {
		return this.message;
	}

}
