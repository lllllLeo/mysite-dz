package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.Auth.Role;
import com.douzone.mysite.service.FileUploadService;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVO;


@Auth(role=Role.ADMIN)
@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private SiteService siteService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping("")
	public String main(SiteVO vo, Model model) {
		vo = siteService.getMain();
		System.out.println(vo);
		model.addAttribute("main", vo);
		return "admin/main";
	}
	
	@RequestMapping(value="/main/update", method = RequestMethod.POST)
	public String updateMain(SiteVO vo, @RequestParam("file1") MultipartFile file1) {
		String url = fileUploadService.restore(file1);
		vo.setProfile(url);
		
		siteService.updateMain(vo);
		return "redirect:/admin";
	}
	
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}

	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}

	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}
}
