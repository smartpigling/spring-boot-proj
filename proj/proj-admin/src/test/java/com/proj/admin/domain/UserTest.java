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
    UserRepository userRepository;
    
    @Autowired 
    RoleRepository roleRepository;
    
    
    @Autowired 
    AuthorityRepository authorityRepository;

    
    User tom;
    User jarry;
    
    Role role_admin;
    Role role_user;


    @Before
    public void setUp() {
    	this.mockMvc = webAppContextSetup(this.wac).build();
    	
        tom = new User(1L,"tom","tom",true,"tom","tom@proj.com",false,false);
        jarry = new User(2L,"jarry","jarry",true,"jarry","jarry@proj.com",false,false);
        
        
        role_admin = new Role(1L,"ROLE_ADMIN");
        role_user = new Role(2L,"ROLE_USER");
        

        jarry.setRoles(new HashSet<Role>(){{
        	add(role_admin);
        	add(role_user);
        }});      

//        userRepository.deleteAllInBatch();
//        roleRepository.deleteAllInBatch();
//        userRepository.save(Arrays.asList(tom, jarry));
        

    }


    public void canFetchUser() throws Exception {
        Long id = jarry.getId();

        this.mockMvc.perform(get("/userInfo/{id}"))
        	.andExpect(status().isOk())
        	.andExpect(content().contentType(contentType))
        	.andExpect(jsonPath("$.name").value("jarry"));
    }


    public void canFetchAll() {
    	List<User> list =userRepository.findAll();
    	for(User u : list){
    		logger.info(u.toString());
    	}
    }

    @Test
    public void canFetchUserPage(){
    	String username="";
    	Pageable pageable=new PageRequest(0,10);
    	Page<User> page = userRepository.findByUsernameContainingIgnoreCase(username, pageable);
    	logger.info(page.getContent().toString());
    	logger.info(String.valueOf(page.getSize()));
    	logger.info(String.valueOf(page.getTotalElements()));
    	logger.info(String.valueOf(page.getTotalPages()));
    	logger.info(String.valueOf(page.getNumber()));
    }
    
    
    public void canDeleteUser() {
    	User u = userRepository.findByUsername("jarry");
    	userRepository.delete(u);
    }
    
    
    public void canFindUserAuthorities(){
    	List<Object[]> auths = userRepository.findAuthorityByUsername("jarry");
    	for(Object[] auth: auths){
//    		Authority _auth = (Authority)auth;
    		logger.info("jarry auths:"+auth);
    	}
    }
    
    
    public void canCreatePassword(){
    	BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder(4);
    	logger.info("BCryptPasswordEncoder :"+passwordEncoder.encode("tom"));
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
