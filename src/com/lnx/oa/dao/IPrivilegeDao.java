package com.lnx.oa.dao;

import java.util.List;

import com.lnx.oa.base.IBaseDao;
import com.lnx.oa.domain.Privilege;

public interface IPrivilegeDao extends IBaseDao<Privilege> {

	public List<Privilege> findTopList();

	public List<String> findAllUrl();

}
