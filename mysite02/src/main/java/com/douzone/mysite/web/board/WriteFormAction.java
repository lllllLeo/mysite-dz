package com.douzone.mysite.web.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class WriteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
	
		if(session.getAttribute("authUser") == null) {
			MvcUtils.redirect("user?a=loginform", request, response);
			return;
		}
		
		MvcUtils.forward("board/write", request, response);
	}
}
