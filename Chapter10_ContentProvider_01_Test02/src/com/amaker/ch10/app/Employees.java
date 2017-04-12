package com.amaker.ch10.app;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * @author ������
 * �q�T���`�����O
 */
public final class Employees {
	// ���v�`��
    public static final String AUTHORITY = "com.amaker.provider.Employees";
    private Employees() {}
    // �������O
    public static final class Employee implements BaseColumns {
    	// �غc�l
        private Employee() {}
        // �s��Uri
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/employee");
        // ���e����
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.amaker.employees";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.amaker.employees";
        
        // �w�]�ƧǱ`��
        public static final String DEFAULT_SORT_ORDER = "name DESC";	// ���m�W�Ƨ�
        // ���`��
        public static final String NAME = "name";					// �m�W
        public static final String GENDER= "gender";				// �ʧO
        public static final String AGE = "age"; 					// �~��
    }
}
