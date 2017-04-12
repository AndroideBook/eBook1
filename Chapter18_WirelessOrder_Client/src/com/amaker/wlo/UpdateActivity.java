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
 * @author 郭宏志
 * 實作資料同步功能
 */
public class UpdateActivity extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 設定標題
		setTitle("掌中寶無線點餐系統-資料同步");
		// 取得ListView實例
		ListView listView = getListView();
		// 宣告ListView要繫結的資料
		String[] items = {"更新菜單資料[MenuTbl]", "更新餐桌資料表[TableTbl]" };
		// 實例化adapter
		ListAdapter adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, items);
		// 為ListView設定adapter
		listView.setAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		switch (position) {
		// 更新菜單資料
		case 0:
			confirm(1);
			break;
		// 更新桌位資料表
		case 1:
			confirm(2);
			break;
		default:
			break;
		}
	}
	// 確認對話框
	private void confirm(final int item) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("真的要更新嗎？").setCancelable(false).setPositiveButton(
				"確定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						if (item == 1) {
							// 更新菜單資料表
							updateMenu();
						} else {
							// 更新桌位資料表
							updateTable();
						}
					}
				}).setNegativeButton("取消",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();
	}
	// 更新菜單
	private void updateMenu() {
		// 存取伺服器url
		String urlStr = HttpUtil.BASE_URL + "servlet/UpdateServlet?src=menu";
		try {
			// 實例化URL物件
			URL url = new URL(urlStr);
			// 開啟連線
			URLConnection conn = url.openConnection();
			// 取得輸入串流
			InputStream in = conn.getInputStream();
			// 實例化DocumentBuilderFactory
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			// 實例化DocumentBuilder
			DocumentBuilder builder = factory.newDocumentBuilder();
			// 取得Document
			Document doc = builder.parse(in);
			// 取得節點列表
			NodeList nl = doc.getElementsByTagName("menu");
			// 取得存取資料介面ContentResolver
			ContentResolver cr = getContentResolver();
			// 存取資料的Uri
			Uri uri1 = Menus.CONTENT_URI;
			// 刪除本地SQLite資料庫中菜單資料表的資料
			cr.delete(uri1, null, null);

			// 循環將資料儲存到菜單資料表
			for (int i = 0; i < nl.getLength(); i++) {
				// 實例化ContentValues
				ContentValues values = new ContentValues();
				// 解析XML文件取得菜單id
				int id = Integer.parseInt(doc.getElementsByTagName("id")
						.item(i).getFirstChild().getNodeValue());
				// 名稱
				String name = doc.getElementsByTagName("name").item(i)
						.getFirstChild().getNodeValue();
				// 圖片路徑
				String pic = doc.getElementsByTagName("pic").item(i)
						.getFirstChild().getNodeValue();
				// 價格
				int price = Integer.parseInt(doc.getElementsByTagName("price")
						.item(i).getFirstChild().getNodeValue());
				// 分類編號
				int typeId = Integer.parseInt(doc
						.getElementsByTagName("typeId").item(i).getFirstChild()
						.getNodeValue());
				// 備註
				String remark = doc.getElementsByTagName("remark").item(i)
						.getFirstChild().getNodeValue();
				
				// 添加到ContenValues物件
				values.put("_id", id);
				values.put("name", name);
				values.put("price", price);
				values.put("pic", pic);
				values.put("typeId", typeId);
				values.put("remark", remark);
				// 插入資料庫
				cr.insert(uri1, values);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateTable() {
		// 存取伺服器url
		String urlStr = HttpUtil.BASE_URL + "servlet/UpdateServlet?src=table";
		try {
			// 實例化URL物件
			URL url = new URL(urlStr);
			// 開啟連線
			URLConnection conn = url.openConnection();
			// 取得輸入串流
			InputStream in = conn.getInputStream();
			// 實例化DocumentBuilderFactory
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			// 實例化DocumentBuilder
			DocumentBuilder builder = factory.newDocumentBuilder();
			// 取得Document
			Document doc = builder.parse(in);
			// 取得節點列表
			NodeList nl = doc.getElementsByTagName("table");
			// 取得存取資料介面ContentResolver
			ContentResolver cr = getContentResolver();
			// 存取資料的Uri
			Uri uri1 = Tables.CONTENT_URI;
			// 刪除本地SQLite資料庫中菜單資料表的資料
			cr.delete(uri1, null, null);

			// 循環將資料儲存到菜單資料表
			for (int i = 0; i < nl.getLength(); i++) {
				// 實例化ContentValues
				ContentValues values = new ContentValues();
				// 解析XML文件取得菜單id
				int id = Integer.parseInt(doc.getElementsByTagName("id")
						.item(i).getFirstChild().getNodeValue());
				// 價格
				int num = Integer.parseInt(doc.getElementsByTagName("num")
						.item(i).getFirstChild().getNodeValue());
				//String num = doc.getElementsByTagName("num")
				//		.item(i).getFirstChild().getNodeValue();
				//
				//int flag = Integer.parseInt(doc.getElementsByTagName("flag")
				//		.item(i).getFirstChild().getNodeValue());
				// 備註
				String remark = doc.getElementsByTagName("description").item(i)
						.getFirstChild().getNodeValue();
				
				// 添加到ContenValues物件
				values.put("_id", id);
				values.put("num", num);
				//values.put("flag", flag);
				values.put("description", remark);
				// 插入資料庫
				cr.insert(uri1, values);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
