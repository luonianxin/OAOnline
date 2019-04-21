<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- 导航栏菜单 -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <%@ include file="/WEB-INF/public/header.jsp" %>
<script type="text/javaScript" src="${pageContext.request.contextPath}/script/menu.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/blue/menu.css" />
</head>
<body style="margin: 0">
<div id="Menu">
    <ul id="MenuUl">
        <li class="level1">
            <div onClick="menuClick(this)" class="level1Style"><img src="${pageContext.request.contextPath}/style/images/MenuIcon/FUNC20001.gif" class="Icon" />个人办公</div>
            <ul style="display: none;" class="MenuLevel2">
                <li class="level2">
                    <div class="level2Style"><img src="${pageContext.request.contextPath}/style/images/MenuIcon/menu_arrow_single.gif" /> 打卡签到</div>
                </li>
                <li class="level2">
                    <div class="level2Style"><img src="${pageContext.request.contextPath}/style/images/MenuIcon/menu_arrow_single.gif" /> 备忘录</div>
                </li>
                <!-- <li class="level2">
                    <div class="level2Style"><img src="${pageContext.request.contextPath}/style/images/MenuIcon/menu_arrow_single.gif" /> 工作计划</div>
                </li> -->
               
                
            </ul>
        </li>
        <li class="level1">
            <div onClick="menuClick(this);" class="level1Style"><img src="${pageContext.request.contextPath}/style/images/MenuIcon/FUNC20071.gif" class="Icon" style="margin-left: 1px"/> 项目查询</div>
            <ul style="display: none;" class="MenuLevel2">
                <li class="level2">
                    <div class="level2Style"><img src="${pageContext.request.contextPath}/style/images/MenuIcon/menu_arrow_single.gif" /> <a target="right" href="Flow_ProcessDefinition/list.html">审批流程管理</a></div>
                </li>
		
            </ul>
        </li>
       
        <li class="level1">
            <div onClick="menuClick(this);" class="level1Style"><img src="${pageContext.request.contextPath}/style/images/MenuIcon/FUNC20077.gif" class="Icon" /> 个人设置</div>
            <ul style="display: none;" class="MenuLevel2">
                <!-- <li class="level2">
                    <div class="level2Style"><img src="${pageContext.request.contextPath}/style/images/MenuIcon/menu_arrow_single.gif" /> <s:a target="right" action="user_list">个人信息</s:a></div>
                </li> -->
                <li class="level2">
                    <div class="level2Style"><img src="${pageContext.request.contextPath}/style/images/MenuIcon/menu_arrow_single.gif" /> <s:a target="right" action="user_editPasswdUI" namespace="/">密码修改</s:a></div>
                </li>

            </ul>
        </li>
        <s:iterator value="#application.privilegeTopList">
        <!-- 从application中获取当前登录用户，然后根据当前用户的角色获取对应的权限判断是否拥有该权限 -->
        	 <s:if test="#session.loginUser.hasPrivilegeByName(name)"> 
        	 
	        <li class="level1">
	            <div onClick="menuClick(this);" class="level1Style"><img src="${pageContext.request.contextPath}/style/images/MenuIcon/${id}.gif" class="Icon" /> ${name}</div>
		            <ul style="display: none;" class="MenuLevel2">
		               
		               <s:iterator value="children">
			               <s:if test="#session.loginUser.hasPrivilegeByName(name)"> 
			               <li class="level2">
			                    <div class="level2Style"><img src="${pageContext.request.contextPath}/style/images/MenuIcon/menu_arrow_single.gif" /> <a target="right" href="${pageContext.request.contextPath}${url}.do" > ${ name }</a></div>
			                </li> 
			                </s:if>
		                </s:iterator> 
		            </ul>
	        </li>
	     </s:if> 
        </s:iterator>
        
    </ul>
</div>
</body>
</html>
