package com.proj.admin.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import com.proj.admin.domain.SysUser;
import com.proj.admin.util.AdminUtils;

public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private static final Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		//TOD 更新用户IP 登录时间
		SysUser user = (SysUser) authentication.getPrincipal();
		logger.info(String.format("用户[%s]登录成功。IP：%s", user.getUsername(),AdminUtils.getIpAddress(request)));
		super.onAuthenticationSuccess(request, response, authentication);
	}



}
