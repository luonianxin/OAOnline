package com.lnx.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lnx.oa.dao.IPrivilegeDao;
import com.lnx.oa.domain.Privilege;
import com.lnx.oa.service.IPrivilegeService;

@Service
@Transactional
public class PrivilegeServiceImpl implements IPrivilegeService{
		//真正在服务当中去调用dao中的方法去查询
	@Resource
	public IPrivilegeDao privilegeDao;

	@Override
	public List<Privilege> findAll() {
		
		return privilegeDao.findAll();
	}


	@Override
	public List<Privilege> findTopList() {
		
		return privilegeDao.findTopList();
	}


	@Override
	public List<Privilege> findByIds(Long[] ids) {
		
		return privilegeDao.findByIds(ids);
	}


	//查询所有权限对应的URL
	public List<String> findAllUrl() {
		
		return privilegeDao.findAllUrl();
	}




	


	


	
}
