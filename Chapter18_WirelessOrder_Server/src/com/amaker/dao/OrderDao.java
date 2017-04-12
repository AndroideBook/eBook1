package com.amaker.dao;

import com.amaker.entity.Order;
import com.amaker.entity.OrderDetail;
//import com.amaker.entity.*;

public interface OrderDao {
	// 保存開桌資訊
	public int saveOrder(Order o );
	// 保存點菜列表資訊
	public void saveOrderDetail(OrderDetail od);
	// 更新桌號狀態，有人
	public void updateTableStatus(int tableId);
	// 更新桌號狀態，空位
	public void updateTableStatus2(int orderId);
}
