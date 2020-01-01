package com.ele.item.common.config.vo.base;

import java.util.Date;

import lombok.Data;

@Data
public class BaseRoutesVo {
	
	private Integer id;
	private String path;
	private String component;
	private String redirect;
	private String name;
	private String title;
	private String icon;
	private String type;
	private Integer parentId;
	private Integer sort;
	private Date createTime;
	private Date updateTime;
	
	
}
