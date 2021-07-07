package com.douzone.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.SiteVO;

@Repository
public class SiteRepository {

	@Autowired
	private SqlSession sqlSession;

	public SiteVO getMain() {
		return sqlSession.selectOne("site.getMain");
	}

	public void updateMain(SiteVO vo) {
		sqlSession.update("site.update", vo);
		
	}

	public String getProfilePath() {
		return sqlSession.selectOne("site.getProfilePath");
	}
}
