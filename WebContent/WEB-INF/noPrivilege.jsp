<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<HTML>
<HEAD>
    <TITLE>没有权限</TITLE>
    <META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=utf-8" />
    <SCRIPT type="text/javascript" SRC="${pageContext.request.contextPath}/bootstrap3.3.7/js/jquery-2.1.0.js" CHARSET="utf-8"></SCRIPT>
    <SCRIPT type="text/javascript" SRC="${pageContext.request.contextPath}/bootstrap3.3.7/js/bootstrap.js" CHARSET="utf-8"></SCRIPT>
    <LINK TYPE="text/css" REL="stylesheet" HREF="${pageContext.request.contextPath}/boostrap3.3.7/bootstrap.css" />
    <SCRIPT TYPE="text/javascript">
    </SCRIPT>
</HEAD>
<BODY>


<DIV ID="MainArea">
		  <div class="card bg-warning text-white">
    		<div class="card-body">很抱歉，您没有权限访问此功能！</div>
  			</div>
                
           
        
        <!-- 返回操作 -->
        <DIV ID="InputDetailBar">
            <A HREF="javascript:history.go(-1);">返回</A>
        </DIV>
    
</DIV>

</BODY>
</HTML>
