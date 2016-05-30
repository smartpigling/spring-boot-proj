package com.proj.admin.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class SysUserForm {
	
	@NotEmpty
	private String username;
    
	private String password;
    
    private String name;
    
	private boolean enabled;//是否可用
    
	private boolean accountNonExpired;//是否过期
	
	private boolean accountNonLocked;//是否锁定
	
	private boolean credentialsNonExpired;//证书是否有效
}
