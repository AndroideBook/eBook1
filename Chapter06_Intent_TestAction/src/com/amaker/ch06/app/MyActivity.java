package com.amaker.ch06.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
/**
 * @author ������
 * ����Intent Action �ݩ�
 */
public class MyActivity extends Activity {
	// �ŧiTextView
	private TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // �]�w���ϧG��
        setContentView(R.layout.my_layout);
        // �]�wIntent����
        Intent intent = getIntent();
        // ���oAction
        String action = intent.getAction();
        // ���oTextView
        tv = (TextView)findViewById(R.id.TextView01);
        // �]�w���e
        tv.setText(action);
    }
}