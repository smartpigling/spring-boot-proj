package com.proj.admin.domain;

import java.util.Collection;
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
public class RoleTest {

	private static Logger logger = LoggerFactory.getLogger(RoleTest.class);
	
	
	@Autowired
	SysRoleRepository sysRoleRepository;
	
	@Autowired
	SysAuthorityRepository sysAuthorityRepository;
	
	SysRole role;
	
	//@Before
	public void setUp(){
		role = new SysRole();
		role.setRoleName("role1");
		
		Collection<SysAuthority> authSet =sysAuthorityRepository.findAll();
		if(authSet != null){
			role.setSysAuthorities(authSet);
		}
	}
	
	//@Test
	public void canSaveRole(){
		sysRoleRepository.save(role);
	}
	
	//@Test
	public void canGetRole(){
		role = sysRoleRepository.getByRoleName("role1");
		logger.info("******"+role.toString());
	}
	
	@Test
	public void canUpdateRole(){
		role = sysRoleRepository.getByRoleName("role1");
		
		SysAuthority auth = sysAuthorityRepository.getByAuthorityName("auth88");
		
		Set<SysAuthority> authSet = new HashSet<>();
		authSet.add(auth);
		role.setSysAuthorities(authSet);
		sysRoleRepository.save(role);
	}
}
