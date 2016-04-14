package com.proj.admin.util;

import javax.servlet.http.HttpServletRequest;

public class AdminUtils {

	/**
	 * 获取请求IP地址
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		return ipAddress;
	}
}
