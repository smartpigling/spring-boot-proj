package com.proj.admin.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private static final Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		org.springframework.security.core.userdetails.User userDetails = (org.springframework.security.core.userdetails.User) authentication
				.getPrincipal();
		logger.info(String.format("用户[%s]登录成功。IP：%s", userDetails.getUsername(),AdminUtils.getIpAddress(request)));
		super.onAuthenticationSuccess(request, response, authentication);
	}



}
