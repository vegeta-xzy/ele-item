package com.ele.item.common.config.response;

/**
 * 
 * @author nec
 * @Desc 参数校验异常-一般对前端输入参数进行校验
 */
public class ParamVerifyException extends GlobalException {

	private static final long serialVersionUID = 1L;
	
	
	private String msg;// 错误提示信息

	public ParamVerifyException(String msg) {
		super(HttpCodeException.PARAM_INVALID);
		this.msg = msg;
	}

	public ParamVerifyException(String msg, Throwable t) {
		super(HttpCodeException.PARAM_INVALID, t);
		this.msg = msg;
	}

}
