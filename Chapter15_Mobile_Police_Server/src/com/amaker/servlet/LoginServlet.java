package com.amaker.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.dao.UserDao;
import com.amaker.dao.impl.UserDaoImpl;
import com.amaker.entity.User;
/**
 * 
 * @author 郭宏志
 * 回應 Android用戶端送來的請求
 */
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		UserDao dao = new UserDaoImpl();
		// 取得用戶端請求參數
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User u = dao.login(username, password);
		if(u!=null){
			// 回應用戶端內容，登錄成功
			out.print("1");
		}else{
			// 回應用戶端內容，登錄失敗
			out.print("0");
		}
		out.flush();
		out.close();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
	public void init() throws ServletException {
	}
	
	public LoginServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

}
