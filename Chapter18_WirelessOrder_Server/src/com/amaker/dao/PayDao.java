package com.amaker.dao;

import java.util.List;

import com.amaker.entity.QueryOrder;

public interface PayDao {
	// �d�߭q���T
	public QueryOrder getOrderById(int id);
	// �d�߭q�����
	public List getOrderDetailList(int id);
	// ����
	public void pay(int id);
}
