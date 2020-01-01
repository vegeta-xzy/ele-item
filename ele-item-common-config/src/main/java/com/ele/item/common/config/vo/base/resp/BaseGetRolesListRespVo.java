package com.ele.item.common.config.vo.base.resp;

import java.sql.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseGetRolesListRespVo {

	@ApiModelProperty(name="id",value="主键")
	private String id;
	@ApiModelProperty(name="roleName",value="角色名称")
	private String roleName;
	@ApiModelProperty(name="chRoleName",value="角色-中文名称")
	private String chRoleName;
	@ApiModelProperty(name="note",value="描述")
	private String note;
	@ApiModelProperty(name="createTime",value="创建时间")
	private Date createTime;
	@ApiModelProperty(name="updateTime",value="修改时间")
	private String updateTime;
	@ApiModelProperty(name="deleteFlag",value="删除标注")
	private String deleteFlag;
	
	private List<BaseRoutesRespVo> routesList;
	
}
