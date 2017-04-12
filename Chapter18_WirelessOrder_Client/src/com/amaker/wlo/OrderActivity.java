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
	// �ู�U�ԲM��
	private Spinner tableNoSpinner;
	// �}��B�I��M�U����s
	private Button orderBtn, addBtn, startBtn;
	// �H�ƽs���
	private EditText personNumEt;
	// �I��C��
	private ListView lv;
	// �}�ಣ�ͪ��q��Id
	private String orderId;
	// �I��C��ô�������
	//@SuppressWarnings("rawtypes")
	private List data = new ArrayList();
	// �I��C�����骺��ƶ���
	//@SuppressWarnings("rawtypes")
	private Map map;
	// ListView �� Adapter
	private SimpleAdapter sa;
	// ListView ����ܪ���ƶ���
	private String[] from = { "id", "name", "num", "price", "remark" };
	// �ޥΪ�TextView Drawable ID
	private int[] to = new int[5];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ��Activity�]�w�����G��
		setContentView(R.layout.order);
		
		// ��Ҥ�Spinner
		tableNoSpinner = (Spinner) findViewById(R.id.tableNoSpinner);
		// ���ู�U�ԦC��Spinnerô�����
		setTableAdapter();
		
		// ��Ҥƶ}����s
		startBtn = (Button) findViewById(R.id.startButton02);
		// ���}����s�K�[��ť��
		startBtn.setOnClickListener(startListener);
		
		// ��Ҥ��I����s
		addBtn = (Button) findViewById(R.id.addMealButton01);
		// ���I����s�K�[��ť��
		addBtn.setOnClickListener(addListener);
			
		// ��ҤƤU����s
		orderBtn = (Button) findViewById(R.id.orderButton02);
		// ���U����s�K�[��ť��
		orderBtn.setOnClickListener(orderListener);
		
		// ��ҤƤH�ƽs���
		personNumEt = (EditText) findViewById(R.id.personNumEditText02);
		
		// ��Ҥ�ListView
		lv = (ListView) findViewById(R.id.orderDetailListView01);
		// ���I��C��ListViewô�����
		setMenusAdapter();
	}
	
	
	// ���I��C��ListViewô�����
	//@SuppressWarnings("unchecked")
	private void setMenusAdapter(){
		// ����I�涵�ت�TextView�}�C
		to[0] = R.id.id_ListView;
		to[1] = R.id.name_ListView;
		to[2] = R.id.num_ListView;
		to[3] = R.id.price_ListView;
		to[4] = R.id.remark_ListView;
		// ��Ҥ��I��C��ListView Adapter
		sa = new SimpleAdapter(this, data, R.layout.listview, from, to);
		// ��ListViewô�����
		lv.setAdapter(sa);
	}
	
	
	// ���ู�U�ԦC��Spinnerô�����
	private void setTableAdapter(){
		// �s�����aSQLite��Ʈw�ู��ƪ�Uri
	    Uri uri = Uri.parse("content://com.amaker.provider.TABLES/table");
		// �n��ܮู�������
		String[] projection = { "_id", "num", "description" };
		// ���oContentResolver���
		ContentResolver cr = getContentResolver();
		// ��^���
		//Cursor c = managedQuery(uri, projection, null, null, null);
		Cursor c = cr.query(uri, projection, null, null, null);
		// ��ҤƮู�U�ԦC��Spinner��Adapter
		SimpleCursorAdapter adapter2 = new SimpleCursorAdapter(this,
				android.R.layout.simple_spinner_item, c,
				new String[] { "_id" }, new int[] { android.R.id.text1 });

		// ���ูSpinnerô�����
		tableNoSpinner.setAdapter(adapter2);
	}
	
	
	// �}���ť��
	private OnClickListener startListener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yy:mm:dd hh:MM");
			// �}��ɶ�
			String orderTime = sdf.format(date);
			// �}��Τ�A�q�n���պA�ɤ����o
			SharedPreferences pres = getSharedPreferences("user_msg", 
					MODE_WORLD_READABLE);
			String userId = pres.getString("id", "");
			// �ู
			TextView tv = (TextView) tableNoSpinner.getSelectedView();
			String tableId = tv.getText().toString();
			// �H��
			String personNum = personNumEt.getText().toString();
			// �ШD�ѼƦC��
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// �K�[�ШD�Ѽ�
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
			// �ШD���A��url
			String url = HttpUtil.BASE_URL+"servlet/StartTableServlet";
			// ���o�ШD����HttpPost
			HttpPost request = HttpUtil.getHttpPost(url);
			// �]�w�d�߰Ѽ�
			request.setEntity(entity1);
			// ���o�^�����G
			String result= HttpUtil.queryStringForPost(request);
			// �N�}�ಣ�ͪ��q��Id�뵹�����ܼ�orderId
			orderId = result;
			Toast.makeText(OrderActivity.this, result, Toast.LENGTH_LONG).show();
		}
	};
	
	// �[���ť��
	private OnClickListener addListener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
			// �I�s�I���k
			addMeal();
		}
	};
	
	//�[���k
	private void addMeal() {
		// ���oLayoutInflater���
		LayoutInflater inflater = LayoutInflater.from(this);
		// ��ҤƦb�u�X��ܮؤ��K�[������
		final View v = inflater.inflate(R.layout.order_detail, null);
		// ���o���Ϥ���Spinner����A���U�ԦC��
		final Spinner menuSpinner = (Spinner) v.findViewById(R.id.menuSpinner);
		// ���o���Ϥ���EditText����P�ƶq
		EditText numEt = (EditText) v.findViewById(R.id.numEditText);
		// ���o���Ϥ���EditText��һP�Ƶ�
		EditText remarkEt = (EditText) v.findViewById(R.id.add_remarkEditText);
		
		// �s�����aSQLite��Ʈw��MenuTbl��ƪ�Uri
		Uri uri = Uri.parse("content://com.amaker.provider.MENUS/menu1");
		// �d�����
		String[] projection = { "_id", "name", "price" };
		// ���oContentResolver���
		ContentResolver cr = getContentResolver();
		// ��^���
		Cursor c = cr.query(uri, projection, null, null, null);
		// ��Ҥ�SimpleCursorAdapter
		SimpleCursorAdapter adapter2 = new SimpleCursorAdapter(this,
				R.layout.spinner_lo, c,
				new String[] { "_id", "price", "name" }, 
				new int[] {R.id.id_TextView01, R.id.price_TextView02,
						R.id.name_TextView03 }	//,
		);
		// ���I��U�ԦC��Spinnerô�����
		menuSpinner.setAdapter(adapter2);
		
		// ���oAlertDialog.Builder���
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder
		// �]�w���D
		.setMessage("���I��G")
		// �]�w�۩w����
		.setView(v)
		// �]�w�T�w���s
		.setPositiveButton("�T�w", new DialogInterface.OnClickListener() {
					// �T�w���s�ƥ�
			public void onClick(DialogInterface dialog, int id) {
						
				// ���oListView�����۩w����LinearLayout
				LinearLayout v1 = (LinearLayout) menuSpinner.getSelectedView();
						
				// ���oTextView�A��s��
				TextView id_tv = (TextView) v1.findViewById(R.id.id_TextView01);
				// ���oTextView�A�����
				TextView price_tv = (TextView) v1.findViewById(R.id.price_TextView02);
				// ���oTextView�A��W��
				TextView name_tv = (TextView) v1.findViewById(R.id.name_TextView03);
				// ���oEditText�A��ƶq
				EditText num_et = (EditText) v.findViewById(R.id.numEditText);
				// ���oEditText�A��Ƶ�
				EditText remark_et = (EditText) v.findViewById(R.id.add_remarkEditText);
				// ��s��
				String idStr = id_tv.getText().toString();
				// �����
				String priceStr = price_tv.getText().toString();
				// ��W��
				String nameStr = name_tv.getText().toString();
				// ��ƶq
				String numStr = num_et.getText().toString();
				// ��Ƶ�
				String remarkStr = remark_et.getText().toString();
						
				// �ʸ˨�Map��
				map = new HashMap();

				map.put("id", idStr);
				map.put("name", nameStr);
				map.put("num", numStr);
				map.put("price", priceStr);
				map.put("remark", remarkStr);
						
				// �K�[��ListView
				data.add(map);
						
				// ���p��TextView
				to[0] = R.id.id_ListView;
				to[1] = R.id.name_ListView;
				to[2] = R.id.price_ListView;
				to[3] = R.id.remark_ListView;
						
				// ��Ҥ�SimpleAdapter
				sa = new SimpleAdapter(OrderActivity.this, data,
						R.layout.listview, from, to);
				// ��ListViewô�����
				lv.setAdapter(sa);

			}
				}).setNegativeButton("����", null);
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	// �U���ť��
	private OnClickListener orderListener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
			// �M���I��C��
			for (int i = 0; i < data.size(); i++) {
				// ���o�䤤�I��map
				Map map = (Map)data.get(i);
				// ���o�I�涵��
				String menuId = (String) map.get("id");
				String num = (String)map.get("num");
				String remark = (String)map.get("remark");
				String myOrderId = orderId;
				
				// �ʸ˨�ШD�ѼƤ�
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				// �K�[��ШD�ѼƤ�
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
				
				// �ШD���A��Servlet��url
				String url = HttpUtil.BASE_URL+"servlet/OrderDetailServlet";
				// ���oHttpPost����
				HttpPost request = HttpUtil.getHttpPost(url);
				// ���ШD�]�w�Ѽ�
				request.setEntity(entity1);
				// ���o��^���G
				String result= HttpUtil.queryStringForPost(request);
				
				Toast.makeText(OrderActivity.this, result, Toast.LENGTH_LONG).show();
				
				finish();
			}
		}
	};
}
