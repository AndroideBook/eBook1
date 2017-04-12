package com.amaker.util;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpFormUtil {

	public static String post(String actionUrl, Map<String, String> params,
			FormFile[] files) {
		try {
			String BOUNDARY = "———7d4a6d158c9";
			String MULTIPART_FORM_DATA = "multipart/form-data";

			URL url = new URL(actionUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);// 允許輸入
			conn.setDoOutput(true);// 允許輸出
			conn.setUseCaches(false);// 不使用Cache
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Charset", "UTF-8");
			conn.setRequestProperty("Content-Type", MULTIPART_FORM_DATA
					+ "; boundary=" + BOUNDARY);

			StringBuilder sb = new StringBuilder();

			// 上傳的表單參數部分，格式請參考章節說明
			for (Map.Entry<String, String> entry : params.entrySet()) {// 建構表單欄位
				sb.append("–");
				sb.append(BOUNDARY);
				sb.append("\r\n");
				sb.append("Content-Disposition: form-data; name=\""
						+ entry.getKey() + "\"\r\n\r\n");
				sb.append(entry.getValue());
				sb.append("\r\n");
			}
			DataOutputStream outStream = new DataOutputStream(conn
					.getOutputStream());
			outStream.write(sb.toString().getBytes());// 發送表單欄位

			// 上傳的文件部份，格式請參考章節說明
			for (FormFile file : files) {
				StringBuilder split = new StringBuilder();
				split.append("–");
				split.append(BOUNDARY);
				split.append("\r\n");
				split.append("Content-Disposition: form-data;name=\""
						+ file.getFormname() + "\";filename=\""
						+ file.getFilname() + "\"\r\n");
				split.append("Content-Type: " + file.getContentType()
						+ "\r\n\r\n");
				outStream.write(split.toString().getBytes());
				outStream.write(file.getData(), 0, file.getData().length);
				outStream.write("\r\n".getBytes());
			}
			byte[] end_data = ("–" + BOUNDARY + "–\r\n").getBytes();// 資料結束標誌
			outStream.write(end_data);
			outStream.flush();
			int cah = conn.getResponseCode();
			if (cah != 200)
				throw new RuntimeException("請求url失敗");
			
			InputStream is = conn.getInputStream();
			int ch;
			StringBuilder b = new StringBuilder();
			while ((ch = is.read()) != -1) {
				b.append((char) ch);
			}
			outStream.close();
			conn.disconnect();
			
			return b.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
