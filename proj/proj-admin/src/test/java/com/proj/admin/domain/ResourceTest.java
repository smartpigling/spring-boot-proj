package com.proj.admin.domain;

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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.proj.admin.AdminRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AdminRunner.class)
@WebAppConfiguration
public class ResourceTest {

	private static Logger logger = LoggerFactory.getLogger(ResourceTest.class);
	
	@Autowired
	SysResourceRepository sysResourceRepository;
	
	SysResource sres1;
	
	SysResource sres2;
	
    @Before
    public void setUp() {
    	sres1 = new SysResource();
    	sres1.setResourceName("res1");
    	sres1.setEnabled(true); 
    	
    	sres2 = new SysResource();
    	sres2.setResourceName("res2");
    	sres2.setEnabled(true);    	
 
    }
    
    @Test
    public void canSaveResource(){
    	
    	Set<SysResource> reSet =new HashSet<SysResource>();
    	reSet.add(sres1);
    	reSet.add(sres2);
    	sysResourceRepository.save(reSet);
    }
    
    //@Test
    public void canFetchAll() {
    	List<SysResource> list =sysResourceRepository.findAll();
    	logger.info("*****************SysResource***************");
    	for(SysResource r : list){
    		logger.info(r.toString());
    	}
    	logger.info("*******************************************");
    } 
    
    //@Test
    public void canRemove(){
    	SysResource res =sysResourceRepository.getByResourceName("res2");
    	sysResourceRepository.delete(res);
    }
    
}
