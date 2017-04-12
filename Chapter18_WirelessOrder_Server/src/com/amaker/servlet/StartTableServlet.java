package com.amaker.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.dao.OrderDao;
import com.amaker.dao.impl.OrderDaoImpl;
import com.amaker.entity.Order;

/**
 * @author 郭宏志
 * 開桌Servlet，將操作員輸入的資料保存到OrderTbl資料表
 */
public class StartTableServlet extends HttpServlet {
	// 建構子
	public StartTableServlet() {
		super();
	}

	// 銷毀方法
	public void destroy() {
		super.destroy(); 
	}

	// 回應Get請求
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// 點餐時間
		String orderTime = request.getParameter("orderTime");
		// 操作員編號
		String userId = request.getParameter("userId");
		// 桌號
		String tableId = request.getParameter("tableId");
		// 人數
		String personNum = request.getParameter("personNum");
		// 取得DAO介面
		OrderDao dao = new OrderDaoImpl();
		// 實例化訂單類別
		Order o = new Order();
		// 設定訂單屬性
		o.setOrderTime(orderTime);
		o.setPersonNum(Integer.parseInt(personNum));
		o.setUserId(Integer.parseInt(userId));
		o.setTableId(Integer.parseInt(tableId));
		// 返回訂單ID
		int id = dao.saveOrder(o);
		
		// 更新餐桌狀態為有人��
		dao.updateTableStatus(Integer.parseInt(tableId));
		// 傳回ID
		out.print(id);
		out.flush();
		out.close();
	}

	// 回應Post請求
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

	// 初始化
	public void init() throws ServletException {
	}

}
