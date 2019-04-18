package com.lnx.oa.base;

import java.util.List;

import com.lnx.oa.domain.PageBean;
import com.lnx.oa.utils.HQLHelper;



/**
 *	通用接口 
 * @author acer
 *
 * @param <T>
 */

public interface IBaseDao<T> {
	/**
	 * 添加对象
	 * @param entity
	 */
	public  void save(T entity);
	
	/**
	 * 删除对象
	 */
	public void  delete(Long id);
	

	
	/**
	 * 根据id来修改对应实体
	 */
	public void  update(T entity);
	
	/**
	 * 根据id来查询
	 */
	public T  findById(Long id);
	
	
	/**
	 * 根据id数组来查询
	 */
	public List<T>  findByIds(Long []ids);
	
	/**
	 * 查询所有信息
	 */
	public List<T> findAll();
	
	/*
	 * 	根据页码查询页面信息.
	 * 
	 */
	public PageBean getPageBean(HQLHelper hh, int currentPage);
} 
