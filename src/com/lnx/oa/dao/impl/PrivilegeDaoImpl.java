package com.lnx.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lnx.oa.base.BaseDaoImpl;
import com.lnx.oa.dao.IPrivilegeDao;
import com.lnx.oa.domain.Privilege;

@Repository
@SuppressWarnings("unchecked")
public class PrivilegeDaoImpl  extends BaseDaoImpl<Privilege> implements IPrivilegeDao{

	
	@Override
	public List<Privilege> findTopList() {
		String hql = "FROM Privilege p WHERE p.parent IS NULL";
		return this.getSession().createQuery(hql).list();
	}

	//查询所有权限对应的url
	public List<String> findAllUrl() {
		String hql = " SELECT url FROM Privilege WHERE url IS NOT NULL";
		return this.getSession().createQuery(hql).list();
	}
		
}
