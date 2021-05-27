package com.douzone.mysite.mvc.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.Action;
import com.douzone.mvc.util.MvcUtils;
import com.douzone.mysite.repository.GuestBookRepository;
import com.douzone.mysite.vo.GuestBookVO;

public class AddAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password= request.getParameter("password");
		String message = request.getParameter("message");
		
		GuestBookVO vo = new GuestBookVO();
		vo.setName(name);
		vo.setPassword(password);
		vo.setMessage(message);
		
		new GuestBookRepository().insert(vo);
		MvcUtils.redirect(request.getContextPath() + "/guestbook?a=list", request, response);
	}
	
}
