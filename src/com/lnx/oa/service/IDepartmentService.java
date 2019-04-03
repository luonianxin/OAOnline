package com.lnx.oa.service;

import java.util.List;

import com.lnx.oa.domain.Department;

public interface IDepartmentService  {

	public List<Department> findAll();

	public Department findById(Long id);

	public void delete(Department model);

	public void save(Department model);

	public void update(Department model);

	public List<Department> findTopList();

	public List<Department> findChildren(Long parentId);

}
