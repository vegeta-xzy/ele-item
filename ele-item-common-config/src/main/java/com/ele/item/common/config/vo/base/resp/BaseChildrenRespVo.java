package com.ele.item.common.config.vo.base.resp;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseChildrenRespVo {

	
	@ApiModelProperty(name="id",value="主键id",dataType="int")
	@JsonInclude(Include.NON_EMPTY)
	private Integer id;
	
	@ApiModelProperty(name="path",value="路径",dataType="java.lang.String")
	@JsonInclude(Include.NON_EMPTY)
	private String path;
	@ApiModelProperty(name="component",value="跳转",dataType="java.lang.String")
	@JsonInclude(Include.NON_EMPTY)
	private String component;
	@ApiModelProperty(name="name",value="命名",dataType="java.lang.String")
	@JsonInclude(Include.NON_EMPTY)
	private String name;
	
	@JsonInclude(Include.NON_EMPTY)
	BaseMetaRespVo meta;
	
}
