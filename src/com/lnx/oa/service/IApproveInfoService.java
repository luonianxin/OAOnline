package com.lnx.oa.service;

import java.util.List;

import com.lnx.oa.domain.ApproveInfo;

public interface IApproveInfoService {

	public List<ApproveInfo> findApproveInfoListByApplicationId(Long applicationId);

}
