package com.amaker.test;
import java.util.EventObject;
// �ƥ����O
public class FireEvent extends EventObject {
	// �w�q�ƥ�ӷ�����
	private Object eventSource;
	// �غc�l
	public FireEvent(Object obj) {
		super(obj);
		this.eventSource = obj;
	}
	// ���o�ƥ�ӷ�
	public Object getEventSource() {
		return eventSource;
	}
}
