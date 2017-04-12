package com.amaker.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.amaker.dao.VehicleDao;
import com.amaker.entity.VehicleFaultInfo;
import com.amaker.util.DBUtil;

/**
 * 
 * @author 郭宏志
 * 保存機動車違規資訊Dao介面實作類別
 */
public class VehicleFaultInfoDaoImpl implements VehicleDao {

	public boolean save(VehicleFaultInfo v) {
		String sql = " insert into vehicleinfotbl(name,idno,license,createTime,faultRecord,penalty)" +
				" values(?,?,?,?,?,?)  ";
		DBUtil util = new DBUtil();
		Connection conn = util.openConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, v.getName());
			pstmt.setString(2, v.getIdno());
			pstmt.setString(3, v.getLicense());
			pstmt.setString(4, v.getCreateTime());
			pstmt.setString(5, v.getFaultRecord());
			pstmt.setDouble(6, v.getPenalty());

			if (pstmt.executeUpdate() > 0)
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return false;
	}
}
