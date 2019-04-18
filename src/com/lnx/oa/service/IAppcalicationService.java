package com.lnx.oa.service;

import java.io.InputStream;

import com.lnx.oa.domain.Application;
import com.lnx.oa.domain.PageBean;
import com.lnx.oa.utils.HQLHelper;

public interface IAppcalicationService {
	
	//分页查询方法
	public PageBean getPageBean(HQLHelper hh, int currentPage);

	//根据申请id来获取对应的输入流
	public InputStream getInputStreamById(Long applicationId);

	//根据id来获取申请信息
	public Application getById(Long applicationId);
}
