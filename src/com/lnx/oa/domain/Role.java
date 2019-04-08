package com.lnx.oa.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 岗位(角色)表对应类
 * 注意其中的set集合在定义的时候就new一个出来否则后期会报错
 * @author acer
 *
 */

public class Role {

	
	
	private Long id;
	private String name;
	private String description;
	
	private Set<User> users = new HashSet<User>();
	private Set<Privilege> privileges = new HashSet<Privilege>();
	
	public Set<Privilege> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}
	
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
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	
	
	
}
