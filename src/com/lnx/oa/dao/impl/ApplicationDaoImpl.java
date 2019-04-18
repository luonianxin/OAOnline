package com.lnx.oa.dao.impl;

import org.springframework.stereotype.Repository;
import com.lnx.oa.base.BaseDaoImpl;
import com.lnx.oa.dao.IApplicationDao;
import com.lnx.oa.domain.Application;
import com.lnx.oa.domain.PageBean;
import com.lnx.oa.utils.HQLHelper;

@Repository
public class ApplicationDaoImpl  extends BaseDaoImpl<Application> implements IApplicationDao{

}
