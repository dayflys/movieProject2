package model.dao;

import model.vo.CommentVO;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class CommentDAO {
	
	public ArrayList<CommentVO> select(String movie) {
		Connection conn = ConnectDB.connect();
		
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<CommentVO> list = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM COMM WHERE moviename = '" + movie + "'");
			
			list = new ArrayList<CommentVO>();
			while(rs.next()) {
				CommentVO vo = new CommentVO();	
				vo.setNickname(rs.getString(1));
				vo.setPrehour(rs.getDate(2));
				vo.setContent(rs.getString(3));
				vo.setMoviename(rs.getString(4));
				vo.setLike(rs.getInt(5));
				vo.setCnt(rs.getInt(6));
				list.add(vo);
				
				System.out.println(rs.getString(3));

			}			
		} catch (SQLException e) {			
			System.out.println("오류 발생 : " + e);
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
				if(conn != null)
					conn.close();
			} catch (Exception e) {		
				System.out.println("오류 발생 : " + e);
			}
		}
		return list;
	}
	
	public boolean update(CommentVO vo) {
		Connection conn = ConnectDB.connect();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(
					"update comm set " +  
					"cont = ?, " + 
					"prehour= ?" +
					"where cnt = ?"); 
			
			
			Date today = new Date ();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String targetDay = sdf.format(today);

			pstmt.setString(1, vo.getContent());
			pstmt.setString(2, targetDay);
			pstmt.setInt(3, vo.getCnt());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println("comment insert 과정에서 오류 발생 " + e);
			return false;
		} finally {
			close(pstmt, null, conn);
		}
	}
		
	public boolean updateLike(CommentVO vo) {
		Connection conn = ConnectDB.connect();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(
					"update comm set " +  
					"like_no = ? "+ 
					"where cnt = ?"); 

			pstmt.setInt(1, vo.getLike());
			pstmt.setInt(2, vo.getCnt());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println("comment insert 과정에서 오류 발생 " + e);
			return false;
		} finally {
			close(pstmt, null, conn);
		}
	}
		
	public boolean delete(int cnt) {
		Connection conn = ConnectDB.connect();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("DELETE FROM comm WHERE cnt = " + cnt);

			if (pstmt.executeUpdate() != 0)
				return true;
			else
				return false;
		} catch (SQLException e) {
			System.err.println("delete 과정에서 오류 발생 " + e);
			return false;
		} finally {
			close(pstmt, null, conn);
		}
	}
	
	public boolean insert(CommentVO vo) {
		Connection conn = ConnectDB.connect();
		PreparedStatement pstmt = null;
		Date today = new Date ();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String targetDay = sdf.format(today);
		
		try {
			pstmt = conn.prepareStatement("INSERT INTO comm(nickname,preHour,cont,movieName,like_no) VALUES(?,?,?,?,?)");
			pstmt.setString(1, vo.getNickname());
			pstmt.setString(2, targetDay);
			pstmt.setString(3, vo.getContent());
			pstmt.setString(4, vo.getMoviename());
			pstmt.setInt(5, 0);

			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println("meeting insert 과정에서 오류 발생 " + e);
			return false;
		} finally {
			close(pstmt, null, conn);
		}
	}
	
		
	private void close(Statement s, ResultSet r, Connection conn) {
		try {
			if (r != null)
				r.close();
			if (s != null)
				s.close();
			ConnectDB.close(conn);
		} catch (SQLException e) {
			System.err.println("객체 close 과정에서 문제 발생" + e);
		}
	}
		
	
}
	
	
