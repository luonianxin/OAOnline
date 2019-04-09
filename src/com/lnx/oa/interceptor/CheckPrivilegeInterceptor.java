package com.lnx.oa.interceptor;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.lnx.oa.domain.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;





/**
 * 	进行权限检查的自定义拦截器类
 * @author acer
 *
 */
@SuppressWarnings("serial")
public class CheckPrivilegeInterceptor  extends AbstractInterceptor{

	/**
	 *  拦截方法
	 */
	public String intercept(ActionInvocation ai) throws Exception {
		System.out.println("开始执行自定义的拦截器方法......");
		//通过actionContext获取request来获取登录的用户信息
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		
		String  actionName = ai.getProxy().getActionName();
		String  namespace = ai.getProxy().getNamespace();
		String url = namespace + actionName;
		System.out.println("访问的url是"+url);
		//为了解决用户无权限，但是还是能够敲url地址访问相应页面，判断是否以UI结尾,如果是则去掉UI
		if(url.endsWith("UI")) {
			url = url.substring(0, url.length()-2);
		}
		
		if(user==null) {
		//一、用户没有登录
			
			//a。如果用户访问的是登录功能，就不拦截
			if("/user_login".equals(url)) {
				
				ai.invoke();
			}else {
				//b.如果用户访问的不是登录功能，则跳转到登录界面
				System.out.println("必须登录后才能使用本系统的功能");
				return "loginUI";
			}
		}
		else {
		//二、用户已登录
			@SuppressWarnings("unchecked")
			List<String> allUrl = (List<String>) ServletActionContext.getServletContext().getAttribute("allUrl");
			// 如果用户访问的是要验证的权限
			if(allUrl.contains(url)) {
				boolean b = user.hasPrivilegeByUrl(url);
				//a.如果用户有权限,就不拦截
				if(b) {
					ai.invoke();
				}else{
					//b.如果用户没有权限，则跳转到没有权限的提示页面
					return "noPrivilegeUI";
				}
			}else {
				// 如果用户访问的是不需要验证的权限
				//通知struts2照常进行
				ai.invoke();
			}
			
		}
		return ai.invoke();
	}

}
