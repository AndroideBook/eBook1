package com.amaker.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * @author ������
 * �ู�`�����O
 */
public final class Menus  implements BaseColumns{
		// ���v�`��
	    public static final String AUTHORITY = "com.amaker.provider.MENUS";
        // �s��Uri
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/menu1");
        // �w�]�ƧǱ`��
        public static final String DEFAULT_SORT_ORDER = "typeId DESC";// �ู̮�Ƨ�
        // ���`��
        public static final String TYPE_ID = "typeId";			// ����
        public static final String NAME= "name";				// �W��
        public static final String PRICE= "price";				// ����
        public static final String PIC= "pic";					// �Ϥ�
        public static final String REMARK= "remark";			// �Ƶ�
}
