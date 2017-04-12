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
 * @author ������
 * �Τ�n��DAO��@���O
 */
public class UserDaoImpl implements UserDao {
	
	/**
	 * �z�L�Τ�W�٩M�K�X�n���A���\��^User����A���Ѫ�^null
	 */
	public User login(String account, String password) {
		// SQL�d�߻y�y
		String sql = " select id,account,password,name,permission,remark "+
						" from userTbl "+
						" where account=? and password=? ";
		try {
			// ��ҤƸ�Ʈw�u�����O
			DBUtil util = new DBUtil();
			// ���o��Ʈw�s�u
			Connection conn = util.openConnection();
			// �إ߹w���w�q�y�y
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// �]�w�d�߰Ѽ�
			pstmt.setString(1, account);
			pstmt.setString(2, password);
			// ���o���G��
			ResultSet rs = pstmt.executeQuery();
			// �P�_�Τ�O�_�s�b
			if (rs.next()) {
				// ���o�������
				int id = rs.getInt(1);
				String name = rs.getString(4);
				int permission = rs.getInt(5);
				String remark = rs.getString(6);
				// ��Ҥ�User
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
