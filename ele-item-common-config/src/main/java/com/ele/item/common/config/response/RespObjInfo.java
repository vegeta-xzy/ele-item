package com.ele.item.common.config.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RespObjInfo {

	public RespObjInfo(){}
	
	public RespObjInfo(int code ,String msg){
		this.code=code;
		this.msg=msg;
	}
	
	public RespObjInfo(int code ,String msg,Object data){
		this.code=code;
		this.msg=msg;
		this.data=data;
	}
	
	@ApiModelProperty(name="code",value="错误码")
	private int code;
	@ApiModelProperty(name="msg",value="描述 ")
	private String msg; 
	@ApiModelProperty(name="data",value="对象")
	private Object data;
	
}
