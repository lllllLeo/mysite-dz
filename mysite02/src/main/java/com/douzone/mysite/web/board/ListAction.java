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
	private static final int SHOW_MAX_PAGE = 5;
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		totalPage -> ceil(전체글 /3) ceil하면 +1안해도되너, firstPageNo, lastPageNo, nextPageNo = -1보다크면 11?, prevPageNo, currentPage (css먹이고, a링크빼고)     => Map에 넣고 setAttribute
		int page = Integer.parseInt(request.getParameter("page"));
		List<Map<String, Object>> result = new ArrayList();
		Map<String, Object> map = new HashMap<String, Object>();
		
		
		
//		int totalCount = result.size();
//		int countPage = 10;
//		int totalPage = (int) Math.ceil(totalCount / countPage); // 전체글 개수 /10
//		int firstPageNo = (totalPage - 1) * SHOW_MAX_PAGE + 1;  // 1 6 11 16 21 (totalPage-1) * 5 + 1    , 5
//		int lastPageNo = (totalPage - 1) * SHOW_MAX_PAGE + 5;   // 5 10 15 20 25    (totalPage - 1) * 5 + 5   없으면 회색
//		int nextPageNo = lastPageNo + 1;   // 6 
//		int prevPageNo = firstPageNo - 1;   //  firstPageNo == 1 ? 
//		int currentPageNo = Integer.parseInt(page);  // 임시
		
		int totalCount = new BoardRepository().getBoardCount();
		int countBoard= 10;
		int countPage = 5;
		int totalPage = (int) Math.ceil(totalCount / countBoard); // 전체글 개수 /10
//		int startCount = (page - 1) * countBoard + 1;  // 1 6 11 16 21 (totalPage-1) * 5 + 1    , 5
		int startCount = (page - 1) * countBoard;  // 1 6 11 16 21 (totalPage-1) * 5 + 1    , 5
		int endCount = page * countBoard;   // 5 10 15 20 25    (totalPage - 1) * 5 + 5   없으면 회색
		int firstPageNo = (((int) ((double) page / 10 + 0.9)) - 1) * countBoard + 1;
		int lastPageNo = totalPage;
//		int nextPageNo = endCount + 1;   // 6 
//		int prevPageNo = startCount - 1;   //  firstPageNo == 1 ? 
		int currentPageNo = page; 
		if (lastPageNo > firstPageNo + 10 - 1) { // 마지막 페이지 번호가 
			lastPageNo = firstPageNo + 10 - 1;
		}
		result = new BoardRepository().getAllBoard(startCount, countBoard);
		
		map.put("totalCount", totalCount);
		map.put("totalPage", totalPage);
		map.put("startCount", startCount);
		map.put("endCount", endCount);
		map.put("firstPageNo", firstPageNo);
		map.put("lastPageNo", lastPageNo);
//		map.put("nextPageNo", nextPageNo);
//		map.put("prevPageNo", prevPageNo);
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