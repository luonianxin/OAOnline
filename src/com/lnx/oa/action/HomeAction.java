package com.lnx.oa.action;
/**
 * 主页action
 * @author acer
 *
 */
public class HomeAction {
	
	/*
	 * 跳转到首页
	 * 
	 */
	public String index() {
		return "index";
	}
	/**
	 * 返回顶部菜单top.jsp
	 * @return
	 */
	public String top() {
		return "top";
	}
	/**
	 * 跳转到left.jsp
	 * @return
	 */
	public String left() {
		return "left";
	}
	/**
	 * 跳转到right.jsp
	 * @return
	 */
	public String right() {
		return "right";
	}
}
