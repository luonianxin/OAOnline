<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title> OA自动化办公系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<frameset rows="100,*" framespacing="0" border="0" frameborder="0">
    <frame src="${pageContext.request.contextPath}/home_top.do" name="TopMenu"  scrolling="no" noresize />
    <frameset cols="180,*" id="resize">
        <frame noresize name="menu" src="${pageContext.request.contextPath}/home_left.do" scrolling="yes" />
        <frame noresize name="right" src="${pageContext.request.contextPath}/home_right.do" scrolling="yes" />
    </frameset>
</frameset>

</html>
