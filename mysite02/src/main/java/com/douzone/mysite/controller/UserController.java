package com.douzone.mysite.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.Action;
import com.douzone.mysite.mvc.user.UserActionFactory;

public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserController() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String actionName = request.getParameter("a");
		
		Action action = new UserActionFactory().getAction(actionName);
		action.execute(request, response);
		
//		if("joinform".equals(action)) {
//			new JoinFormAction().execute(request, response);
//		} else if("joinsuccess".equals(action)) {
//			new JoinSuccessAction().execute(request, response);
//		} else if("join".equals(action)) {
//			new JoinAction().execute(request, response);
//		} else {
//			new MainAction().execute(request, response);
//		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
