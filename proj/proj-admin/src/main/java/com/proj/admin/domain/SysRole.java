package com.proj.admin.domain;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="SYS_ROLE")
public class SysRole implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6817501096784798352L;

	@Id
    @GeneratedValue(generator = "generator")  
    @GenericGenerator(name = "generator", strategy = "uuid.hex")  
    @Column(name = "ROLE_ID", unique = true, nullable = false, length = 36)  
	private String roleId;
    
    @NotNull
	private String roleName; //角色名称
    
    private String roleDesc;//角色描述
    
    private Boolean enabled;//是否可用
    
    private Integer sysSpec;//0、系统,1、子系统
    
    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.REFRESH})
    @JoinTable(
        name="SYS_ROLE_AUTHORITY",
        joinColumns=@JoinColumn(name="ROLE_ID", referencedColumnName="ROLE_ID"),
        inverseJoinColumns=@JoinColumn(name="AUTHORITY_ID", referencedColumnName="AUTHORITY_ID"))	
    private Collection<SysAuthority> sysAuthorities;
    
    
    @ManyToMany
    @JoinTable(
        name="SYS_USER_ROLE",
        joinColumns=@JoinColumn(name="ROLE_ID", referencedColumnName="ROLE_ID"),
        inverseJoinColumns=@JoinColumn(name="USER_ID", referencedColumnName="USER_ID"))	
    private Collection<SysUser> sysUsers;
    

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Integer getSysSpec() {
		return sysSpec;
	}

	public void setSysSpec(Integer sysSpec) {
		this.sysSpec = sysSpec;
	}



	public Collection<SysAuthority> getSysAuthorities() {
		return sysAuthorities;
	}

	public void setSysAuthorities(Collection<SysAuthority> sysAuthorities) {
		this.sysAuthorities = sysAuthorities;
	}

	
	
	
	public Collection<SysUser> getSysUsers() {
		return sysUsers;
	}

	public void setSysUsers(Collection<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}

	@Override
	public String toString() {
		return "SysRole [roleId=" + roleId + ", roleName=" + roleName + ", roleDesc=" + roleDesc + ", enabled="
				+ enabled + ", sysSpec=" + sysSpec + "]";
	}

    	

}
