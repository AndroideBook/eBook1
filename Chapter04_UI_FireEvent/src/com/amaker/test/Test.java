package com.amaker.test;
public class Test {
	public static void main(String[] args) {
          // �ƥ�ӷ��Фl
          House h = new House();
          // �K�[��ť��
          h.addListener(new FireListener());
          // �ΦW�������O��@
          h.addListener(new Listener(){
              public void listen(FireEvent e) {
				System.out.println("�_�ϰաI");
              }
          });
          h.setFlag(true);
	}
}
