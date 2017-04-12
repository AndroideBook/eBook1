package com.amaker.ch06.app1;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.amaker.ch06.app.R;

/**
 * @author ������
 * ����Intent��ComponentName�ݩ�
 */
public class MyActivity extends Activity {
	// �ť[TextView
	private TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	// �]�w���ϧG��
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_layout);
        // ���oIntent
        Intent intent = this.getIntent();
        // ���o����W�٪���
        ComponentName cn = intent.getComponent();
        // ���o�M��W��
        String packageName = cn.getPackageName();
        // ���o���O�W��
        String className = cn.getClassName();
        // ��Ҥ�TextView
        tv = (TextView)findViewById(R.id.TextView01);
        // ���
        tv.setText("�M��W�١G"+packageName+"\n"+"���O�W�١G"+className);
    }
}