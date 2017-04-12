package com.amaker.wlo;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.amaker.provider.Menus;
import com.amaker.util.HttpUtil;

import com.amaker.provider.Tables;	//added by Alvin
/**
 * 
 * @author ������
 * ��@��ƦP�B�\��
 */
public class UpdateActivity extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// �]�w���D
		setTitle("�x���_�L�u�I�\�t��-��ƦP�B");
		// ���oListView���
		ListView listView = getListView();
		// �ŧiListView�nô�������
		String[] items = {"��s�����[MenuTbl]", "��s�\���ƪ�[TableTbl]" };
		// ��Ҥ�adapter
		ListAdapter adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, items);
		// ��ListView�]�wadapter
		listView.setAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		switch (position) {
		// ��s�����
		case 0:
			confirm(1);
			break;
		// ��s����ƪ�
		case 1:
			confirm(2);
			break;
		default:
			break;
		}
	}
	// �T�{��ܮ�
	private void confirm(final int item) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("�u���n��s�ܡH").setCancelable(false).setPositiveButton(
				"�T�w", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						if (item == 1) {
							// ��s����ƪ�
							updateMenu();
						} else {
							// ��s����ƪ�
							updateTable();
						}
					}
				}).setNegativeButton("����",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();
	}
	// ��s���
	private void updateMenu() {
		// �s�����A��url
		String urlStr = HttpUtil.BASE_URL + "servlet/UpdateServlet?src=menu";
		try {
			// ��Ҥ�URL����
			URL url = new URL(urlStr);
			// �}�ҳs�u
			URLConnection conn = url.openConnection();
			// ���o��J��y
			InputStream in = conn.getInputStream();
			// ��Ҥ�DocumentBuilderFactory
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			// ��Ҥ�DocumentBuilder
			DocumentBuilder builder = factory.newDocumentBuilder();
			// ���oDocument
			Document doc = builder.parse(in);
			// ���o�`�I�C��
			NodeList nl = doc.getElementsByTagName("menu");
			// ���o�s����Ƥ���ContentResolver
			ContentResolver cr = getContentResolver();
			// �s����ƪ�Uri
			Uri uri1 = Menus.CONTENT_URI;
			// �R�����aSQLite��Ʈw������ƪ����
			cr.delete(uri1, null, null);

			// �`���N����x�s�����ƪ�
			for (int i = 0; i < nl.getLength(); i++) {
				// ��Ҥ�ContentValues
				ContentValues values = new ContentValues();
				// �ѪRXML�����o���id
				int id = Integer.parseInt(doc.getElementsByTagName("id")
						.item(i).getFirstChild().getNodeValue());
				// �W��
				String name = doc.getElementsByTagName("name").item(i)
						.getFirstChild().getNodeValue();
				// �Ϥ����|
				String pic = doc.getElementsByTagName("pic").item(i)
						.getFirstChild().getNodeValue();
				// ����
				int price = Integer.parseInt(doc.getElementsByTagName("price")
						.item(i).getFirstChild().getNodeValue());
				// �����s��
				int typeId = Integer.parseInt(doc
						.getElementsByTagName("typeId").item(i).getFirstChild()
						.getNodeValue());
				// �Ƶ�
				String remark = doc.getElementsByTagName("remark").item(i)
						.getFirstChild().getNodeValue();
				
				// �K�[��ContenValues����
				values.put("_id", id);
				values.put("name", name);
				values.put("price", price);
				values.put("pic", pic);
				values.put("typeId", typeId);
				values.put("remark", remark);
				// ���J��Ʈw
				cr.insert(uri1, values);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateTable() {
		// �s�����A��url
		String urlStr = HttpUtil.BASE_URL + "servlet/UpdateServlet?src=table";
		try {
			// ��Ҥ�URL����
			URL url = new URL(urlStr);
			// �}�ҳs�u
			URLConnection conn = url.openConnection();
			// ���o��J��y
			InputStream in = conn.getInputStream();
			// ��Ҥ�DocumentBuilderFactory
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			// ��Ҥ�DocumentBuilder
			DocumentBuilder builder = factory.newDocumentBuilder();
			// ���oDocument
			Document doc = builder.parse(in);
			// ���o�`�I�C��
			NodeList nl = doc.getElementsByTagName("table");
			// ���o�s����Ƥ���ContentResolver
			ContentResolver cr = getContentResolver();
			// �s����ƪ�Uri
			Uri uri1 = Tables.CONTENT_URI;
			// �R�����aSQLite��Ʈw������ƪ����
			cr.delete(uri1, null, null);

			// �`���N����x�s�����ƪ�
			for (int i = 0; i < nl.getLength(); i++) {
				// ��Ҥ�ContentValues
				ContentValues values = new ContentValues();
				// �ѪRXML�����o���id
				int id = Integer.parseInt(doc.getElementsByTagName("id")
						.item(i).getFirstChild().getNodeValue());
				// ����
				int num = Integer.parseInt(doc.getElementsByTagName("num")
						.item(i).getFirstChild().getNodeValue());
				//String num = doc.getElementsByTagName("num")
				//		.item(i).getFirstChild().getNodeValue();
				//
				//int flag = Integer.parseInt(doc.getElementsByTagName("flag")
				//		.item(i).getFirstChild().getNodeValue());
				// �Ƶ�
				String remark = doc.getElementsByTagName("description").item(i)
						.getFirstChild().getNodeValue();
				
				// �K�[��ContenValues����
				values.put("_id", id);
				values.put("num", num);
				//values.put("flag", flag);
				values.put("description", remark);
				// ���J��Ʈw
				cr.insert(uri1, values);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
