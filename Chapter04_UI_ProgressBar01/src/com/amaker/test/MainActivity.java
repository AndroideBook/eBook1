package com.amaker.test;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button myBtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        myBtn = (Button)findViewById(R.id.Button01);
        myBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showDialog(0);
			}
		});
    }

	@Override
	protected Dialog onCreateDialog(int id) {
		ProgressDialog dialog = new ProgressDialog(this);
		// 可以不顯示標題
		dialog.setTitle("測試對話框");
		dialog.setIndeterminate(true);
		dialog.setMessage("正在載入程式，請稍候！");
		dialog.setCancelable(true);
		
		dialog.setButton(Dialog.BUTTON_POSITIVE, "確定", 
				new DialogInterface.OnClickListener() {
					//@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				}
		);
		
		return dialog;
	}
}