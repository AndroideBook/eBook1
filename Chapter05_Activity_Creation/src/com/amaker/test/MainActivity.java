package com.amaker.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

// �~��Activity
public class MainActivity extends Activity {
	// �ŧi�n�ϥΪ�����
	private TextView myTextView;
	private Button myButton;
    // �л\onCreate��k
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // �]�w�ثe����
        setContentView(R.layout.main);
        // �z�LfindViewById()��k��ҤƤ���
        myTextView = (TextView) findViewById(R.id.TextView01);
        myButton = (Button) findViewById(R.id.Button01);
    }
}