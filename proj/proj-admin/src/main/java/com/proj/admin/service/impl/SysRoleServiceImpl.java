package com.proj.admin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.proj.admin.domain.SysRole;
import com.proj.admin.domain.SysRoleRepository;
import com.proj.admin.service.SysRoleService;

@Service("SysRoleService")
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	SysRoleRepository sysRoleRepository;
	
	
	@Override
	public SysRole getRoleByRoleName(String roleName) {
		return sysRoleRepository.getByRoleName(roleName);
	}

	@Override
	public Page<SysRole> findSysRoles(Map<String, Object> criteria, Pageable pageable) {
		return sysRoleRepository.findAll(SysRole.builderSearchWhereClause(criteria),pageable);
	}

	@Override
	public SysRole saveRole(SysRole role) {
		return sysRoleRepository.save(role);
	}

	@Override
	public SysRole getRoleByRoleId(String roleId) {
		return sysRoleRepository.findOne(roleId);
	}

	@Override
	public void delRoleByRoleId(String roleId) {
		sysRoleRepository.delete(roleId);
	}

	@Override
	public void delRoles(List<SysRole> roles) {
		sysRoleRepository.delete(roles);
	}

}
