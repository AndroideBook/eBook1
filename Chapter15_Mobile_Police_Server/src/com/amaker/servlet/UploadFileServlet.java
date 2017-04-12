package com.amaker.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.amaker.dao.UploadFileDao;
import com.amaker.dao.impl.UploadFileDaoImpl;
import com.amaker.entity.UploadFile;

/**
 * 
 * @author 郭宏志
 * 檔案上傳Servlet
 */
public class UploadFileServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 取得檔案項目工廠
		FileItemFactory factory = new DiskFileItemFactory();
		// 取得Servlet檔案上傳物件
		ServletFileUpload upload = new ServletFileUpload(factory);

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		UploadFileDao dao = new UploadFileDaoImpl();
		UploadFile uf = new UploadFile();
		String tempPath = "";
		try {
			// 解析請求
			List items = upload.parseRequest(request);
			Iterator iter = items.iterator();
			// 進入迴圈
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				// 判斷是否為表單欄位
				if (item.isFormField()) {
					String fieldName = item.getFieldName();
					if(fieldName.toLowerCase().equals("uploadtime")){
						uf.setUploadTime(item.getString());
					}
					
					if(fieldName.toLowerCase().equals("filedesc")){
						uf.setFileDesc(item.getString());
					}
				} else {
					// 是否為檔案
					String fullFileName = item.getName();
					int idx = fullFileName.lastIndexOf(".");
					String subfix = fullFileName.substring(idx);
					String fileName = new Date().getTime() + subfix;
					String path = this.getServletContext().getRealPath(
							"\\upload");
					
					File f = new File(path + "\\" + fileName);
					tempPath = "\\upload\\"+fileName;
					item.write(f);

				}
			}
			
			uf.setFilePath(tempPath);
			dao.save(uf);

		} catch (Exception e) {
			e.printStackTrace();
			out.print("0");
		}

		out.print("1");

		out.flush();
		out.close();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public UploadFileServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void init() throws ServletException {

	}

}
