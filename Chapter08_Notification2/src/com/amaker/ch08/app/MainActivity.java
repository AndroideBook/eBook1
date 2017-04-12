package com.amaker.ch08.app;

import com.amaker.ch08.app.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/**
 * @author ������
 * ���ռs���M�q��
 */
public class MainActivity extends Activity {
	// �ŧiButton
	private Button btn;
	// �w�qBroadcast Receiver action
	private static final String MY_ACTION = "com.amaker.ch08.app.MY_ACTION";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // �]�w�ثe�G������
        setContentView(R.layout.main);
        // ��Ҥ�Button
        btn = (Button)findViewById(R.id.Button01);
        // �K�[�ƥ��ť��
        btn.setOnClickListener(listener);
    }
    // �إߨƥ��ť��
    private OnClickListener listener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
			// ��Ҥ�Intent
			Intent intent = new Intent();
			// �]�wIntent action�ݩ�
			intent.setAction(MY_ACTION);
			// �o�X�q��
			sendBroadcast(intent);
		}
	};
}