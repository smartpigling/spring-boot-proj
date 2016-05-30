package com.proj.admin.domain;

import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj.admin.AdminRunner;


@FixMethodOrder(MethodSorters.DEFAULT)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AdminRunner.class)
@WebAppConfiguration
public class ResourceDbTest {

	private static Logger logger = LoggerFactory.getLogger(ResourceDbTest.class);
	
	@Autowired
	SysResourceRepository sysResourceRepository;
	
	
	SysResource sres1;
	
	SysResource sres2;
	
    @Before
    public void setUp() {
    	sres1 = new SysResource();
    	sres1.setResourceName("res3");
    	sres1.setResourceType("URL");
    	sres1.setEnabled(true); 
    	
    	sres2 = new SysResource();
    	sres2.setResourceName("res4");
    	sres2.setResourceType("URL");
    	sres2.setEnabled(true);    	
 
    }
    
    @Test
    @Ignore
    public void canGetResource(){
    	sres1 = sysResourceRepository.getByResourceName("res1");
    	logger.info("*****"+sres1.toString());
    }
    
    @Test
    public void canSaveResource(){
    	SysResource sres = sysResourceRepository.getByResourceName("res2");
    	
    	sres1.setSysResources(new HashSet(){{ add(sres2); }});
    	sres.setSysResources(new HashSet(){{ add(sres1); }});
    	sysResourceRepository.save(sres);
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
    	SysResource res =sysResourceRepository.getByResourceName("res11");
    	sysResourceRepository.delete(res);
    }
    
    //@Test
    public void canFetchParentIsNulll() {
    	List<SysResource> list =sysResourceRepository.findByParentIdIsNull();
    	logger.info("*****************SysResource***************");
    	ObjectMapper mapper = new ObjectMapper(); 
    	try {
			String json = mapper.writeValueAsString(list);
			logger.info(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
//    	for(SysResource r : list){
//    		logger.info(r.toString());
//    	}
    	logger.info("*******************************************");
    }     
    
}
