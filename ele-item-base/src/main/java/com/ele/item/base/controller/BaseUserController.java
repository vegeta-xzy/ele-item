package com.ele.item.base.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ele.item.base.service.BaseUserService;
import com.ele.item.common.config.response.RespObjInfo;
import com.ele.item.common.config.util.GsonUtil;
import com.ele.item.common.config.util.ValidateUtils;
import com.ele.item.common.config.vo.base.req.BaseAddRoleReqVo;
import com.ele.item.common.config.vo.base.req.BaseAddRouteReqVo;
import com.ele.item.common.config.vo.base.req.BaseLoginReqVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import redis.clients.jedis.JedisCluster;

/**
 * 
 * @author nec
 * 注意：
 * 
   @ApiImplicitParams 
    paramType
    header-->请求参数的获取：@RequestHeader
    query-->请求参数的获取：@RequestParam
    path（用于restful接口）-->请求参数的获取：@PathVariable
    body（不常用）
    form（不常用）

 *
 */
@RestController
@RequestMapping("/user")
public class BaseUserController {

	@Autowired
	private BaseUserService baseLoginService;
	
	@Autowired
	private JedisCluster jedisCluster;
	
	@ApiOperation(notes="登录",httpMethod = "POST",value = "登录")
//	@ApiImplicitParams({
//		@ApiImplicitParam(name = "memberNo", value = "账号", required = true, paramType = "query", dataType = "string"),
//		@ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query", dataType = "string")
//	})
	@PostMapping("login")
	public RespObjInfo logon(@RequestBody @Validated BaseLoginReqVo reqVo,BindingResult result,HttpServletRequest request) {
		ValidateUtils.validateReqParam(result);
		RespObjInfo objInfo= baseLoginService.logon(reqVo);
		return objInfo;
	}
	
	@ApiOperation(notes="",httpMethod="GET",value="返回用户信息")
	@ApiImplicitParam(name = "token", value = "令牌", required = true, paramType = "query", dataType = "string")
	@GetMapping("/info")
	public RespObjInfo info(String token,HttpServletRequest request) {
		RespObjInfo objInfo = baseLoginService.getInfo(token);
		return objInfo;
	}
	
	@ApiOperation(notes="",httpMethod="POST",value="退出登录")
	@PostMapping("/logout")
	public RespObjInfo logout(HttpServletRequest request) {
		RespObjInfo objInfo = baseLoginService.logout();
		return objInfo;
	}
	
	
	@ApiOperation(notes="",httpMethod="GET",value="获取动态路由")
	@GetMapping("/queryRoutesList")
	public RespObjInfo queryRoutesList(HttpServletRequest request) {
		RespObjInfo objInfo = baseLoginService.queryRoutesList();
		return objInfo;
	}
	
	@ApiOperation(notes="",httpMethod="POST",value="新增路由")
	@PostMapping("/addRoute")
	public RespObjInfo addRoute(@RequestBody @Validated BaseAddRouteReqVo reqVo,BindingResult result,HttpServletRequest request) {
		System.out.println("addRoute=======>"+GsonUtil.toJson(reqVo));
		ValidateUtils.validateReqParam(result);
		RespObjInfo objInfo = baseLoginService.addRoute(reqVo);
		return objInfo;
	}
	
	@ApiOperation(notes="",httpMethod="GET",value="获取角色信息")
	@GetMapping("/queryRolesList")
	public RespObjInfo queryRolesList() {
		RespObjInfo objInfo = baseLoginService.queryRolesList();
		return objInfo;
	}
	
	@ApiOperation(notes="",httpMethod="PUT",value="创建角色信息")
	@PutMapping("addRole")
	public RespObjInfo addRole(@RequestBody @Validated BaseAddRoleReqVo reqVo,BindingResult result,HttpServletRequest request) {
		ValidateUtils.validateReqParam(result);
		RespObjInfo objInfo = baseLoginService.addRole(reqVo);
		return objInfo;
	}
	
	@ApiOperation(notes="",httpMethod="POST",value="测试redis")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "redisKey", value = "key值", required = true, paramType = "query", dataType = "string"),
		@ApiImplicitParam(name = "redisValue", value = "value值", required = true, paramType = "query", dataType = "string")
	})
	@PostMapping("setRedis")
	public void setRedis(String redisKey,String redisValue) {
		System.out.println("进入===setRedis");
		jedisCluster.set(redisKey, redisValue);
		System.out.println("通过"+redisKey+" 存入redis====>");
		System.out.println("获取value====>"+jedisCluster.get(redisKey));
		try {
			jedisCluster.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
}

