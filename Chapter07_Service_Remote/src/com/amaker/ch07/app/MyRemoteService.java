package com.amaker.ch07.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.amaker.ch07.app.IPerson.Stub;

/**
 * @author ������
 * �ϥ�Service�N�����n�S���Τ��
 */
public class MyRemoteService extends Service{
	// �ŧiIPerson����
	private Stub iPerson = new IPersonImpl();
	@Override
	public IBinder onBind(Intent intent) {
		return iPerson;
	}
}
