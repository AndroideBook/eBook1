package com.amaker.ch07.app;

import com.amaker.ch07.app.IPerson;

import android.os.RemoteException;
/**
 * 
 * @author ������
 * IPerson������@���O
 */
public class IPersonImpl extends IPerson.Stub{
	// �ŧi����ܼ�
	private int age;
	private String name;
	// @Override
	// ���name�Mage
	public String display() throws RemoteException {
		return "name:"+name+";age="+age;
	}
	// @Override
	// �]�wage
	public void setAge(int age) throws RemoteException {
		this.age = age;
	}
	// @Override
	// �]�wname
	public void setName(String name) throws RemoteException {
		this.name = name;
	}
}
