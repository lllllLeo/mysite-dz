package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.UserVO;

@Repository
public class UserRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public Boolean insert(UserVO vo) {
		int count = sqlSession.insert("user.insert",vo);		
		return count == 1;		
	}

//	public List<UserVO> findAll() {
//		List<UserVO> result = new ArrayList<>();
//
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = getConnection();
//			
//			String sql ="SELECT no, name, email, gender FROM user order by no desc";
//			pstmt = conn.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//
//			while(rs.next()) {
//				Long no = rs.getLong(1);
//				String name = rs.getString(2);
//				String email = rs.getString(3);
//				String gender= rs.getString(4);
//				
//				UserVO vo = new UserVO();
//				vo.setNo(no);
//				vo.setName(name);
//				vo.setEmail(email);
//				vo.setGender(gender);
//				
//				result.add(vo);
//			}
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		} finally {
//			try {
//				if(rs != null) {
//					rs.close();
//				}
//				if(pstmt != null) {
//					pstmt.close();
//				}
//				if(conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return result;
//	}
	
	public UserVO findByEmailAndPassword(String email, String password) {
		Map<String, Object> map = new HashMap();
		map.put("e", email);
		map.put("p", password);
		return sqlSession.selectOne("findByEmailAndPassword", map);
	}

//	값이 비어있으면 안바꾸겠다는거니까 set안하고 값이 넣어잇으면 ㄱㄱ
	public UserVO findEmailByNo(Long no) {
		return sqlSession.selectOne("user.findEmailByNo", no);
	}

	public void updateByNo(UserVO vo) {
		sqlSession.update("user.update", vo);
	}

	public UserVO findByEmail(String email) {
		return sqlSession.selectOne("user.findByEmail", email);
	}
}
