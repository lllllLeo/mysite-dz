package com.douzone.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.GalleryVO;

@Repository
public class GalleryRepository {
	
	@Autowired
	private SqlSession sqlSession;

	public void uploadImage(String url) {
		sqlSession.insert("gallery.insert", url);
	}

	public List<GalleryVO> getAllImages() {
		return sqlSession.selectList("gallery.getAllImages");
	}

	public void delete(Long no) {
		sqlSession.delete("gallery.delete", no);
	}
}
