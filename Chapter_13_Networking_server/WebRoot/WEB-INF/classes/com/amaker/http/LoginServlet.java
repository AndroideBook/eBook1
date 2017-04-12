package com.amaker.http;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author ³¢§»§Ó
 * ¦^À³µn¿ýServlet
 */
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username+":"+password);
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String msg = null;
		if(username!=null&&username.equals("admin")&&password!=null&&password.equals("1")){
			msg = "µn¿ý¦¨¥\!";
		}else{
			msg = "µn¿ý¥¢±Ñ!";
		}
		out.print(msg);
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
