package com.proj.admin.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



@Entity
@Table(name = "SYS_USER")
public class SysUser implements UserDetails,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -17286720695763652L;

	@Id
    @GeneratedValue(generator = "generator")  
    @GenericGenerator(name = "generator", strategy = "uuid.hex")  
    @Column(name = "USER_ID", unique = true, nullable = false, length = 36)  	
	private String userId;
    
    @Column(unique = true, nullable = false)
	private String username;
    
    @Column(nullable = false)
	private String password;
    
    private String name;
    
    @Column(nullable = false)
	private boolean enabled;//是否可用
    
	private boolean accountNonExpired;//是否过期
	
	private boolean accountNonLocked;//是否锁定
	
	private boolean credentialsNonExpired;//证书是否有效
	
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @org.hibernate.annotations.CreationTimestamp
	private Date createdTime; //创建日期
	
    @Temporal(TemporalType.TIMESTAMP)
	private Date lastLogin;//最后登录日期
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date validTime;//有效日期

	private String loginIp;//登录IP
	
	private String orgId;//所属机构ID
	
	private String orgName;//所属机构名称
	
	@Transient
	private Collection<GrantedAuthority>  authorities;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Date getValidTime() {
		return validTime;
	}

	public void setValidTime(Date validTime) {
		this.validTime = validTime;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}




	
}