package com.lnx.oa.domain;

import java.util.HashSet;
import java.util.Set;
/**
 * 部门表对应类
 * 注意其中的set集合在定义的时候就new一个出来否则后期会报错
 * @author acer
 *
 */
public class Department {
	private Long id;
	private String name;
	private String description;
	private Department parent;
	private Set<Department> children = new HashSet<Department>();
	private Set<User> users= new HashSet<User>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Department getParent() {
		return parent;
	}
	public void setParent(Department parent) {
		this.parent = parent;
	}
	public Set<Department> getChildren() {
		return children;
	}
	public void setChildren(Set<Department> children) {
		this.children = children;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	
}
