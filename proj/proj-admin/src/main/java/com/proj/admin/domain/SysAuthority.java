package com.proj.admin.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "SYS_AUTHORITY")
public class SysAuthority implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1305694885035421947L;

	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Column(name = "AUTHORITY_ID", unique = true, nullable = false, length = 36)
	private String authorityId;

	@Column(nullable = false)
	private String authorityMark;// 权限标识

	@Column(nullable = false)
	private String authorityName;// 权限名称

	private String authorityDesc;// 权限描述

	private String message;// 提示信息

	private boolean enable;// 是否可用

	private boolean issys;// 是否系统权限

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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public boolean isIssys() {
		return issys;
	}

	public void setIssys(boolean issys) {
		this.issys = issys;
	}
	
	

}
