package com.ele.item.common.config.vo.base.resp;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseMetaRespVo {

	@ApiModelProperty(name="title",value="主题",dataType="java.lang.String")
	@JsonInclude(Include.NON_EMPTY)
	private String title;
	@ApiModelProperty(name="icon",value="引入图标",dataType="java.lang.String")
    @JsonInclude(Include.NON_EMPTY)
	private String icon;
//	@ApiModelProperty(name="affix",value="")
//	private boolean affix;
//	@ApiModelProperty(name="path",value="")
//	private boolean noCache;
	
	@JsonInclude(Include.NON_EMPTY)
	private List<String> roles;
}
