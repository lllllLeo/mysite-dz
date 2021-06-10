package com.douzone.mysite.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVO;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	public void writeBoard(String status, BoardVO vo) {
		if ("reply".equals(status)) {
			vo.setOrderNo(vo.getOrderNo() + 1);
			boardRepository.replyUpdate(vo);
			vo.setOrderNo(vo.getOrderNo() + 1);
			boardRepository.replyInsert(vo);
		} else {
			boardRepository.insert(vo);
		}
	}

	public BoardVO getViewDetail(Long no) {
		return boardRepository.getViewDetail(no);
	}

	public void updateBoardByBoardNo(BoardVO vo) {
		boardRepository.updateBoardByBoardNo(vo);
	}

	public List<Map<String, Object>> getAllBoard(int startCount, int countBoard) {
		return boardRepository.getAllBoard(startCount, countBoard);
	}

	public void setHitCount(Long no) {
		boardRepository.setHitCount(no);
		
	}

	public int getBoardCount() {
		return boardRepository.getBoardCount();
	}

	public void delete(Long no) {
		boardRepository.delete(no);
	}

}
