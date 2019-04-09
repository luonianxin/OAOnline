package com.lnx.oa.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;

import org.jbpm.api.NewDeployment;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.ProcessEngine;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lnx.oa.service.IProcessDefinitionService;

/*
 * 流程定义部署服务
 */
@Service
@Transactional
public class ProcessDefinitionServiceImpl implements IProcessDefinitionService{
	
	//流程引擎
	@Resource
	private ProcessEngine processEngine;

	/**
	 * 	查询最新的流程定义列表
	 */
	public List<ProcessDefinition> findLastList() {
		//获取流程定义查询对象
		ProcessDefinitionQuery 	query = processEngine.getRepositoryService().createProcessDefinitionQuery();
		//根据版本进行升序排列
		query.orderAsc(ProcessDefinitionQuery.PROPERTY_VERSION);
		List<ProcessDefinition> list = query.list();
		
		Map<String ,ProcessDefinition> map = new  HashMap<String ,ProcessDefinition>();
		
		//循环遍历集合把集合当中的内容放入map中
		for(ProcessDefinition pd : list) {
			map.put(pd.getKey(), pd);
		}
		
		return new ArrayList<ProcessDefinition>(map.values());
	}

	//根据上传文件来部署流程定义
	public void deploy(File resource) {
		
		//调用jbpm框架的引擎方法获取部署对象
		NewDeployment deployment = processEngine.getRepositoryService().createDeployment();
		ZipInputStream zipInputStream = null;
		
		try {
			zipInputStream = new ZipInputStream(new FileInputStream(resource));
			//调用部署对象的添加源文件的方法(zip文件由xxx.jbpm.XML和一个对应的图片组成)
			deployment.addResourcesFromZipInputStream((zipInputStream));
			deployment.deploy();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}finally {
			if(zipInputStream !=null) {
				try {
					zipInputStream.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		}
		
	}

	/**
	 * 根据流程定义时的key值来删除对应的流程
	 */
	public void deleteBykey(String key) {
		ProcessDefinitionQuery query = processEngine.getRepositoryService().createProcessDefinitionQuery();
		//指定查询条件
		query.processDefinitionKey(key);
		List<ProcessDefinition> list = query.list();//同一个key值可能对应多个版本的processDefinition
		
		for(ProcessDefinition pd : list) {
			processEngine.getRepositoryService().deleteDeployment(pd.getDeploymentId());
		}
	}

	/*
	 * 	根据流程定义id来获取相应流程图的输入流(non-Javadoc)
	 */
	public InputStream getImageInputStream(String processDefinitionId) {
		ProcessDefinitionQuery query = processEngine.getRepositoryService().createProcessDefinitionQuery();
		//设置查询id
		query.processDefinitionId(processDefinitionId);
		ProcessDefinition pd = query.uniqueResult();
		String imageName = pd.getImageResourceName();
		InputStream inputStream = processEngine.getRepositoryService().getResourceAsStream(pd.getDeploymentId(), imageName);
		
		return inputStream;
	}
}
