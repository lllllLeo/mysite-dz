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

public class UpdateFormAction implements Action {

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
		
		
		Long userNo = authUser.getNo();
		String email = new UserRepository().findEmailByNo(userNo);  //번호로 가져와서 updateForm에 뿌리기
		System.out.println(email);
		request.setAttribute("email", email);
		MvcUtils.forward("/user/updateform", request, response);
		
	}

}
