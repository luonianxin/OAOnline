package com.lnx.oa.service;

import java.util.List;


import com.lnx.oa.domain.Book;

public interface IBookService {
	/**
	 * 添加对象
	 * @param entity
	 */
	public  void save(Book entity);
	
	/**
	 * 删除对象
	 */
	public void  delete(Long id);
	
	/**
	 * 根据id来修改对应实体
	 */
	public void  update(Book entity);
	
	/**
	 * 根据id来查询
	 */
	public Book  findById(Long id);
	
	
	/**
	 * 根据id数组来查询
	 */
	public List<Book>  findByIds(Long []ids);
	
	/**
	 * 查询所有信息
	 */
	public List<Book> findAll();
}
