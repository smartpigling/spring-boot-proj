package com.proj.admin.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;


public class AdminMethodSecurityConfig extends GlobalMethodSecurityConfiguration{

	  @Override
	  protected MethodSecurityExpressionHandler createExpressionHandler() {
	    DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
//	    expressionHandler.setPermissionEvaluator(new CustomPermissionEvaluator());
	    return expressionHandler;
	  }
	  
	    @Override
	    protected AccessDecisionManager accessDecisionManager() {
//	        return new AuthScopeDecisionManager();
	    	return super.accessDecisionManager();
	    }
}
