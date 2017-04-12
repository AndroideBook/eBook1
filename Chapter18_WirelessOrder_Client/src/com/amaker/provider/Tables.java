package com.amaker.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * @author ������
 * �ู�`�����O
 */
public final class Tables  implements BaseColumns{
		// ���v�`��
	    public static final String AUTHORITY = "com.amaker.provider.TABLES";
        // �s��Uri
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/table");
        // �w�]�ƧǱ`��
        public static final String DEFAULT_SORT_ORDER = "num DESC";// ���ู�Ƨ�
        // ���`��
        public static final String NUM = "num";					// �ู
        public static final String DESCRIPTION= "description";	// �y�z
}
