package com.ele.item.common.config.vo.base;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseLoginReqVo {

	@NotBlank(message="登录账号不能为空")
	@ApiModelProperty(name="memberNo",value="登录账号")
	private String memberNo;
	
	@NotBlank(message="登录密码不能为空")
	@ApiModelProperty(name="password",value="登录密码")
	private String password;
	
	
}
