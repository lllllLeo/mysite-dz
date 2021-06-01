package com.douzone.mysite.web.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.GuestBookRepository;
import com.douzone.mysite.vo.GuestBookVO;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long no = Long.parseLong(request.getParameter("no"));
		String password= request.getParameter("password");
		
		GuestBookVO vo = new GuestBookVO();
		vo.setNo(no);
		vo.setPassword(password);
		new GuestBookRepository().delete(vo);
		MvcUtils.redirect(request.getContextPath() + "/guestbook?a=list", request, response);
	}
	
}
