package com.amaker.dao;

import java.util.List;

import com.amaker.entity.QueryOrder;

public interface PayDao {
	// 查詢訂單資訊
	public QueryOrder getOrderById(int id);
	// 查詢訂單明細
	public List getOrderDetailList(int id);
	// 結算
	public void pay(int id);
}
