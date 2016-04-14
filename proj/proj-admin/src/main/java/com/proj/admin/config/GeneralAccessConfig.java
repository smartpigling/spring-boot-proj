package com.proj.admin.config;



import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.proj.admin.util.AdminUtils;




@Controller
public class GeneralAccessConfig {

	private static final Logger logger =LoggerFactory.getLogger(GeneralAccessConfig.class);

	@RequestMapping("/")
	public String home(Map<String, Object> model) {
		model.put("message", "Project 项目首页");
		model.put("title", "Project Home");
		model.put("date", new Date());
		return "home";
	}
	
	@RequestMapping("/unauthorized")
	public ModelAndView accessDenied() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("timestamp", new Date());
		mav.setViewName("unauthorized");
		return mav;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(HttpServletRequest req, Exception e) {
		logger.warn("In handleException", e);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.addObject("timestamp", new Date());
		mav.addObject("url", req.getRequestURL());
		mav.setViewName("exception");
		return mav;
	}	
	
	
	
	@ExceptionHandler(AccessDeniedException.class)
	public String handleAccessDeniedException(HttpServletRequest req, Exception e) {
		logger.info(String.format("IP[%s]用户非法访问！", AdminUtils.getIpAddress(req)));
		return "redirect:/unauthorized";
	}
	

	@RequestMapping("/router")
	public String accessDeniedRouter(@RequestParam("q") String resource) {
		logger.debug("In accessDeniedRouter resource = " + resource);

		return "redirect:/" + resource;
	}



	@RequestMapping(value = "/oups", method = RequestMethod.GET)
	public String triggerException() {
		throw new RuntimeException(
				"Expected: controller used to showcase what happens when an exception is thrown");
	}
	

}
