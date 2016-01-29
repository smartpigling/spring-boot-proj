package com.proj.admin.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="roles")
public class Role {
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
    
    @NotNull
	private String name;
    
    @ManyToMany(mappedBy = "roles",
    		cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<User> users;
    
    
    
    @ManyToMany(mappedBy = "roles",
    		cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Group> groups;    
    
    
    
	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE},
			fetch=FetchType.EAGER)
	@JoinTable(name = "roles_authorities", 
		joinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "authorities_id", referencedColumnName = "id"))
	private Set<Authority> authorities;	    
    
    
    public Role(){}

	public Role(Long id, String name) {
		this.id = id;
		this.name = name;
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

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}
    
    	

}
