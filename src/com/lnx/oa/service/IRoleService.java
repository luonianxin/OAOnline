package com.lnx.oa.service;

import java.util.List;

import com.lnx.oa.domain.Role;

/*
 *	岗位角色管理服务
 * 
 */
public interface IRoleService {
	
	/**
	 * 添加对象
	 * @param entity
	 */
	public  void save(Role entity);
	
	/**
	 * 删除对象
	 */
	public void  delete(Long id);
	
	/**
	 * 根据id来修改对应实体
	 */
	public void  update(Role entity);
	
	/**
	 * 根据id来查询
	 */
	public Role  findById(Long id);
	
	
	/**
	 * 根据id数组来查询
	 */
	public List<Role>  findByIds(Long []ids);
	
	/**
	 * 查询所有信息
	 */
	public List<Role> findAll();

	public void delete(Role model);

}
