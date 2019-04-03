package com.lnx.oa.dao.impl;


import org.springframework.stereotype.Repository;

import com.lnx.oa.base.BaseDaoImpl;
import com.lnx.oa.dao.IUserDao;
import com.lnx.oa.domain.User;

@Repository

public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

	@Override
	public int findByLoginName(String loginName) {
		String hql ="SELECT COUNT(id) FROM User u WHERE u.loginName = ?";
		Long count = (Long) getSession().createQuery(hql).setParameter(0, loginName).uniqueResult();
		return count.intValue();
	}

	

}
