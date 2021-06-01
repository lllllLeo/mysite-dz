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

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password= request.getParameter("password");
		
		UserVO vo = new UserRepository().findByEmailAndPassword(email, password);
		if(vo == null) {
			request.setAttribute("result", "fail");  // new Boolean(false)와 같은 객체를 넣어야하는데알아서 AutoBoxing해줌
			request.setAttribute("email", email);  // new Boolean(false)와 같은 객체를 넣어야하는데알아서 AutoBoxing해줌
			
			MvcUtils.forward("user/loginform", request, response);
			return;
		}
		
//		인증처리(session 처리)
		HttpSession session = request.getSession(true); // true 인자 넣으면 없으면 만들어서 줘 / false면 null  / login처리할때 true하는게 좋다
		session.setAttribute("authUser", vo);
		
		MvcUtils.redirect(request.getContextPath(), request, response);

	}

}
