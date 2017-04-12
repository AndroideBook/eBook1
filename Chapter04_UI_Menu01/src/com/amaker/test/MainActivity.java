package com.amaker.test;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
/**
 * 
 * @author ������
 * �ﶵ�\���d��
 */
public class MainActivity extends Activity {
	
	private static final int ITEM1 = Menu.FIRST;
	private static final int ITEM2 = Menu.FIRST+1;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

	/**
	 * �л\�Ӥ�k�K�[�\�����
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, ITEM1, 0, "�}�l");
		menu.add(0, ITEM2, 0, "�h�X");
		return true;
	}
    
    /**
     * �л\�Ӥ�k�A�^����涵�سQ��������
     */
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
		case ITEM1:
			setTitle("�}�l�C���I");
			break;
		case ITEM2:
			setTitle("�h�X�I");
			break;
		}
    	return true;
    }
}
