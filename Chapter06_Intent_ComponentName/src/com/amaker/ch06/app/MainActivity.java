package com.amaker.ch06.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author ������
 * ����Intent��ComponentName�ݩ�
 */
public class MainActivity extends Activity {
	private Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // �]�w���ϧG��
        setContentView(R.layout.main);
        // ��Ҥ�Button
        btn = (Button)findViewById(R.id.myButton01);
        // �K�[�����ʾ���
        btn.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				// ��ҤƤ���W��
				ComponentName cn = new ComponentName(MainActivity.this, "com.amaker.ch06.app1.MyActivity");
				// ��Ҥ�Intent
		        Intent intent = new Intent();
		        // ��Intent�]�w����W���ݩ�
		        intent.setComponent(cn);
		        // �Ұ�Activity
		        startActivity(intent);
			}
		});
    }
}