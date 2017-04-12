package com.amaker.ch06.app;

import com.amaker.ch06.app.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author 郭宏志
 * 發送Email
 */
public class MainActivity extends Activity {
	// 宣告視圖元件
	private EditText toEditText,subjectEditText,contentEditText;
	private Button sendBtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // 實例化視圖元件
        toEditText = (EditText)findViewById(R.id.toEditText01);
        subjectEditText = (EditText)findViewById(R.id.subjectEditText01);
        contentEditText = (EditText)findViewById(R.id.contentEditText01);
        
        sendBtn = (Button)findViewById(R.id.sendButton01);
        // 為按鈕添加單擊監聽器
        sendBtn.setOnClickListener(listener);
    }
    
    
    // 發送按鈕單擊監聽器
    private OnClickListener listener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
			// 取得輸入訊息
			String to = toEditText.getText().toString();
			String subject = subjectEditText.getText().toString();
			String content = contentEditText.getText().toString();
			// 建立Intent
			Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
			// 設定內容類型
			emailIntent.setType("plain/text");
			// 設定額外訊息
			emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{to});
			emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
			emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, content);
			
			startActivity(Intent.createChooser(emailIntent, "發送郵件..."));
			
		}
	};
}