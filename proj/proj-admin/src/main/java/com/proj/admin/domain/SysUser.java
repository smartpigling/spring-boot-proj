package com.proj.admin.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;


@Entity
@Table(name = "SYS_USER")
@DynamicUpdate
public class SysUser implements UserDetails,Serializable{
	
	/**
	 * users template search form
	 * @param username
	 * @param name
	 * @param enabled
	 * @return
	 */
    public static Specification<SysUser> builderSearchWhereClause(final Map<String, Object> criteria) {
        return new Specification<SysUser>() {
            @Override
            public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            	List<Predicate> predicate = new ArrayList<Predicate>();
            	if(!StringUtils.isEmpty(criteria.getOrDefault("username",""))){
            		predicate.add(cb.like(root.get("username").as(String.class), "%"+criteria.get("username")+"%"));
            	}
            	
            	if(!StringUtils.isEmpty(criteria.getOrDefault("name",""))){
            		predicate.add(cb.like(root.get("name").as(String.class), "%"+criteria.get("name")+"%"));
            	}
            	
				if(!StringUtils.isEmpty(criteria.getOrDefault("enabled","false"))){
					predicate.add(cb.equal(root.get("enabled").as(Boolean.class), 
							Boolean.parseBoolean(criteria.getOrDefault("enabled","false").toString())));
				}
            	
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }
    
    
	private static final long serialVersionUID = -17286720695763652L;

	@Id
    @GeneratedValue(generator = "generator")  
    @GenericGenerator(name = "generator", strategy = "uuid.hex")  
    @Column(name = "USER_ID", unique = true, nullable = false, length = 36)  	
	private String userId;
    
    @Column(unique = true, nullable = false)
	private String username;
    
    @Column(nullable = false,updatable = false)
	private String password;
    
    private String name;
    
    @Column(nullable = false)
	private boolean enabled;//是否可用
    
	private boolean accountNonExpired;//是否过期
	
	private boolean accountNonLocked;//是否锁定
	
	private boolean credentialsNonExpired;//证书是否有效
	
	@org.hibernate.annotations.CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
	private Date createdTime; //创建日期
	
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
	private Date lastLogin;//最后登录日期
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date validTime;//有效日期

	@Column(updatable = false)
	private String loginIp;//登录IP
	
	private String orgId;//所属机构ID
	
	private String orgName;//所属机构名称
	
	@Transient
	private Collection<GrantedAuthority>  authorities;
	
	
    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.REFRESH})
    @JoinTable(
        name="SYS_USER_ROLE",
        joinColumns=@JoinColumn(name="USER_ID", referencedColumnName="USER_ID"),
        inverseJoinColumns=@JoinColumn(name="ROLE_ID", referencedColumnName="ROLE_ID"))	
	private Collection<SysRole> sysRoles;
	
	

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
	
	public Collection<SysRole> getSysRoles() {
		return sysRoles;
	}

	public void setSysRoles(Collection<SysRole> sysRoles) {
		this.sysRoles = sysRoles;
	}

	@Override
	public String toString() {
		return "SysUser [userId=" + userId + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", enabled=" + enabled + ", accountNonExpired=" + accountNonExpired + ", accountNonLocked="
				+ accountNonLocked + ", credentialsNonExpired=" + credentialsNonExpired + ", createdTime=" + createdTime
				+ ", lastLogin=" + lastLogin + ", validTime=" + validTime + ", loginIp=" + loginIp + ", orgId=" + orgId
				+ ", orgName=" + orgName + ", authorities=" + authorities + "]";
	}




	
}
