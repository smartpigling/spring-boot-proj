package com.proj.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.proj.admin.domain.UserRepository;

/**
 * 系统用户配置
 * @author 汤晓川
 */

@Controller
public class UserController {

	/**
	*@PreAuthorize("hasAuthority('')")
	*/
	@Autowired
	private UserRepository userRepository;
	
	
	@RequestMapping("user/new")
	public String newUser(Model model){
		model.addAttribute("user", new com.proj.admin.domain.User());
		return "user/userform";
	}

	@RequestMapping(value = "user", method = RequestMethod.POST)
	public String saveUser(com.proj.admin.domain.User user){
		userRepository.save(user);
		return "redirect:/user/"+user.getId();
	}
	
	@RequestMapping("user/{id}")
	public String showUser(@PathVariable Long id, Model model){
		model.addAttribute("user", userRepository.findOne(id));
		return "user/usershow";
	}
	
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String listUser(Model model){
		model.addAttribute("users",userRepository.findAll());
		return "user/users";
	}
	
	@RequestMapping("user/edit/{id}")
	public String editUser(@PathVariable Long id, Model model){
		model.addAttribute("user",userRepository.findOne(id));
		return "user/userform";
	}
	
	@RequestMapping("user/delete/{id}")
	public String deleteUser(@PathVariable Long id){
		userRepository.delete(id);
		return "redirect:/users";
	}	
}
