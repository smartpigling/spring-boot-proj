package com.proj.admin.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

@Entity
@Table(name = "SYS_RESOURCE")
public class SysResource implements Serializable {

	public static Specification<SysResource> builderSearchWhereClause(final Map<String, Object> criteria){
		return new Specification<SysResource>(){
			@Override
			public Predicate toPredicate(Root<SysResource> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicate = new ArrayList<Predicate>();
            	if(!StringUtils.isEmpty(criteria.getOrDefault("resourceName",""))){
            		predicate.add(cb.like(root.get("resourceName").as(String.class), "%"+criteria.get("resourceName")+"%"));
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
	
	private static final long serialVersionUID = 3345916776753770292L;

	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Column(name = "RESOURCE_ID", unique = true, nullable = false, length = 36)
	private String resourceId;// 资源ID
	
	private String resourceType;// 资源类别 <目录\菜单\按扭>
	
	private String resourceName;// 资源名称
	
	private String resourceDesc;// 资源描述
	
	private String resourcePath;// 资源路径
	
	private Integer priority;// 优先级<菜单顺序>
	
	private Boolean enabled;// 是否可用
	
	private String parentId;//父类

	private Integer sysSpec;;//保留1:可见,0:不可见
	
	@ManyToMany
    @JoinTable(
            name="SYS_AUTHORITY_RESOURCE",
            joinColumns=@JoinColumn(name="RESOURCE_ID", referencedColumnName="RESOURCE_ID"),
            inverseJoinColumns=@JoinColumn(name="AUTHORITY_ID", referencedColumnName="AUTHORITY_ID"))	
    private Collection<SysAuthority> sysAuthorities;
	
	@ManyToOne
	@JoinColumn(name="MODULE_ID")
	private SysModule module;
	

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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
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

	public SysModule getModule() {
		return module;
	}

	public void setModule(SysModule module) {
		this.module = module;
	}

	@Override
	public String toString() {
		return "SysResource [resourceId=" + resourceId + ", resourceType=" + resourceType + ", resourceName="
				+ resourceName + ", resourceDesc=" + resourceDesc + ", resourcePath=" + resourcePath + ", priority="
				+ priority + ", enabled=" + enabled + ", parentId=" + parentId + ", sysSpec=" + sysSpec + "]";
	}


}
