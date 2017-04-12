package com.amaker.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.dao.OrderDao;
import com.amaker.dao.PayDao;
import com.amaker.dao.impl.OrderDaoImpl;
import com.amaker.dao.impl.PayDaoImpl;
/**
 * @author 郭宏志
 * 實作結算功能
 */
public class PayMoneyServlet extends HttpServlet {
	// 建構子
	public PayMoneyServlet() {
		super();
	}
	// 銷毀方法
	public void destroy() {
		super.destroy(); 
	}
	// 回應Get請求
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		// 實例化PayDao
		PayDao dao = new PayDaoImpl();
		// 取得訂單編號
		String id = request.getParameter("id");
		// 結算
		dao.pay(Integer.parseInt(id));
		// 實例化OrderDao
		OrderDao dao2 = new OrderDaoImpl();
		// 將餐桌狀態更新為空位
		dao2.updateTableStatus2(Integer.parseInt(id));
		// 向用戶端發送資訊
		out.print("已結算！");
		out.flush();
		out.close();
	}
	// 回應Post請求
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
	// 初始化方法
	public void init() throws ServletException {
	}
}
