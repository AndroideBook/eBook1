package com.amaker.dao;

import com.amaker.entity.VehicleFaultInfo;
/**
 * 
 * @author 郭宏志
 * 儲存車輛的Dao介面
 */
public interface VehicleDao {
	public boolean save(VehicleFaultInfo v);
}
