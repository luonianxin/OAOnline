package com.lnx.oa.dao;

import java.util.List;

import com.lnx.oa.base.IBaseDao;
import com.lnx.oa.domain.ApproveInfo;

/*
 * 申请信息的dao
 */
public interface IApproveInfoDao extends IBaseDao<ApproveInfo>{

	public List<ApproveInfo> findApproveInfoListByApplicationId(Long applicationId);

}
