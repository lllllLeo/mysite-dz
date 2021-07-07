package com.douzone.mysite.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestBookVO;

@RestController("guestbookControllerApi")
@RequestMapping("/guestbook/api")
public class GuestbookController {
	
	/*
	 * 1.add 2.delete 3.list -> 0이면 findAll < findAllByNo(ajax
	 *   POST  DELETE   GET
	 * */
	
	@Autowired
	private GuestbookService guestbookService; 
	
	
	@RequestMapping("/add")
	public JsonResult add(@RequestBody GuestBookVO vo) {
		System.out.println(vo);
		guestbookService.addMessage(vo); 
		System.out.println(vo); // no 적힘
		return JsonResult.success(vo);
	}
	@RequestMapping("/list")
	public JsonResult list(@RequestParam Long no) {
		System.out.println("no는 " +no);
		List<GuestBookVO> list = new ArrayList<>(); 
		list = guestbookService.getMessageList(no);
		System.out.println(list);
		return JsonResult.success(list);
	}
	
	@RequestMapping("/delete/{no}")
	public JsonResult delete(
			@PathVariable("no") Long no, 
			@RequestParam String password ) {
		System.out.println("delete  no는  " +no + "password는 " +password);
		Boolean result;
		String r;
		result = guestbookService.deleteMessage(no, password);
		if(result) {
			r = no.toString();
		} else {
			r = "false";
		}
		System.out.println("d??  " + r);
		return JsonResult.success(r);		
	}
	
	

}
