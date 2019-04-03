package com.lnx.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lnx.oa.base.BaseDaoImpl;
import com.lnx.oa.dao.IDepartmentDao;
import com.lnx.oa.domain.Department;

@Repository
@SuppressWarnings("unchecked")
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements IDepartmentDao {

	/**
	 * 查询顶级部门列表
	 */

	
	public List<Department> findTopList() {
		String hql= "FROM Department d WHERE d.parent IS NULL ";
		return this.getSession().createQuery(hql).list();
	
	}

	/*
	 * 查询指定部门的子部门列表
	 */
	public List<Department> findChildren(Long parentId) {
		String hql= "FROM Department d WHERE d.parent.id = ?";
		return this.getSession().createQuery(hql).setParameter(0, parentId).list();
	}

}
