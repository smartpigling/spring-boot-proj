package com.proj.serv.example.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;



@Controller
public class CustomerController extends WebMvcConfigurerAdapter {
	private static final Logger logger =LoggerFactory.getLogger(CustomerController.class);
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("customer_results");
    }

    @RequestMapping(value="/customer", method=RequestMethod.GET)
    public String showForm(CustomerForm customerForm) {
        return "customer_form";
    }

    @RequestMapping(value="/customer", method=RequestMethod.POST)
    public String checkPersonInfo(@Valid CustomerForm customerForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
        	logger.info("111111111111111111111111111111111111");
            return "customer_form";
        }

        return "redirect:/results";
    }
}
