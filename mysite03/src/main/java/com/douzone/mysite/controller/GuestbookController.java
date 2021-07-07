package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestBookVO;

//@Auth
@Controller
@RequestMapping("/guestbook")
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping("")
	public String index(Model model) {
		List<GuestBookVO> list = guestbookService.getMessageList();
		System.out.println(list);
		model.addAttribute("list", list);
		return "guestbook/index";
	}
	
	@RequestMapping("/add")
	public String add(GuestBookVO vo) {
		guestbookService.addMessage(vo);
		return "redirect:/guestbook";
	}
	
	@RequestMapping(value = "/delete/{no}", method= RequestMethod.GET)
	public String delete(@PathVariable Long no, Model model) {
		model.addAttribute("no", no);
		return "guestbook/deleteform";
	}

	@RequestMapping(value = "/delete/{no}", method= RequestMethod.POST)
	public String delete(@PathVariable Long no, String password) {
		guestbookService.deleteMessage(no, password);
		return "redirect:/guestbook";
	}
	
	@RequestMapping("/spa")
	public String spaLanding() {
		return "guestbook/spa-landing";
	}

	
//	Controller로 Exception이 들어오면 여기로 모임
	@ExceptionHandler(Exception.class)
	public String handlerException() {
//		1. logging
		return "error/exception"; // 2. ㅈㅅㅈㅅ
	}
	
	
}
