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
 * @author ������
 * ��@����\��
 */
public class PayMoneyServlet extends HttpServlet {
	// �غc�l
	public PayMoneyServlet() {
		super();
	}
	// �P����k
	public void destroy() {
		super.destroy(); 
	}
	// �^��Get�ШD
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		// ��Ҥ�PayDao
		PayDao dao = new PayDaoImpl();
		// ���o�q��s��
		String id = request.getParameter("id");
		// ����
		dao.pay(Integer.parseInt(id));
		// ��Ҥ�OrderDao
		OrderDao dao2 = new OrderDaoImpl();
		// �N�\�બ�A��s���Ŧ�
		dao2.updateTableStatus2(Integer.parseInt(id));
		// �V�Τ�ݵo�e��T
		out.print("�w����I");
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
