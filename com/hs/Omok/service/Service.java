package com.hs.omok.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.hs.omok.common.JDBCTemplate;
import com.hs.omok.model.dao.OmokDao;
import com.hs.omok.model.vo.User;

public class Service {
	
	public int insertUser(User u) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new OmokDao().insertUser(conn, u);
		
		if (result > 0) {
			JDBCTemplate.commit(conn);
			System.out.println("커밋");
		} else {
			JDBCTemplate.rollback(conn);
			System.out.println("롤백");

		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public ArrayList<User> selectAllList(){
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<User> list = new ArrayList<>();
		list = new OmokDao().selectAllList(conn);
		JDBCTemplate.close(conn);
		
		return list;
	}
}
