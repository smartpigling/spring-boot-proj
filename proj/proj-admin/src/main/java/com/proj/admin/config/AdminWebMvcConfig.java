package com.proj.admin.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.proj.admin.filter.ThymeleafLayoutInterceptor;

/**
 * Admin MVC Adapter 用户自定义
 * 
 * @author Tang Xiaochuan
 *
 */

@EnableAutoConfiguration
@Controller
public class AdminWebMvcConfig extends WebMvcConfigurerAdapter{
	
	private static final Logger logger =LoggerFactory.getLogger(AdminWebMvcConfig.class);
	
	
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    	registry.addViewController("/login").setViewName("login");
    	registry.addViewController("/test").setViewName("test");
//    	logger.info("start web mvc config !");
    }
    

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		/**
		 * thymeleaf Layout ThymeleafLayoutInterceptor deprecated
		 */
		//registry.addInterceptor(new ThymeleafLayoutInterceptor());
		
		//registry.addInterceptor(new AdminSecurityInterceptor()).addPathPatterns("/**");
	}
	
}
