package com.amaker.dao;

import java.util.List;
// �֮�\�श��
public interface UnionTableDao {
	// ���o���C��
	public  List getTableList();
	// ��s�X�᪺֫���
	public void updateStatus(int tableId1,int tableId2);
}
