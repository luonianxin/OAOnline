package com.lnx.oa.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户表对应类
 * 注意其中的set集合在定义的时候就new一个出来否则后期会报错
 * @author acer
 *
 */
public class User {
	private Long id;
	private String loginName;
	private String name;
	private int gender;
	private String phone;
	private String email;
	private String description;
	private String password;
	private Department department;
	private Set<Role> roles= new HashSet<Role>();
	
	/**
	 * 判断当前用户是否拥有对应的权限
	 * @return
	 */
	public boolean hasPrivilegeByName(String name){
		//判断是否是超级管理员如果是则直接显示所有权限
		if(isAdmin()) {
			return true;
		}
		
		//遍历当前用户角色，此方法需要将懒加载关闭
		for(Role role : roles) {
			//获取权限列表，然后遍历角色中的权限名与传入的名称相互比较
			Set<Privilege> privileges = role.getPrivileges();
			for(Privilege p : privileges) {
				if(name.equals(p.getName())) {
					return true;
				}
			}
		}
		return false;
	}
	/*
	 * 判断当前用户是否为超级管理员
	 */
	private boolean isAdmin() {
		if("admin".equals(loginName)) {
			return true;
		}else {
			return false;
		}
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	/**
	 *  根据url判断当前用户是否拥有对应的权限
	 * @return
	 */
	public boolean hasPrivilegeByUrl(String url){
		//判断是否是超级管理员如果是则直接显示所有权限
		if(isAdmin()) {
			return true;
		}
		
		//遍历当前用户角色，此方法需要将懒加载关闭
		for(Role role : roles) {
			//获取权限列表，然后遍历角色中的权限Url与传入的url进行比较，考虑顶级权限的情况
			Set<Privilege> privileges = role.getPrivileges();
			for(Privilege p : privileges) {
				String pUrl = p.getUrl();
				if(url.equals(pUrl)) {
					return true;
				}
			}
		}
		return false;
	}
	
		
}
