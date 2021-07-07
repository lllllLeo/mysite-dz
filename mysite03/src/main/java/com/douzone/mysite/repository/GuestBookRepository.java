package com.douzone.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.GuestBookVO;

@Repository
public class GuestBookRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public Boolean delete(GuestBookVO vo) {
		int count = sqlSession.delete("guestbook.delete", vo);
		return count == 1;
	}
	
	public Boolean insert(GuestBookVO vo) {
		int count = sqlSession.insert("guestbook.insert", vo);
		return count == 1;		
	}

	public List<GuestBookVO> findAll() {
		return sqlSession.selectList("guestbook.findAll");
	}
	public List<GuestBookVO> findAll(Long no) {
		return sqlSession.selectList("guestbook.findAllByNo", no);
	}
}
