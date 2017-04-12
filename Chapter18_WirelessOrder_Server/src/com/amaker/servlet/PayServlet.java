package com.amaker.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.dao.PayDao;
import com.amaker.dao.impl.PayDaoImpl;
import com.amaker.entity.QueryOrder;
import com.amaker.entity.QueryOrderDetail;

/**
 * @author 郭宏志
 * 根據訂單編號，查詢訂單明細
 */
public class PayServlet extends HttpServlet {
	// 建構子
	public PayServlet() {
		super();
	}

	// 銷毀方法
	public void destroy() {
		super.destroy(); 
	}

	// 回應Get請求
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		// 實例化DAO
		PayDao dao = new PayDaoImpl();
		// 取得訂單ID
		String id = request.getParameter("id");
		// 查詢訂單資訊
		QueryOrder qo = dao.getOrderById(Integer.parseInt(id));
		// 查詢訂單明細
		List list = dao.getOrderDetailList(Integer.parseInt(id));
		
		// 組成HTML頁面
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD></HEAD>");
		out.println("  <BODY>");
		out.print("<table>");
			out.print("<tr>");
			
				out.print("<th>");
					out.print("訂單編號");
				out.print("</th>");
				
				out.print("<th>");
					out.print("下單時間");
				out.print("</th>");
				
				out.print("<th>");
					out.print("服務員");
				out.print("</th>");
				
				out.print("<th>");
					out.print("人數");
				out.print("</th>");
				
				out.print("<th>");
					out.print("桌號");
				out.print("</th>");
				
			out.print("</tr>");
			
			out.print("<tr>");
				
				out.print("<td>");
					out.print(id);
				out.print("</td>");
				
				out.print("<td>");
					out.print(qo.getOrderTime());
				out.print("</td>");
				
				out.print("<td>");
					out.print(qo.getName());
				out.print("</td>");
				
				out.print("<td>");
					out.print(qo.getPersonNum());
				out.print("</td>");
				
				out.print("<td>");
					out.print(qo.getTableId());
				out.print("</td>");
				
			out.print("</tr>");
			
			
			out.print("<tr>");
			
			out.print("<th>");
				out.print("菜名");
			out.print("</th>");
			
			out.print("<th>");
				out.print("價格");
			out.print("</th>");
			
			out.print("<th>");
				out.print("數量");
			out.print("</th>");
			
			out.print("<th>");
				out.print("總計");
			out.print("</th>");
			
			out.print("<th>");
				out.print("備註");
			out.print("</th>");
			
		out.print("</tr>");
			
			for (int i = 0; i < list.size(); i++) {
				QueryOrderDetail qod = (QueryOrderDetail) list.get(i);
				String name = qod.getName();
				int price = qod.getPrice();
				int num = qod.getNum();
				int total = qod.getTotal();
				String remark = qod.getRemark();
				
				out.print("<tr>");
				
					out.print("<td>");
						out.print(name);
					out.print("</td>");
					
					out.print("<td>");
						out.print(price==0? "" :price+"");
					out.print("</td>");
					
					out.print("<td>");
						out.print(num==0? "" :num+"");
					out.print("</td>");
					
					out.print("<td>");
						out.print(total);
					out.print("</td>");
					
					out.print("<td>");
						out.print(remark);
					out.print("</td>");
					
				out.print("</tr>");
			}
		out.print("</table>");
		out.println("  </BODY>");
		out.println("</HTML>");
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
