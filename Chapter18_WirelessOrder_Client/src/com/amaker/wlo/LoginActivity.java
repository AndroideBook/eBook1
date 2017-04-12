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
	// 宣告取消、登錄按鈕
	private Button cancelBtn,loginBtn;
	// 宣告用戶名稱與密碼輸入框
	private EditText userEditText,pwdEditText;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 設定標題
		setTitle("掌中寶無線訂餐系統-用戶登錄");
		// 設定目前Activity介面佈局
		setContentView(R.layout.login_system);
		// 透過findViewById方法實例化物件
		cancelBtn = (Button)findViewById(R.id.cancelButton);
		// 透過findViewById方法實例化物件
		loginBtn = (Button)findViewById(R.id.loginButton);
		// 透過findViewById方法實例化物件
		userEditText = (EditText)findViewById(R.id.userEditText);
		// 透過findViewById方法實例化物件
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
						showDialog("用戶名稱或密碼錯誤，請重新輸入！");
					}
				}
			}
		});
	}
	// 登錄方法
	private boolean login(){
		// 取得用戶名稱
		String username = userEditText.getText().toString();
		// 取得密碼
		String pwd = pwdEditText.getText().toString();
		// 取得登錄結果
		String result=query(username,pwd);
		if(result!=null&&result.equals("0")){
			return false;
		}else{
			saveUserMsg(result);
			return true;
		}
	}
	
	// 將用戶資訊保存到組態檔
	private void saveUserMsg(String msg){
		// 用戶編號
		String id = "";
		// 用戶名稱
		String name = "";
		// 取得訊息陣列
		String[] msgs = msg.split(";");
		int idx = msgs[0].indexOf("=");
		id = msgs[0].substring(idx+1);
		idx = msgs[1].indexOf("=");
		name = msgs[1].substring(idx+1);
		// 共享訊息
		SharedPreferences pre = getSharedPreferences("user_msg", MODE_WORLD_WRITEABLE);
		SharedPreferences.Editor editor = pre.edit();
		editor.putString("id", id);
		editor.putString("name", name);
		editor.commit();
	}
	
	// 驗證方法
	private boolean validate(){
		String username = userEditText.getText().toString();
		if(username.equals("")){
			showDialog("用戶名稱不可空白！");
			return false;
		}
		String pwd = pwdEditText.getText().toString();
		if(pwd.equals("")){
			showDialog("用戶密碼不可空白！");
			return false;
		}
		return true;
	}
	private void showDialog(String msg){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(msg)
		       .setCancelable(false)
		       .setPositiveButton("確定", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	}
	// 根據用戶名稱和密碼查詢
	private String query(String account,String password){
		// 查詢參數
		String queryString = "account="+account+"&password="+password;
		// url
		String url = HttpUtil.BASE_URL+"servlet/LoginServlet?"+queryString;
		// 返回結果
		return HttpUtil.queryStringForPost(url);
    }
}