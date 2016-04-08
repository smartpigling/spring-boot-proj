package com.proj.admin.domain;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.GrantedAuthority;

/**
 * 
 * @author Tang Xiaochuan
 *
 */

public interface SysUserRepository extends JpaRepository<SysUser, String> {

	public SysUser getByUsername(String username);

	public Collection<GrantedAuthority> loadUserAuthorities(String username);

	public Page<SysUser> findByUsernameContainingIgnoreCase(String username, Pageable pageable);

	@Query(value = "SELECT * FROM SYS_AUTHORITY WHERE AUTHORITY_ID IN( "+  
			"SELECT DISTINCT AUTHORITY_ID FROM SYS_ROLE_AUTHORITY  S1 "+  
			"JOIN SYS_USER_ROLE S2 ON S1.ROLE_ID = S2.ROLE_ID "+  
			"JOIN SYS_USER S3 ON S3.USER_ID = S2.USER_ID AND S3.USERNAME=?1)", nativeQuery = true)
	public List<SysAuthority> findSysAuthoritiesByUsername(String username);
}
