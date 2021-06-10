package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVO;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(UserVO vo) {
		return "user/join";
	}
	
	@RequestMapping(value = "/joinsuccess", method = RequestMethod.POST)
	public String joinsuccess(UserVO vo) {
		return "user/joinsuccess";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}
		
	@Auth
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(@AuthUser UserVO authUser, Model model) {
		Long no = authUser.getNo();
		UserVO vo = userService.getUser(no);
		model.addAttribute("user", vo);
		return "user/update";
	}
	
	@Auth
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@AuthUser UserVO authUser, UserVO vo) {
		vo.setNo(authUser.getNo());
		userService.updateUser(vo);
		authUser.setName(vo.getName());
		return "redirect:/user/update";
	}
}
