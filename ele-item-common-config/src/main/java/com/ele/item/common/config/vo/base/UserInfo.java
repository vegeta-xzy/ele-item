package com.ele.item.common.config.vo.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserInfo {

	@ApiModelProperty(name="memberNo",value="账号",dataType="java.lang.String")
	private String memberNo;
	@ApiModelProperty(name="phone",value="电话",dataType="java.lang.Long")
	private long phone;
	@ApiModelProperty(name="name",value="名称",dataType="java.lang.String")
	private String name;
}
