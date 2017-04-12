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
	// �w�qAction �ݩʱ`��
	public static final String MY_ACTION="com.amaker.ch06.app.MY_ACTION";
	// �ŧiButton
	private Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // �]�w�G������
        setContentView(R.layout.main);
        // ��Ҥ�Button
        btn = (Button)findViewById(R.id.Button01);
        btn.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				// ��Ҥ�Intent
		        Intent intent = new Intent();
		        // ��Intent�]�wAction�ݩ�
		        intent.setAction(MY_ACTION);
		        // �Ұ�Activity
		        startActivity(intent);
			}
		});
    }
}