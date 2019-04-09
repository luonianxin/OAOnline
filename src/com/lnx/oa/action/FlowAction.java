package com.lnx.oa.action;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lnx.oa.domain.Application;
import com.lnx.oa.domain.Template;
import com.lnx.oa.domain.User;
import com.lnx.oa.service.IFlowService;
import com.lnx.oa.service.ITemplateService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 流转Action
 * @author acer
 *
 */
@Controller
@Scope("prototype")
public class FlowAction extends ActionSupport {
	private Long templateId;//属性驱动，模板id
	private File resource;
	@Resource
	private ITemplateService templateService;
	@Resource
	private IFlowService flowService;
	
	/**
	 * 起草申请（模板列表）
	 */
	public String templateList(){
		List<Template> list = templateService.findAll();
		ActionContext.getContext().getValueStack().set("list", list);
		return "templateList";
	}
	
	/**
	 * 跳转到提交申请页面
	 */
	public String submitUI(){
		return "submitUI";
	}
	
	/**
	 * 提交申请
	 */
	public String submit(){
		Template template = templateService.findById(templateId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//处理上传文件
		String filePath = uploadFile(resource);
		
		//保存一个申请记录
		Application app = new Application();
		String title = template.getName() + "_" + getCurrentUser().getName() + "_" + sdf.format(new Date());
		app.setTitle(title);//申请的标题 ---{模板名称}_{申请人姓名}_{日期}
		app.setStatus(Application.STATUS_RUNNING);//申请的状态
		app.setApplyTime(new Date());//申请时间
		app.setFilePath(filePath);//文件存储路径
		app.setTemplate(template);//使用的模板
		app.setApplicant(getCurrentUser());//申请人
		
		flowService.submit(app);
		
		return "toMyApplicationList";
	}
	
	/**
	 * 我的申请查询列表
	 */
	public String myApplicationList(){
		
		
		return "myApplicationList";
	}
	
	/**
	 *  查看申请信息（下载申请文件）
	 */
	public String download(){
		
		return "download";
	}
	
	/**
	 * 查看流转记录（审批信息）
	 */
	public String historyApprovedList(){
		
		
		return "historyApprovedList";
	}
	
	/**
	 * 待我审批（我的任务列表）
	 */
	public String myTaskList(){
		
		return "myTaskList";
	}
	
	/**
	 * 跳转到审批页面
	 */
	public String approveUI(){
		return "approveUI";
	}
	
	/**
	 * 审批处理
	 */
	public String approve(){
		
		
		return "toMyTaskList";
	}

	
	
	/**
	 * 上传文件 
	 * @param file
	 * @return
	 */
	public String uploadFile(File file){
		String realPath = ServletActionContext.getServletContext().getRealPath("/WEB-INF/uploadFiles");
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
		String dateStr = sdf.format(new Date());
		dateStr = realPath + dateStr;
		File dateFile = new File(dateStr);
		
		if(!dateFile.exists()){
			dateFile.mkdirs();
		}
		
		String filePath = dateStr + UUID.randomUUID().toString() + ".doc";
		File dest = new File(filePath);
		file.renameTo(dest);
		return filePath;
	}
	
	/**
	 * 获取当前登录用户
	 */
	public User getCurrentUser(){
		return (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
	}
	
	/**
	 * 下载文件时，针对不同浏览器，进行附件名的编码
	 * @param filename 下载文件名
	 * @param agent 客户端浏览器(通过request.getHeader("user-agent")获得)
	 * @return 编码后的下载附件名
	 * @throws IOException
	 */
	public String encodeDownloadFilename(String filename, String agent) throws IOException{
		 // IE及其他浏览器
		
		return URLEncoder.encode(filename,"utf-8");
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public File getResource() {
		return resource;
	}

	public void setResource(File resource) {
		this.resource = resource;
	}


	
}
