package com.amaker.test;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
/**
 * 
 * @author 郭宏志
 * 子功能表範例
 */
public class MainActivity extends Activity {
	
	private static final int ITEM1 = Menu.FIRST;
	private static final int ITEM2 = Menu.FIRST+1;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

	/**
	 * 覆蓋該方法添加子功能表項目
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		SubMenu file = menu.addSubMenu("檔案");
		SubMenu edit = menu.addSubMenu("編輯");
		file.add(0, ITEM1, 0, "新建");
		file.add(0, ITEM2, 0, "開啟");
		return true;
	}
    
    /**
     * 覆蓋該方法，回應功能表選項被單擊事件
     */
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
		case ITEM1:
			setTitle("新建檔案！");
			break;
		case ITEM2:
			setTitle("開啟檔案！");
			break;
		}
    	return true;
    }
}