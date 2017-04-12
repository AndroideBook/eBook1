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
 * @author 郭宏志
 * 點餐功能DAO實作類別
 */
public class OrderDaoImpl implements OrderDao {

	// 保存點餐資訊，返回訂單ID
	public int saveOrder(Order o) {
		// 新增SQL語句
		String sql = " insert into ordertbl(orderTime,userId,tableId,personNum)values(?,?,?,?) ";
		// 資料庫連線工具類別
		DBUtil util = new DBUtil();
		// 取得連線
		Connection conn = util.openConnection();
		try {
			// 取得預定語句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 設定參數
			pstmt.setString(1, o.getOrderTime());
			pstmt.setInt(2, o.getUserId());
			pstmt.setInt(3, o.getTableId());
			pstmt.setInt(4, o.getPersonNum());
			// 執行更新
			pstmt.executeUpdate();
			// 返回訂單編號
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
	// 保存點餐列表
	public void saveOrderDetail(OrderDetail od) {
		// 新增SQL語句
		String sql = " insert into orderdetailtbl(orderId,menuId,num,remark)values(?,?,?,?) ";
		// 資料庫連線工具類別
		DBUtil util = new DBUtil();
		// 開啟連線
		Connection conn = util.openConnection();
		try {
			// 取得預定語句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 設定參數
			pstmt.setInt(1, od.getOrderId());
			pstmt.setInt(2, od.getMenuId());
			pstmt.setInt(3, od.getNum());
			pstmt.setString(4, od.getRemark());
			// 執行更新
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
	}
	
	// 更新桌號狀態，有人
	public void updateTableStatus(int tableId) {
		// 更新SQL語句
		String sql = " update tableTbl set flag=1 where id = ? ";
		// 資料庫連線工具類別
		DBUtil util = new DBUtil();
		// 取得連線
		Connection conn = util.openConnection();
		try {
			// 取得預定語句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 設定參數
			pstmt.setInt(1, tableId);
			// 執行更新
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
	}
	// 更新桌號狀態，空位
	public void updateTableStatus2(int orderId) {
		// 更新SQL語句
		String sql = " update TableTbl set flag = 0 where id = "+
							" ( select tableId from OrderTbl where id = ?) ";
		// 資料庫連線工具類別
		DBUtil util = new DBUtil();
		// 取得連線
		Connection conn = util.openConnection();
		try {
			// 取得預定語句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 設定參數
			pstmt.setInt(1, orderId);
			// 執行更新
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
	}
}
