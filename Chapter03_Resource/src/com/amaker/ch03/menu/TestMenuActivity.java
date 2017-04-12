package com.amaker.ch03.menu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.amaker.test.R;
/**
 * @author 郭宏志
 * 測試功能表資源
 */
public class TestMenuActivity extends Activity {
	private MenuInflater mi;
    @Override
   public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.test_menu);
       mi = new MenuInflater(this);
    }
    
    /*
     * 建立功能表
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        mi.inflate(R.menu.file_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
		case R.id.about:
			aboutAlert("本範例展示的是如何使用XML功能表資源來定義功能表！");
			break;
		case R.id.exit:
			exitAlert("真的要退出嗎？");
			break;
		}
    	return true;
    }
    
	// 顯示對話框
	private void exitAlert(String msg){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(msg)
		       .setCancelable(false)
		       .setPositiveButton("確定", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   finish();
		           }
		       }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   return;
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	// 顯示對話框
	private void aboutAlert(String msg){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(msg)
		       .setCancelable(false)
		       .setPositiveButton("確定", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	}

}