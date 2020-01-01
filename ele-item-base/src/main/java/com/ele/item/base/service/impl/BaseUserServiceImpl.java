package com.ele.item.base.service.impl;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ele.item.base.service.BaseUserService;
import com.ele.item.common.config.response.ParamVerifyException;
import com.ele.item.common.config.response.RespObjInfo;
import com.ele.item.common.config.util.GsonUtil;
import com.ele.item.common.config.util.encryption.PasswordEncryption;
import com.ele.item.common.config.util.jwt.JwtUtil;
import com.ele.item.common.config.vo.base.BaseRoutesVo;
import com.ele.item.common.config.vo.base.UserInfo;
import com.ele.item.common.config.vo.base.common.CommonRespVo;
import com.ele.item.common.config.vo.base.req.BaseAddRoleReqVo;
import com.ele.item.common.config.vo.base.req.BaseAddRouteReqVo;
import com.ele.item.common.config.vo.base.req.BaseLoginReqVo;
import com.ele.item.common.config.vo.base.resp.BaseChildrenRespVo;
import com.ele.item.common.config.vo.base.resp.BaseGetRolesListRespVo;
import com.ele.item.common.config.vo.base.resp.BaseInfoRespVo;
import com.ele.item.common.config.vo.base.resp.BaseLoginRespVo;
import com.ele.item.common.config.vo.base.resp.BaseMetaRespVo;
import com.ele.item.common.config.vo.base.resp.BaseRoutesRespVo;
import com.ele.item.database.service.base.BaseUserDaoService;

import io.jsonwebtoken.Claims;


@Service
public class BaseUserServiceImpl implements BaseUserService{
	
	@Autowired
	private BaseUserDaoService baseUserDaoService;
	
	/**
     * 
     * @Title: logon 
     * @Description: TODO 登录
     * @param @param reqVo
     * @param @return 设定文件 
     * @return RespObjInfo 返回类型 
     * @throws
     */
	public RespObjInfo logon(BaseLoginReqVo reqVo) {
		BaseLoginRespVo respVo = new BaseLoginRespVo();
		UserInfo userInfo = new UserInfo();//用户信息
		//test
		String token = "";//用户token
		Map<String,String> map = baseUserDaoService.isExistMemberNo(reqVo.getMemberNo());
		if(map.size()<=0) {
			return new RespObjInfo(1002,"账号不存在");
		}
		//判断密码是否正确
		try {
			boolean flag = PasswordEncryption.authenticate(reqVo.getPassword(), map.get("password"), map.get("salt"));
			if(!flag) {
				return new RespObjInfo(1002,"账号不存在或密码错误");
			}
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new ParamVerifyException("内部错误");
		}
		//生成token
		Map<String,Object> claims = new ConcurrentHashMap<>();
		claims.put(JwtUtil.PARAM_KEY_ID,map.get("id"));
		claims.put(JwtUtil.PARAM_KEY_NO,map.get("memberNo"));
		claims.put(JwtUtil.PARAM_KEY_NAME,map.get("name"));
		claims.put("time",JwtUtil.PARAM_KEY_TIME);
		token = JwtUtil.createJWT(claims);
		
		//存入redis缓存中
		
		
		
		userInfo.setMemberNo(map.get("memberNo"));
		userInfo.setName(map.get("name"));
		userInfo.setPhone(Long.parseLong(map.get("phone")));
		respVo.setToken(token);
		respVo.setUserInfo(userInfo);
		return new RespObjInfo(1001,"登录成功",respVo);
	}
	
	/**
	 * 
	 * @Title: getInfo   
	 * @Description: TODO (验证token)   
	 * @param: @param token
	 * @param: @return      
	 * @return: RespObjInfo      
	 * @throws
	 */
	public RespObjInfo getInfo(String token) {
		//解析token 
		Claims claims = JwtUtil.parseJWT(token);
		
		//获取用户信息
		BaseInfoRespVo respVo = baseUserDaoService.queryInfoByMemberNo(String.valueOf(claims.get("memberno")));
		
		//查询该用户的角色
		
		
		
		//角色问题暂时处理
		List<String> roles = new ArrayList<>();
		roles.add("admin");//editor
		respVo.setRoles(roles);
		return new RespObjInfo(1001,"成功用户信息",respVo);
	}
	
	/**
	 * 
	 * @Title: logout   
	 * @Description: TODO (退出登录)   
	 * @param: @param
	 * @param: @return      
	 * @return: RespObjInfo      
	 * @throws
	 */
	public RespObjInfo logout() {
		//清空缓存redis token
		
		
		
		return new RespObjInfo(1001,"退出成功");
	}
	
	/**
	 * 
	 * @Title: getRoutesList   
	 * @Description: TODO (获取路由列表)   
	 * @param: @param
	 * @param: @return      
	 * @return: RespObjInfo      
	 * @throws
	 */
	public RespObjInfo queryRoutesList() {
		CommonRespVo<BaseRoutesRespVo> comRespVo = new CommonRespVo<>();
		//封装数据
		List<BaseRoutesRespVo> respVoList = new ArrayList<>();
		//查询第一级 路由列表
		List<BaseRoutesVo> firstList = baseUserDaoService.queryResourceByParentIdList(0);
		for(BaseRoutesVo firstRoute:firstList) {
			BaseRoutesRespVo respVo = new BaseRoutesRespVo();
			//查询该路由 分配角色信息
			List<String> roles = baseUserDaoService.queryRolesName(firstRoute.getId());
			BaseMetaRespVo metaRespVo = new BaseMetaRespVo();
			metaRespVo.setIcon(firstRoute.getIcon());
			metaRespVo.setTitle(firstRoute.getTitle());
			metaRespVo.setRoles(roles);
			
			respVo.setId(firstRoute.getId());
			respVo.setPath(firstRoute.getPath());
			respVo.setComponent(firstRoute.getComponent());
			respVo.setRedirect(firstRoute.getRedirect());
			respVo.setName(firstRoute.getName());
			respVo.setMeta(metaRespVo);
			
			List<BaseChildrenRespVo> childrenListRespVo = new ArrayList<>();
			//查询第二级 路由列表
			List<BaseRoutesVo> secodList = baseUserDaoService.queryResourceByParentIdList(firstRoute.getId());
			for(BaseRoutesVo secodRoute:secodList) {
				//查询该路由 分配角色信息
				List<String> childRoles = baseUserDaoService.queryRolesName(firstRoute.getId());
				BaseMetaRespVo meta = new BaseMetaRespVo();
				meta.setTitle(secodRoute.getTitle());
				meta.setRoles(childRoles);
				
				BaseChildrenRespVo childrenRespVo = new BaseChildrenRespVo();
				childrenRespVo.setId(secodRoute.getId());
				childrenRespVo.setPath(secodRoute.getPath());
				childrenRespVo.setComponent(secodRoute.getComponent());
				childrenRespVo.setName(secodRoute.getName());
				childrenRespVo.setMeta(meta);
				
				childrenListRespVo.add(childrenRespVo);
			}
//			if(childrenListRespVo.size()>0) {//判断是否为空
				respVo.setChildren(childrenListRespVo);
//			}
			respVoList.add(respVo);
		}
		comRespVo.setRespVoList(respVoList);
		return new RespObjInfo(1001,"成功",comRespVo);
	}
	
	/**
	 * 
	 * @Title: addRoute   
	 * @Description: TODO (新增菜单)   
	 * @param: @param
	 * @param: @return      
	 * @return: RespObjInfo      
	 * @throws
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public RespObjInfo addRoute(BaseAddRouteReqVo reqVo) {
		//参数处理
		reqVo.setTitle(reqVo.getName());//菜单名称跟主题一样，数据需要后端处理
		//新增菜单
		baseUserDaoService.addRoute(reqVo);
		return new RespObjInfo(1001,"成功");
	}
	
	/**
	 * 
	 * @Title: getRolesList   
	 * @Description: TODO (获取角色列表信息)   
	 * @param: @param
	 * @param: @return      
	 * @return: RespObjInfo      
	 * @throws
	 */
	public RespObjInfo queryRolesList() {
		//查询角色信息
		List<BaseGetRolesListRespVo> respVoList = baseUserDaoService.queryRolesList();
		return new RespObjInfo(1001,"成功",respVoList);
	}
	
	/**
	 * 
	 * @Title: addRole   
	 * @Description: TODO (新增角色信息)   
	 * @param: @param
	 * @param: @return      
	 * @return: RespObjInfo      
	 * @throws
	 */
	@Transactional(rollbackFor=Exception.class)
	public RespObjInfo addRole(BaseAddRoleReqVo reqVo) {
		baseUserDaoService.addRole(reqVo);
		return new RespObjInfo(1001,"成功");
	}
	
	

}
