package com.douzone.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LogoutInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession(); // default가 false라서 비워놓아도 됨
		session.removeAttribute("authUser");
		session.invalidate();
		System.out.println("로그아웃 성공 in LogoutInterceptor");
		response.sendRedirect(request.getContextPath());
		return false;
	}

}

