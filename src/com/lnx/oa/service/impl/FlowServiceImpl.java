package com.lnx.oa.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.TaskQuery;
import org.jbpm.api.task.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lnx.oa.dao.IApplicationDao;
import com.lnx.oa.domain.Application;
import com.lnx.oa.service.IFlowService;
/**
 *  流转的控制实现服务
 * @author acer
 *
 */
@Service
@Transactional
public class FlowServiceImpl implements IFlowService{
	@Resource
	private IApplicationDao applicationDao;
	@Resource
	private ProcessEngine processEngine;

	@Override
	public void submit(Application app) {
		//保存一个申请记录
		applicationDao.save(app);
				
		//启动一个流程实例
		Map<String, Application> map = new HashMap<String, Application>();
		map.put("application", app);
		//获取流程的实例
		ProcessInstance pi = processEngine.getExecutionService().startProcessInstanceByKey(app.getTemplate().getProcessDefinitionKey(), map);
				
		//办理提交申请的任务
		TaskQuery query = processEngine.getTaskService().createTaskQuery();
		query.processInstanceId(pi.getId());//获取当前流程实例下唯一的一个任务
		Task task = query.uniqueResult();
		
		String taskId = task.getId();
		processEngine.getTaskService().completeTask(taskId);
		
	}

}
