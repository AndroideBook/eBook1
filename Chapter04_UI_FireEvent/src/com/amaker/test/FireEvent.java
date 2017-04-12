package com.amaker.test;
import java.util.EventObject;
// 事件類別
public class FireEvent extends EventObject {
	// 定義事件來源物件
	private Object eventSource;
	// 建構子
	public FireEvent(Object obj) {
		super(obj);
		this.eventSource = obj;
	}
	// 取得事件來源
	public Object getEventSource() {
		return eventSource;
	}
}
