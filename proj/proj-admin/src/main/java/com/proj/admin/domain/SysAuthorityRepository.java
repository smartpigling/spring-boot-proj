package com.proj.admin.domain;


import org.springframework.data.jpa.repository.JpaRepository;

public interface SysAuthorityRepository extends JpaRepository<SysAuthority,String>{

	public SysAuthority getByAuthorityName(String authorityName);
}
