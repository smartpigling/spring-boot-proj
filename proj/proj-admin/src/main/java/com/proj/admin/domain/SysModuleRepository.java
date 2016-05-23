package com.proj.admin.domain;

import org.springframework.data.jpa.repository.JpaRepository;


/**
 * 
 * @author Tang Xiaochuan
 *
 */

public interface SysModuleRepository extends JpaRepository<SysModule, String>{

	public SysModule getByModuleName(String moduleName);
	
}
