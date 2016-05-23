package com.proj.admin.domain;

import java.util.Collection;

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
public class ModuleTest {
	private static Logger logger = LoggerFactory.getLogger(ModuleTest.class);
	
	@Autowired
	SysModuleRepository sysModuleRepository;
	
	@Autowired
	SysResourceRepository sysResourceRepository;
	
	SysModule mod;
	
    //@Before
    public void setUp() {
    	mod = new SysModule();
    	mod.setModuleName("mod2");
    	mod.setEnabled(true);
    	
    }
    
    //@Test
    public void canSaveModule(){
    	sysModuleRepository.save(mod);
    }
    
    public void canGetModule(){
    	mod = sysModuleRepository.getByModuleName("mod1");
    	logger.info("*****"+mod.toString());
    }
    
    //@Test
    public void canUpdateModule(){
    	mod = sysModuleRepository.getByModuleName("mod2");
    	
    	Collection<SysResource> reSet = sysResourceRepository.findAll();
    	if(reSet != null){
    		mod.setSysResources(reSet);
    	}
    	sysModuleRepository.save(mod);
    }
    
    @Test
    public void canDeleteModule(){
    	mod = sysModuleRepository.getByModuleName("mod2");
    	sysModuleRepository.delete(mod);
    }
}
