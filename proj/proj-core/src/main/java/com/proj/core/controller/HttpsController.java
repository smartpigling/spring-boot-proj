package com.proj.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class HttpsController {
	
	
    @RequestMapping("/")
    public String test() {
        return "HTTPS OK!";
    }
}
