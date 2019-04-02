package com.lnx.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lnx.oa.dao.IBookDao;
import com.lnx.oa.domain.Book;
import com.lnx.oa.service.IBookService;
@Service
@Transactional
public class BookServiceImpl implements IBookService {
	@Resource
	IBookDao bookDao ;



	public void save(Book entity) {
		bookDao.save(entity);
		
	}

	public void delete(Long id) {
		bookDao.delete(id);
	}
	


	public void update(Book entity) {
		bookDao.update(entity);
		
	}

	@Override
	public Book findById(Long id) {
		
		return bookDao.findById(id);
	}

	
	

	public List<Book> findAll() {
		
		return bookDao.findAll();
	}


	public List<Book> findByIds(Long[] ids) {
		
		return bookDao.findByIds(ids);
	}

	

}
