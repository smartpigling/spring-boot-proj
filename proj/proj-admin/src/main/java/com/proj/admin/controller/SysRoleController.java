package com.proj.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.proj.admin.domain.SysRole;
import com.proj.admin.domain.SysRoleRepository;

/**
 * 系统角色配置
 * 
 * @author Tang Xiaochuan
 *
 */

@Controller
public class SysRoleController {

	@Autowired
	private SysRoleRepository sysRoleRepository;
	
	@RequestMapping("role/new")
	public String newRole(Model model){
		model.addAttribute("role", new SysRole());
		return "role/roleform";
	}

	@RequestMapping(value = "role", method = RequestMethod.POST)
	public String saveRole(SysRole role){
		sysRoleRepository.save(role);
		return "redirect:/role/"+role.getRoleId();
	}
	
	@RequestMapping("role/{id}")
	public String showRole(@PathVariable String id, Model model){
		model.addAttribute("role", sysRoleRepository.findOne(id));
		return "role/roleshow";
	}
	
	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public String listRole(Model model){
		model.addAttribute("roles",sysRoleRepository.findAll());
		return "role/roles";
	}
	
	@RequestMapping("role/edit/{id}")
	public String editRole(@PathVariable String id, Model model){
		model.addAttribute("role",sysRoleRepository.findOne(id));
		return "role/roleform";
	}
	
	@RequestMapping("role/delete/{id}")
	public String deleteRole(@PathVariable String id){
		sysRoleRepository.delete(id);
		return "redirect:/roles";
	}		
	
}
