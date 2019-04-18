package com.lnx.oa.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.jbpm.api.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lnx.oa.base.BaseAction;
import com.lnx.oa.domain.Template;
import com.lnx.oa.service.IProcessDefinitionService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 模板管理
 * @author acer
 *
 */
@Controller
@Scope("prototype")
public class TemplateAction extends BaseAction<Template> {
	


	private File resource;	//用于上传模板
	
	private InputStream  fileDownloadStream;	//用于文件下载的流
	
	private String fileName;	//文件名用于下载使用
		
	/*
	 *	查询模板列表 
	 */
	public String list() {
		List<Template> list = templateService.findAll();
		getValueStack().set("list", list);
		return "list";
	}
	

	@Resource
	private IProcessDefinitionService processDefinitionService;
	/**
	 *  跳转模板增加界面
	 */
	public String addUI() {
		
		//准备数据用于回显
		List<ProcessDefinition> pdlist = processDefinitionService.findLastList();
		getValueStack().set("pdList", pdlist);
		return "addUI";
	}
	
	/**
	 *  增加模板
	 */
	public String add() {
		String filePath = uploadFile(resource);
		model.setFilePath(filePath);
		templateService.save(model);
		return "toList";
	}
	
	/**
	 *  根据id删除模板
	 */
	public String delete() {
		templateService.deleteById(model.getId());
		return "toList";
	}
	
	/**
	 * 	跳转到模板修改页面
	 */
	public String editUI() {
		//根据id查询模板对象用于页面回显
		Template template = templateService.findById(model.getId());
		getValueStack().push(template);
					
		//查询流程定义列表,用于填充所用流程下拉列表
		List<ProcessDefinition> pdList = processDefinitionService.findLastList();
		getValueStack().set("pdList", pdList);
		return "editUI";
	}
	
	/**
	 * 根据id修改模板信息
	 * @return
	 */
	public String edit() {
		//先查询，再修改
		Template template = templateService.findById(model.getId());
		
		template.setName(model.getName());
		template.setProcessDefinitionKey(model.getProcessDefinitionKey());
		if(resource != null){
			//用户上传了新文件
			String filePath = uploadFile(resource);//上传文件，并且返回上传的文件路径
						
			//删除原来的文件
			String path = template.getFilePath();
			File file = new File(path);
			if(file.exists()){
				file.delete();
			}
			
		template.setFilePath(filePath);	//重新设置新文件路径
		}
		templateService.update(template);
		return "toList";
	}
	/*
	 * 	下载模板对应的文件
	 */
	public String download() {
		//对文件输入流进行初始化
		fileDownloadStream = templateService.getStreamById(model.getId());
		
		Template  template = templateService.findById(model.getId());
		
		//获取客户端浏览器
		String customer = ServletActionContext.getRequest().getHeader("user-agent");
		
		try {
			fileName = encodeDownloadFilename(template.getName()+ ".doc",customer);
		} catch (IOException e) {
			System.out.println("文件名编码出现错误！！");
			e.printStackTrace();
		}
		return "download";
	}
	
	/**
	 *  上传文件(移动文件)
	 * @return
	 */
	public String uploadFile(File file) {
		
		//为了区分文件,以上传日期将文件进行分类整理
		String realPath = "D:\\server\\Tomcat8.5\\uploadFiles";
		SimpleDateFormat sdf = new SimpleDateFormat("\\yyyy\\MM\\dd\\");
		String dateStr = sdf.format(new Date());
		dateStr = realPath + dateStr;
		File dateFile = new File(dateStr);
		
		if(!dateFile.exists()){
			dateFile.mkdirs();
		}
		//把上传的文件保存在uploadFiles目录中
		String filePath  = dateStr + File.separator + UUID.randomUUID().toString() +".doc";
		File dest = new File(filePath);
		resource.renameTo(dest);
		
		return filePath;
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
			filename = URLEncoder.encode(filename,"utf-8");
		
		return filename;
	}
	
	public File getResource() {
		return resource;
	}

	public void setResource(File resource) {
		this.resource = resource;
	}

	public InputStream getFileDownloadStream() {
		return fileDownloadStream;
	}

	public void setFileDownloadStream(InputStream fileDownloadStream) {
		this.fileDownloadStream = fileDownloadStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
