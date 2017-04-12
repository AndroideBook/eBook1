package com.amaker.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.dao.PeopleDao;
import com.amaker.dao.impl.PeopleDaoImpl;

/**
 * 
 * @author 郭宏志
 * 在逃人口查詢，取得照片路徑
 */
public class PeopleImgServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 取得請求參數，身份證字號
		String idno = request.getParameter("idno");
		// 呼叫後台Dao執行查詢
		PeopleDao dao = new PeopleDaoImpl();
		String path = dao.get(idno).getPic();
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// 回應請求，將查詢結果寫回用戶端
		out.write(path);
		
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
	public void init() throws ServletException {
	}
	public PeopleImgServlet() {
		super();
	}
	public void destroy() {
		super.destroy();
	}

}
