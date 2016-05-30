package com.proj.admin.pojo;


public class ResAuthMapping {

	private String resourcePath;

	private String authorityMark;

	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	public String getAuthorityMark() {
		return authorityMark;
	}

	public void setAuthorityMark(String authorityMark) {
		this.authorityMark = authorityMark;
	}

	@Override
	public String toString() {
		return "ResAuthMapping [resourcePath=" + resourcePath + ", authorityMark=" + authorityMark + "]";
	}

	
	
}
