package com.proj.admin.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.proj.admin.AdminRunner;
import com.proj.admin.pojo.ResAuthMapping;
import com.proj.admin.service.SysAuthorityService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AdminRunner.class)
@WebAppConfiguration
public class AuthorityDbTest {
	
	private static Logger logger = LoggerFactory.getLogger(AuthorityDbTest.class);
	
	@Autowired
	SysAuthorityRepository sysAuthorityRepository;
	
	@Autowired
	SysAuthorityService sysAuthorityService;
	
	@Autowired
	SysResourceRepository sysResourceRepository;
	
	SysAuthority auth;
	
    //@Before
    public void setUp() {
    	auth = new SysAuthority();
    	auth.setAuthorityName("auth01");
    	
    	List<SysResource> reSet= sysResourceRepository.findAll();
    	if(reSet != null){
    		auth.setSysResources(new HashSet<>(reSet));
    	}
    	
    }
    
    //@Test
    public void canSaveAuthority(){
    	sysAuthorityRepository.save(auth);
    }

    //@Test
    public void canGetAuthority(){
    	auth = sysAuthorityRepository.getByAuthorityName("auth01");
    	logger.info("******:"+auth.toString());
    }

    //@Test
    public void canUpdateAuthority(){
    	auth = sysAuthorityRepository.getByAuthorityName("auth01");
    	
    	SysResource sres = new SysResource();
    	sres.setResourceName("res3333");
    	sres.setEnabled(true);
    	Set<SysResource> reSet = new HashSet<SysResource>();
    	reSet.add(sres);
    	
    	auth.setSysResources(reSet);
    	sysAuthorityRepository.save(auth);
    }
    
    
    //@Test
    public void canFetchAll() {
    	List<SysAuthority> list =sysAuthorityRepository.findAll();
    	for(SysAuthority a : list){
    		logger.info(a.toString());
    	}
    } 
    
    //@Test
    public void canRemove(){
    	SysAuthority auth =sysAuthorityRepository.getByAuthorityName("auth01");
    	sysAuthorityRepository.delete(auth);
    }  
    
    @Test
    public void canFetchRAMapping(){
    	List<ResAuthMapping> list = sysAuthorityService.findResourceAuthorityMapping();
    	logger.info("%%%%%%:"+list.toString());
    }
}
