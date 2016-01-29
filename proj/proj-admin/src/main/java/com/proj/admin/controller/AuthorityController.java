package com.proj.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.proj.admin.domain.AuthorityRepository;

/**
 * 权限配置
 * @author 汤晓川
 *
 */

@Controller
public class AuthorityController {

	@Autowired
	private AuthorityRepository authorityRepository;
	
	
	@RequestMapping("authority/new")
	public String newAuthority(Model model){
		model.addAttribute("authority", new com.proj.admin.domain.Authority());
		return "authority/authorityform";
	}

	@RequestMapping(value = "authority", method = RequestMethod.POST)
	public String saveAuthority(com.proj.admin.domain.Authority authority){
		authorityRepository.save(authority);
		return "redirect:/authority/"+authority.getId();
	}
	
	@RequestMapping("authority/{id}")
	public String showAuthority(@PathVariable Long id, Model model){
		model.addAttribute("authority", authorityRepository.findOne(id));
		return "authority/authorityshow";
	}
	
	@RequestMapping(value = "/authoritys", method = RequestMethod.GET)
	public String listAuthority(Model model){
		model.addAttribute("authoritys",authorityRepository.findAll());
		return "authority/authoritys";
	}
	
	@RequestMapping("authority/edit/{id}")
	public String editAuthority(@PathVariable Long id, Model model){
		model.addAttribute("authority",authorityRepository.findOne(id));
		return "authority/authorityform";
	}
	
	@RequestMapping("authority/delete/{id}")
	public String deleteAuthority(@PathVariable Long id){
		authorityRepository.delete(id);
		return "redirect:/authoritys";
	}		
	
}
