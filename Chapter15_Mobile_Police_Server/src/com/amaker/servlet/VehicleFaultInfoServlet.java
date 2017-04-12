package com.amaker.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.dao.VehicleDao;
import com.amaker.dao.impl.VehicleFaultInfoDaoImpl;
import com.amaker.entity.VehicleFaultInfo;

/**
 * 
 * @author 郭宏志
 * 回應機動車違規資訊保存的Servlet
 */
public class VehicleFaultInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		// 取得請求參數
		String name = request.getParameter("name");
		String idno = request.getParameter("idno");
		String license = request.getParameter("license");
		String faultRecord = request.getParameter("faultRecord");

		String createTime = request.getParameter("createTime");
		double penalty = Double.parseDouble(request.getParameter("penalty"));
		
		// 封裝於實體類別
		VehicleFaultInfo v = new VehicleFaultInfo();

		v.setName(name);
		v.setCreateTime(createTime);
		v.setIdno(idno);

		v.setFaultRecord(faultRecord);
		v.setPenalty(penalty);
		v.setLicense(license);
		// 呼叫DAO儲存資訊
		VehicleDao dao = new VehicleFaultInfoDaoImpl();
		if (dao.save(v))
			// 回應儲存結果：成功
			out.print("1");
		else
			// 失敗
			out.print("0");

		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void init() throws ServletException {
		
	}
	public VehicleFaultInfoServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

}
