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

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "SYS_AUTHORITY")
public class SysAuthority implements Serializable {

	
	private static final long serialVersionUID = -1305694885035421947L;

	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Column(name = "AUTHORITY_ID", unique = true, nullable = false, length = 36)
	private String authorityId;
	
	private String authorityMark;//权限标识

	@Column(nullable = false)
	private String authorityName;// 权限名称

	private String authorityDesc;// 权限描述
	
	private String message;//消息

	private Boolean enabled;// 是否可用

	private Integer sysSpec;// 保留1:可赋予,0:不可赋予
	
    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.REFRESH})
    @JoinTable(
        name="SYS_AUTHORITY_RESOURCE",
        joinColumns=@JoinColumn(name="AUTHORITY_ID", referencedColumnName="AUTHORITY_ID"),
        inverseJoinColumns=@JoinColumn(name="RESOURCE_ID", referencedColumnName="RESOURCE_ID"))	
	private Collection<SysResource> sysResources;
	

    @ManyToMany
    @JoinTable(
        name="SYS_ROLE_AUTHORITY",
        joinColumns=@JoinColumn(name="AUTHORITY_ID", referencedColumnName="AUTHORITY_ID"),
        inverseJoinColumns=@JoinColumn(name="ROLE_ID", referencedColumnName="ROLE_ID"))	
    private Collection<SysRole> sysRoles;
    
    
	public String getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}

	public String getAuthorityMark() {
		return authorityMark;
	}

	public void setAuthorityMark(String authorityMark) {
		this.authorityMark = authorityMark;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public String getAuthorityDesc() {
		return authorityDesc;
	}

	public void setAuthorityDesc(String authorityDesc) {
		this.authorityDesc = authorityDesc;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}



	public Collection<SysResource> getSysResources() {
		return sysResources;
	}

	public void setSysResources(Collection<SysResource> sysResources) {
		this.sysResources = sysResources;
	}

	public Collection<SysRole> getSysRoles() {
		return sysRoles;
	}

	public void setSysRoles(Collection<SysRole> sysRoles) {
		this.sysRoles = sysRoles;
	}

	@Override
	public String toString() {
		return "SysAuthority [authorityId=" + authorityId + ", authorityMark=" + authorityMark + ", authorityName="
				+ authorityName + ", authorityDesc=" + authorityDesc + ", message=" + message + ", enabled=" + enabled
				+ ", sysSpec=" + sysSpec + ", sysResources=" + sysResources + "]";
	}

	

}
