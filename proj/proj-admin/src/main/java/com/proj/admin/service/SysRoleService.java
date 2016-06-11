package com.proj.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.proj.admin.domain.SysRole;

public interface SysRoleService {

	public SysRole getRoleByRoleName(String roleName); 
	
	public Page<SysRole> findSysRoles(Map<String, Object> criteria, Pageable pageable);
	
	public SysRole saveRole(SysRole role);
	
	public SysRole getRoleByRoleId(String roleId);
	
	public void delRoleByRoleId(String roleId);
	
	public void delRoles(List<SysRole> roles);
}
