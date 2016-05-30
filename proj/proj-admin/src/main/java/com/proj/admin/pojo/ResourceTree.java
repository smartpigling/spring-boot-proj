package com.proj.admin.pojo;

import java.util.Collection;

public class ResourceTree {
	
	private String resourceName;// 资源名称
	
	private String resourceDesc;// 资源描述
	
	private String resourcePath;// 资源路径
	
	private Collection<ResourceTree> resourceTrees;

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

	public Collection<ResourceTree> getResourceTrees() {
		return resourceTrees;
	}

	public void setResourceTrees(Collection<ResourceTree> resourceTrees) {
		this.resourceTrees = resourceTrees;
	}

	@Override
	public String toString() {
		return "[resourceName=" + resourceName + ", resourceDesc=" + resourceDesc + ", resourcePath="
				+ resourcePath + ", resourceTrees=" + resourceTrees + "]";
	}
	
	
}
