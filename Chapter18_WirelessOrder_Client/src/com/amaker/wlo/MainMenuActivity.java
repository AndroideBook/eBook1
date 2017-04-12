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
        setTitle("�x���_�L�u�q�\�t��-�D���");
        setContentView(R.layout.main_menu);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
    }
    
    // �~��BaseAdapter
    public class ImageAdapter extends BaseAdapter {
    	// �W�U��
        private Context mContext;
        // �غc�l
        public ImageAdapter(Context c) {
            mContext = c;
        }
        // ����Ӽ�
        public int getCount() {
            return mThumbIds.length;
        }
        // �ثe����
        public Object getItem(int position) {
            return null;
        }
        // �ثe����id
        public long getItemId(int position) {
            return 0;
        }
        // ���o�ثe����
        public View getView(int position, View convertView, ViewGroup parent) {
        	// �ŧi�Ϥ�����
            ImageView imageView;
            if (convertView == null) {
            	// ��ҤƹϤ�����
                imageView = new ImageView(mContext);
                // �]�w�Ϥ������ݩ�
                //imageView.setLayoutParams(new GridView.LayoutParams(82, 82));
                imageView.setLayoutParams(new GridView.LayoutParams(74, 74));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 8, 8, 8);
            } else {
                imageView = (ImageView) convertView;
            }
            // �]�w�Ϥ����ϹϤ��귽
            imageView.setImageResource(mThumbIds[position]);
            // ���ثe���ϲK�[��ť��
            switch (position) {
			case 0:
				// �K�[�I���ť��
				imageView.setOnClickListener(orderLinstener);
				break;
			case 1:
				// �}���ť��
				imageView.setOnClickListener(unionTableLinstener);
				break;
			case 2:
				// ����ť��
				imageView.setOnClickListener(changeTableLinstener);
				break;
			case 3:
				// �d���ť��
				imageView.setOnClickListener(checkTableLinstener);
				break;
			case 4:
				// ��s��ť��
				imageView.setOnClickListener(updateLinstener);
				break;
			case 6:
				// ���}��ť��
				imageView.setOnClickListener(exitLinstener);
				break;
			case 7:
				// ���b��ť��
				imageView.setOnClickListener(payLinstener);
				break;
			default:
				break;
			}
            
            return imageView;
        }
        // �Ϥ��귽�}�C
        private Integer[] mThumbIds = {
                R.drawable.diancai, R.drawable.bingtai,
                R.drawable.zhuantai, R.drawable.chatai,
                R.drawable.gengxin, R.drawable.shezhi,
                R.drawable.zhuxiao, R.drawable.jietai
        };
    }
    
    // ��s��ť��
    OnClickListener updateLinstener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			// �Ұʧ�sActivity
			intent.setClass(MainMenuActivity.this, UpdateActivity.class);
			startActivity(intent);
		}
	};
    
    // �d���ť��
    OnClickListener checkTableLinstener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			// �Ұʬd��Activity
			intent.setClass(MainMenuActivity.this, CheckTableActivity.class);
			startActivity(intent);
		}
	};
    
    // ���b��ť��
    OnClickListener payLinstener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			// �Ұʵ��bActivity
			intent.setClass(MainMenuActivity.this, PayActivity.class);
			startActivity(intent);
		}
	};
    
    // �I���ť��
    OnClickListener orderLinstener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			// �Ұ��I��Activity
			intent.setClass(MainMenuActivity.this, OrderActivity.class);
			startActivity(intent);
		}
	};
	// ���}��ť��
    OnClickListener exitLinstener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
			logout();
		}
	};
	
	// ����ť��
    OnClickListener changeTableLinstener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
			changeTable();
		}
	};
	
	// �֮��ť��
    OnClickListener unionTableLinstener = new OnClickListener() {
		//@Override
		public void onClick(View v) {
			unionTable();
		}
	};
	
	
	// ����t��
	private void changeTable(){
		// ���oLayoutInflater���
		LayoutInflater inflater = LayoutInflater.from(this);
		// ���oLinearLayout���Ϲ��
		View v =inflater.inflate(R.layout.change_table, null);
		// �qLinearLayout�����oEditText���
		final EditText et1 = (EditText) v.findViewById(R.id.change_table_order_number_EditText);
		final EditText et2 = (EditText) v.findViewById(R.id.change_table_no_EditText);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(" �u���n�����ܡH")
		       .setCancelable(false)
		       .setView(v)
		       .setPositiveButton("�T�w", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	// ���o�q�渹�X
		        	String orderId = et1.getText().toString();
		        	// ���o�ู
		       		String tableId = et2.getText().toString();
		       		// �d�߰Ѽ�
		       		String queryString = "orderId="+orderId+"&tableId="+tableId;
		       		// url
		       		String url = HttpUtil.BASE_URL+"servlet/ChangeTableServlet?"+queryString;
		       		// �d�ߪ�^���G
		       		String result = HttpUtil.queryStringForPost(url);
		       		// ��ܵ��G
		       		Toast.makeText(MainMenuActivity.this, result, Toast.LENGTH_LONG).show();
		           }
		       })
		       .setNegativeButton("����", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		                dialog.cancel();
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	
	// �֮�t��
	private void unionTable(){
		// ��Ҥ�LayoutInflater
		LayoutInflater inflater = LayoutInflater.from(this);
		// ���o�۩w����
		View v =inflater.inflate(R.layout.union_table, null);
		// ���oSpinner
		final Spinner spinner1 = (Spinner) v.findViewById(R.id.union_table_Spinner1);
		final Spinner spinner2 = (Spinner) v.findViewById(R.id.union_table_Spinner2);
		// �s�����A����URL
		String urlStr = HttpUtil.BASE_URL + "servlet/UnionTableServlet";
		try {
			// ��Ҥ�URL
			URL url = new URL(urlStr);
			// URLConnection ���
			URLConnection conn = url.openConnection();
			// ���o��J��y
			InputStream in = conn.getInputStream();
			// ���oDocumentBuilderFactory����
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			// ���oDocumentBuilder����
			DocumentBuilder builder = factory.newDocumentBuilder();
			// ���oDocument����
			Document doc = builder.parse(in);
			// ���o�`�I�C��
			NodeList nl = doc.getElementsByTagName("table");
			// Spinner���
			List items = new ArrayList();
			
			// ���oXML���
			for (int i = 0; i < nl.getLength(); i++) {
				// ���s��
				String id = doc.getElementsByTagName("id")
						.item(i).getFirstChild().getNodeValue();
				// �ู
				int num = Integer.parseInt(doc.getElementsByTagName("num")
						.item(i).getFirstChild().getNodeValue());
				Map data = new HashMap();
				data.put("id", id);
				items.add(data);
			}
			
			// ���oSpinnerAdapter
			SpinnerAdapter as = new 
			SimpleAdapter(this, items, 
					android.R.layout.simple_spinner_item,
					new String[] { "id" }, new int[] { android.R.id.text1 });
			
			// ô�����
			spinner1.setAdapter(as);
			spinner2.setAdapter(as);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(" �u���n�֮�ܡH")
		       .setCancelable(false)
		       .setView(v)
		       .setPositiveButton("�T�w", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   TextView tv1 = (TextView) spinner1.getSelectedView();
		        	   TextView tv2 = (TextView) spinner2.getSelectedView();
		        	   
		        	   String tableId1 = tv1.getText().toString();
		        	   String tableId2 = tv2.getText().toString();
		        		// �d�߰Ѽ�
		       			String queryString = "tableId1="+tableId1+"&tableId2="+tableId2;
		       			// url
		       			String url = HttpUtil.BASE_URL+"servlet/UnionTableServlet2?"+queryString;
		       			// �d�ߪ�^���G
		       			String result =  HttpUtil.queryStringForPost(url);
		           }
		       })
		       .setNegativeButton("����", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		                dialog.cancel();
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	
	// �h�X�t��
	private void logout(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("�u���n�h�X�t�ζܡH")
		       .setCancelable(false)
		       .setPositiveButton("�T�w", new DialogInterface.OnClickListener() {
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
		       .setNegativeButton("����", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		                dialog.cancel();
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	}
}