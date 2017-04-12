package com.amaker.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView myTV;
	private Button myBtn;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        myTV = (TextView)findViewById(R.id.TextView01);
        myBtn = (Button)findViewById(R.id.Button01);
        final String[] items = {"�����}���L��","�»����L��","�@��"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        myBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//builder.setTitle("���I�\").setItems(items, new DialogInterface.OnClickListener() {
				//�p�G�אּ�p�U��k�A�H���s�˦����
				builder.setTitle("���I�\").setSingleChoiceItems(items,-1, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
						myTV.setText(items[which]);
					}
				});
				AlertDialog ad = builder.create();
				ad.show();
			}
		});
    }
}