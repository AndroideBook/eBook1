package com.amaker.dao;
import com.amaker.entity.People;
/**
 * @author 郭宏志
 * 在逃人口查詢介面
 */
public interface PeopleDao {
	/*
	 * 返回People欄位字串
	 */
	public String getString(String idno);
	/*
	 * 返回People物件
	 */
	public People get(String idno);
}
