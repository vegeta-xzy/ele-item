package com.ele.item.database.mapper.base;

import java.util.List;
import java.util.Map;

import com.ele.item.common.config.vo.base.BaseRoutesVo;
import com.ele.item.common.config.vo.base.req.BaseAddRoleReqVo;
import com.ele.item.common.config.vo.base.req.BaseAddRouteReqVo;
import com.ele.item.common.config.vo.base.resp.BaseGetRolesListRespVo;
import com.ele.item.common.config.vo.base.resp.BaseInfoRespVo;

public interface BaseUserMapper {

	
	Map<String,String> isExistMemberNo(String memberNo);
	
	BaseInfoRespVo queryInfoByMemberNo(String memberNo);
	
	List<BaseGetRolesListRespVo> queryRolesList();
	
	void addRole(BaseAddRoleReqVo reqVo);
	
	List<BaseRoutesVo> queryResourceByParentIdList(Integer parentId);
	
	List<String> queryRolesName(Integer id);
	
	void addRoute(BaseAddRouteReqVo reqVo);
}
