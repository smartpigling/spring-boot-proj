package com.proj.admin.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.proj.admin.AdminRunner;
import com.proj.admin.domain.SysUser;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AdminRunner.class)
@WebAppConfiguration
public class UserWebTest {

	private static Logger logger = LoggerFactory.getLogger(UserWebTest.class);
	
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("UTF-8"));	
    
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
    @Before
    public void setUp() {
    	this.mockMvc = webAppContextSetup(this.wac).build();
    	
    }
    
    @Test
    public void test_user_userid() throws Exception {
        String userId = "";

        this.mockMvc.perform(get("user/{userId}",userId))
        	.andExpect(status().isOk())
        	.andExpect(content().contentType(contentType))
        	.andExpect(jsonPath("$.name").value("jarry"));
    }
}
