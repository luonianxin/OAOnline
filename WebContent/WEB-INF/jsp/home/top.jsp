<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link type="text/css" rel="stylesheet" href="style/blue/top.css" />
	<%@ include file="/WEB-INF/public/header.jsp" %>	
</head>

<title>顶部导航栏</title>
<body class="PageBody" style="margin: 0">
 
	<div id="Head1">
		<div id="Logo">
			<a id="msgLink" href="javascript:void(0)"></a>
            <font color="#0000CC" style="color:#F1F9FE; font-size:28px; font-family:Arial Black, Arial">基于宏兴爆破公司的OA办公自动化系统</font> 
			
        </div>
		
		<div id="Head1Right">
			<div id="Head1Right_UserName">
                <img border="0" width="13" height="14" src="style/images/top/user.gif" /> 您好，<b>${ sessionScope.loginUser.loginName }</b>
			
			</div>
			<div id="Head1Right_UserDept"></div>
			<div id="Head1Right_Time"></div>
		</div>
		
        <div id="Head1Right_SystemButton">
            <s:a target="_parent" action="user_logout" namespace="/">
				<img width="78" height="20" alt="退出系统" src="style/blue/images/top/logout.gif" />
			</s:a>
        </div>
		
        <!-- <div id="Head1Right_Button">
            <a target="desktop" href="/desktop?method=show">
				<img width="65" height="20" alt="显示桌面" src="style/blue/images/top/desktop.gif" />
			</a>
        </div> -->
	</div>
    
    <div id="Head2">
        <div id="Head2_Awoke">
            <ul id="AwokeNum">
                
				  <!-- 是否有待审批文档的提示1，数量 -->
                <li><a href="Flow_Formflow/myTaskList.html" target="desktop">
                		<img border="0" width="12" height="14" src="style/images/top/wait.gif" /> 
                		待办事项（<span id="wait" class="taskListSize">1</span>）
                	</a>
                </li>
				  
                <!-- 是否有待审批文档的提示2，提示审批 -->
                <li id="messageArea">您有 1 个待审批文档，请及时审批！</li>
            </ul>
        </div>
        
		
	</div>

</body>
</html>

