package com.amaker.util;

public class FormFile {
	/* 上傳的資料 */
	private byte[] data;
	/* 檔案名稱 */
	private String filname;
	/* 表單欄位名稱 */
	private String formname;
	/* 內容類型 */
	private String contentType = "application/octet-stream"; // 需要查詢相關的資料

	public FormFile(String filname, byte[] data, String formname,
			String contentType) {
		this.data = data;
		this.filname = filname;
		this.formname = formname;
		if (contentType != null)
			this.contentType = contentType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getFilname() {
		return filname;
	}

	public void setFilname(String filname) {
		this.filname = filname;
	}

	public String getFormname() {
		return formname;
	}

	public void setFormname(String formname) {
		this.formname = formname;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

}
