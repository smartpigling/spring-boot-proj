package com.proj.admin.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.proj.admin.domain.UserRepository;


@Component
public class AdminUserDetailsService implements UserDetailsService{
	
	private static final Logger logger = LoggerFactory.getLogger(AdminUserDetailsService.class);

	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.proj.admin.domain.User u = userRepository.findByUsername(username);
		
		if (u == null){
			 throw new UsernameNotFoundException(messages.getMessage(
	                    "User.notFound", new Object[] { username },
	                    "Username {0} not found"));
		}
		
		logger.info(String.format("user [%s] login success !",username));
		
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		List<Object[]> u_auths = userRepository.findAuthorityByUsername(username);
		for(Object[] u_auth : u_auths){
			authorities.add(new SimpleGrantedAuthority((String) u_auth[2]));
		}
		
		return new org.springframework.security.core.userdetails.User(
				u.getUsername(), u.getPassword(),u.getEnabled(), !u.getAccountExpired(),true,!u.getAccountLocked(),authorities);
	}

}
