package com.ele.item.common.config.vo.base;

import java.util.List;

import lombok.Data;

@Data
public class BaseInfoRespVo {

	private List<String> roles;
	
	private String avatar;
	private String introduction;
	private String name;
	
	
}
