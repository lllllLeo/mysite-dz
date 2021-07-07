package com.douzone.mysite.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVO;
import com.douzone.mysite.vo.UserVO;

@Controller
@RequestMapping("/board")
public class BoardController {
	
    @Autowired
    private BoardService boardService;
	
    @RequestMapping(value = "/{pageNum}", method = RequestMethod.GET)
	public String index(@PathVariable("pageNum") String pageNum, Model model) {
		int page = Integer.parseInt(pageNum);
		List<Map<String, Object>> result = new ArrayList<>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		int totalCount = boardService.getBoardCount();   							// 글 전체 개수
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
		
		result = boardService.getAllBoard(startCount, countBoard);
		
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
		
		model.addAttribute("list", result);
		model.addAttribute("map", map);
		
		return "board/list";
	}
	@RequestMapping(value = "/view/{no}", method = RequestMethod.GET)
	public String view(@PathVariable("no") Long no, Model model) {
		BoardVO vo = boardService.getViewDetail(no);
		boardService.setHitCount(no);
		model.addAttribute("board",vo);
		return "board/view";
	}
	
	@Auth
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() { 
		return "board/write";
	}
	
//	TODO 고치자
	@Auth
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@AuthUser UserVO authUser, 
			@RequestParam(value="status", required = false, defaultValue = "write")  String status, 
			@RequestParam(value = "group_no", required = false) String groupNo, 
			@RequestParam(value = "order_no", required = false) String orderNo, 
			@RequestParam(value = "depth", required = false) String depth, 
			BoardVO vo
			) {
		vo.setUserNo(authUser.getNo());
		System.out.println("status는 " + status);
		System.out.println(groupNo);
		System.out.println(orderNo);
		System.out.println(depth);
		if("reply".equals(status)) {
			vo.setGroupNo(Integer.parseInt(groupNo));
			vo.setOrderNo(Integer.parseInt(orderNo));
			vo.setDepth(Integer.parseInt(depth));
		}
		System.out.println(vo);
		boardService.writeBoard(status, vo);
		return "redirect:/board/1";
		
	}
	@Auth
	@RequestMapping(value = "/modify/{no}", method = RequestMethod.GET)
	public String modify(@PathVariable("no") Long no, Model model) {
		BoardVO vo = boardService.getViewDetail(no);
		model.addAttribute("board", vo);
		return "board/modify";
	}
	@Auth
	@RequestMapping(value = "/modify/{no}", method = RequestMethod.POST)
	public String modify(@PathVariable("no") Long no, BoardVO vo) {
		boardService.updateBoardByBoardNo(vo);
		return "redirect:/board/view/" + no;
	}
	@Auth
	@RequestMapping(value = "/delete/{no}", method = RequestMethod.GET)
	public String delete(@PathVariable("no") Long no) {
		boardService.delete(no);
		return "redirect:/board/1";
	}
	
//	TODO 고치자
	@Auth
	@RequestMapping(value = "/reply/{no}", method = RequestMethod.POST)
	public String reply(@PathVariable("no") Long no, 
			@RequestParam(value = "group_no", required = false) String groupNo, 
			@RequestParam(value = "order_no", required = false) String orderNo, 
			@RequestParam(value = "depth", required = false) String depth,
			Model model
			) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("boardNo", no); // 노필요인듯
		map.put("groupNo", groupNo);
		map.put("orderNo", orderNo);
		map.put("depth", depth);
		model.addAttribute("status", "reply");
		model.addAttribute("map", map);
		return "board/write";
	}
}
