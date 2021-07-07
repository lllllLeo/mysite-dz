package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestBookRepository;
import com.douzone.mysite.vo.GuestBookVO;

@Service
public class GuestbookService {

	@Autowired
	private GuestBookRepository guestBookRepository;
	
	public List<GuestBookVO> getMessageList() {
		return guestBookRepository.findAll();
	}
	
	public List<GuestBookVO> getMessageList(Long no) { // no는 기준  no값이 안넘어오면 0을 줌(처음에 뽑아야하니까)   var no = 0 / 13 list?no=13 다음 누르면 이래넘어감  / append 맨 마지막 게시글 넘버를 var에 세팅
		return guestBookRepository.findAll(no);
	}

	public void addMessage(GuestBookVO vo) {
		guestBookRepository.insert(vo);
	}
	
	public Boolean deleteMessage(Long no, String password) {
		GuestBookVO vo = new GuestBookVO();
		vo.setNo(no);
		vo.setPassword(password);
		return guestBookRepository.delete(vo);
	}
}
