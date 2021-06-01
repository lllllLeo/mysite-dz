package com.douzone.mysite.web.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVO;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
//		접근제어(인증이 필요한 접근에 대한 체크)
		// 없어도됨.
		if(session == null) {
			MvcUtils.redirect(request.getContextPath(), request, response);
			return;
		}
		
		UserVO authUser = (UserVO) session.getAttribute("authUser");
		
		if(authUser == null) {
			MvcUtils.redirect(request.getContextPath(), request, response);
			return;
		}
		
		Long no = authUser.getNo();
		String name = request.getParameter("name");
		String password= request.getParameter("password");
		String gender = request.getParameter("gender");
		
		new UserRepository().updateByNo(no, name, password, gender);
		
		System.out.println("ㅎㅇ");
		
//		인증처리(session 처리)
		
		MvcUtils.redirect(request.getContextPath(), request, response);

	}

}
