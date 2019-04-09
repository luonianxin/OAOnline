package com.lnx.oa.dao.impl;

import org.springframework.stereotype.Repository;

import com.lnx.oa.base.BaseDaoImpl;
import com.lnx.oa.dao.IBookDao;
import com.lnx.oa.domain.Book;
/**
 * bookdao的实现继承自base类实现，实现本类接口IBook
 * @author acer
 *
 */

@Repository
public class BookDaoImpl extends BaseDaoImpl<Book>implements IBookDao {

	
}
