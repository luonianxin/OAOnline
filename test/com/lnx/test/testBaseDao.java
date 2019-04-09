package com.lnx.test;


import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lnx.oa.domain.Book;
import com.lnx.oa.service.IBookService;

public class testBaseDao {
	@Test
	/**
	 *测试增加
	 */
	public void test() {
		
		//initializing spring 
	ApplicationContext atc = new ClassPathXmlApplicationContext("beans.xml");
		//此处一定只能用接口IBookService来引用方法,此处的beanid是service实现类的全名，第一个字母小写
	IBookService bookservice = (IBookService)atc.getBean("bookServiceImpl");
	
	Book book = new Book();
	book.setName("c111+");
	System.out.println(book);
	bookservice.save(book);
	}
	
	/**
	 * 测试删除
	 */
	@Test
	public void test1() {
		
		//initializing spring 
	ApplicationContext atc = new ClassPathXmlApplicationContext("beans.xml");
		//此处一定只能用接口IBookService来引用方法,此处的beanid是service实现类的全名，第一个字母小写
	IBookService bookservice = (IBookService)atc.getBean("bookServiceImpl");
	
	bookservice.delete(1L);
	}
	
	/**
	 * 测试修改
	 */
	@Test
	public void test2() {
		
		//initializing spring 
	ApplicationContext atc = new ClassPathXmlApplicationContext("beans.xml");
		//此处一定只能用接口IBookService来引用方法,此处的beanid是service实现类的全名，第一个字母小写
	IBookService bookservice = (IBookService)atc.getBean("bookServiceImpl");
	
	Book book = new Book();
	book.setId(11L);
	book.setName("修改2");
	System.out.println(book);
	bookservice.update(book);
	}
	
	/**
	 * 测试一次查询一个
	 */
	@Test
	public void test3() {
		
		//initializing spring 
	ApplicationContext atc = new ClassPathXmlApplicationContext("beans.xml");
		//此处一定只能用接口IBookService来引用方法,此处的beanid是service实现类的全名，第一个字母小写
	IBookService bookservice = (IBookService)atc.getBean("bookServiceImpl");
	Book book = bookservice.findById(2L);
	System.out.println(book);
	}
	
	/**
	 * 测试一次查询多个
	 */
	@Test
	public void test4() {
		
		//initializing spring 
	ApplicationContext atc = new ClassPathXmlApplicationContext("beans.xml");
		//此处一定只能用接口IBookService来引用方法,此处的beanid是service实现类的全名，第一个字母小写
	IBookService bookservice = (IBookService)atc.getBean("bookServiceImpl");
	
	List <Book> books = new ArrayList<Book>();
	Long []ids = new Long[]{2L,3L,4L,5L,6L};
	books = bookservice.findByIds(ids);
	for(Book book :books) {
		System.out.println(book);
	}
	}
	@Test
public void test5() {
		
		//initializing spring 
	ApplicationContext atc = new ClassPathXmlApplicationContext("beans.xml");
		//此处一定只能用接口IBookService来引用方法,此处的beanid是service实现类的全名，第一个字母小写
	IBookService bookservice = (IBookService)atc.getBean("bookServiceImpl");
	
	List <Book> books = new ArrayList<Book>();
	
	books = bookservice.findAll();
	for(Book book :books) {
		System.out.println(book);
	}
	}
}
