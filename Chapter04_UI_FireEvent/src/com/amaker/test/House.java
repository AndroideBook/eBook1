package com.amaker.test;
import java.util.ArrayList;
import java.util.List;
// �ƥ�ӷ�
public class House {
     // �w�q�Х�
     private boolean flag = false;
     // ��ť���C��
     private List listeners = new ArrayList();
    // ���o�Х�
     public boolean getFlag() {
		return flag;
     }
    // �]�w�Х�
     public void setFlag(boolean flag) {
		this.flag = flag;
		if(flag){
			ntf();
		}
     }
     // �K�[��ť��
     public void addListener(Listener l) {
		listeners.add(l);
     }
     // ���o��ť��
     public List getListeners() {
		return listeners;
	}
     // �q����k
     public void ntf() {
    	// �M����ť��
		for (int i = 0; i < listeners.size(); i++) {
          	// ���o��ť��
			Listener l = (Listener) listeners.get(i);
          	// ��ť
			l.listen(new FireEvent(this));
		}
	}
}
