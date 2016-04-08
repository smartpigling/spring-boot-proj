package com.proj.admin.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class AdminSecurityInterceptor extends HandlerInterceptorAdapter{

	private static final Logger logger =LoggerFactory.getLogger(AdminSecurityInterceptor.class);
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
//    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    	if (principal instanceof UserDetails) {
//    		UserDetails ud = (UserDetails)principal;
//    	    String username = ud.getUsername();
//    	    logger.info("get user object:"+ud.toString());
//    	} else {
//    	    String username = principal.toString();
//    	    logger.info("get user username:"+username);
//    	} 		
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
