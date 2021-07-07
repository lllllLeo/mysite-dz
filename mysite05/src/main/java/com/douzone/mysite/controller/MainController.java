package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVO;
import com.douzone.mysite.vo.UserVO;

@Controller
public class MainController {
	
	@Autowired
	private SiteService siteService; 
	
	@RequestMapping("")
	public String index(Model model) {
		SiteVO vo = siteService.getMain();
		model.addAttribute("main", vo);
		return "main/index";
	}
	@ResponseBody
	@RequestMapping("/msg")
	public UserVO msg(@AuthUser UserVO vo) {
		return vo;
	}
	
}