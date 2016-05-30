package com.proj.admin.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * <p>系统资源</p>
 * 
 * @author Tang Xiaochuan
 */

public interface SysResourceRepository extends JpaRepository<SysResource, String>, JpaSpecificationExecutor<SysResource>{

	@Query(value=" SELECT * FROM SYS_RESOURCE  WHERE PARENT_ID IS NULL ", nativeQuery = true)
	public List<SysResource> findByParentIdIsNull();
	
	public SysResource getByResourceName(String resourceName);
}
