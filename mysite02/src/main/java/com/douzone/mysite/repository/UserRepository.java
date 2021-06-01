package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.UserVO;

public class UserRepository {
	public Boolean insert(UserVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			conn = getConnection();
			
			String sql = "INSERT INTO user VALUES (null, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());
			
			int count = pstmt.executeUpdate();
			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;		
	}

	public List<UserVO> findAll() {
		List<UserVO> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql ="SELECT no, name, email, gender FROM user order by no desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String gender= rs.getString(4);
				
				UserVO vo = new UserVO();
				vo.setNo(no);
				vo.setName(name);
				vo.setEmail(email);
				vo.setGender(gender);
				
				result.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
//			String url = "jdbc:mysql://192.168.254.30:3307/webdb?characterEncoding=utf8";
//			conn = DriverManager.getConnection(url, "webdb", "webdb");
			String url = "jdbc:mysql://127.0.0.1:3306/bookmall_local";
			conn = DriverManager.getConnection(url, "bookmall_local", "bookmall_local");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} 
		
		return conn;
	}

	public UserVO findByEmailAndPassword(String email, String password) {
		UserVO result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select no, name from user where email = ? AND password = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				
				result = new UserVO();
				result.setNo(no);
				result.setName(name);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

//	값이 비어있으면 안바꾸겠다는거니까 set안하고 값이 넣어잇으면 ㄱㄱ
	public UserVO updateByNo(Long no, String name, String password, String gender) {
		UserVO result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		System.out.println(no + " " + name + " " + password + " " + gender);
		
		try {
			conn = getConnection();
//			String sql = "select name, email, gender from user where no = ?";
			if ("".equals(name)) {
				System.out.println("이름 널");
				sql = "UPDATE user SET password = ?, gender = ? WHERE no = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, password);
				pstmt.setString(2, gender);
				pstmt.setLong(3, no);
			} else if("".equals(password)) {
				System.out.println("비번 널");
				sql = "UPDATE user SET name = ?, gender = ? WHERE no = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, name);
				pstmt.setString(2, gender);
				pstmt.setLong(3, no);
			} else {
				System.out.println("전부 널이아닐때 (그밖)");
				sql = "UPDATE user SET name = ?, password = ?, gender = ? WHERE no = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, name);
				pstmt.setString(2, password);
				pstmt.setString(3, gender);
				pstmt.setLong(4, no);
			}
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public String findEmailByNo(Long userNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		try {
			conn = getConnection();
			String sql = "select email from user where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, userNo);
			pstmt.executeUpdate();
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String email = rs.getString(1);
				result = email;
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
