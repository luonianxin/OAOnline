package com.lnx.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lnx.oa.dao.IRoleDao;
import com.lnx.oa.domain.Role;
import com.lnx.oa.service.IRoleService;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {
		//真正在服务当中去调用dao中的方法去查询
	@Resource
	public IRoleDao roleDao;

	public void save(Role entity) {
		roleDao.save(entity);
	}


	public void delete(Long id) {
		roleDao.delete(id);
	}

	public void update(Role entity) {
		roleDao.update(entity);
		
	}

	public Role findById(Long id) {
		
		return roleDao.findById(id);
	}

	public List<Role> findByIds(Long[] ids) {
		return roleDao.findByIds(ids);
	}

	public List<Role> findAll() {
		return roleDao.findAll();
	}


	public void delete(Role model) {
		roleDao.delete(model.getId());
	}

}
