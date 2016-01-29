package com.proj.admin.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="groups")
public class Group {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
    
    @NotNull
    @Max(100)
	private String name;
    
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @org.hibernate.annotations.CreationTimestamp
    private Date createdDate;
    

	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE},
		fetch=FetchType.EAGER)
	@JoinTable(name = "users_groups", 
		joinColumns = @JoinColumn(name = "groups_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"))    
    private Set<User> users;
    
    
	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE},
			fetch=FetchType.EAGER)
	@JoinTable(name = "groups_roles", 
		joinColumns = @JoinColumn(name = "groups_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"))
	private Set<Role> roles;	    
    
    
    public Group(){}
    

	public Group(Long id, String name) {
		super();
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

	
	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public Set<User> getUsers() {
		return users;
	}


	public void setUsers(Set<User> users) {
		this.users = users;
	}


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
    
    
}
