package com.lnx.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lnx.oa.dao.IApproveInfoDao;
import com.lnx.oa.domain.ApproveInfo;
import com.lnx.oa.service.IApproveInfoService;

/*
 * 审批服务
 */
@Service
@Transactional
public class ApproveInfoServiceImpl implements IApproveInfoService {
	@Resource
	private IApproveInfoDao approveInfoDao;

	/*
	 * 根据id查询审批信息列表
	 */
	public List<ApproveInfo> findApproveInfoListByApplicationId(Long applicationId) {
		
		return approveInfoDao.findApproveInfoListByApplicationId(applicationId);
	}
}
