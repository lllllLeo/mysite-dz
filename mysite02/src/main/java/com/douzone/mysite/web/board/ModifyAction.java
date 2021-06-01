package com.douzone.mysite.web.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVO;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String boardNo = (String) request.getParameter("no");
		String title = (String) request.getParameter("title");
		String content = (String) request.getParameter("content");
		
		BoardVO vo = new BoardVO();
		vo.setNo(Long.parseLong(boardNo));
		vo.setTitle(title);
		vo.setContents(content);
		
		new BoardRepository().updateBoardByBoardNo(vo);
		
		MvcUtils.redirect("board?a=view&no=" + boardNo, request, response);
	}

}
