package com.amaker.dao;

import com.amaker.entity.User;
// UesrDao ����
public interface UserDao {
	// �n����k
	public User login(String account,String password);
}
