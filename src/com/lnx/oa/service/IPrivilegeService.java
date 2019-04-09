package com.lnx.oa.service;

import java.util.List;

import com.lnx.oa.domain.Privilege;

public interface IPrivilegeService {

	public List<Privilege> findAll();


	public List<Privilege> findByIds(Long[] ids);


	public List<Privilege> findTopList();


	public List<String> findAllUrl();

	
	
}
