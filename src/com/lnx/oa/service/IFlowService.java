package com.lnx.oa.service;

import java.util.List;

import com.lnx.oa.domain.Application;
import com.lnx.oa.domain.ApproveInfo;
import com.lnx.oa.domain.TaskView;
import com.lnx.oa.domain.User;

public interface IFlowService {

	public void submit(Application app);

	public List<TaskView> findTaskList(User currentUser);

	public void approve(ApproveInfo ai, String taskId);

}
