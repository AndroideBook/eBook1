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
 * @author ������
 * ��@�d��\��
 */
public class CheckTableServlet extends HttpServlet {
	// �غc�l
	public CheckTableServlet() {
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
		// ��Ҥ�CheckTableDao
		CheckTableDao dao = new CheckTableDaoImpl();
		// ���o�\���ƦC��
		List list = dao.getTableList();
		// �ഫ���r��
		String msg = build(list);
		// ��^�Τ��
		out.print(msg);
		out.flush();
		out.close();
	}
	// �NList�C���ন�r��
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
	// �^��Post�ШD
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
	// ��l��
	public void init() throws ServletException {
	}

}
