package com.amaker.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.amaker.dao.ChangeTableDao;
import com.amaker.util.DBUtil;
/**
 * @author ������
 * �������\��
 */
public class ChangeTableDaoImpl implements ChangeTableDao {

	public void changeTable(int orderId, int tableId) {
		// ��sSQL�y�y
		
		String sql = " update TableTbl set flag = 0 where id = "+
		  " (select tableId from OrderTbl  as ot where ot.id = ?)";
		String sql2 = " update OrderTbl set tableId = ? where id = ? ";
		String sql3 = " update TableTbl set flag = 1 where id = ?";
		
		// ��Ʈw�s�u�u�����O
		DBUtil util = new DBUtil();
		// ���o�s�u
		Connection conn = util.openConnection();
		
		try {
			conn.setAutoCommit(false);
			// ���o�w�w�y�y
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orderId);
			// ��s��쪬�A
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql2);
			
			// �]�w�Ѽ�
			pstmt.setInt(1, tableId);
			pstmt.setInt(2, orderId);
			// ��s�q��
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(sql3);
			pstmt.setInt(1, tableId);
			// ��s��쪬�A
			pstmt.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			util.closeConn(conn);
		}
	}
}
