package com.proj.admin.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <p>系统资源</p>
 * 
 * @author Tang Xiaochuan
 */

public interface SysResourceRepository extends JpaRepository<SysResource, String>, JpaSpecificationExecutor<SysResource>{

	public SysResource getByResourceName(String resourceName);
}
