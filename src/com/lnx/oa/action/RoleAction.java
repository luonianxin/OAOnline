package com.lnx.oa.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lnx.oa.base.BaseAction;
import com.lnx.oa.domain.Role;
import com.opensymphony.xwork2.util.ValueStack;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role>{
 /**
  * 查询岗位列表
  * 
  */
	
	
	//跳转到添加页面
		public String addUI() {
		
			return "addUI";
		}
	
	//跳转到编辑页面
	public String editUI() {

		//根据id查询，用于回显
		Role role = roleService.findById(model.getId());
		ValueStack vs = getValueStack();
		vs.push(role);
		return "editUI";
	}
		
	
	//实际增加岗位(Role)方法
	public String add() {
		roleService.save(model);
		return "toList";
	}
	//实际删除方法
	public String delete() {
		roleService.delete(model);
		return "toList";
	}
	//实际的修改方法
	public String edit() {
		
		//先查询，后修改.修改的数据来自封装的model
		Role role = roleService.findById(model.getId());
		
		role.setName(model.getName());
		role.setDescription(model.getDescription());
		roleService.save(role);
		return "toList";
	}
	
	public String list() {
		//此步走完列表中的内容已经被查询出来到值栈中了，调用对应的获取值栈方法以及值栈本身的方法来获取list
		List <Role> list = roleService.findAll();
		ValueStack vs = getValueStack();
		 
		vs.set("list", list);
		return "list";
	}
}
