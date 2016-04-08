package com.proj.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.proj.admin.domain.SysAuthority;
import com.proj.admin.domain.SysAuthorityRepository;

/**
 * 系统权限配置
 * 
 * @author Tang Xiaochuan
 *
 */

@Controller
public class SysAuthorityController {

	@Autowired
	private SysAuthorityRepository sysAuthorityRepository;
	
	
	@RequestMapping("authority/new")
	public String newAuthority(Model model){
		model.addAttribute("authority", new SysAuthority());
		return "authority/authorityform";
	}

	@RequestMapping(value = "authority", method = RequestMethod.POST)
	public String saveAuthority(SysAuthority authority){
		sysAuthorityRepository.save(authority);
		return "redirect:/authority/"+authority.getAuthorityId();
	}
	
	@RequestMapping("authority/{id}")
	public String showAuthority(@PathVariable String id, Model model){
		model.addAttribute("authority", sysAuthorityRepository.findOne(id));
		return "authority/authorityshow";
	}
	
	@RequestMapping(value = "/authoritys", method = RequestMethod.GET)
	public String listAuthority(Model model){
		model.addAttribute("authoritys",sysAuthorityRepository.findAll());
		return "authority/authoritys";
	}
	
	@RequestMapping("authority/edit/{id}")
	public String editAuthority(@PathVariable String id, Model model){
		model.addAttribute("authority",sysAuthorityRepository.findOne(id));
		return "authority/authorityform";
	}
	
	@RequestMapping("authority/delete/{id}")
	public String deleteAuthority(@PathVariable String id){
		sysAuthorityRepository.delete(id);
		return "redirect:/authoritys";
	}		
	
}
