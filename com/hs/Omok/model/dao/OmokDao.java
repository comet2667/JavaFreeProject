package com.hs.omok.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.hs.omok.common.JDBCTemplate;
import com.hs.omok.model.vo.User;

public class OmokDao {
	
	public int insertUser(Connection conn, User u) {
		
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		String sql = "INSERT INTO TB_OMOK VALUES (SEQ_OM.NEXTVAL, ?, DEFAULT, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, u.getUserName());
			pstmt.setString(2, u.getStone());
			pstmt.setString(3, u.getResult());
			pstmt.setInt(4, u.getCount());
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	public ArrayList<User> selectAllList(Connection conn){
		ArrayList<User> list = new ArrayList<>();
		
		String sql = "SELECT USER_NO 번호, USER_NAME 이름, PLAY_DATE 접속일, STONE 돌, RESULT 결과, COUNT 착수 FROM TB_OMOK";
		
		Statement stmt = null;
		ResultSet rSet = null;
		
		try {
			stmt = conn.createStatement();
			rSet = stmt.executeQuery(sql);
			
			while(rSet.next()) {
				User u = new User();
				u.setUserNo(rSet.getInt(1));
				u.setUserName(rSet.getString(2));
				u.setPlayDate(rSet.getDate(3));
				u.setStone(rSet.getString(4));
				u.setResult(rSet.getString(5));
				u.setCount(rSet.getInt(6));
				
				list.add(u);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rSet);
			JDBCTemplate.close(stmt);
		}
		
		return list;
	}

}
