package com.amaker.ch03.color;

import android.app.Activity;
import android.os.Bundle;
import com.amaker.test.R;
/**
 * 
 * @author ������
 *
 */
public class TestColorActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_color);
        // �ޥ��C��귽�A�]�w�I���⬰����
        getWindow().setBackgroundDrawableResource(R.color.red_bg);
    }
}