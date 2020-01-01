package com.ele.item.common.config.util.jwt;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.ele.item.common.config.response.GlobalException;
import com.ele.item.common.config.response.HttpCodeException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtUtil {

	
	private static final String SHA1PRNGRAMDOW = "d1d5ef512efb5a1f5453a6d4d61b66be";
	public static final String PARAM_KEY_ID = "id";
	public static final String PARAM_KEY_NO = "memberno";
	public static final String PARAM_KEY_TOKEN = "token";
	public static final String PARAM_KEY_NAME = "name";
	public static final long PARAM_KEY_TIME = 28800l;
	
	/**
	 * 
	 * @Title: createJWT 
	 * @Description: TODO 用户登录成功后生成Jwt 使用Hs256算法  私匙使用用户密码
	 * @param @param map 登录成功的对象
	 * @param @return 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	public static String createJWT(Map<String,Object> claims) {
		//指定指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		
		//下面就是在为payload添加各种标准声明和私有声明了
        //这里其实就是new一个JwtBuilder，设置jwt的body
		JwtBuilder builder = Jwts.builder()
				//如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
				.setClaims(claims)
                //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                .setId(UUID.randomUUID().toString())
                //iat: jwt的签发时间
//                .setIssuedAt(now)
                //代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
//                .setSubject(subject)
                //设置签名使用的签名算法和签名使用的秘钥
                .signWith(signatureAlgorithm, SHA1PRNGRAMDOW.getBytes());
		return builder.compact();
	}
	
	/**
	 * 
	 * @Title: parseJWT 
	 * @Description: TODO token 解密
	 * @param @param token
	 * @param @return 设定文件 
	 * @return Claims 返回类型 
	 * @throws
	 */
	public static Claims parseJWT(String token) {
		Claims claims = null ;
		try {
			claims = Jwts.parser()
					//设置签名的秘钥
					.setSigningKey(SHA1PRNGRAMDOW.getBytes())
					//设置需要解析的jwt
					.parseClaimsJws(token).getBody();
		} catch (Exception e) {
			log.error("JwtUtil.parseJWT exception : " + e.getMessage());
			throw new GlobalException(HttpCodeException.TOKEN_DECFAIL);
		}
		return claims;
	}
	
	/**
	 * 
	 * @Title: currentToken 
	 * @Description: TODO 获取 token
	 * @param @return 设定文件 
	 * @return String 返回类型 
	 * @throws
	 */
	public static String currentToken(HttpServletRequest request) {
		return request.getHeader("authorization");
	}
	
//	public static String currentUserId(HttpServletRequest request) {
//		
//	}
	
	public static void main(String[] args) {
		
	}
	
}
