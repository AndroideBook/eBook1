package com.amaker.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.dao.CheckTableDao;
import com.amaker.dao.impl.CheckTableDaoImpl;
import com.amaker.entity.CheckTable;
/**
 * @author 郭宏志
 * 實作查桌功能
 */
public class CheckTableServlet extends HttpServlet {
	// 建構子
	public CheckTableServlet() {
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
		// 實例化CheckTableDao
		CheckTableDao dao = new CheckTableDaoImpl();
		// 取得餐桌資料列表
		List list = dao.getTableList();
		// 轉換為字串
		String msg = build(list);
		// 返回用戶端
		out.print(msg);
		out.flush();
		out.close();
	}
	// 將List列表轉成字串
	private String build(List list){
		String msg = "";
		for (int i = 0; i < list.size(); i++) {
			CheckTable ct = (CheckTable) list.get(i);
			int num = ct.getNum();
			int flag = ct.getFlag();
			msg+=num+","+flag;
			if(i<(list.size()-1))msg+=";";
		}
		return msg;
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
