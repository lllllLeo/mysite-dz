package com.douzone.mysite.web.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVO;
import com.douzone.mysite.vo.UserVO;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		MvcUtils.forward("board/list", request, response);
		HttpSession session = request.getSession();
		UserVO authUser = (UserVO) session.getAttribute("authUser");
		String status = request.getParameter("status");
	
		
		BoardVO vo = new BoardVO();
		vo.setUserNo(authUser.getNo());
		vo.setTitle(request.getParameter("title"));
		vo.setContents(request.getParameter("content"));
		if ("reply".equals(status)) {
			vo.setGroupNo(Integer.parseInt(request.getParameter("group_no")));
			vo.setOrderNo(Integer.parseInt(request.getParameter("order_no")) + 1);
			vo.setDepth(Integer.parseInt(request.getParameter("depth")));
			new BoardRepository().replyUpdate(vo);
			vo.setOrderNo(Integer.parseInt(request.getParameter("order_no")) + 1);
			new BoardRepository().replyInsert(vo);
		} else {
			System.out.println("일반");
			new BoardRepository().insert(vo);
		}
		
		
		MvcUtils.redirect("board?page=1", request, response);
		
	}

}
