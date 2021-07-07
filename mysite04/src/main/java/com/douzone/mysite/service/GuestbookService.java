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

	public void addMessage(GuestBookVO vo) {
		guestBookRepository.insert(vo);
	}
	
	public void deleteMessage(Long no, String password) {
		GuestBookVO vo = new GuestBookVO();
		vo.setNo(no);
		vo.setPassword(password);
		guestBookRepository.delete(vo);
	}
}
