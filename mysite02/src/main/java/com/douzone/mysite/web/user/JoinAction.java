package com.douzone.mysite.web.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVO;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class JoinAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		String name = request.getParameter("name");
		String email= request.getParameter("email");
		String password= request.getParameter("password");
		String gender = request.getParameter("gender");
		
		UserVO vo = new UserVO();
		vo.setName(name);
		vo.setEmail(email);
		vo.setPassword(password);
		vo.setGender(gender);
		
		new UserRepository().insert(vo);
		MvcUtils.redirect(request.getContextPath()+"/user?a=joinsuccess", request, response);
	}

}
