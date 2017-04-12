package com.amaker.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.amaker.entity.User;

import com.amaker.dao.UserDao;
import com.amaker.util.DBUtil;
/**
 * 
 * @author 郭宏志
 * 用戶登錄DAO實作類別
 */
public class UserDaoImpl implements UserDao {
	
	/**
	 * 透過用戶名稱和密碼登錄，若成功則返回User物件，否則回傳null
	 */
	public User login(String username, String password) {

		String sql = " select id,username,password from UserTbl where username=? and password=? ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, username);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int id = rs.getInt(1);
				User u = new User();
				u.setId(id);
				u.setPassword(password);
				u.setUsername(username);
				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return null;
	}

}
