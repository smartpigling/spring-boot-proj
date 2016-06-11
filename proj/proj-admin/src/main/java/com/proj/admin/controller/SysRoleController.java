package com.proj.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proj.admin.domain.SysRole;
import com.proj.admin.service.SysRoleService;
import com.proj.admin.util.PageWrapper;
import com.proj.admin.util.StringUtils;

/**
 * 系统角色配置
 * 
 * @author Tang Xiaochuan
 *
 */

@Controller
public class SysRoleController {
	
	private static final Logger logger = LoggerFactory.getLogger(SysRoleController.class);

	@Autowired
	private SysRoleService sysRoleService;
	
	@RequestMapping("role/new")
	public String newRole(Model model){
		model.addAttribute("role", new SysRole());
		return "role/roleform";
	}

	@RequestMapping(value = "role", method = RequestMethod.POST)
	public String saveRole(SysRole role){
		SysRole entity = sysRoleService.saveRole(role);
		return "redirect:/role/"+entity.getRoleId();
	}
	
	@RequestMapping("role/{roleId}")
	public String showRole(@PathVariable String roleId, Model model){
		model.addAttribute("role", sysRoleService.getRoleByRoleId(roleId));
		return "role/roleshow";
	}
	
	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public String listRole(@PageableDefault(sort={"roleName"},direction = Direction.ASC) Pageable pageable,
			@RequestParam Map<String, Object> searchTerm,
			Model model){
        PageWrapper<SysRole> page = new PageWrapper<SysRole>(
        		sysRoleService.findSysRoles(searchTerm, pageable),"/roles");
        
		model.addAttribute("page", page);
		model.addAttribute("searchTerm", searchTerm);
		return "role/roles";
	}
	
	@RequestMapping("role/edit/{roleId}")
	public String editRole(@PathVariable String roleId, Model model){
		model.addAttribute("role",sysRoleService.getRoleByRoleId(roleId));
		return "role/roleform";
	}
	
	@RequestMapping("role/delete/{roleId}")
	public String deleteRole(@PathVariable String roleId){
		sysRoleService.delRoleByRoleId(roleId);
		return "redirect:/roles";
	}
	
	@RequestMapping(value = "role/delete", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> deleteRoles(@RequestParam String ids){
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			String[] roleIds = StringUtils.convertStrToArray(ids,",");
			List<SysRole> roles= new ArrayList<SysRole>();
			for(String roleId : roleIds){
				SysRole r =new SysRole();
				r.setRoleId(roleId);
				roles.add(r);
			}
			sysRoleService.delRoles(roles);
			result.put("result", "success");
		} catch (Exception e) {
			result.put("result", "failure");
			logger.error(String.format("delete role [%s] failure message:%s!", ids, e.getMessage()));
		}
		return result;
	}	
	
}
