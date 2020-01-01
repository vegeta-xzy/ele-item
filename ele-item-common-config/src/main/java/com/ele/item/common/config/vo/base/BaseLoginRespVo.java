package com.ele.item.common.config.vo.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseLoginRespVo {

	@ApiModelProperty(name="token",value="用户token",dataType="java.lang.String")
	private String token;
	
	private UserInfo userInfo;//用户信息
	
	//角色信息
	//菜单信息
}
