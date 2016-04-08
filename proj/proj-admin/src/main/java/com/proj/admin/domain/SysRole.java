package com.proj.admin.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    
    private boolean enable;//是否可用

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

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}
    
    
    	

}
