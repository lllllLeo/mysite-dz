package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVO;

@Repository
public class BoardRepository {

	@Autowired
	private SqlSession sqlsession;
	
	public Boolean insert(BoardVO vo) {
		int count = sqlsession.insert("board.insert", vo);
		return count == 1;		
	}

	public List<Map<String, Object>> getAllBoard(int startCount, int countBoard) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startCount", startCount);
		map.put("countBoard", countBoard);
		List<Map<String, Object>> list = sqlsession.selectList("board.getAllBoard", map);
		System.out.println(list);
		return list;
	}

	public BoardVO getViewDetail(long no) {
		return sqlsession.selectOne("board.getViewDetail", no);
		
	}

	public void updateBoardByBoardNo(BoardVO vo) {
		sqlsession.update("board.updateBoardByBoardNo", vo);
	}

	public void setHitCount(long no) {
		sqlsession.update("board.updateHitCount", no);
	}

	public void delete(long no) {
		sqlsession.delete("board.delete", no);
	}

	public int getBoardCount() {
		return sqlsession.selectOne("board.getBoardCount");
	}
	
	public void replyInsert(BoardVO vo) {
		sqlsession.insert("board.replyInsert", vo);
	}

	public void replyUpdate(BoardVO vo) {
		sqlsession.update("board.replyUpdate", vo);
	}

}
