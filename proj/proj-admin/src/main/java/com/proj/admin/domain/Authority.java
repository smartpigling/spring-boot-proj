package com.proj.admin.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="authorities")
public class Authority {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
    
    @NotNull
	private String name;
    
    @NotNull
    private String resource;
    
    
    @ManyToMany(mappedBy = "authorities",
    		cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Role> roles; 
    
    
    public Authority(){}

	public Authority(Long id, String name, String resource) {
		super();
		this.id = id;
		this.name = name;
		this.resource = resource;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
    
}
