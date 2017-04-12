package com.amaker.test;
public class Test {
	public static void main(String[] args) {
          // 事件來源房子
          House h = new House();
          // 添加監聽器
          h.addListener(new FireListener());
          // 匿名內部類別實作
          h.addListener(new Listener(){
              public void listen(FireEvent e) {
				System.out.println("冒煙啦！");
              }
          });
          h.setFlag(true);
	}
}
