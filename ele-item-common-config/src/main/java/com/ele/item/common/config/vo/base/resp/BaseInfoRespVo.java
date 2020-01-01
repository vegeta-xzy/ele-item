package com.ele.item.common.config.vo.base.resp;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseInfoRespVo {

	
	private List<String> roles;
	
	@ApiModelProperty(name="avatar",value="头像",dataType="java.lang.String")
	private String avatar;
	@ApiModelProperty(name="introduction",value="描述",dataType="java.lang.String")
	private String introduction;
	@ApiModelProperty(name="name",value="名称",dataType="java.lang.String")
	private String name;
	
	
}
