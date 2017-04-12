package com.amaker.entity;

/**
 * 
 * @author hz.guo
 *	用來封裝UserTbl資料表的實體類別
 */
public class User {
	// 編號
	private int id;
	// 帳號
	private String account;
	// 密碼
	private String password;
	// 用戶名稱
	private String name;
	// 性別
	private String gender;
	// 權限
	private int permission;
	// 備註
	private String remark;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPermission() {
		return permission;
	}
	public void setPermission(int permission) {
		this.permission = permission;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
