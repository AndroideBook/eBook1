package com.amaker.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.amaker.dao.UserDao;
import com.amaker.entity.User;
import com.amaker.util.DBUtil;
/**
 * 
 * @author 郭宏志
 * 用戶登錄DAO實作類別
 */
public class UserDaoImpl implements UserDao {
	
	/**
	 * 透過用戶名稱和密碼登錄，成功返回User物件，失敗返回null
	 */
	public User login(String account, String password) {
		// SQL查詢語句
		String sql = " select id,account,password,name,permission,remark "+
						" from userTbl "+
						" where account=? and password=? ";
		try {
			// 實例化資料庫工具類別
			DBUtil util = new DBUtil();
			// 取得資料庫連線
			Connection conn = util.openConnection();
			// 建立預先定義語句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 設定查詢參數
			pstmt.setString(1, account);
			pstmt.setString(2, password);
			// 取得結果集
			ResultSet rs = pstmt.executeQuery();
			// 判斷用戶是否存在
			if (rs.next()) {
				// 取得相關欄位
				int id = rs.getInt(1);
				String name = rs.getString(4);
				int permission = rs.getInt(5);
				String remark = rs.getString(6);
				// 實例化User
				User u = new User();
				
				u.setId(id);
				u.setAccount(account);
				u.setPassword(password);
				u.setName(name);
				u.setPermission(permission);
				u.setRemark(remark);
				
				return u;
			}
			util.closeConn(conn);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//util.closeConn(conn);
		}
		return null;
	}
	
	public static void main(String[] args) {
		UserDao dao = new UserDaoImpl();
		User u = dao.login("admin", "123");
		System.out.println(u.getAccount());
	}

}
