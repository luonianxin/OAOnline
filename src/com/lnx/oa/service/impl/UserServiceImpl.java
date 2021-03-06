package com.lnx.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lnx.oa.dao.IUserDao;
import com.lnx.oa.domain.User;
import com.lnx.oa.service.IUserService;
import com.lnx.oa.utils.MD5Utils;



@Service
@Transactional
public class UserServiceImpl implements IUserService {
	
	@Resource
	public IUserDao userDao;

	@Override
	public List<User> findAll() {
		
		return userDao.findAll();
	}

	@Override
	public void delete(User model) {
		userDao.delete(model.getId());
	}

	@Override
	public void save(User model) {
		//新建用户时为用户设定初始密码
		model.setPassword(MD5Utils.getMD5("123456"));
		userDao.save(model);
	}

	@Override
	public User findById(Long id) {
		
		return userDao.findById(id);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
		
	}

	@Override
	public int findByLoginName(String loginName) {
		
		return userDao.findByLoginName(loginName);
	}

	//用户登录方法
	public User login(User model) {
		
		return userDao.login(model);
	} 
}
