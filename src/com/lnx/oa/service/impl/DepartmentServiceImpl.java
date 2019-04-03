package com.lnx.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lnx.oa.dao.IDepartmentDao;
import com.lnx.oa.domain.Department;
import com.lnx.oa.service.IDepartmentService;

@Service
@Transactional
public class DepartmentServiceImpl implements IDepartmentService{

	@Resource
	public IDepartmentDao departmentDao;
	@Override
	public List<Department> findAll() {
		
		return departmentDao.findAll();
	}
	@Override
	public Department findById(Long id) {
		
		return departmentDao.findById(id);
	}
	@Override
	public void delete(Department model) {
		departmentDao.delete(model.getId());
	}
	@Override
	public void save(Department model) {
		departmentDao.save(model);
		
	}
	@Override
	public void update(Department model) {
		departmentDao.update(model);
		
	}
	@Override
	public List<Department> findTopList() {
		
		return departmentDao.findTopList();
	}
	@Override
	public List<Department> findChildren(Long parentId) {
		
		return departmentDao.findChildren(parentId);
	}

}
