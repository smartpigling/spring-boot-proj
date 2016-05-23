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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "SYS_MODULE")
public class SysModule implements Serializable {

	private static final long serialVersionUID = -4522847116706518783L;
	
	@Id
	@GeneratedValue(generator = "generator")  
	@GenericGenerator(name = "generator", strategy = "uuid.hex")  
	@Column(name = "MODULE_ID", unique = true, nullable = false, length = 36)  
	private String moduleId;// 模块ID
	
	private String moduleType;// 模块类型
	
	private String moduleName;// 模块名称
	
	private String moduleDesc;// 模块描述
	
	private String moduleUrl;//
	
	private Integer Level;// 菜单级别
	
	private Boolean leaf;// 是否叶子
	
	private Boolean enabled;// 是否可用
	
	private Integer priority;// 优先级
	
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE})
	@JoinColumn(name="MODULE_ID")
	private Collection<SysResource> sysResources;
	
	
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE})
	@JoinColumn(name="PARENT_ID")
	private Collection<SysModule> sysModules;
	
	
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getModuleType() {
		return moduleType;
	}
	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getModuleDesc() {
		return moduleDesc;
	}
	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}
	public String getModuleUrl() {
		return moduleUrl;
	}
	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}
	public Integer getLevel() {
		return Level;
	}
	public void setLevel(Integer level) {
		Level = level;
	}
	public Boolean getLeaf() {
		return leaf;
	}
	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Collection<SysResource> getSysResources() {
		return sysResources;
	}
	public void setSysResources(Collection<SysResource> sysResources) {
		this.sysResources = sysResources;
	}
	public Collection<SysModule> getSysModules() {
		return sysModules;
	}
	public void setSysModules(Collection<SysModule> sysModules) {
		this.sysModules = sysModules;
	}
	@Override
	public String toString() {
		return "SysModule [moduleId=" + moduleId + ", moduleType=" + moduleType + ", moduleName=" + moduleName
				+ ", moduleDesc=" + moduleDesc + ", moduleUrl=" + moduleUrl + ", Level=" + Level + ", leaf=" + leaf
				+ ", enabled=" + enabled + ", priority=" + priority + ", sysModules=" + sysModules + "]";
	}
	

	

}
