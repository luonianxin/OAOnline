package com.lnx.oa.domain;

import org.jbpm.api.task.Task;

/**
 * 任务实体类  封装申请信息和task信息便于后面使用
 * @author acer
 *
 */
public class TaskView {
	
	private Task task ;
	private Application application;
	
	
	public TaskView() {
	}
	public TaskView(Application application, Task task) {
		this.task = task;
		this.application = application;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public Application getApplication() {
		return application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}
}
