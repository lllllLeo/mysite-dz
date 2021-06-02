package com.douzone.mysite.web.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.net.openssl.OpenSSLUtil;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class ListAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		totalPage -> ceil(전체글 /3) ceil하면 +1안해도되너, firstPageNo, lastPageNo, nextPageNo = -1보다크면 11?, prevPageNo, currentPage (css먹이고, a링크빼고)     => Map에 넣고 setAttribute
		int page = Integer.parseInt(request.getParameter("page"));
		List<Map<String, Object>> result = new ArrayList();
		Map<String, Object> map = new HashMap<String, Object>();
		
		int totalCount = new BoardRepository().getBoardCount();   							// 글 전체 개수
		int countBoard= 10;										  							// 글 10개 뽑음
		int countPage = 5;										  							// 페이지 5개만 보이게
		int totalPage = (int) Math.ceil(totalCount / countBoard); 							// 전체 페이지 개수
		int startCount = (page - 1) * countBoard;  				  							// 쿼리 limit에서 사용할 startCount  
		int endCount = page * countBoard;   					  							// 쿼리 limit에서 사용할 endCount      ex) limit startCount, endCount
		int firstPageNo = (((int) ((double) page / 10 + 0.9)) - 1) * countBoard + 1;		// 첫 페이지 번호
		int lastPageNo = totalPage;															// 마지막 페이지 번호
		int currentPageNo = page; 															// 현재 페이지
		if (lastPageNo > firstPageNo + 10 - 1) { 											// 마지막 페이지 번호를 설정해줌 
			lastPageNo = firstPageNo + 10 - 1;
		}
		
		result = new BoardRepository().getAllBoard(startCount, countBoard);
		
		map.put("totalCount", totalCount);
		map.put("totalPage", totalPage);
		map.put("startCount", startCount);
		map.put("endCount", endCount);
		map.put("firstPageNo", firstPageNo);
		map.put("lastPageNo", lastPageNo);
		map.put("currentPageNo", currentPageNo);
		map.put("countPage", countPage);
		map.put("countBoard", countBoard);
		map.put("currentPageNo", currentPageNo);
		map.put("page", page);
		
		request.setAttribute("list", result);
		request.setAttribute("map", map);
		
		MvcUtils.forward("board/list", request, response);
	}

}