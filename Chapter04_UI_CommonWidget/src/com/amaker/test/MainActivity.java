package com.amaker.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	
	private Button register,cancel;
	private ToggleButton marriged;
	private RadioButton male,female;
	private EditText username,password;
	private Spinner position;
	private CheckBox reading,swimming;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        
        male = (RadioButton)findViewById(R.id.male);
        female = (RadioButton)findViewById(R.id.female);
        
        reading = (CheckBox)findViewById(R.id.reading);
        swimming = (CheckBox)findViewById(R.id.swimming);
        
        marriged = (ToggleButton)findViewById(R.id.marriged);
        
        position = (Spinner)findViewById(R.id.position);
        
        String[] str = {"CEO","CFO","PM"};
        
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,str);
        
        position.setAdapter(aa);
        
        register = (Button)findViewById(R.id.register);
        cancel = (Button)findViewById(R.id.cancel);
        
        register.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Bundle b = new Bundle();
				b.putString("username", "�Τ�W�١G"+username.getText().toString());
				b.putString("password", "�Τ�K�X�G"+password.getText().toString());
				
				if(male.isChecked()){
					b.putString("gender", "�ʧO�G�k");
				}else{
					b.putString("gender", "�ʧO�G�k");
				}
				String temp = "�R�n�G";
				if(reading.isChecked()){
					temp+="�\Ū";
				}
				if(swimming.isChecked()){
					temp+=" ";
					temp+="��a";
				}
				
				b.putString("hobby", temp);
				
				if(marriged.isChecked()){
					b.putString("marriged", "�B�_�G�w�B");
				}else{
					b.putString("marriged", "�B�_�G���B");
				}
				
				b.putString("position","¾��G"+ position.getSelectedItem().toString());
				
				Intent intent = new Intent(MainActivity.this,ResultActivity.class);
				
				intent.putExtra("data", b);
				
				startActivity(intent);
			}
		});
        
    }
}