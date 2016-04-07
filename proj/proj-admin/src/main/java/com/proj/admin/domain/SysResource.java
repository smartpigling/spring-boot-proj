package com.proj.admin.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "SYS_RESOURCE")
public class SysResource implements Serializable {

	private static final long serialVersionUID = 3345916776753770292L;
	
	@Id
	@GeneratedValue(generator = "generator")  
	@GenericGenerator(name = "generator", strategy = "uuid.hex")  
	@Column(name = "RESOURCE_ID", unique = true, nullable = false, length = 36)  
	private String resourceId;// 资源ID
	
	private String resourceType;// 资源类别 URL,METHOD
	private String resourceName;// 资源名称
	private String resourceDesc;// 资源描述
	private String resourcePath;// 资源路径
	private Integer priority;// 优先级
	private Boolean enable;// 是否可用
	private String moduleId;// 模块
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getResourceDesc() {
		return resourceDesc;
	}
	public void setResourceDesc(String resourceDesc) {
		this.resourceDesc = resourceDesc;
	}
	public String getResourcePath() {
		return resourcePath;
	}
	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Boolean getEnable() {
		return enable;
	}
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}


}
