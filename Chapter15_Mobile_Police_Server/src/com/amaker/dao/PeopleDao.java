package com.amaker.dao;
import com.amaker.entity.People;
/**
 * @author ������
 * �b�k�H�f�d�ߤ���
 */
public interface PeopleDao {
	/*
	 * ��^People���r��
	 */
	public String getString(String idno);
	/*
	 * ��^People����
	 */
	public People get(String idno);
}
