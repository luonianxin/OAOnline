package com.lnx.oa.dao.impl;


import java.io.File;

import org.springframework.stereotype.Repository;

import com.lnx.oa.base.BaseDaoImpl;
import com.lnx.oa.dao.ITemplateDao;
import com.lnx.oa.domain.Template;

@Repository

public class TemplateDaoImpl extends BaseDaoImpl<Template> implements ITemplateDao  {
		//重写根据id删除对象方法,删除模板对象时同时删除文件
		public void delete(Long id) {
			Template template = super.findById(id);
			String filePath = template.getFilePath();
			
			//delete
			File file = new File(filePath);
			if(file.exists()) {
				file.delete();
			}
			super.delete(id);	//删除模板对象数据
		}
}
