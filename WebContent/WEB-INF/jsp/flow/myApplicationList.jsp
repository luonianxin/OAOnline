<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
   <%@ include file="/WEB-INF/public/header.jsp" %>
<html>
<head>
    <title>我的申请查询</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 我的申请查询
        </div>
        <div id="Title_End"></div>
    </div>
</div>


<div id="QueryArea">
	<div style="height: 30px">
		<s:form id="pageForm" action="flow_myApplicationList" namespace="/" method="post">
		<table border=0 cellspacing=3 cellpadding=5>
			<tr>
				<td>按条件查询：</td>
				<td><s:select cssClass="SelectStyle" list="templateList" listKey="id" listValue="name" headerkey="" headerValue="查看全部模板"></s:select>
				
				</td>
				<td>
				<s:select cssClass="SelectStyle" name="status" list="%{ {'审批中','未通过','已通过'} }" headerKey="" headerValue="查看全部状态"></s:select>
				</td>
				<td><a href=""><input type="IMAGE" src="${pageContext.request.contextPath}/style/blue/images/button/query.PNG"/></a></td>
			</tr>
		</table>
		</s:form>
	</div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
				<td width="250px">标题</td>
				<td width="115px">申请人</td>
				<td width="115px">申请日期</td>
				<td width="115px">当前状态</td>
				<td>相关操作</td>
			</tr>
		</thead>	


		<!--显示数据列表：正在审批或审批完成的表单显示示例-->
        <tbody id="TableData" class="dataContainer" datakey="formList">
			<!-- 正在审批或审批完成的表单显示示例 -->
			<s:iterator value="recordList">
				<tr class="TableDetail1 template">
					<td><a href="${pageContext.request.contextPath}/Flow_Formflow/showForm.html">${title}</a></td>
					<td>${applicant.name}&nbsp;</td>
					<td>
					<s:date name="applyTime" format="yyyy-MM-dd"/>
				&nbsp;</td>
					<td>${status}&nbsp;</td>
					<td><s:a action="flow_download?applicationId=%{id}" namespace="/" >查看申请信息</s:a>
						<s:a action="flow_historyApprovedList?applicationId=%{id}" namespace="/">查看流转记录</s:a>
					</td>
				</tr>
			</s:iterator>
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail"><div id="TableTail_inside"></div></div>
</div>

<!--分页信息-->
  <%@ include file="/WEB-INF/public/pageView.jsp" %>

<div class="Description">
	
</div>

</body>
</html>
