package com.lnx.oa.action;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lnx.oa.base.BaseAction;
import com.lnx.oa.domain.Department;
import com.lnx.oa.domain.Role;
import com.lnx.oa.domain.User;
import com.lnx.oa.utils.DepartmentUtils;
import com.lnx.oa.utils.MD5Utils;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	
	//属性驱动，只要是前台传递过来的数据，模型驱动中没有或者无法直接接收的都需要定义驱动
	private Long departmentId;	
	private Long[] roleIds;
		
	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}
	
	
	//用户登录
	public String login() {
		User user = userService.login(model);
		if(user!=null) {
			//登录成功就将用户信息写入session中
			ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
			
			return "home";
		}else {
			//登录失败
			//给出相应的提示
			this.addActionError("用户名或密码错误！！！");
			
		}
		return "loginUI";
	}
		//跳转到添加页面
		public String addUI() {
		//准备数据，用于回显(部门树列表，岗位列表)
			List<Department> topList = departmentService.findTopList();		
			List<Department> treeList = DepartmentUtils.getTreeList(topList, null);
			
			List<Role> roleList = roleService.findAll();
			getValueStack().set("treelist", treeList);
			getValueStack().set("rolelist", roleList);
			return "addUI";
		}
		
		//实际增加用户方法
		public String add() {
			int count = userService.findByLoginName(model.getLoginName());
			if(count>0) {
			//	alert("用户添加失败,该登录名已被使用!");
				return "addUI";
			}
			//判断是否选中了部门
			if(departmentId!=null) {
				Department dept= departmentService.findById(departmentId);
				//为新建用户设置选中的部门信息
				model.setDepartment(dept);
			}
			if(roleIds!=null) {
				List<Role> rolelist= roleService.findByIds(roleIds);
				//为新建用户设置选中角色信息
				model.setRoles(new HashSet<Role>(rolelist)); 
			}
			
			userService.save(model);
			return "toList";
		}
		//根据id删除用户方法
		public String delete() {
			userService.delete(model);
			return "toList";
		}
		
		//跳转到编辑页面
		public String editUI() {
			//修改前先查询
			User user = userService.findById(model.getId());
			getValueStack().push(user);
			
			//准备数据，用于回显(部门树列表，岗位列表)
			
			List<Department> topList = departmentService.findTopList();		
			List<Department> treeList = DepartmentUtils.getTreeList(topList, null);
			
			List<Role> roleList = roleService.findAll();
			getValueStack().set("treelist", treeList);
			getValueStack().set("rolelist", roleList);
			
			//要考虑用户是否有部门
			if(user.getDepartment()!=null) {
				//查询用户所属部门，用于回显
				departmentId = user.getDepartment().getId();
			}
			
			//把当前用户的岗位信息拿出来
			Set<Role> roles = user.getRoles();
			if(roles!=null&&roles.size()>0){
				//roleIds = new Long[1];
				//roleIds[0]=1L;
				//获取当前的用户岗位信息用于回显
				roleIds = new Long[roles.size()];
				
				for(Role role:roles) {
					int c = 0;
					roleIds[c++]=role.getId();
				}
				
			}
			return "editUI";
		}
		//实际根据id修改用户
		public String edit() {
			
			//先查询，后修改.修改的数据来自封装的model
			User user = userService.findById(model.getId());
			
			user.setName(model.getName());
			user.setLoginName(model.getLoginName());
			user.setDescription(model.getDescription());
			user.setEmail(model.getEmail());
			user.setGender(model.getGender());
			user.setPhone(model.getPhone());
			
			if(departmentId!=null) {
				Department dept = departmentService.findById(departmentId);
				user.setDepartment(dept);
			}else {
				user.setDepartment(null);
			}
			
			if(roleIds!=null&&roleIds.length>0) {
				List<Role> roles = roleService.findByIds(roleIds);
				user.setRoles(new HashSet<Role>(roles));
			}else {
				user.setRoles(null);
			}
			userService.update(user); 
			
			return "toList";
		}
		
		/**
		  * 查询所有用户列表
		  * 
		  */
		public String list() {
			//此步走完列表中的内容已经被查询出来到值栈中了，调用对应的获取值栈方法以及值栈本身的方法来获取list
			List<User> list = userService.findAll();
			getValueStack().set("userlist", list);
			return "list";
		}
		
		/**
		 *  初始化登录密码
		 */
		public String initPassword() {
			//还是先查询再修改
			User user = userService.findById(model.getId());
			user.setPassword(MD5Utils.getMD5("123456"));
			userService.update(user);
			return "toList";
		}
		
		/*
		 *	查询登录名是否已存在 
		 */
		public String findByLoginName() {
			String loginName = model.getLoginName();
			int count = userService.findByLoginName(loginName);
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			String flag="1";	//当前登录名可以使用
			
			if(count > 0) {
				flag="0";		//当前登录名无法使用
			}
			try {
				ServletActionContext.getResponse().getWriter().print(flag);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return NONE;
		}
}
