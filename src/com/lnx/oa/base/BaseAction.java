package com.lnx.oa.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.annotation.Resource;
import com.lnx.oa.service.IBookService;
import com.lnx.oa.service.IRoleService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
/**
 * 	通用的action
 * @author acer
 *
 * @param <T>
 */
public class BaseAction<T> extends  ActionSupport implements ModelDriven<T> {
	@Resource
	protected IBookService bookService;
	@Resource
	protected IRoleService roleService;
	protected T model;
	//model的类型也得获取，所以在构造方法中进行初始化model
	@SuppressWarnings("unchecked")
	public BaseAction(){
	      ParameterizedType type =(ParameterizedType) this.getClass().getGenericSuperclass();
	      Type [] types = type.getActualTypeArguments();
	      Class<T> clazz= (Class<T>) types[0];
	      try {
			model=clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public T getModel() {
		return model;
	}

	
	/**
	 * 获取值栈,后期页面传值时需要用到
	 */
	protected ValueStack getValueStack() {
		return ActionContext.getContext().getValueStack();
	}
}
