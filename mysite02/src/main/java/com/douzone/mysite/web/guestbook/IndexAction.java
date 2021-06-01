package com.douzone.mysite.web.guestbook;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.GuestBookRepository;
import com.douzone.mysite.vo.GuestBookVO;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class IndexAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<GuestBookVO> list = new GuestBookRepository().findAll();
		request.setAttribute("list", list); 
		MvcUtils.forward("guestbook/list", request, response);
	}
	
}
