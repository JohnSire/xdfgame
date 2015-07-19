<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>哈哈</title>
    
	<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1" />

  </head>
  
  <body>
    红包您已经领取过了！
  </body>
</html>
