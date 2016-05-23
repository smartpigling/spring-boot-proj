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
import com.proj.admin.AdminRunner;


@FixMethodOrder(MethodSorters.DEFAULT)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AdminRunner.class)
@WebAppConfiguration
public class ResourceDbTest {

	private static Logger logger = LoggerFactory.getLogger(ResourceDbTest.class);
	
	@Autowired
	SysResourceRepository sysResourceRepository;
	
	SysResource sres;
	
	SysResource sres1;
	
	SysResource sres2;
	
    @Before
    @Ignore
    public void setUp() {
    	sres1 = new SysResource();
    	sres1.setResourceName("res11");
    	sres1.setEnabled(true); 
    	
    	sres2 = new SysResource();
    	sres2.setResourceName("res22");
    	sres2.setEnabled(true);    	
 
    }
    
    @Test
    @Ignore
    public void canGetResource(){
    	sres = sysResourceRepository.getByResourceName("res1");
    	logger.info("*****"+sres.toString());
    }
    
    //@Test
    public void canSaveResource(){
    	sres = sysResourceRepository.getByResourceName("res1");
    	
    	sres1.setSysResource(new HashSet(){{ add(sres2); }});
    	sres.setSysResource(new HashSet(){{ add(sres1); }});
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
    
    @Test
    public void canRemove(){
    	SysResource res =sysResourceRepository.getByResourceName("res11");
    	sysResourceRepository.delete(res);
    }
    
}
