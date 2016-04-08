package com.proj.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.proj.admin.domain.SysResourceRepository;

/**
 * 系统资源配置
 * 
 * @author Tang Xiaochuan
 *
 */

@Controller
public class SysResourceController {

//	@Autowired
//	private SysResourceRepository sysResourceRepository;
//	
//	
//	@RequestMapping("group/new")
//	public String newGroup(Model model){
//		model.addAttribute("group", new com.proj.admin.domain.Group());
//		return "group/groupform";
//	}
//
//	@RequestMapping(value = "group", method = RequestMethod.POST)
//	public String saveGroup(com.proj.admin.domain.Group group){
//		groupRepository.save(group);
//		return "redirect:/group/"+group.getId();
//	}
//	
//	@RequestMapping("group/{id}")
//	public String showGroup(@PathVariable Long id, Model model){
//		model.addAttribute("group", groupRepository.findOne(id));
//		return "group/groupshow";
//	}
//	
//	@RequestMapping(value = "/groups", method = RequestMethod.GET)
//	public String listGroup(Model model){
//		model.addAttribute("groups",groupRepository.findAll());
//		return "group/groups";
//	}
//	
//	@RequestMapping("group/edit/{id}")
//	public String editGroup(@PathVariable Long id, Model model){
//		model.addAttribute("group",groupRepository.findOne(id));
//		return "group/groupform";
//	}
//	
//	@RequestMapping("group/delete/{id}")
//	public String deleteGroup(@PathVariable Long id){
//		groupRepository.delete(id);
//		return "redirect:/groups";
//	}		
	
}
