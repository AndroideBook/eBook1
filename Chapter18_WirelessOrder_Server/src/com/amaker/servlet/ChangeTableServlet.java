package com.amaker.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.dao.ChangeTableDao;
import com.amaker.dao.impl.ChangeTableDaoImpl;
/**
 * @author 郭宏志
 * 完成轉桌功能
 */
public class ChangeTableServlet extends HttpServlet {
	// 建構子
	public ChangeTableServlet() {
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
		// 取得請求參數
		String orderId = request.getParameter("orderId");
		String tableId = request.getParameter("tableId");
		// 實例化ChangeTableDao
		ChangeTableDao dao = new ChangeTableDaoImpl();
		// 呼叫轉桌方法
		dao.changeTable(Integer.parseInt(orderId), Integer.parseInt(tableId));
		// 返回用戶端資料
		out.println("更換成功！");
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
