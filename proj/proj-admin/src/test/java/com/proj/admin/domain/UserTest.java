package com.proj.admin.domain;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.proj.admin.AdminRunner;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AdminRunner.class)
@WebAppConfiguration
public class UserTest {
	
	private static Logger logger = LoggerFactory.getLogger(UserTest.class);
	
	
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));	
    
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
    @Autowired 
    SysUserRepository sysUserRepository;
    
    @Autowired 
    SysRoleRepository sysRoleRepository;
    
    


    
    SysUser tom;

    //@Before
    public void setUp() {
    	this.mockMvc = webAppContextSetup(this.wac).build();
    	
    	for (int i = 0; i < 100; i++) {
    		tom = new SysUser();
    		tom.setName("tom"+i);
    		tom.setUsername("tom"+i);
    		tom.setPassword("$2a$04$ng.tYkv.xnH0U3G3wvKCse4upJrcrb1K6TZbExCQktU5tpGB/gB2m");
    		tom.setEnabled(true);
    		tom.setAccountNonExpired(true);
    		tom.setAccountNonLocked(true);
    		tom.setCredentialsNonExpired(true);
    		sysUserRepository.save(tom);
		}

        
        

    }


    public void canFetchUser() throws Exception {
        String userId = tom.getUserId();

        this.mockMvc.perform(get("user/{userId}",userId))
        	.andExpect(status().isOk())
        	.andExpect(content().contentType(contentType))
        	.andExpect(jsonPath("$.name").value("jarry"));
    }

    
    public void canFetchAll() {
    	List<SysUser> list =sysUserRepository.findAll();
    	for(SysUser u : list){
    		logger.info(u.toString());
    	}
    }

    @Test
    public void canFetchUserPage(){
    	String username="";
    	String name = "";
    	boolean enabled=true;
    	Pageable pageable=new PageRequest(0,10);
    	
    	
    	Page<SysUser> page = sysUserRepository.findBySearchForm(pageable);
    	
//    	Page<SysUser> page = sysUserRepository.findByUsernameContainingIgnoreCase(username, pageable);
    	logger.info(page.getContent().toString());
    	logger.info(String.valueOf(page.getSize()));
    	logger.info(String.valueOf(page.getTotalElements()));
    	logger.info(String.valueOf(page.getTotalPages()));
    	logger.info(String.valueOf(page.getNumber()));
    }
    
    
    public void canDeleteUser() {
    	SysUser u = sysUserRepository.getOne("");
    	sysUserRepository.delete(u);
    }
    
    
    public void canFindUserAuthorities(){
    	List<SysAuthority> auths = sysUserRepository.findSysAuthoritiesByUsername("tom");
    	for(SysAuthority auth: auths){
//    		Authority _auth = (Authority)auth;
    		logger.info("jarry auths:"+auth);
    	}
    }
    
    
    public void canCreatePassword(){
    	BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder(4);
    	logger.info("BCryptPasswordEncoder :"+passwordEncoder.encode("admin"));
    }
    
    
    public void canGetCurrentUser(){
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	if (principal instanceof UserDetails) {
    		UserDetails ud = (UserDetails)principal;
    	    String username = ud.getUsername();
    	    logger.info("get user object:"+ud.toString());
    	} else {
    	    String username = principal.toString();
    	    logger.info("get user username:"+username);
    	}   	
    }
 
}
