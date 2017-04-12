package com.amaker.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
/**
 * 
 * @author amaker
 * AlertDialog 測試
 */
public class MainActivity extends Activity {
	
	private TextView myTV;
	private Button myBtn;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        myTV = (TextView)findViewById(R.id.TextView01);
        myBtn = (Button)findViewById(R.id.Button01);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
        myBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				builder.setMessage("真的要刪除該記錄嗎？").setPositiveButton("是", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						myTV.setText("刪除成功！");
					}
				}).setNegativeButton("否", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						myTV.setText("取消刪除！");
					}
				});
				AlertDialog ad = builder.create();
				ad.show();
			}
		}
        );
    }
}