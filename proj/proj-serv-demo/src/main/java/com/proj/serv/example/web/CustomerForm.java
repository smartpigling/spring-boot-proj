package com.proj.serv.example.web;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CustomerForm {

	@Size(min=4, max=30)
	private String name;
	
	@NotNull
	@Min(18)
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "CustomerForm [name=" + name + ", age=" + age + "]";
	}
	
	
}
