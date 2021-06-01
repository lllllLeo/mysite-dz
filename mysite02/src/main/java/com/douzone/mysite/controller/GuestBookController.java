package com.douzone.mysite.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.web.guestbook.GuestbookActionFactory;
import com.douzone.web.Action;

public class GuestBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GuestBookController() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // Exception을 tomcat한테 던짐
//		request.setCharacterEncoding("UTF-8");
		String actionName = request.getParameter("a");
		Action action = new GuestbookActionFactory().getAction(actionName);
		action.execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
