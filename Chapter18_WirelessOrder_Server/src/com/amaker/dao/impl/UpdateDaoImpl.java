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
 * @author ������
 *	������Ƨ�s�\��
 */
public class UpdateDaoImpl implements UpdateDao {
	// ���o���C��
	public List getMenuList() {
		// �d��SQL�y�y
		String sql =" select id,typeId,price,name,pic,remark from MenuTbl ";
		// ��Ʈw�s�u�u�����O
		DBUtil util = new DBUtil();
		// ���o�s�u
		Connection conn = util.openConnection();
		try {
			// ���oStatement
			Statement pstmt = conn.createStatement();
			// ����d��
			ResultSet rs = pstmt.executeQuery(sql);
			List list = new ArrayList();
			// �P�_�q��W��
			while (rs.next()) {
				// ���o�\�����
				
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
	
	// ���o�\��C��
	public List getTableList() {
		// �d��SQL�y�y
		String sql =" select id,num,flag,description from TableTbl ";
		// ��Ʈw�s�u�u�����O
		DBUtil util = new DBUtil();
		// ���o�s�u
		Connection conn = util.openConnection();
		try {
			// ���oStatement
			Statement pstmt = conn.createStatement();
			// ����d��
			ResultSet rs = pstmt.executeQuery(sql);
			List list = new ArrayList();
			// �P�_�q��W��
			while (rs.next()) {
				// ���o�\�����
				
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
