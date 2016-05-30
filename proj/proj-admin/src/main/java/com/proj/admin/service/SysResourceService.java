package com.proj.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.proj.admin.domain.SysResource;


public interface SysResourceService {
	
	public Page<SysResource> findResources(Map<String, Object> criteria, Pageable pageable);

	public void saveResource(SysResource resource);
	
	public SysResource getResourceByResourceId(String resourceId);
	
	public void delResourceByResourceId(String resourceId);
	
	public void delResources(List<SysResource> resources);
	
	public List<SysResource> findResourceTree();
}
