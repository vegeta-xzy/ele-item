package com.ele.item.common.config.vo.base.req;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseAddRouteReqVo {

	
	@ApiModelProperty(name="path",value="原地址",dataType="java.lang.String")
	@NotBlank(message="原地址不能为空")
	private String path;
	
	@ApiModelProperty(name="component",value="跳转路径",dataType="java.lang.String")
	@NotBlank(message="跳转路径不能为空")
	private String component;
	
	@ApiModelProperty(name="redirect",value="重定向地址",dataType="java.lang.String")
	private String redirect;
	
	@ApiModelProperty(name="name",value="菜单名称",dataType="java.lang.String")
//	@NotBlank(message="菜单名称不能为空")
	private String name;
	
	@ApiModelProperty(name="title",value="主题",dataType="java.lang.String")
	private String title;
	
	@ApiModelProperty(name="icon",value="图片标签",dataType="java.lang.String")
	private String icon;
	
	@ApiModelProperty(name="type",value="类型",dataType="java.lang.String")
	@NotBlank(message="类型不能为空")
	private String type;
	
	@ApiModelProperty(name="parentId",value="父类id",dataType="java.lang.Integer")
	@NotNull(message="父类id不能为空")
	private Integer parentId;
	
	@ApiModelProperty(name="sort",value="排序",dataType="java.lang.String")
	@NotBlank(message="排序不能为空")
	private String sort;
	
	@ApiModelProperty(name="deleteFlag",value="标识",dataType="java.lang.Integer")
	@Min(value=0,message="最小不能小于0")
	@Max(value=1,message="最大不能大于1")
	private Integer deleteFlag;
	
}
