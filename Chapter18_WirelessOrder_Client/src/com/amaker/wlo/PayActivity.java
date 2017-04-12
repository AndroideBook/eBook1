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
	// ����I�\�T��WebView
	private WebView wv;
	// �d���I�\�T�����s�M������s
	private Button queryBtn,payBtn;
	// �q��s��
	private EditText orderIdEt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// �]�w�ثeActivity�������G��
		setContentView(R.layout.pay);
		// ���oWebView���
		wv = (WebView)findViewById(R.id.pay_webview);
		// ��ҤƬd�߫��s
		queryBtn = (Button)findViewById(R.id.pay_query_Button01);
		// ��ҤƵ�����s
		payBtn = (Button)findViewById(R.id.pay_Button01);
		// ��Ҥƭq��s���s���
		orderIdEt = (EditText)findViewById(R.id.pay_order_number_EditText01);
		
		// �K�[�d���I�\�T����ť��
		queryBtn.setOnClickListener(queryListener);
		// �K�[�����ť��
		payBtn.setOnClickListener(payListener);
	}
	
	// �d���I�\�T����ť��
	OnClickListener queryListener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
			// ���o�q��s��
			String orderId = orderIdEt.getText().toString();
			// �ШD���A��url
			String url = HttpUtil.BASE_URL+"servlet/PayServlet?id="+orderId;
			// �N��^�T����ܩ�WebView
			wv.loadUrl(url);
		}
	};
	
	// �����ť��
	OnClickListener payListener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
			// ���o�q��s��
			String orderId = orderIdEt.getText().toString();
			// �ШD���A��url
			String url = HttpUtil.BASE_URL+"servlet/PayMoneyServlet?id="+orderId;
			// ���o�d�ߵ��G
			String result = HttpUtil.queryStringForPost(url);
			// ��ܵ��⵲�G
			Toast.makeText(PayActivity.this, result, Toast.LENGTH_LONG).show();
			// �T�ε�����s
			payBtn.setEnabled(false);
		}
	};

}
