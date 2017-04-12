package com.amaker.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.amaker.dao.OrderDao;
import com.amaker.entity.Order;
import com.amaker.entity.OrderDetail;
import com.amaker.util.DBUtil;
/**
 * @author ������
 * �I�\�\��DAO��@���O
 */
public class OrderDaoImpl implements OrderDao {

	// �O�s�I�\��T�A��^�q��ID
	public int saveOrder(Order o) {
		// �s�WSQL�y�y
		String sql = " insert into ordertbl(orderTime,userId,tableId,personNum)values(?,?,?,?) ";
		// ��Ʈw�s�u�u�����O
		DBUtil util = new DBUtil();
		// ���o�s�u
		Connection conn = util.openConnection();
		try {
			// ���o�w�w�y�y
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// �]�w�Ѽ�
			pstmt.setString(1, o.getOrderTime());
			pstmt.setInt(2, o.getUserId());
			pstmt.setInt(3, o.getTableId());
			pstmt.setInt(4, o.getPersonNum());
			// �����s
			pstmt.executeUpdate();
			// ��^�q��s��
			String sql2 = " select max(id) as id  from orderTbl ";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql2);
			if(rs.next()){
				int id = rs.getInt(1);
				return id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return 0;
	}
	// �O�s�I�\�C��
	public void saveOrderDetail(OrderDetail od) {
		// �s�WSQL�y�y
		String sql = " insert into orderdetailtbl(orderId,menuId,num,remark)values(?,?,?,?) ";
		// ��Ʈw�s�u�u�����O
		DBUtil util = new DBUtil();
		// �}�ҳs�u
		Connection conn = util.openConnection();
		try {
			// ���o�w�w�y�y
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// �]�w�Ѽ�
			pstmt.setInt(1, od.getOrderId());
			pstmt.setInt(2, od.getMenuId());
			pstmt.setInt(3, od.getNum());
			pstmt.setString(4, od.getRemark());
			// �����s
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
	}
	
	// ��s�ู���A�A���H
	public void updateTableStatus(int tableId) {
		// ��sSQL�y�y
		String sql = " update tableTbl set flag=1 where id = ? ";
		// ��Ʈw�s�u�u�����O
		DBUtil util = new DBUtil();
		// ���o�s�u
		Connection conn = util.openConnection();
		try {
			// ���o�w�w�y�y
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// �]�w�Ѽ�
			pstmt.setInt(1, tableId);
			// �����s
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
	}
	// ��s�ู���A�A�Ŧ�
	public void updateTableStatus2(int orderId) {
		// ��sSQL�y�y
		String sql = " update TableTbl set flag = 0 where id = "+
							" ( select tableId from OrderTbl where id = ?) ";
		// ��Ʈw�s�u�u�����O
		DBUtil util = new DBUtil();
		// ���o�s�u
		Connection conn = util.openConnection();
		try {
			// ���o�w�w�y�y
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// �]�w�Ѽ�
			pstmt.setInt(1, orderId);
			// �����s
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
	}
}
