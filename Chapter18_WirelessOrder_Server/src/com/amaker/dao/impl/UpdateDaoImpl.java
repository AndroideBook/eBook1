package com.amaker.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.amaker.dao.UpdateDao;
import com.amaker.entity.Menu;
import com.amaker.util.DBUtil;

import com.amaker.entity.Table;

/**
 * @author 郭宏志
 *	完成資料更新功能
 */
public class UpdateDaoImpl implements UpdateDao {
	// 取得菜單列表
	public List getMenuList() {
		// 查詢SQL語句
		String sql =" select id,typeId,price,name,pic,remark from MenuTbl ";
		// 資料庫連線工具類別
		DBUtil util = new DBUtil();
		// 取得連線
		Connection conn = util.openConnection();
		try {
			// 取得Statement
			Statement pstmt = conn.createStatement();
			// 執行查詢
			ResultSet rs = pstmt.executeQuery(sql);
			List list = new ArrayList();
			// 判斷訂單名細
			while (rs.next()) {
				// 取得功能表資料
				
				int id = rs.getInt(1);
				int typeId = rs.getInt(2);
				int price = rs.getInt(3);
				String name = rs.getString(4);
				String pic = rs.getString(5);
				String remark = rs.getString(6);
				
				Menu m = new Menu();
				m.setId(id);
				m.setName(name);
				m.setPic(pic);
				m.setPrice(price);
				m.setRemark(remark);
				m.setTypeId(typeId);
				
				list.add(m);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return null;
	}
	
	// 取得餐桌列表
	public List getTableList() {
		// 查詢SQL語句
		String sql =" select id,num,flag,description from TableTbl ";
		// 資料庫連線工具類別
		DBUtil util = new DBUtil();
		// 取得連線
		Connection conn = util.openConnection();
		try {
			// 取得Statement
			Statement pstmt = conn.createStatement();
			// 執行查詢
			ResultSet rs = pstmt.executeQuery(sql);
			List list = new ArrayList();
			// 判斷訂單名細
			while (rs.next()) {
				// 取得功能表資料
				
				int id = rs.getInt(1);
				int num = rs.getInt(2);
				int flag = rs.getInt(3);
				String remark = rs.getString(4);
				
				Table m = new Table();
				m.setId(id);
				m.setNum(num);
				m.setFlag(flag);
				m.setDescription(remark);
				
				list.add(m);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return null;
		//return null;
	}

}
