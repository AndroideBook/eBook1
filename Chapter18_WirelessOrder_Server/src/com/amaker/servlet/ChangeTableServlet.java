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
 * @author ������
 * �������\��
 */
public class ChangeTableServlet extends HttpServlet {
	// �غc�l
	public ChangeTableServlet() {
		super();
	}
	// �P����k
	public void destroy() {
		super.destroy(); 
	}
	// �^��Get�ШD
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// ���o�ШD�Ѽ�
		String orderId = request.getParameter("orderId");
		String tableId = request.getParameter("tableId");
		// ��Ҥ�ChangeTableDao
		ChangeTableDao dao = new ChangeTableDaoImpl();
		// �I�s����k
		dao.changeTable(Integer.parseInt(orderId), Integer.parseInt(tableId));
		// ��^�Τ�ݸ��
		out.println("�󴫦��\�I");
		out.flush();
		out.close();
	}
	// �^��Post�ШD
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
	// ��l�Ƥ�k
	public void init() throws ServletException {
	}

}
