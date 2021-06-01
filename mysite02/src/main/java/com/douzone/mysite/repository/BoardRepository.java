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

import com.douzone.mysite.vo.BoardVO;
import com.douzone.mysite.vo.UserVO;

public class BoardRepository {
	
	public Boolean insert(BoardVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			conn = getConnection();
//			INSERT INTO board VALUES (<{no: }>, <{title: }>, <{contents: }>, <{reg_date: }>, <{hit: }>, <{group_no: }>, <{order_no: }>, <{depth: }>, <{user_no: }>)
			String sql = "INSERT INTO board VALUES (null, ?, ?, now(), 0, IFNULL((SELECT (max(group_no) +1) FROM board b), 0), 1, 0, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getUserNo());
			
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

	public List<Map<String, Object>> getAllBoard(int startCount, int countBoard) {
		List<Map<String, Object>> result = new ArrayList();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
//			번호 제목 (뎁스) (사용자번호) 사용자이름 조회수 작성일
			String sql ="select a.no, a.title, a.depth, a.hit, a.reg_date, b.no as user_no, b.name from board a, user b where a.user_no = b.no order by a.group_no DESC, a.order_no asc limit ?,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startCount);
			pstmt.setInt(2, countBoard);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				int depth = rs.getInt(3);
				int hit = rs.getInt(4);
				String regDate = rs.getString(5);
				Long userNo = rs.getLong(6);
				String userName = rs.getString(7);
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("no", no);
				map.put("title", title);
				map.put("depth", depth);
				map.put("hit", hit);
				map.put("regDate", regDate);
				map.put("userNo", userNo);
				map.put("userName", userName);
				
				
				result.add(map);
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

	public BoardVO getViewDetail(long boardNo) {
		BoardVO result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		이름뽑을까 조인
		try {
			conn = getConnection();
			String sql = "select no, title, contents, group_no, order_no, depth, user_no from board where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, boardNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				int groupNo = rs.getInt(4);
				int orderNo = rs.getInt(5);
				int depth = rs.getInt(6);
				Long userNo = rs.getLong(7);

				result = new BoardVO();
				result.setNo(no);
				result.setTitle(title);
				result.setContents(content);
				result.setGroupNo(groupNo);
				result.setOrderNo(orderNo);
				result.setDepth(depth);
				result.setUserNo(userNo);
				
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

	public void updateBoardByBoardNo(BoardVO vo) {
		UserVO result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		
		try {
			conn = getConnection();
//			String sql = "select name, email, gender from user where no = ?";
			sql = "update board set title = ?, contents = ? where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getNo());
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
		
	}

	public void setHitCount(long boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			conn = getConnection();
			String sql = "UPDATE board set hit = hit + 1 where no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, boardNo);
			pstmt.executeUpdate();
			
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
		
	}

	public void delete(long boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			conn = getConnection();
			String sql = "delete from board where no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, boardNo);
			pstmt.executeUpdate();
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
		
	}

	public int getBoardCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		이름뽑을까 조인
		try {
			conn = getConnection();
			String sql = "select count(no) from board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
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
		return count;
	}
	
	public void replyInsert(BoardVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			conn = getConnection();
			String sql = "INSERT INTO board VALUES (null, ?, ?, now(), 0, ?, ?, ? + 1, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setInt(3, vo.getGroupNo());
			pstmt.setInt(4, vo.getOrderNo());
			pstmt.setInt(5, vo.getDepth());
			pstmt.setLong(6, vo.getUserNo());
			pstmt.executeUpdate();
			
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
	}


	public void replyUpdate(BoardVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "UPDATE board set order_no = order_no + 1 where group_no = ? AND order_no >= ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getGroupNo());
			pstmt.setInt(2, vo.getOrderNo());
			pstmt.executeUpdate();
			
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


}
