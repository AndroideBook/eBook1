package com.amaker.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SecondActivity extends Activity {
    /** Called when the activity is first created. */
	private Button b2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        
        b2 = (Button) findViewById(R.id.Button02);
        // �^������ƥ�
        b2.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				// ��ܤ覡�ŧiIntent�A�����Ұ�SecondActivity
		        Intent intent = new Intent(SecondActivity.this,FirstActivity.class);
		        startActivity(intent);
			}
		});
    }
}