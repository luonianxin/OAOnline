package com.lnx.oa.action;

import org.springframework.stereotype.Controller;

import com.lnx.oa.base.BaseAction;
import com.lnx.oa.domain.Book;

@Controller
public class BookAction extends BaseAction<Book> {
	
	public String execute() throws Exception{
		System.out.println(model);
		bookService.save(model);
		return NONE;
	}
	
	
}
