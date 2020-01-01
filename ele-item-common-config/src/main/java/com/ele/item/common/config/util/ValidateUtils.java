package com.ele.item.common.config.util;

import org.springframework.validation.BindingResult;

import com.ele.item.common.config.response.ParamVerifyException;

/**
 * @ 前端参数校验工具
 * @author nec
 *
 */
public class ValidateUtils {

	/**
	 * 
	* @Title: validateReqParam 
	* @Description: TODO 校验参数
	* @param @param result
	* @param @return 设定文件 
	* @return RespObjInfo 返回类型 
	* @throws
	 */
	public static void validateReqParam(BindingResult result) {
		if (result.hasErrors()) {
			throw new ParamVerifyException(result.getAllErrors().get(0).getDefaultMessage());
		}
	}
	
}
