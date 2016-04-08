package com.proj.admin.service.impl;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.proj.admin.domain.SysUser;
import com.proj.admin.service.SysUserService;


@Component
public class AdminUserDetailsService implements UserDetailsService{
	
	private static final Logger logger = LoggerFactory.getLogger(AdminUserDetailsService.class);

	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
	
	@Autowired
	private SysUserService sysUserService;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser user = sysUserService.getUserByUsername(username);
		
		if (user == null){
			 throw new UsernameNotFoundException(messages.getMessage(
	                    "User.notFound", new Object[] { username },
	                    "Username {0} not found"));
		}
		
		logger.info(String.format("user [%s] login success !",username));
		
		Collection<GrantedAuthority> auths = sysUserService.loadUserAuthorities(username);
		user.setAuthorities(auths);
		
		return user;
	}

}
