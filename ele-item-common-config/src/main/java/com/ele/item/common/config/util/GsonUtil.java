package com.ele.item.common.config.util;

import java.lang.reflect.Type;

import com.google.gson.Gson;

/**
 * goole json与对象 ，字符串之间的转换
 * @author nec
 *
 */
public class GsonUtil {

	
	/**
	 * 将JSON格式的字符串转化为对象
	 * 适用处理一些简单的对象
	 * @param json格式的字符串
	 * @param clazz
	 * @return
	 */
	public static <T> T fromJson(String json,Class<T> clazz){
		Gson gson=new Gson();
		return gson.fromJson(json, clazz);
	}
	
	
	/**
	 * 将对象转换JSON格式的字符串
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj){
		Gson gson=new Gson();
		return gson.toJson(obj);
	}
	
	/**
	 * 
	 * @param json格式的字符串
	 * @param t
	 * @return
	 */
	public static <T> T fromJson(String json,Type t){
		Gson gson=new Gson();
		return gson.fromJson(json, t);
	}
	
}
