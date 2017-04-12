package com.amaker.ch07.app;

import com.amaker.ch07.app.IPerson;

import android.os.RemoteException;
/**
 * 
 * @author 郭宏志
 * IPerson介面實作類別
 */
public class IPersonImpl extends IPerson.Stub{
	// 宣告兩個變數
	private int age;
	private String name;
	// @Override
	// 顯示name和age
	public String display() throws RemoteException {
		return "name:"+name+";age="+age;
	}
	// @Override
	// 設定age
	public void setAge(int age) throws RemoteException {
		this.age = age;
	}
	// @Override
	// 設定name
	public void setName(String name) throws RemoteException {
		this.name = name;
	}
}
