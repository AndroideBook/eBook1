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
 * @author 郭宏志
 * 完成資料更新功能
 */
public class UpdateServlet extends HttpServlet {
	// 建構子
	public UpdateServlet() {
		super();
	}
	// 銷毀方法
	public void destroy() {
		super.destroy();
	}
	// 回應Get請求
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();

		String srcTable = request.getParameter("src");	//added
		
		// 實例化dao
		UpdateDao dao = new UpdateDaoImpl();
		
		if (srcTable.equals("table")) {	//added
			// 取得餐桌列表
			List list = dao.getTableList();
			
			// 組成XML格式資料
			out.println("<?xml version='1.0' encoding='UTF-8'?>");
			// 根節點
			out.println("<tablelist>");
			for (int i = 0; i <list.size(); i++) {
				Table m = (Table)list.get(i);
				out.println("<table>");
					// ID
					out.print("<id>");
						out.print(m.getId());
					out.println("</id>");
					// 餐桌編號
					out.print("<num>");
						out.print(m.getNum());
					out.println("</num>");

					// 描述
					out.print("<description>");
						out.print(m.getDescription());
					out.println("</description>");
					
				out.println("</table>");
			}
			out.println("</tablelist>");
		} else {
			// 取得菜單列表
			List list = dao.getMenuList();
			
			// 組成XML格式資料
			out.println("<?xml version='1.0' encoding='UTF-8'?>");
			// 根節點
			out.println("<menulist>");
			for (int i = 0; i <list.size(); i++) {
				Menu m = (Menu)list.get(i);
				out.println("<menu>");
					// 菜色編號
					out.print("<id>");
						out.print(m.getId());
					out.println("</id>");
					// 分類
					out.print("<typeId>");
						out.print(m.getTypeId());
					out.println("</typeId>");
					// 名稱
					out.print("<name>");
						out.print(m.getName());
					out.println("</name>");
					// 圖片路徑
					out.print("<pic>");
						out.print(m.getPic());
					out.println("</pic>");
					// 價格
					out.print("<price>");
						out.print(m.getPrice());
					out.println("</price>");
					// 備註
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
	// 回應Post請求
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
	// 初始化方法
	public void init() throws ServletException {
	}
}
