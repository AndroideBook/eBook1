package com.amaker.wlo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amaker.util.HttpUtil;

public class PayActivity extends Activity{
	// 顯示點餐訊息WebView
	private WebView wv;
	// 查詢點餐訊息按鈕和結算按鈕
	private Button queryBtn,payBtn;
	// 訂單編號
	private EditText orderIdEt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 設定目前Activity的介面佈局
		setContentView(R.layout.pay);
		// 取得WebView實例
		wv = (WebView)findViewById(R.id.pay_webview);
		// 實例化查詢按鈕
		queryBtn = (Button)findViewById(R.id.pay_query_Button01);
		// 實例化結算按鈕
		payBtn = (Button)findViewById(R.id.pay_Button01);
		// 實例化訂單編號編輯框
		orderIdEt = (EditText)findViewById(R.id.pay_order_number_EditText01);
		
		// 添加查詢點餐訊息監聽器
		queryBtn.setOnClickListener(queryListener);
		// 添加結算監聽器
		payBtn.setOnClickListener(payListener);
	}
	
	// 查詢點餐訊息監聽器
	OnClickListener queryListener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
			// 取得訂單編號
			String orderId = orderIdEt.getText().toString();
			// 請求伺服器url
			String url = HttpUtil.BASE_URL+"servlet/PayServlet?id="+orderId;
			// 將返回訊息顯示於WebView
			wv.loadUrl(url);
		}
	};
	
	// 結算監聽器
	OnClickListener payListener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
			// 取得訂單編號
			String orderId = orderIdEt.getText().toString();
			// 請求伺服器url
			String url = HttpUtil.BASE_URL+"servlet/PayMoneyServlet?id="+orderId;
			// 取得查詢結果
			String result = HttpUtil.queryStringForPost(url);
			// 顯示結算結果
			Toast.makeText(PayActivity.this, result, Toast.LENGTH_LONG).show();
			// 禁用結算按鈕
			payBtn.setEnabled(false);
		}
	};

}
