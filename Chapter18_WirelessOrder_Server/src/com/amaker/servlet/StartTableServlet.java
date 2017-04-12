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
 * @author ������
 * �}��Servlet�A�N�ާ@����J����ƫO�s��OrderTbl��ƪ�
 */
public class StartTableServlet extends HttpServlet {
	// �غc�l
	public StartTableServlet() {
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
		// �I�\�ɶ�
		String orderTime = request.getParameter("orderTime");
		// �ާ@���s��
		String userId = request.getParameter("userId");
		// �ู
		String tableId = request.getParameter("tableId");
		// �H��
		String personNum = request.getParameter("personNum");
		// ���oDAO����
		OrderDao dao = new OrderDaoImpl();
		// ��Ҥƭq�����O
		Order o = new Order();
		// �]�w�q���ݩ�
		o.setOrderTime(orderTime);
		o.setPersonNum(Integer.parseInt(personNum));
		o.setUserId(Integer.parseInt(userId));
		o.setTableId(Integer.parseInt(tableId));
		// ��^�q��ID
		int id = dao.saveOrder(o);
		
		// ��s�\�બ�A�����H��
		dao.updateTableStatus(Integer.parseInt(tableId));
		// �Ǧ^ID
		out.print(id);
		out.flush();
		out.close();
	}

	// �^��Post�ШD
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

	// ��l��
	public void init() throws ServletException {
	}

}
