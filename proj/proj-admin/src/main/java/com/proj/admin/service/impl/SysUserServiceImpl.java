package com.proj.admin.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.proj.admin.domain.SysAuthority;
import com.proj.admin.domain.SysUser;
import com.proj.admin.domain.SysUserRepository;
import com.proj.admin.service.SysUserService;


@Component("SysUsersService")
@Transactional
public class SysUserServiceImpl implements SysUserService{
	
	private static final Logger logger =LoggerFactory.getLogger(SysUserServiceImpl.class);
	
	private final SysUserRepository sysUserRepository;
	
	@Autowired
	public SysUserServiceImpl(SysUserRepository sysUserRepository){
		this.sysUserRepository = sysUserRepository;
	}
	
	
	/**
	 * 根据用户名获取到用户的权限并封装成GrantedAuthority集合
	 * @param username
	 */
	public Collection<GrantedAuthority> loadUserAuthorities(String username){
		List<SysAuthority> list = sysUserRepository.findSysAuthoritiesByUsername(username);
		
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		
		for (SysAuthority authority : list) {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getAuthorityMark());
			auths.add(grantedAuthority);
		}

		return auths;
		
	}

	
	@Override
	public SysUser getUserByUsername(String username) {
		return sysUserRepository.getByUsername(username);
	}
	
	@Override
	public Page<SysUser> findSysUsersByUsername(String username, Pageable pageable) {
		return sysUserRepository.findByUsernameContainingIgnoreCase(username, pageable);
	}


	@Override
	public void saveUser(SysUser user) {
		sysUserRepository.save(user);
	}


	@Override
	public SysUser getUserByUserId(String userId) {
		return sysUserRepository.getOne(userId);
	}


	@Override
	public void delUserByUserId(String userId) {
		sysUserRepository.delete(userId);
	}
}
