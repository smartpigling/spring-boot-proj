package com.proj.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proj.admin.domain.SysAuthorityRepository;
import com.proj.admin.pojo.ResAuthMapping;
import com.proj.admin.service.SysAuthorityService;


@Service("SysAuthorityService")
public class SysAuthorityServiceImpl implements SysAuthorityService {
	
	private static final Logger logger =LoggerFactory.getLogger(SysAuthorityServiceImpl.class);

	@Autowired
	SysAuthorityRepository sysAuthorityRepository;
	
	@Override
	public List<ResAuthMapping> findResourceAuthorityMapping() {
		List<ResAuthMapping> listDTO= new ArrayList<ResAuthMapping>();
		List<Object[]> resultList = sysAuthorityRepository.findResourceAuthorityMapping();
		for(Object[] objectArray : resultList){
			ResAuthMapping m =new ResAuthMapping();
			m.setResourcePath(String.valueOf(objectArray[0]));
			m.setAuthorityMark(String.valueOf(objectArray[1]));
			listDTO.add(m);
		}
		return listDTO;
	}

}
