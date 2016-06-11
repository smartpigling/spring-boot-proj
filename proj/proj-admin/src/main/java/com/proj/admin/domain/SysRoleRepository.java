package com.proj.admin.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 
 * @author Tang Xiaochuan
 *
 */
public interface SysRoleRepository extends JpaRepository<SysRole,String>, JpaSpecificationExecutor<SysRole>{
	
	public SysRole getByRoleName(String roleName);
	
	

}
