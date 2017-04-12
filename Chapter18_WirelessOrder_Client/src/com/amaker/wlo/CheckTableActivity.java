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
 * �d��
 * @author ������
 */
public class CheckTableActivity extends Activity{
	// ����\�બ�A��GridView
	private GridView gv;
	// �\��ƶq
	private int count;
	// �x�s�\���ƪ��C��
	private List list = new ArrayList();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// �]�w���D
		setTitle("�x���_�L�u�q�\�t��-�d��");
		// �]�w�ثeActivity�������G��
		setContentView(R.layout.check_table);
		// ��Ҥ�
        gv = (GridView) findViewById(R.id.check_table_gridview);
        //���o�\��C��
        getTableList();
        // ��GridViewô�����
        gv.setAdapter(new ImageAdapter(this));
	}
	
	// ���o�\��T���C��A�]�A�ู�M���A
	private void getTableList(){
		// �s�����A��url
		String url = HttpUtil.BASE_URL+"servlet/CheckTableServlet";
		// �d�ߪ�^���G
		String result = HttpUtil.queryStringForPost(url);
		// ����r��B�ഫ������B�K�[��C��
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
            return list.size();
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
        	LayoutInflater inflater = 
        		LayoutInflater.from(CheckTableActivity.this);
        	View v = null;
        	ImageView imageView =null;
        	TextView tv =null;
            if (convertView == null) {
            	// ��ҤƹϤ�����
            	v = inflater.inflate(R.layout.check_table_view,null);
            	// �]�w�Ϥ������ݩ�
                v.setPadding(8, 8, 8, 8);
            } else {
                v = (View) convertView;
            }
            // ���oImageView����
            imageView = (ImageView) v.findViewById(R.id.check_table_ImageView01);
       	 	// ���oTextView����
            tv = (TextView) v.findViewById(R.id.check_tableTextView01);
            // ���oCheckTable����
            CheckTable ct = (CheckTable) list.get(position);
            if(ct.getFlag()==1){
            	// �]�wImageView�Ϥ������H
            	imageView.setImageResource(R.drawable.youren);
            }else{
            	// �]�wImageView�Ϥ����Ŧ�
            	imageView.setImageResource(R.drawable.kongwei);
            }
            // ��TextView�]�w�ู
            tv.setText(ct.getNum()+"");
            return v;
        }
    }
}
