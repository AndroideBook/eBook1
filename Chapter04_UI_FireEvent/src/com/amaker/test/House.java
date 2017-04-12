package com.amaker.test;
import java.util.ArrayList;
import java.util.List;
// 事件來源
public class House {
     // 定義標示
     private boolean flag = false;
     // 監聽器列表
     private List listeners = new ArrayList();
    // 取得標示
     public boolean getFlag() {
		return flag;
     }
    // 設定標示
     public void setFlag(boolean flag) {
		this.flag = flag;
		if(flag){
			ntf();
		}
     }
     // 添加監聽器
     public void addListener(Listener l) {
		listeners.add(l);
     }
     // 取得監聽器
     public List getListeners() {
		return listeners;
	}
     // 通知方法
     public void ntf() {
    	// 遍覽監聽器
		for (int i = 0; i < listeners.size(); i++) {
          	// 取得監聽器
			Listener l = (Listener) listeners.get(i);
          	// 監聽
			l.listen(new FireEvent(this));
		}
	}
}
