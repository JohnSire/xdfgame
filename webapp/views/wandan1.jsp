<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>完蛋了！</title>
    <meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1" />
  </head>
  
  <body>
    	<font style="margin-left: 50%; size=14">（完）</font>
  </body>
</html>
