package com.lnx.oa.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lnx.oa.dao.IApplicationDao;
import com.lnx.oa.domain.Application;
import com.lnx.oa.domain.PageBean;
import com.lnx.oa.service.IAppcalicationService;
import com.lnx.oa.utils.HQLHelper;

/*
 * 申请服务
 */
@Service
@Transactional
public class ApplicationServiceImpl implements IAppcalicationService{

	@Resource
	private IApplicationDao applicationDao;
	@Override
	public PageBean getPageBean(HQLHelper hh, int currentPage) {
		
		return applicationDao.getPageBean(hh, currentPage);
	}

	@Override
	public InputStream getInputStreamById(Long applicationId) {
		Application application = applicationDao.findById(applicationId);
		String filePath = application.getFilePath();
		InputStream in = null;
		try {
			in = new FileInputStream(new File(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return in;
		
	}

	@Override
	public Application getById(Long applicationId) {
		return  applicationDao.findById(applicationId);
	}

}
