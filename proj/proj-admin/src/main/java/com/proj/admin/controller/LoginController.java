package com.proj.admin.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	private static final Logger logger =LoggerFactory.getLogger(LoginController.class);
	
    @RequestMapping(value = "/perform_login", method = RequestMethod.GET)
    public ModelAndView getLoginPage(@RequestParam String captcha) {
    	logger.info("captcha is :{}",captcha);
        logger.debug("Getting login page, error=captcha invalid");
        return new ModelAndView("login", "error", "captcha invalid");
    }
}
