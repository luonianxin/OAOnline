package com.lnx.oa.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;



@SuppressWarnings("unchecked")

public class BaseDaoImpl<T> implements IBaseDao<T> {
	
	@Resource
	private SessionFactory sessionFactory;
	private Class<T> clazz;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public BaseDaoImpl() {
		//获得实体类型，初始化clazz参数
		ParameterizedType genericsuperclass	=(ParameterizedType) this.getClass().getGenericSuperclass();
		Type [] types = genericsuperclass.getActualTypeArguments();//获取<>中的泛型
		this.clazz =  (Class<T>) types[0];
	}
	
	public void save(T entity) {
		getSession().save(entity);		
	}

	public void delete(Long id) {
		 Object object = getSession() .get(clazz, id);
		 getSession().delete(object);	
		
	}


	public void update(T entity) {
		getSession().update(entity);
	}


	public T findById(Long id) {
		
		return (T) getSession().get(clazz, id);
	}
	

	public List<T> findAll() {
		String hql ="FROM " +clazz.getSimpleName();
		return getSession().createQuery(hql).list();
	}
	
	
	
	public List<T> findByIds(Long[] idss) {
		String hql ="FROM "+clazz.getSimpleName()+" WHERE id in(:ids)";
		Query query = getSession().createQuery(hql);
		query.setParameterList("ids",idss);//一次性给多个元素赋值
		return  query.list();
	}
	
	public Session getSession() {
		return	sessionFactory.getCurrentSession();
	}

	


}
