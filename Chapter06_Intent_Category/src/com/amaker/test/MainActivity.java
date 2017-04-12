package com.amaker.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author ������
 * ����Intent �� Category�ݩ�
 */
public class MainActivity extends Activity {
	// �ŧi Button
	private Button b1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // �]�w�ثe�G��
        setContentView(R.layout.main);
        // ��Ҥ� Button
        b1 = (Button)findViewById(R.id.Button01);
        // ��Button�K�[��ť����
        b1.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				// ��Ҥ�Intent
		        Intent i = new Intent();
		        // �K�[Action�ݩ�
		        i.setAction(Intent.ACTION_MAIN);
		        // �K�[Category�ݩ�
		        i.addCategory(Intent.CATEGORY_HOME);
		        // �Ұ�Activity
		        startActivity(i);
			}
		});
    }
}