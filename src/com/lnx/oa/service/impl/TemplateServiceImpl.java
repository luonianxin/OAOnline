package com.lnx.oa.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lnx.oa.dao.ITemplateDao;
import com.lnx.oa.domain.Template;
import com.lnx.oa.service.ITemplateService;

/*
 * 	模板管理服务
 */
@Service
@Transactional
public class TemplateServiceImpl implements ITemplateService{

	@Resource
	private ITemplateDao templateDao;
	
	
	//查询所有模板列表
	public List<Template> findAll() {
		
		return templateDao.findAll();
	}


	//保存模板
	public void save(Template model) {
		templateDao.save(model);
		
	}


	
	public void deleteById(Long id) {
		templateDao.delete(id);
	}


	@Override
	public Template findById(Long id) {
		
		return templateDao.findById(id);
	}


	@Override
	public void update(Template template) {
		templateDao.update(template);
		
	}


	//根据传入id获取对应模板文件的输入流
	public InputStream getStreamById(Long id) {
		Template template  = templateDao.findById(id);
		String filePath = template.getFilePath();
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return inputStream;
	}

}
