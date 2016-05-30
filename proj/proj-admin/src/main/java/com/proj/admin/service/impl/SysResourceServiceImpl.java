package com.proj.admin.service.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.proj.admin.domain.SysResource;
import com.proj.admin.domain.SysResourceRepository;
import com.proj.admin.service.SysResourceService;


@Service("SysResourceService")
@Transactional
public class SysResourceServiceImpl implements SysResourceService {

	private static final Logger logger =LoggerFactory.getLogger(SysResourceServiceImpl.class);
	
	private final SysResourceRepository sysResourceRepository;
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	public SysResourceServiceImpl(SysResourceRepository sysResourceRepository){
		this.sysResourceRepository = sysResourceRepository;
	}
	
	
	@Override
	public void saveResource(SysResource resource) {
		sysResourceRepository.save(resource);
	}

	@Override
	public SysResource getResourceByResourceId(String resourceId) {
		return sysResourceRepository.getOne(resourceId);
	}

	@Override
	public void delResourceByResourceId(String resourceId) {
		sysResourceRepository.delete(resourceId);
	}

	@Override
	public void delResources(List<SysResource> resources) {
		sysResourceRepository.delete(resources);
	}


	@Override
	public Page<SysResource> findResources(Map<String, Object> criteria, Pageable pageable) {
		return sysResourceRepository.findAll(SysResource.builderSearchWhereClause(criteria), pageable);
	}


	@Override
	public List<SysResource> findResourceTree() {
		return sysResourceRepository.findByParentIdIsNull();
	}

}
