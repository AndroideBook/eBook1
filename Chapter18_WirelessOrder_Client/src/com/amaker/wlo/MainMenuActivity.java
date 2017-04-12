package com.amaker.wlo;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.amaker.util.HttpUtil;

public class MainMenuActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("掌中寶無線訂餐系統-主選單");
        setContentView(R.layout.main_menu);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
    }
    
    // 繼承BaseAdapter
    public class ImageAdapter extends BaseAdapter {
    	// 上下文
        private Context mContext;
        // 建構子
        public ImageAdapter(Context c) {
            mContext = c;
        }
        // 元件個數
        public int getCount() {
            return mThumbIds.length;
        }
        // 目前元件
        public Object getItem(int position) {
            return null;
        }
        // 目前元件id
        public long getItemId(int position) {
            return 0;
        }
        // 取得目前視圖
        public View getView(int position, View convertView, ViewGroup parent) {
        	// 宣告圖片視圖
            ImageView imageView;
            if (convertView == null) {
            	// 實例化圖片視圖
                imageView = new ImageView(mContext);
                // 設定圖片視圖屬性
                //imageView.setLayoutParams(new GridView.LayoutParams(82, 82));
                imageView.setLayoutParams(new GridView.LayoutParams(74, 74));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 8, 8, 8);
            } else {
                imageView = (ImageView) convertView;
            }
            // 設定圖片視圖圖片資源
            imageView.setImageResource(mThumbIds[position]);
            // 為目前視圖添加監聽器
            switch (position) {
			case 0:
				// 添加點菜監聽器
				imageView.setOnClickListener(orderLinstener);
				break;
			case 1:
				// 開桌監聽器
				imageView.setOnClickListener(unionTableLinstener);
				break;
			case 2:
				// 轉桌監聽器
				imageView.setOnClickListener(changeTableLinstener);
				break;
			case 3:
				// 查桌監聽器
				imageView.setOnClickListener(checkTableLinstener);
				break;
			case 4:
				// 更新監聽器
				imageView.setOnClickListener(updateLinstener);
				break;
			case 6:
				// 離開監聽器
				imageView.setOnClickListener(exitLinstener);
				break;
			case 7:
				// 結帳監聽器
				imageView.setOnClickListener(payLinstener);
				break;
			default:
				break;
			}
            
            return imageView;
        }
        // 圖片資源陣列
        private Integer[] mThumbIds = {
                R.drawable.diancai, R.drawable.bingtai,
                R.drawable.zhuantai, R.drawable.chatai,
                R.drawable.gengxin, R.drawable.shezhi,
                R.drawable.zhuxiao, R.drawable.jietai
        };
    }
    
    // 更新監聽器
    OnClickListener updateLinstener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			// 啟動更新Activity
			intent.setClass(MainMenuActivity.this, UpdateActivity.class);
			startActivity(intent);
		}
	};
    
    // 查桌監聽器
    OnClickListener checkTableLinstener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			// 啟動查桌Activity
			intent.setClass(MainMenuActivity.this, CheckTableActivity.class);
			startActivity(intent);
		}
	};
    
    // 結帳監聽器
    OnClickListener payLinstener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			// 啟動結帳Activity
			intent.setClass(MainMenuActivity.this, PayActivity.class);
			startActivity(intent);
		}
	};
    
    // 點菜監聽器
    OnClickListener orderLinstener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			// 啟動點菜Activity
			intent.setClass(MainMenuActivity.this, OrderActivity.class);
			startActivity(intent);
		}
	};
	// 離開監聽器
    OnClickListener exitLinstener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
			logout();
		}
	};
	
	// 轉桌監聽器
    OnClickListener changeTableLinstener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
			changeTable();
		}
	};
	
	// 併桌監聽器
    OnClickListener unionTableLinstener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
			unionTable();
		}
	};
	
	
	// 換桌系統
	private void changeTable(){
		// 取得LayoutInflater實例
		LayoutInflater inflater = LayoutInflater.from(this);
		// 取得LinearLayout視圖實例
		View v =inflater.inflate(R.layout.change_table, null);
		// 從LinearLayout中取得EditText實例
		final EditText et1 = (EditText) v.findViewById(R.id.change_table_order_number_EditText);
		final EditText et2 = (EditText) v.findViewById(R.id.change_table_no_EditText);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(" 真的要換桌位嗎？")
		       .setCancelable(false)
		       .setView(v)
		       .setPositiveButton("確定", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	// 取得訂單號碼
		        	String orderId = et1.getText().toString();
		        	// 取得桌號
		       		String tableId = et2.getText().toString();
		       		// 查詢參數
		       		String queryString = "orderId="+orderId+"&tableId="+tableId;
		       		// url
		       		String url = HttpUtil.BASE_URL+"servlet/ChangeTableServlet?"+queryString;
		       		// 查詢返回結果
		       		String result = HttpUtil.queryStringForPost(url);
		       		// 顯示結果
		       		Toast.makeText(MainMenuActivity.this, result, Toast.LENGTH_LONG).show();
		           }
		       })
		       .setNegativeButton("取消", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		                dialog.cancel();
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	
	// 併桌系統
	private void unionTable(){
		// 實例化LayoutInflater
		LayoutInflater inflater = LayoutInflater.from(this);
		// 取得自定視圖
		View v =inflater.inflate(R.layout.union_table, null);
		// 取得Spinner
		final Spinner spinner1 = (Spinner) v.findViewById(R.id.union_table_Spinner1);
		final Spinner spinner2 = (Spinner) v.findViewById(R.id.union_table_Spinner2);
		// 存取伺服器的URL
		String urlStr = HttpUtil.BASE_URL + "servlet/UnionTableServlet";
		try {
			// 實例化URL
			URL url = new URL(urlStr);
			// URLConnection 實例
			URLConnection conn = url.openConnection();
			// 取得輸入串流
			InputStream in = conn.getInputStream();
			// 取得DocumentBuilderFactory物件
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			// 取得DocumentBuilder物件
			DocumentBuilder builder = factory.newDocumentBuilder();
			// 取得Document物件
			Document doc = builder.parse(in);
			// 取得節點列表
			NodeList nl = doc.getElementsByTagName("table");
			// Spinner資料
			List items = new ArrayList();
			
			// 取得XML資料
			for (int i = 0; i < nl.getLength(); i++) {
				// 桌位編號
				String id = doc.getElementsByTagName("id")
						.item(i).getFirstChild().getNodeValue();
				// 桌號
				int num = Integer.parseInt(doc.getElementsByTagName("num")
						.item(i).getFirstChild().getNodeValue());
				Map data = new HashMap();
				data.put("id", id);
				items.add(data);
			}
			
			// 取得SpinnerAdapter
			SpinnerAdapter as = new 
			SimpleAdapter(this, items, 
					android.R.layout.simple_spinner_item,
					new String[] { "id" }, new int[] { android.R.id.text1 });
			
			// 繫結資料
			spinner1.setAdapter(as);
			spinner2.setAdapter(as);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(" 真的要併桌嗎？")
		       .setCancelable(false)
		       .setView(v)
		       .setPositiveButton("確定", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   TextView tv1 = (TextView) spinner1.getSelectedView();
		        	   TextView tv2 = (TextView) spinner2.getSelectedView();
		        	   
		        	   String tableId1 = tv1.getText().toString();
		        	   String tableId2 = tv2.getText().toString();
		        		// 查詢參數
		       			String queryString = "tableId1="+tableId1+"&tableId2="+tableId2;
		       			// url
		       			String url = HttpUtil.BASE_URL+"servlet/UnionTableServlet2?"+queryString;
		       			// 查詢返回結果
		       			String result =  HttpUtil.queryStringForPost(url);
		           }
		       })
		       .setNegativeButton("取消", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		                dialog.cancel();
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	
	// 退出系統
	private void logout(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("真的要退出系統嗎？")
		       .setCancelable(false)
		       .setPositiveButton("確定", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	  SharedPreferences pres = getSharedPreferences("user_msg", MODE_WORLD_WRITEABLE);
		        	  SharedPreferences.Editor editor = pres.edit();
		        	  editor.putString("id", "");
		        	  editor.putString("name", "");
		        	  Intent intent = new Intent();
		        	  intent.setClass(MainMenuActivity.this, LoginActivity.class);
		        	  startActivity(intent);
		           }
		       })
		       .setNegativeButton("取消", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		                dialog.cancel();
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	}
}