package com.amaker.test;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
/**
 * 
 * @author 郭宏志
 * 選項功能表範例
 */
public class MainActivity extends Activity {
	
	private static final int ITEM1 = Menu.FIRST;
	private static final int ITEM2 = Menu.FIRST+1;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

	/**
	 * 覆蓋該方法添加功能表項目
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, ITEM1, 0, "開始");
		menu.add(0, ITEM2, 0, "退出");
		return true;
	}
    
    /**
     * 覆蓋該方法，回應選單項目被單擊項目
     */
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
		case ITEM1:
			setTitle("開始遊戲！");
			break;
		case ITEM2:
			setTitle("退出！");
			break;
		}
    	return true;
    }
}
