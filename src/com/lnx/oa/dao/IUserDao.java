package com.lnx.oa.dao;

import com.lnx.oa.base.IBaseDao;
import com.lnx.oa.domain.User;

public interface IUserDao extends IBaseDao<User> {

	public int findByLoginName(String loginName);

}
