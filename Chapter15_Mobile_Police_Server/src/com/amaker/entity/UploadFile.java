package com.amaker.entity;

/**
 * 
 * @author ������
 * �������O�A����uploadfiletbl��ƪ��c
 */
public class UploadFile {

	private int id;			// �s��
	private String uploadTime;	// �W�Ǯɶ�
	private String fileDesc;	// �ɮ״y�z
	private String filePath;	// �W���ɮ׸��|

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
