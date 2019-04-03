package com.lnx.oa.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lnx.oa.base.BaseAction;
import com.lnx.oa.domain.Department;
import com.lnx.oa.utils.DepartmentUtils;
@Controller
@Scope("prototype")

/*
 * 部门管理action
 * 
 */
public class DepartmentAction extends BaseAction<Department>{
	
	private Long parentId;	//属性驱动
	
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * 查询部门列表
	 * @return
	 */
	public String list() {
		List<Department> list = new ArrayList<Department>();
		if(parentId==null) {
			//查询顶级部门列表
			 list = departmentService.findTopList();
		}else {
			//查询子部门列表
			list = departmentService.findChildren(parentId);
			
			//查询该列表中子部门的上级部门
			Department dept = departmentService.findById(parentId);
			getValueStack().set("dept", dept);
		}
		getValueStack().set("list", list);
	
		return "list";
	}
	
	/**
	 *    跳转到添加部门界面,以后可以在这里查询数据
	 */
	public String addUI() {
		//准备部门列表数据，用户select框显示
		//ist<Department> list = departmentService.findAll();
		//查询所有顶级部门信息列表
		List<Department>toplist= departmentService.findTopList();
		List<Department>treelist= DepartmentUtils.getTreeList(toplist,null);
		getValueStack().set("departmentlist", treelist);
		return "addUI";
	}
	
	/**
	 *    添加部门
	 */
	public String add() {
		//根据传递过来的parentId来查询出parent实体
		if(parentId != null) {
			Department parent = departmentService.findById(parentId);
			model.setParent(parent);
		}else {
			model.setParent(null);
		}
		departmentService.save(model);
		return "toList";
	}
	
	/**
	 *  删除部门
	 */
	public String delete() {
		departmentService.delete(model);
		return "toList";
	}
	
	/**
	 *    跳转到修改部门界面
	 */
	public String editUI() {
		//准备部门列表数据，用于select框显示
		//List<Department> list = departmentService.findAll();
		
		//准备数据，查询要修改的部门信息
		Department dept = departmentService.findById(model.getId());
		
		//查询所有顶级部门信息列表
		List<Department>toplist= departmentService.findTopList();
		//获取当前修改的部门信息
		List<Department>treelist= DepartmentUtils.getTreeList(toplist,dept.getId());
		
		
		getValueStack().set("departmentlist", treelist);
		getValueStack().push(dept);
		
		//设置parentId的值，用于回显
		if(dept.getParent()!=null) {
			parentId = dept.getParent().getId();
		}
		return "editUI";
	}
	
	/**
	 * 实际修改的方法
	 */
	public String edit() {
		//遵循先查询后修改的原则
	
		Department dept = departmentService.findById(model.getId());
		
		dept.setName(model.getName());
		dept.setDescription(model.getDescription());
		
		//设置是否有上级部门
		if(parentId!=null) {
			Department parent = departmentService.findById(parentId);
			dept.setParent(parent);
		}else {
			dept.setParent(null);
		}
		departmentService.update(dept);
		
		
		return "toList";
	}
}
