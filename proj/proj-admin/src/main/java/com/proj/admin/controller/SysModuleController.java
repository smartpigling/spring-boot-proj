package com.proj.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.proj.admin.domain.SysModule;
import com.proj.admin.domain.SysModuleRepository;
import com.proj.admin.domain.SysResource;

public class SysModuleController {

//	@Autowired
//	private SysModulesRepository sysModulesRepository;
//	
//	@RequestMapping("group/new")
//	public String newGroup(Model model){
//		model.addAttribute("group", new SysResources());
//		return "group/groupform";
//	}
//
//	@RequestMapping(value = "group", method = RequestMethod.POST)
//	public String saveGroup(SysModules group){
//		sysModulesRepository.save(group);
//		return "redirect:/group/"+group.getModuleId();
//	}
//	
//	@RequestMapping("group/{id}")
//	public String showGroup(@PathVariable String id, Model model){
//		model.addAttribute("group", sysModulesRepository.findOne(id));
//		return "group/groupshow";
//	}
//	
//	@RequestMapping(value = "/groups", method = RequestMethod.GET)
//	public String listGroup(Model model){
//		model.addAttribute("groups",sysModulesRepository.findAll());
//		return "group/groups";
//	}
//	
//	@RequestMapping("group/edit/{id}")
//	public String editGroup(@PathVariable String id, Model model){
//		model.addAttribute("group",sysModulesRepository.findOne(id));
//		return "group/groupform";
//	}
//	
//	@RequestMapping("group/delete/{id}")
//	public String deleteGroup(@PathVariable String id){
//		sysModulesRepository.delete(id);
//		return "redirect:/groups";
//	}		
//		
}
