package com.amaker.ch03.color;

import android.app.Activity;
import android.os.Bundle;
import com.amaker.test.R;
/**
 * 
 * @author 郭宏志
 *
 */
public class TestColorActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_color);
        // 引用顏色資源，設定背景色為紅色
        getWindow().setBackgroundDrawableResource(R.color.red_bg);
    }
}