package com.lnx.oa.dao.impl;


import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.lnx.oa.base.BaseDaoImpl;
import com.lnx.oa.dao.IUserDao;
import com.lnx.oa.domain.User;
import com.lnx.oa.utils.MD5Utils;

@Repository

public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

	@Override
	public int findByLoginName(String loginName) {
		String hql ="SELECT COUNT(id) FROM User u WHERE u.loginName = ?";
		Long count = (Long) getSession().createQuery(hql).setParameter(0, loginName).uniqueResult();
		return count.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	//用户登录
	public User login(User model) {
		String hql = "FROM User u WHERE u.loginName = ? and u.password=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, model.getLoginName());
		query.setParameter(1, MD5Utils.getMD5(model.getPassword()));
		List<User> list = query.list();
		if(list != null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	

}
