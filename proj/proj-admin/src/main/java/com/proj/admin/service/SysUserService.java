package com.proj.admin.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;

import com.proj.admin.domain.SysUser;

public interface SysUserService {
	
	public SysUser getUserByUsername(String username); 
	
	public Page<SysUser> findSysUsers(Map<String, Object> criteria, Pageable pageable);
	
	public Collection<GrantedAuthority> loadUserAuthorities(String username); 
	
	public void saveUser(SysUser user);
	
	public SysUser getUserByUserId(String userId);
	
	public void delUserByUserId(String userId);
	
	public void delUsers(List<SysUser> users);
}
