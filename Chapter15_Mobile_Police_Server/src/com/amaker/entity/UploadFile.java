package com.amaker.entity;

/**
 * 
 * @author 郭宏志
 * 實體類別，對應uploadfiletbl資料表結構
 */
public class UploadFile {

	private int id;			// 編號
	private String uploadTime;	// 上傳時間
	private String fileDesc;	// 檔案描述
	private String filePath;	// 上傳檔案路徑

	public String getFileDesc() {
		return fileDesc;
	}
	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}

	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

}
