package com.amaker.test;
import java.util.EventListener;
// 事件監聽器介面
public interface Listener extends EventListener{
	// 事件方法
	public void listen(FireEvent e);
}
