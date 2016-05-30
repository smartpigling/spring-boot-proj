package com.proj.admin.domain;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface SysAuthorityRepository extends JpaRepository<SysAuthority,String>{
	
	public SysAuthority getByAuthorityName(String authorityName);
	
	@Query(value="SELECT S3.RESOURCE_PATH,S2.AUTHORITY_MARK FROM SYS_AUTHORITY_RESOURCE S1 "
			+ "JOIN SYS_AUTHORITY S2 ON S1.AUTHORITY_ID = S2.AUTHORITY_ID "
			+ "JOIN SYS_RESOURCE S3 ON S1.RESOURCE_ID = S3.RESOURCE_ID "
			+ "AND S3.RESOURCE_TYPE='URL' ORDER BY S3.PRIORITY DESC", nativeQuery=true)
	public List<Object[]> findResourceAuthorityMapping();
}
