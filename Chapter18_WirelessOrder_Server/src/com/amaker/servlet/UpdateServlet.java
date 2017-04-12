package com.amaker.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.dao.UpdateDao;
import com.amaker.dao.impl.UpdateDaoImpl;
import com.amaker.entity.Menu;
import com.amaker.entity.Table;

/**
 * @author ������
 * ������Ƨ�s�\��
 */
public class UpdateServlet extends HttpServlet {
	// �غc�l
	public UpdateServlet() {
		super();
	}
	// �P����k
	public void destroy() {
		super.destroy();
	}
	// �^��Get�ШD
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();

		String srcTable = request.getParameter("src");	//added
		
		// ��Ҥ�dao
		UpdateDao dao = new UpdateDaoImpl();
		
		if (srcTable.equals("table")) {	//added
			// ���o�\��C��
			List list = dao.getTableList();
			
			// �զ�XML�榡���
			out.println("<?xml version='1.0' encoding='UTF-8'?>");
			// �ڸ`�I
			out.println("<tablelist>");
			for (int i = 0; i <list.size(); i++) {
				Table m = (Table)list.get(i);
				out.println("<table>");
					// ID
					out.print("<id>");
						out.print(m.getId());
					out.println("</id>");
					// �\��s��
					out.print("<num>");
						out.print(m.getNum());
					out.println("</num>");

					// �y�z
					out.print("<description>");
						out.print(m.getDescription());
					out.println("</description>");
					
				out.println("</table>");
			}
			out.println("</tablelist>");
		} else {
			// ���o���C��
			List list = dao.getMenuList();
			
			// �զ�XML�榡���
			out.println("<?xml version='1.0' encoding='UTF-8'?>");
			// �ڸ`�I
			out.println("<menulist>");
			for (int i = 0; i <list.size(); i++) {
				Menu m = (Menu)list.get(i);
				out.println("<menu>");
					// ���s��
					out.print("<id>");
						out.print(m.getId());
					out.println("</id>");
					// ����
					out.print("<typeId>");
						out.print(m.getTypeId());
					out.println("</typeId>");
					// �W��
					out.print("<name>");
						out.print(m.getName());
					out.println("</name>");
					// �Ϥ����|
					out.print("<pic>");
						out.print(m.getPic());
					out.println("</pic>");
					// ����
					out.print("<price>");
						out.print(m.getPrice());
					out.println("</price>");
					// �Ƶ�
					out.print("<remark>");
						out.print(m.getRemark());
					out.println("</remark>");
					
				out.println("</menu>");
			}
			out.println("</menulist>");
		}
		

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
