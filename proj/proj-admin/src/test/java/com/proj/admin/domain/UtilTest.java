package com.proj.admin.domain;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

public class UtilTest {

	public static void main(String[] args) {
		
		Map<String,Object> map =new HashMap<String,Object>();
		//map.put("username", "");
		if(!StringUtils.isEmpty(map.getOrDefault("username",""))){
			System.out.println("aaaaaa");
		}else{
			System.out.println("bbbbbb");
		}
		
	}

}
