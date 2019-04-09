package com.lnx.oa.action;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.jbpm.api.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lnx.oa.service.IProcessDefinitionService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 *  流程定义action
 * @author acer
 *
 */
@Controller
@Scope("prototype")
public class ProcessDefinitionAction extends ActionSupport{
	

	private File resource;	//用于文件上传
	
	private String key; 	//属性驱动，用于根据id删除部署流程
	
	private InputStream inputStreamName;	//定义文件输入流，用于文件下载
	
	private String processDefinitionId;	//属性驱动，流程id
	
	
	private static final long serialVersionUID = 1L;	//默认生成的序列化id
	
	@Resource
	private IProcessDefinitionService processDefinitionService;
	

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitonId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}
	
	public InputStream getInputStreamName() {
		return inputStreamName;
	}

	public void setInputStreamName(InputStream inputStreamName) {
		this.inputStreamName = inputStreamName;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	
	
	
	/**
	 *  查询流程定义列表
	 */
	public String  list() {
		List<ProcessDefinition> list = processDefinitionService.findLastList();
		ActionContext.getContext().getValueStack().set("list", list);
		return "list";
	}
	
	/**
	 *  根据key来删除流程定义
	 */
	public String delete() {
		processDefinitionService.deleteBykey(key);
		return "toList";
	}
	
	/***
	 *  跳转到部署流程定义界面
	 */
	public String addUI() {
		return "addUI";
	}
	
	/**
	 *  流程部署
	 * @return
	 */
	public String add() {
		//使用上传文件来部署流程定义
		if(resource != null) {
			processDefinitionService.deploy(resource);
		}
		return "toList";
	}
	
	/*
	 *  查看部署流程
	 */
	
	public String showImage() {
		//为action中的inputStream初始化
		inputStreamName = processDefinitionService.getImageInputStream(processDefinitionId);
		return "showImage";
	}
	
	
	public File getResource() {
		return resource;
	}

	public void setResource(File resource) {
		this.resource = resource;
	}

}
