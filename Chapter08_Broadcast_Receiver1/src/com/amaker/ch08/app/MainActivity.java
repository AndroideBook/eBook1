package com.amaker.ch08.app;

import com.amaker.ch08.app.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/**
 * 
 * @author ������
 * �o�X�s��
 */
public class MainActivity extends Activity {
	// �w�q�@��Action�`��
	private static final String MY_ACTION = "com.amaker.ch08.action.MY_ACTION";
	// �w�q�@��Button����
	private Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // �]�w�ثe�G������
        setContentView(R.layout.main);
        btn = (Button)findViewById(R.id.Button01);
        // �����s�]�w������ť��
        btn.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				// ��Ҥ�Intent����
		        Intent intent = new Intent();
		        // �]�wIntent action�ݩ�
		        intent.setAction(MY_ACTION);
		        // ��Intent�K�[���[�T��
		        intent.putExtra("msg", "���������A�ڬO���J�A����Ц^���A����Ц^���I");
		        // �o�X�s��
		        sendBroadcast(intent);
			}
		});
    }
}