package com.proj.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.proj.admin.domain.SysUser;
import com.proj.admin.service.SysUserService;
import com.proj.admin.util.PageWrapper;

/**
 * 系统用户配置
 * 
 * @author Tang Xiaochuan
 *
 */

@Controller
public class SysUserController {
	
	private static final Logger logger =LoggerFactory.getLogger(SysUserController.class);
	/**
	*@PreAuthorize("hasAuthority('')")
	*/
	@Autowired
	private SysUserService sysUserService;
	
	
	@RequestMapping("user/new")
	public String newUser(Model model){
		model.addAttribute("user", new SysUser());
		return "user/userform";
	}

	@RequestMapping(value = "user", method = RequestMethod.POST)
	public String saveUser(SysUser user){
		sysUserService.saveUser(user);
		return "redirect:/user/"+user.getUserId();
	}
	
	@RequestMapping("user/{userId}")
	public String showUser(@PathVariable String userId, Model model){
		model.addAttribute("user", sysUserService.getUserByUserId(userId));
		return "user/usershow";
	}
	
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String listUser(Pageable pageable,
			@RequestParam(value="username", required=false, defaultValue="") String username,Model model){
		
		logger.info("username:"+username);
		
        PageWrapper<SysUser> page = new PageWrapper<SysUser>(sysUserService.findSysUsersByUsername(
        		username,pageable),"/users");
		
		
//		Page<SysUser>  page= sysUserService.findSysUsersByUsername(username,
//				new PageRequest(pageable.getPageNumber()<= 0 ? 0 : pageable.getPageNumber()-1,
//						pageable.getPageSize(),new Sort(Sort.Direction.ASC,"username")));
        
		model.addAttribute("page",page);
		model.addAttribute("username",username);
		return "user/users";
	}
	
	@RequestMapping("user/edit/{userId}")
	public String editUser(@PathVariable String userId, Model model){
		model.addAttribute("user",sysUserService.getUserByUserId(userId));
		return "user/userform";
	}
	
	@RequestMapping("user/delete/{userId}")
	public String deleteUser(@PathVariable String userId){
		sysUserService.delUserByUserId(userId);;
		return "redirect:/users";
	}	
}
