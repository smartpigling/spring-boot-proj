package com.proj.admin.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface UserRepository extends JpaRepository<User,Long>{

//	@Query(value = "SELECT a.id,a.name,a.resource from users_roles ur "
//			+ "JOIN users u ON ur.users_id = u.id "
//			+ "JOIN roles r ON ur.roles_id = r.id "
//			+ "JOIN roles_authorities ra on ra.roles_id = r.id "
//			+ "JOIN authorities a on ra.authorities_id = a.id "
//			+ "WHERE u.username=?1", nativeQuery = true)
	@Query(value = " select a.id, a.name, a.resource  from User u join u.roles r join r.authorities a where u.username=?1")
	List<Object[]> findAuthorityByUsername(String username);
	
	
	Page<User> findByUsernameContainingIgnoreCase(String username, Pageable pageable);
	
	User findByUsername(String username);
}
