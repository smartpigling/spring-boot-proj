package com.proj.admin.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.proj.admin.domain.SysAuthority;
import com.proj.admin.domain.SysUser;
import com.proj.admin.domain.SysUserRepository;
import com.proj.admin.service.SysUserService;


@Service("SysUserService")
public class SysUserServiceImpl implements SysUserService{
	
	private static final Logger logger =LoggerFactory.getLogger(SysUserServiceImpl.class);
	
	private final SysUserRepository sysUserRepository;
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	public SysUserServiceImpl(SysUserRepository sysUserRepository){
		this.sysUserRepository = sysUserRepository;
	}
	
	
	/**
	 * 根据用户名获取到用户的权限并封装成GrantedAuthority集合
	 * @param username
	 */
	public Collection<GrantedAuthority> loadUserAuthorities(String username){
		List<SysAuthority> list = sysUserRepository.findSysAuthoritiesByUsername(username);
		
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		
		for (SysAuthority authority : list) {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getAuthorityMark());
			auths.add(grantedAuthority);
		}

		return auths;
		
	}

	
	@Override
	public SysUser getUserByUsername(String username) {
		return sysUserRepository.getByUsername(username);
	}
	
	@Override
	public Page<SysUser> findSysUsersByUsername(String username, Pageable pageable) {
		return sysUserRepository.findByUsernameContainingIgnoreCase(username, pageable);
	}


	@Override
	public void saveUser(SysUser user) {
		sysUserRepository.save(user);
	}


	@Override
	public SysUser getUserByUserId(String userId) {
		return sysUserRepository.getOne(userId);
	}


	@Override
	public void delUserByUserId(String userId) {
		sysUserRepository.delete(userId);
	}


	@Override
	@Transactional
	public void delUsers(List<SysUser> users) {
		sysUserRepository.delete(users);
	}
	
	/**
	 * 自定义查询
	 * @param pageable
	 * @param params
	 * @return
	 */
	public Page<SysUser> findCustomSearch(Pageable pageable, String username) {
		StringBuffer qlString = new StringBuffer();
		qlString.append(" select u from SysUser u where 1=1 ");
		if(!StringUtils.isEmpty(username)){
			qlString.append(" u.username = '").append(username).append("'");
		}
		TypedQuery<SysUser> query = em.createQuery(qlString.toString(), SysUser.class);
		query.setFirstResult(pageable.getOffset());
		query.setMaxResults(pageable.getPageSize());
		
		return new PageImpl<SysUser>(query.getResultList());
	}
}
