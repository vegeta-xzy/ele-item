package com.ele.item.common.config.vo.base.req;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseAddRoleReqVo {

	@ApiModelProperty(name="roleName",value="角色名称")
	@NotBlank(message="不能为空")
	private String roleName;
	@ApiModelProperty(name="chRoleName",value="角色中文名称")
	@NotBlank(message="不能为空")
	private String chRoleName;
	@ApiModelProperty(name="note",value="角色描述")
	@NotBlank(message="不能为空")
	private String note;
	
}
