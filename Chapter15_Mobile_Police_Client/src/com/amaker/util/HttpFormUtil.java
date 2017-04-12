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
			String BOUNDARY = "�X�X�X7d4a6d158c9";
			String MULTIPART_FORM_DATA = "multipart/form-data";

			URL url = new URL(actionUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);// ���\��J
			conn.setDoOutput(true);// ���\��X
			conn.setUseCaches(false);// ���ϥ�Cache
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Charset", "UTF-8");
			conn.setRequestProperty("Content-Type", MULTIPART_FORM_DATA
					+ "; boundary=" + BOUNDARY);

			StringBuilder sb = new StringBuilder();

			// �W�Ǫ�����ѼƳ����A�榡�аѦҳ��`����
			for (Map.Entry<String, String> entry : params.entrySet()) {// �غc�������
				sb.append("�V");
				sb.append(BOUNDARY);
				sb.append("\r\n");
				sb.append("Content-Disposition: form-data; name=\""
						+ entry.getKey() + "\"\r\n\r\n");
				sb.append(entry.getValue());
				sb.append("\r\n");
			}
			DataOutputStream outStream = new DataOutputStream(conn
					.getOutputStream());
			outStream.write(sb.toString().getBytes());// �o�e�������

			// �W�Ǫ���󳡥��A�榡�аѦҳ��`����
			for (FormFile file : files) {
				StringBuilder split = new StringBuilder();
				split.append("�V");
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
			byte[] end_data = ("�V" + BOUNDARY + "�V\r\n").getBytes();// ��Ƶ����лx
			outStream.write(end_data);
			outStream.flush();
			int cah = conn.getResponseCode();
			if (cah != 200)
				throw new RuntimeException("�ШDurl����");
			
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