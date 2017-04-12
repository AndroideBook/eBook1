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
 * @author ������
 * �b�k�H�f�d�ߡA���o�Ӥ����|
 */
public class PeopleImgServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// ���o�ШD�ѼơA�����Ҧr��
		String idno = request.getParameter("idno");
		// �I�s��xDao����d��
		PeopleDao dao = new PeopleDaoImpl();
		String path = dao.get(idno).getPic();
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// �^���ШD�A�N�d�ߵ��G�g�^�Τ��
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
