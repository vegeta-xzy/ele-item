package com.ele.item.base.service;

import com.ele.item.common.config.response.RespObjInfo;
import com.ele.item.common.config.vo.base.req.BaseAddRoleReqVo;
import com.ele.item.common.config.vo.base.req.BaseAddRouteReqVo;
import com.ele.item.common.config.vo.base.req.BaseLoginReqVo;

public interface BaseUserService {

	RespObjInfo logon(BaseLoginReqVo reqVo);
	
	RespObjInfo getInfo(String token);
	
	RespObjInfo logout();
	
	RespObjInfo queryRoutesList();
	
	RespObjInfo addRoute(BaseAddRouteReqVo reqVo);
	
	RespObjInfo queryRolesList();
	
	RespObjInfo addRole(BaseAddRoleReqVo reqVo);
	

}
