package com.amaker.dao;

import com.amaker.entity.Order;
import com.amaker.entity.OrderDetail;
//import com.amaker.entity.*;

public interface OrderDao {
	// �O�s�}���T
	public int saveOrder(Order o );
	// �O�s�I��C���T
	public void saveOrderDetail(OrderDetail od);
	// ��s�ู���A�A���H
	public void updateTableStatus(int tableId);
	// ��s�ู���A�A�Ŧ�
	public void updateTableStatus2(int orderId);
}
