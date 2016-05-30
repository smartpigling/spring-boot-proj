package com.proj.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.proj.admin.domain.SysUser;
import com.proj.admin.service.SysUserService;
import com.proj.admin.util.PageWrapper;
import com.proj.admin.util.StringUtils;

/**
 * 系统用户配置
 * 
 * @author Tang Xiaochuan
 *
 *
 * http://jishiweili.iteye.com/blog/2095536
 */

@Controller
public class SysUserController {
	
	private static final Logger logger =LoggerFactory.getLogger(SysUserController.class);
	
	
    // 从 application.properties 中读取配置，如取不到默认值为test
    @Value("${application.conf:test}")
    private String conf = "test";	
    
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
	//@PreAuthorize("hasAuthority('XX_VIEW')")
	public String listUser(@PageableDefault(sort={"createdTime"},direction = Direction.ASC) Pageable pageable,
			@RequestParam Map<String, Object> searchTerm,
			Model model){
        PageWrapper<SysUser> page = new PageWrapper<SysUser>(
        		sysUserService.findSysUsers(searchTerm, pageable),"/users");
        
		model.addAttribute("page", page);
		model.addAttribute("searchTerm", searchTerm);
		return "user/users";
	}
	
	@RequestMapping("user/edit/{userId}")
	public String editUser(@PathVariable String userId, Model model){
		model.addAttribute("user",sysUserService.getUserByUserId(userId));
		return "user/userform";
	}
	
	@RequestMapping(value = "user/delete/{userId}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable String userId){
		sysUserService.delUserByUserId(userId);;
		return "redirect:/users";
	}	
	
	@RequestMapping(value = "user/delete", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> deleteUsers(@RequestParam String ids){
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			String[] userIds = StringUtils.convertStrToArray(ids,",");
			List<SysUser> users= new ArrayList<SysUser>();
			for(String userId : userIds){
				SysUser u =new SysUser();
				u.setUserId(userId);
				users.add(u);
			}
			sysUserService.delUsers(users);
			result.put("result", "success");
		} catch (Exception e) {
			result.put("result", "failure");
			logger.error(String.format("delete user [%s] failure message:%s!", ids, e.getMessage()));
		}
		return result;
	}	
}
