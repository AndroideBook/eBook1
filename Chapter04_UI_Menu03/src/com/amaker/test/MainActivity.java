package com.amaker.test;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
/**
 * 
 * @author ������
 * �l�\���d��
 */
public class MainActivity extends Activity {
	
	private static final int ITEM1 = Menu.FIRST;
	private static final int ITEM2 = Menu.FIRST+1;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

	/**
	 * �л\�Ӥ�k�K�[�l�\�����
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		SubMenu file = menu.addSubMenu("�ɮ�");
		SubMenu edit = menu.addSubMenu("�s��");
		file.add(0, ITEM1, 0, "�s��");
		file.add(0, ITEM2, 0, "�}��");
		return true;
	}
    
    /**
     * �л\�Ӥ�k�A�^���\���ﶵ�Q�����ƥ�
     */
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
		case ITEM1:
			setTitle("�s���ɮסI");
			break;
		case ITEM2:
			setTitle("�}���ɮסI");
			break;
		}
    	return true;
    }
}