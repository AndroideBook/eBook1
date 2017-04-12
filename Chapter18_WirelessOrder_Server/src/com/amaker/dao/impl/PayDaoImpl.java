package com.amaker.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amaker.dao.PayDao;
import com.amaker.entity.QueryOrder;
import com.amaker.entity.QueryOrderDetail;
import com.amaker.util.DBUtil;
/**
 * @author 郭宏志
 * 結算DAO實作類別
 */
public class PayDaoImpl implements PayDao {
	
	// 根據訂單編號查詢訂單資訊
	public QueryOrder getOrderById(int id) {
		// 查詢SQL語句
		String sql =" 	select ot.`orderTime`, "+
					" 	ut.`name`, "+
					" 	ot.`personNum`, "+
					" 	ot.`tableId` "+
					" 	from orderTbl as ot "+
					" 	left join userTbl as ut on ot.`userID` = ut.id "+
					" 	where ot.`id`=? ";

		// 資料庫連線工具類別
		DBUtil util = new DBUtil();
		// 取得連線
		Connection conn = util.openConnection();
		try {
			// 取得預定語句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 設定參數
			pstmt.setInt(1, id);
			// 執行查詢
			ResultSet rs = pstmt.executeQuery();
			// 判斷訂單是否存在
			if (rs.next()) {
				// 取得訂單資訊
				String orderTime = rs.getString(1);
				String userName = rs.getString(2);
				int personNum =rs.getInt(3);
				int tableId = rs.getInt(4);
				QueryOrder qo = new QueryOrder();
				qo.setName(userName);
				qo.setOrderTime(orderTime);
				qo.setPersonNum(personNum);
				qo.setTableId(tableId);
				return qo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		
		return null;
	}
	// 根據訂單編號，查詢訂單明細
	public List getOrderDetailList(int id) {
		// 查詢SQL語句
		String sql =" 	select mt.`name`, "+
					" 	mt.`price`, "+
					" 	odt.`num`, "+
					" 	mt.price*odt.num as total, "+
					" 	odt.`remark` "+
					" 	from orderdetailTbl as odt "+
					" 	left join menuTbl as mt on odt.`menuId` = mt.id "+
					" 	where odt.`orderId`= ?"+
		
					" 	union "+
					
					" 	select          '',"+
					" 	'',"+
					" 	'',"+
					" 	sum(mt.price*odt.num) as total1,"+
					" 	'' "+
					" 	from orderdetailTbl as odt"+
					" 	left join menuTbl as mt on odt.`menuId` = mt.id"+
					" 	where odt.`orderId`= ? ";
		
		// 資料庫連線工具類別
		DBUtil util = new DBUtil();
		// 取得連線
		Connection conn = util.openConnection();
		try {
			// 取得預定語句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 設定查詢參數
			pstmt.setInt(1, id);
			pstmt.setInt(2, id);
			// 執行查詢
			ResultSet rs = pstmt.executeQuery();
			
			List list = new ArrayList();
			
			// 判斷訂單明細
			while (rs.next()) {
				// 取得訂單詳細資料
				String name = rs.getString(1);
				int price = rs.getInt(2);
				int num = rs.getInt(3);
				int total = rs.getInt(4);
				String remark = rs.getString(5);
				
				QueryOrderDetail qod = new QueryOrderDetail();
				
				qod.setName(name);
				qod.setNum(num);
				qod.setPrice(price);
				qod.setTotal(total);
				qod.setRemark(remark);
				
				list.add(qod);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return null;
	}
	
	// 結算
	public void pay(int id) {
		// 查詢SQL語句
		String sql =" update OrderTbl set isPay=1 where id = ?";
		// 資料庫連線工具類別
		DBUtil util = new DBUtil();
		// 取得連線
		Connection conn = util.openConnection();
		try {
			// 取得預定語句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 設定查詢參數
			pstmt.setInt(1, id);
			// 執行查詢
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
	}
}
