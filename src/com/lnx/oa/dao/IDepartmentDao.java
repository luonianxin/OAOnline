package com.lnx.oa.dao;

import java.util.List;

import com.lnx.oa.base.IBaseDao;
import com.lnx.oa.domain.Department;


public interface IDepartmentDao extends IBaseDao<Department>{

	List<Department> findTopList();

	List<Department> findChildren(Long parentId);

}
