package com.amaker.wlo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amaker.util.HttpUtil;

public class LoginActivity extends Activity {
	// �ŧi�����B�n�����s
	private Button cancelBtn,loginBtn;
	// �ŧi�Τ�W�ٻP�K�X��J��
	private EditText userEditText,pwdEditText;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// �]�w���D
		setTitle("�x���_�L�u�q�\�t��-�Τ�n��");
		// �]�w�ثeActivity�����G��
		setContentView(R.layout.login_system);
		// �z�LfindViewById��k��Ҥƪ���
		cancelBtn = (Button)findViewById(R.id.cancelButton);
		// �z�LfindViewById��k��Ҥƪ���
		loginBtn = (Button)findViewById(R.id.loginButton);
		// �z�LfindViewById��k��Ҥƪ���
		userEditText = (EditText)findViewById(R.id.userEditText);
		// �z�LfindViewById��k��Ҥƪ���
		pwdEditText = (EditText)findViewById(R.id.pwdEditText);
		
		cancelBtn.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		loginBtn.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				if(validate()){
					if(login()){
						Intent intent = new Intent(LoginActivity.this,MainMenuActivity.class);
						startActivity(intent);
					}else{
						showDialog("�Τ�W�٩αK�X���~�A�Э��s��J�I");
					}
				}
			}
		});
	}
	// �n����k
	private boolean login(){
		// ���o�Τ�W��
		String username = userEditText.getText().toString();
		// ���o�K�X
		String pwd = pwdEditText.getText().toString();
		// ���o�n�����G
		String result=query(username,pwd);
		if(result!=null&&result.equals("0")){
			return false;
		}else{
			saveUserMsg(result);
			return true;
		}
	}
	
	// �N�Τ��T�O�s��պA��
	private void saveUserMsg(String msg){
		// �Τ�s��
		String id = "";
		// �Τ�W��
		String name = "";
		// ���o�T���}�C
		String[] msgs = msg.split(";");
		int idx = msgs[0].indexOf("=");
		id = msgs[0].substring(idx+1);
		idx = msgs[1].indexOf("=");
		name = msgs[1].substring(idx+1);
		// �@�ɰT��
		SharedPreferences pre = getSharedPreferences("user_msg", MODE_WORLD_WRITEABLE);
		SharedPreferences.Editor editor = pre.edit();
		editor.putString("id", id);
		editor.putString("name", name);
		editor.commit();
	}
	
	// ���Ҥ�k
	private boolean validate(){
		String username = userEditText.getText().toString();
		if(username.equals("")){
			showDialog("�Τ�W�٤��i�ťաI");
			return false;
		}
		String pwd = pwdEditText.getText().toString();
		if(pwd.equals("")){
			showDialog("�Τ�K�X���i�ťաI");
			return false;
		}
		return true;
	}
	private void showDialog(String msg){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(msg)
		       .setCancelable(false)
		       .setPositiveButton("�T�w", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	}
	// �ھڥΤ�W�٩M�K�X�d��
	private String query(String account,String password){
		// �d�߰Ѽ�
		String queryString = "account="+account+"&password="+password;
		// url
		String url = HttpUtil.BASE_URL+"servlet/LoginServlet?"+queryString;
		// ��^���G
		return HttpUtil.queryStringForPost(url);
    }
}