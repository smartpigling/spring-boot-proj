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
@Table(name = "users")
public class User {

//	  @Id
//    @GeneratedValue(generator = "generator")  
//    @GenericGenerator(name = "generator", strategy = "uuid.hex")  
//    @Column(name = "id", unique = true, nullable = false, length = 36)  	
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
    
    @NotNull
	private String username;
    
    @NotNull
	private String password;
    
    @NotNull
	private Boolean enabled;
    
	private String name;
	
	private String email;
	
	private Boolean accountExpired;
	
	private Boolean accountLocked;
	
//	@Temporal(TemporalType.TIMESTAMP)
	

	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE},
			fetch=FetchType.EAGER)
	@JoinTable(name = "users_roles", 
		joinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"))
	private Set<Role> roles;	
	
	
    @ManyToMany(mappedBy = "users",
    		cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Group> groups;
	
	

	public User(){}
	

	public User(Long id, String username, String password, Boolean enabled, String name, String email,
			Boolean accountExpired, Boolean accountLocked) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.name = name;
		this.email = email;
		this.accountExpired = accountExpired;
		this.accountLocked = accountLocked;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public Boolean getAccountExpired() {
		return accountExpired;
	}


	public void setAccountExpired(Boolean accountExpired) {
		this.accountExpired = accountExpired;
	}


	public Boolean getAccountLocked() {
		return accountLocked;
	}


	public void setAccountLocked(Boolean accountLocked) {
		this.accountLocked = accountLocked;
	}


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	public Set<Group> getGroups() {
		return groups;
	}


	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled
				+ ", name=" + name + ", email=" + email + ", accountExpired=" + accountExpired + ", accountLocked="
				+ accountLocked + "]";
	}
	
}
