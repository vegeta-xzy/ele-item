package com.ele.item.common.config.vo.base.resp;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseRoutesRespVo {
	
	@ApiModelProperty(name="id",value="主键",dataType="java.lang.String")
	@JsonInclude(Include.NON_EMPTY)
	private Integer id;
	@ApiModelProperty(name="path",value="路径",dataType="java.lang.String")
	@JsonInclude(Include.NON_EMPTY)
	private String path;
	@ApiModelProperty(name="component",value="跳转",dataType="java.lang.String")
	@JsonInclude(Include.NON_EMPTY)
	private String component;
	@ApiModelProperty(name="redirect",value="重定向地址",dataType="java.lang.String")
	@JsonInclude(Include.NON_EMPTY)
	private String redirect;
//	@ApiModelProperty(name="hidden",value="是否隐藏",example="隐藏true,显示：false")
//	private boolean hidden;
//	@ApiModelProperty(name="alwaysShow",value="是否总是显示",example="总是显示：true ,false")
//	private boolean alwaysShow;
	@ApiModelProperty(name="name",value="名称",dataType="java.lang.String")
	@JsonInclude(Include.NON_EMPTY)
	private String name;
	
	@JsonInclude(Include.NON_EMPTY)
	BaseMetaRespVo meta;
	
	@JsonInclude(Include.NON_EMPTY)
	private List<BaseChildrenRespVo> children;
	
}
