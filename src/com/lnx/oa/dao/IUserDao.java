package com.lnx.oa.dao;

import com.lnx.oa.base.IBaseDao;
import com.lnx.oa.domain.User;

public interface IUserDao extends IBaseDao<User> {

	//根据用户名查找
	public int findByLoginName(String loginName);

	//用户登录	
	public User login(User model);

}
