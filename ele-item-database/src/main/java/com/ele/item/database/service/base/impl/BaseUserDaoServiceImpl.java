package com.ele.item.database.service.base.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ele.item.common.config.response.RespObjInfo;
import com.ele.item.common.config.vo.base.BaseRoutesVo;
import com.ele.item.common.config.vo.base.req.BaseAddRoleReqVo;
import com.ele.item.common.config.vo.base.req.BaseAddRouteReqVo;
import com.ele.item.common.config.vo.base.resp.BaseGetRolesListRespVo;
import com.ele.item.common.config.vo.base.resp.BaseInfoRespVo;
import com.ele.item.database.mapper.base.BaseUserMapper;
import com.ele.item.database.service.base.BaseUserDaoService;

@Service
public class BaseUserDaoServiceImpl implements BaseUserDaoService {

	
	@Autowired
	private BaseUserMapper baseUserMapper;
	
	public Map<String,String> isExistMemberNo(String memberNo) {
		return baseUserMapper.isExistMemberNo(memberNo);
	}
	
	public BaseInfoRespVo queryInfoByMemberNo(String memberNo) {
		return baseUserMapper.queryInfoByMemberNo(memberNo);
	}
	
	public List<BaseGetRolesListRespVo> queryRolesList(){
		return baseUserMapper.queryRolesList();
	}
	
	public void addRole(BaseAddRoleReqVo reqVo) {
		baseUserMapper.addRole(reqVo);
	}
	
	public List<BaseRoutesVo> queryResourceByParentIdList(Integer parentId){
		return baseUserMapper.queryResourceByParentIdList(parentId);
	}
	
	public List<String> queryRolesName(Integer id){
		return baseUserMapper.queryRolesName(id);
	}
	
	public void addRoute(BaseAddRouteReqVo reqVo) {
		baseUserMapper.addRoute(reqVo);
	}
}
