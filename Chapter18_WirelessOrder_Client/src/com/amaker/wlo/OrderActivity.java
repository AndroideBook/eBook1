package com.amaker.wlo;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amaker.util.HttpUtil;

public class OrderActivity extends Activity {
	// 桌號下拉清單
	private Spinner tableNoSpinner;
	// 開桌、點菜和下單按鈕
	private Button orderBtn, addBtn, startBtn;
	// 人數編輯框
	private EditText personNumEt;
	// 點菜列表
	private ListView lv;
	// 開桌產生的訂單Id
	private String orderId;
	// 點菜列表中繫結的資料
	//@SuppressWarnings("rawtypes")
	private List data = new ArrayList();
	// 點菜列表中具體的資料項目
	//@SuppressWarnings("rawtypes")
	private Map map;
	// ListView 的 Adapter
	private SimpleAdapter sa;
	// ListView 中顯示的資料項目
	private String[] from = { "id", "name", "num", "price", "remark" };
	// 引用的TextView Drawable ID
	private int[] to = new int[5];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 為Activity設定介面佈局
		setContentView(R.layout.order);
		
		// 實例化Spinner
		tableNoSpinner = (Spinner) findViewById(R.id.tableNoSpinner);
		// 為桌號下拉列表Spinner繫結資料
		setTableAdapter();
		
		// 實例化開桌按鈕
		startBtn = (Button) findViewById(R.id.startButton02);
		// 為開桌按鈕添加監聽器
		startBtn.setOnClickListener(startListener);
		
		// 實例化點菜按鈕
		addBtn = (Button) findViewById(R.id.addMealButton01);
		// 為點菜按鈕添加監聽器
		addBtn.setOnClickListener(addListener);
			
		// 實例化下單按鈕
		orderBtn = (Button) findViewById(R.id.orderButton02);
		// 為下單按鈕添加監聽器
		orderBtn.setOnClickListener(orderListener);
		
		// 實例化人數編輯框
		personNumEt = (EditText) findViewById(R.id.personNumEditText02);
		
		// 實例化ListView
		lv = (ListView) findViewById(R.id.orderDetailListView01);
		// 為點菜列表ListView繫結資料
		setMenusAdapter();
	}
	
	
	// 為點菜列表ListView繫結資料
	//@SuppressWarnings("unchecked")
	private void setMenusAdapter(){
		// 顯示點菜項目的TextView陣列
		to[0] = R.id.id_ListView;
		to[1] = R.id.name_ListView;
		to[2] = R.id.num_ListView;
		to[3] = R.id.price_ListView;
		to[4] = R.id.remark_ListView;
		// 實例化點菜列表ListView Adapter
		sa = new SimpleAdapter(this, data, R.layout.listview, from, to);
		// 為ListView繫結資料
		lv.setAdapter(sa);
	}
	
	
	// 為桌號下拉列表Spinner繫結資料
	private void setTableAdapter(){
		// 存取本地SQLite資料庫桌號資料表的Uri
	    Uri uri = Uri.parse("content://com.amaker.provider.TABLES/table");
		// 要選擇桌號表中的欄位
		String[] projection = { "_id", "num", "description" };
		// 取得ContentResolver實例
		ContentResolver cr = getContentResolver();
		// 返回游標
		//Cursor c = managedQuery(uri, projection, null, null, null);
		Cursor c = cr.query(uri, projection, null, null, null);
		// 實例化桌號下拉列表Spinner的Adapter
		SimpleCursorAdapter adapter2 = new SimpleCursorAdapter(this,
				android.R.layout.simple_spinner_item, c,
				new String[] { "_id" }, new int[] { android.R.id.text1 });

		// 為桌號Spinner繫結資料
		tableNoSpinner.setAdapter(adapter2);
	}
	
	
	// 開桌監聽器
	private OnClickListener startListener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yy:mm:dd hh:MM");
			// 開桌時間
			String orderTime = sdf.format(date);
			// 開桌用戶，從登錄組態檔中取得
			SharedPreferences pres = getSharedPreferences("user_msg", 
					MODE_WORLD_READABLE);
			String userId = pres.getString("id", "");
			// 桌號
			TextView tv = (TextView) tableNoSpinner.getSelectedView();
			String tableId = tv.getText().toString();
			// 人數
			String personNum = personNumEt.getText().toString();
			// 請求參數列表
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// 添加請求參數
			params.add(new BasicNameValuePair("orderTime", orderTime));
			params.add(new BasicNameValuePair("userId", userId));
			params.add(new BasicNameValuePair("tableId", tableId));
			params.add(new BasicNameValuePair("personNum", personNum));
			UrlEncodedFormEntity entity1=null;
			try {
				entity1 =  new UrlEncodedFormEntity(params,HTTP.UTF_8);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			// 請求伺服器url
			String url = HttpUtil.BASE_URL+"servlet/StartTableServlet";
			// 取得請求物件HttpPost
			HttpPost request = HttpUtil.getHttpPost(url);
			// 設定查詢參數
			request.setEntity(entity1);
			// 取得回應結果
			String result= HttpUtil.queryStringForPost(request);
			// 將開桌產生的訂單Id塞給全域變數orderId
			orderId = result;
			Toast.makeText(OrderActivity.this, result, Toast.LENGTH_LONG).show();
		}
	};
	
	// 加菜監聽器
	private OnClickListener addListener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
			// 呼叫點菜方法
			addMeal();
		}
	};
	
	//加菜方法
	private void addMeal() {
		// 取得LayoutInflater實例
		LayoutInflater inflater = LayoutInflater.from(this);
		// 實例化在彈出對話框中添加的視圖
		final View v = inflater.inflate(R.layout.order_detail, null);
		// 取得視圖中的Spinner物件，菜單下拉列表
		final Spinner menuSpinner = (Spinner) v.findViewById(R.id.menuSpinner);
		// 取得視圖中的EditText物件與數量
		EditText numEt = (EditText) v.findViewById(R.id.numEditText);
		// 取得視圖中的EditText實例與備註
		EditText remarkEt = (EditText) v.findViewById(R.id.add_remarkEditText);
		
		// 存取本地SQLite資料庫中MenuTbl資料表的Uri
		Uri uri = Uri.parse("content://com.amaker.provider.MENUS/menu1");
		// 查詢欄位
		String[] projection = { "_id", "name", "price" };
		// 取得ContentResolver實例
		ContentResolver cr = getContentResolver();
		// 返回游標
		Cursor c = cr.query(uri, projection, null, null, null);
		// 實例化SimpleCursorAdapter
		SimpleCursorAdapter adapter2 = new SimpleCursorAdapter(this,
				R.layout.spinner_lo, c,
				new String[] { "_id", "price", "name" }, 
				new int[] {R.id.id_TextView01, R.id.price_TextView02,
						R.id.name_TextView03 }	//,
		);
		// 為點菜下拉列表Spinner繫結資料
		menuSpinner.setAdapter(adapter2);
		
		// 取得AlertDialog.Builder實例
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder
		// 設定標題
		.setMessage("請點菜：")
		// 設定自定視圖
		.setView(v)
		// 設定確定按鈕
		.setPositiveButton("確定", new DialogInterface.OnClickListener() {
					// 確定按鈕事件
			public void onClick(DialogInterface dialog, int id) {
						
				// 取得ListView中的自定視圖LinearLayout
				LinearLayout v1 = (LinearLayout) menuSpinner.getSelectedView();
						
				// 取得TextView，菜編號
				TextView id_tv = (TextView) v1.findViewById(R.id.id_TextView01);
				// 取得TextView，菜價格
				TextView price_tv = (TextView) v1.findViewById(R.id.price_TextView02);
				// 取得TextView，菜名稱
				TextView name_tv = (TextView) v1.findViewById(R.id.name_TextView03);
				// 取得EditText，菜數量
				EditText num_et = (EditText) v.findViewById(R.id.numEditText);
				// 取得EditText，菜備註
				EditText remark_et = (EditText) v.findViewById(R.id.add_remarkEditText);
				// 菜編號
				String idStr = id_tv.getText().toString();
				// 菜價格
				String priceStr = price_tv.getText().toString();
				// 菜名稱
				String nameStr = name_tv.getText().toString();
				// 菜數量
				String numStr = num_et.getText().toString();
				// 菜備註
				String remarkStr = remark_et.getText().toString();
						
				// 封裝到Map中
				map = new HashMap();

				map.put("id", idStr);
				map.put("name", nameStr);
				map.put("num", numStr);
				map.put("price", priceStr);
				map.put("remark", remarkStr);
						
				// 添加到ListView
				data.add(map);
						
				// 關聯的TextView
				to[0] = R.id.id_ListView;
				to[1] = R.id.name_ListView;
				to[2] = R.id.price_ListView;
				to[3] = R.id.remark_ListView;
						
				// 實例化SimpleAdapter
				sa = new SimpleAdapter(OrderActivity.this, data,
						R.layout.listview, from, to);
				// 為ListView繫結資料
				lv.setAdapter(sa);

			}
				}).setNegativeButton("取消", null);
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	// 下單監聽器
	private OnClickListener orderListener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
			// 遍覽點菜列表
			for (int i = 0; i < data.size(); i++) {
				// 取得其中點菜map
				Map map = (Map)data.get(i);
				// 取得點菜項目
				String menuId = (String) map.get("id");
				String num = (String)map.get("num");
				String remark = (String)map.get("remark");
				String myOrderId = orderId;
				
				// 封裝到請求參數中
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				// 添加到請求參數中
				params.add(new BasicNameValuePair("menuId", menuId));
				params.add(new BasicNameValuePair("orderId", myOrderId));
				params.add(new BasicNameValuePair("num", num));
				params.add(new BasicNameValuePair("remark", remark));
				UrlEncodedFormEntity entity1=null;
				try {
					 entity1 =  new UrlEncodedFormEntity(params,HTTP.UTF_8);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
				// 請求伺服器Servlet的url
				String url = HttpUtil.BASE_URL+"servlet/OrderDetailServlet";
				// 取得HttpPost物件
				HttpPost request = HttpUtil.getHttpPost(url);
				// 為請求設定參數
				request.setEntity(entity1);
				// 取得返回結果
				String result= HttpUtil.queryStringForPost(request);
				
				Toast.makeText(OrderActivity.this, result, Toast.LENGTH_LONG).show();
				
				finish();
			}
		}
	};
}
