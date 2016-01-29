package com.proj.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.proj.admin.domain.RoleRepository;

/**
 * 角色配置
 * @author 汤晓川
 *
 */

@Controller
public class RoleController {

	@Autowired
	private RoleRepository roleRepository;
	
	@RequestMapping("role/new")
	public String newRole(Model model){
		model.addAttribute("role", new com.proj.admin.domain.Role());
		return "role/roleform";
	}

	@RequestMapping(value = "role", method = RequestMethod.POST)
	public String saveRole(com.proj.admin.domain.Role role){
		roleRepository.save(role);
		return "redirect:/role/"+role.getId();
	}
	
	@RequestMapping("role/{id}")
	public String showRole(@PathVariable Long id, Model model){
		model.addAttribute("role", roleRepository.findOne(id));
		return "role/roleshow";
	}
	
	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public String listRole(Model model){
		model.addAttribute("roles",roleRepository.findAll());
		return "role/roles";
	}
	
	@RequestMapping("role/edit/{id}")
	public String editRole(@PathVariable Long id, Model model){
		model.addAttribute("role",roleRepository.findOne(id));
		return "role/roleform";
	}
	
	@RequestMapping("role/delete/{id}")
	public String deleteRole(@PathVariable Long id){
		roleRepository.delete(id);
		return "redirect:/roles";
	}		
	
}
