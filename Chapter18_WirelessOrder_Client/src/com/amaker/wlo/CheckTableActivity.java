package com.amaker.wlo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.amaker.util.CheckTable;
import com.amaker.util.HttpUtil;
/**
 * 查桌
 * @author 郭宏志
 */
public class CheckTableActivity extends Activity{
	// 顯示餐桌狀態的GridView
	private GridView gv;
	// 餐桌數量
	private int count;
	// 儲存餐桌資料的列表
	private List list = new ArrayList();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 設定標題
		setTitle("掌中寶無線訂餐系統-查桌");
		// 設定目前Activity的介面佈局
		setContentView(R.layout.check_table);
		// 實例化
        gv = (GridView) findViewById(R.id.check_table_gridview);
        //取得餐桌列表
        getTableList();
        // 為GridView繫結資料
        gv.setAdapter(new ImageAdapter(this));
	}
	
	// 取得餐桌訊息列表，包括桌號和狀態
	private void getTableList(){
		// 存取伺服器url
		String url = HttpUtil.BASE_URL+"servlet/CheckTableServlet";
		// 查詢返回結果
		String result = HttpUtil.queryStringForPost(url);
		// 拆分字串、轉換成物件、添加到列表
		String[] strs = result.split(";");
		for (int i = 0; i < strs.length; i++) {
			int idx = strs[i].indexOf(",");
			int num = Integer.parseInt(strs[i].substring(0, idx));
			int flag = Integer.parseInt(strs[i].substring(idx+1));
			CheckTable ct = new CheckTable();
			ct.setFlag(flag);
			ct.setNum(num);
			list.add(ct);
		}
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
            return list.size();
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
        	LayoutInflater inflater = 
        		LayoutInflater.from(CheckTableActivity.this);
        	View v = null;
        	ImageView imageView =null;
        	TextView tv =null;
            if (convertView == null) {
            	// 實例化圖片視圖
            	v = inflater.inflate(R.layout.check_table_view,null);
            	// 設定圖片視圖屬性
                v.setPadding(8, 8, 8, 8);
            } else {
                v = (View) convertView;
            }
            // 取得ImageView物件
            imageView = (ImageView) v.findViewById(R.id.check_table_ImageView01);
       	 	// 取得TextView物件
            tv = (TextView) v.findViewById(R.id.check_tableTextView01);
            // 取得CheckTable物件
            CheckTable ct = (CheckTable) list.get(position);
            if(ct.getFlag()==1){
            	// 設定ImageView圖片為有人
            	imageView.setImageResource(R.drawable.youren);
            }else{
            	// 設定ImageView圖片為空位
            	imageView.setImageResource(R.drawable.kongwei);
            }
            // 為TextView設定桌號
            tv.setText(ct.getNum()+"");
            return v;
        }
    }
}
