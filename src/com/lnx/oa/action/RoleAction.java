package com.lnx.oa.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lnx.oa.base.BaseAction;
import com.lnx.oa.domain.Privilege;
import com.lnx.oa.domain.Role;
import com.opensymphony.xwork2.util.ValueStack;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role>{
	
	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

	//权限数组
	private Long [] privilegeIds;

	
	
	//跳转到权限配置界面
	public String setPrivilegeUI() {
		//1.根据id查询当前要设置的角色，用于回显
		Role role = roleService.findById(model.getId());
		getValueStack().push(role);
		
		//2.查询所有权限数据，在当前页面显示
//		List<Privilege> privilegeList = privilegeService.findAll();
		List<Privilege> privilegeList = privilegeService.findTopList();
		
		getValueStack().set("privilegeList", privilegeList);
		//3.查询当前角色  权限用于回显
		Set<Privilege>privileges = role.getPrivileges();
		//如果权限数据不为空，则循环遍历把权限id值赋值给privilegeIds数组
		
		if(privileges != null && privileges.size()>0) {
			int i=0;
			privilegeIds = new Long [privileges.size()];
			for(Privilege privilege:privileges) {
				
				privilegeIds[i++] = privilege.getId();
			}
			
		}
		getValueStack().set("privilegeIds", privilegeIds);
		return "setPrivilegeUI";
	}
	
	
	
	//跳转到增加页面
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
	
		
	//增加权限
	public String setPrivileges() {
		//先查询后修改
		Role role = roleService.findById(model.getId());
		//如果数组不为空，则根据id查询多个权限，然后给角色设置权限
		if(privilegeIds!=null && privilegeIds.length>0) {
			List<Privilege> privilegeList = privilegeService.findByIds(privilegeIds);
			role.setPrivileges(new HashSet<Privilege>(privilegeList));
		}else {
			role.setPrivileges(null);
		}
		roleService.update(role);
		
		return "toList";
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
	
	/**
	  * 查询岗位列表
	  * 
	  */
	public String list() {
		//此步走完列表中的内容已经被查询出来到值栈中了，调用对应的获取值栈方法以及值栈本身的方法来获取list
		List <Role> list = roleService.findAll();
		ValueStack vs = getValueStack();
		 
		vs.set("list", list);
		return "list";
	}
	

}
