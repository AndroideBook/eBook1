package com.amaker.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.amaker.dao.UnionTableDao;
import com.amaker.entity.UnionTable;
import com.amaker.util.DBUtil;

/**
 * @author 郭宏志
 * 實作併桌功能
 */
public class UnionTableDaoImpl implements UnionTableDao {
	
	public List getTableList() {
		// 查詢SQL語句
		String sql =" select id,num from TableTbl where flag = 1 ";
		// 資料庫連線工具類別
		DBUtil util = new DBUtil();
		// 取得連線
		Connection conn = util.openConnection();
		try {
			// 取得Statement
			Statement pstmt = conn.createStatement();
			// 執行查詢
			ResultSet rs = pstmt.executeQuery(sql);
			// 判斷訂單明細
			List list = new ArrayList();
			while (rs.next()) {
				// 取得功能表資料
				
				int id = rs.getInt(1);
				int num = rs.getInt(2);
				
				UnionTable ut = new UnionTable();
				ut.setId(id);
				ut.setNum(num);
				
				list.add(ut);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return null;
	}

	public void updateStatus(int tableId1,int tableId2) {
		// 資料庫連線工具類別
		DBUtil util = new DBUtil();
		// 取得連線
		Connection conn = util.openConnection();
		CallableStatement cstmt;
		try {
			cstmt = conn.prepareCall("{call new_proc(?,?)}");
			cstmt.setInt(1, tableId1);
			cstmt.setInt(2, tableId2);
			cstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
