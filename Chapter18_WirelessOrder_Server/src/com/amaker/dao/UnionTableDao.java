package com.amaker.dao;

import java.util.List;
// 併桌功能介面
public interface UnionTableDao {
	// 取得桌位列表
	public  List getTableList();
	// 更新合併後的資料
	public void updateStatus(int tableId1,int tableId2);
}
