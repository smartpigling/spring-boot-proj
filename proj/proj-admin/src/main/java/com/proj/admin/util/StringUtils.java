package com.proj.admin.util;

import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class StringUtils {

	
	/**
	 * 分割字符串转换为字符串数组
	 * @param str 分割字符串
	 * @param delim 分隔符
	 * @return
	 */
	public static String[] convertStrToArray(String str,String delim){
		StringTokenizer st = new StringTokenizer(str,delim);
		String[] strArray = new String[st.countTokens()];
		int i = 0;
		while(st.hasMoreTokens()){
			strArray[i++] = st.nextToken();
		}
		return strArray;
	}
	
	/**
	 * 判断是否为浮点数，包括double和float 
	 * @param str
	 * @return 是浮点数返回true,否则返回false 
	 */
	public static boolean isDouble(String str) {    
		Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");    
		return pattern.matcher(str).matches();    
	}  	
}
