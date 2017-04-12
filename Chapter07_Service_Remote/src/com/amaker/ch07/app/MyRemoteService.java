package com.amaker.ch07.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.amaker.ch07.app.IPerson.Stub;

/**
 * @author 郭宏志
 * 使用Service將介面曝露給用戶端
 */
public class MyRemoteService extends Service{
	// 宣告IPerson介面
	private Stub iPerson = new IPersonImpl();
	@Override
	public IBinder onBind(Intent intent) {
		return iPerson;
	}
}
