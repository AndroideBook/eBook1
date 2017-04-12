package com.amaker.ch06.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 
 * @author ������
 * ����Intent Action �ݩ�
 */
public class MainActivity extends Activity {
	// �ŧiButton
	private Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // �]�w�ثe�G������
        setContentView(R.layout.main);
        // ��Ҥ�Button
        btn = (Button)findViewById(R.id.Button01);
        btn.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
		        // �إ�Intent
		        Intent intent = new Intent();
		        // �]�wIntent Action�ݩ�
		        intent.setAction(Intent.ACTION_GET_CONTENT);
		        // �]�wIntent Type �ݩ�
		        intent.setType("vnd.android.cursor.item/phone");
		        // �Ұ�Activity
		        startActivity(intent);
			}
		});
    }
}