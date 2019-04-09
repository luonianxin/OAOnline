package com.lnx.oa.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lnx.oa.domain.Privilege;
import com.lnx.oa.service.IPrivilegeService;
/**
 * 项目启动时自动加载的监听器
 * @author acer
 *
 */
public class MyInitListener  implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("自定义监听器，开始加载权限数据");
		//1.获取spring容器
		// 此处使用上级接口来获取实际对象的引用
		WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		
		//2.从spring容器中获取privilegeService
		//如果使用bean配置文件式，则可以直接通过beanid来获取，此处使用实现类的全称来获取，首字母小写
		//此处使用上级接口来获取实际对象的引用
		IPrivilegeService  service = (IPrivilegeService) applicationContext.getBean("privilegeServiceImpl");
		
		//3.调用Service获取权限数据
		List<Privilege> topList = service.findTopList();
		//4.将查询到的权限数据application作用域
		sce.getServletContext().setAttribute("privilegeTopList", topList);
		//5.查询所有要待验证的权限数据URL
		List<String> allUrl = service.findAllUrl();
		sce.getServletContext().setAttribute("allUrl", allUrl);

	}

}
