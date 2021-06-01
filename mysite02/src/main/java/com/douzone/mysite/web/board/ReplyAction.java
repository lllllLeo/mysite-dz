package com.douzone.mysite.web.board;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class ReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long boardNo = Long.parseLong(request.getParameter("no"));
		int groupNo = Integer.parseInt(request.getParameter("group_no"));
		int orderNo = Integer.parseInt(request.getParameter("order_no"));
		int depth = Integer.parseInt(request.getParameter("depth"));
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("boardNo", boardNo);
		map.put("groupNo", groupNo);
		map.put("orderNo", orderNo);
		map.put("depth", depth);
		
		request.setAttribute("status", "reply");
		request.setAttribute("map", map);
		MvcUtils.forward("board/write", request, response);
	}
}
