<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>红包打开页</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/views/style/style.css">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<script src="${ctx}/views/js/jquery-1.7.2.min.js"></script>
	<script src="${ctx}/views/js/jquery.cookie.js"></script>
	<script src="${ctx}/views/js/WeixinApi.js"></script>
	<script src="${ctx}/views/js/iniH.js"></script>
	<script src="${ctx}/views/js/jquery.Jcrop.min.js"></script>
	<%@include file="/views/include/share.jsp" %>
  </head>
  
  <body>
    	<center>你还没有领取过红包！<br><br><br>
    	<c:if test="${qufen==1}">
    		<a href="${ctx }/oauth/index?bagId=${bagId}&openId=${openId}&panduan=1">继续领红包</a>
    	</c:if>
    	<c:if test="${qufen!=1}">
    		<a href="${ctx }/oauth/index?bagId=${bagId}&openId=${openId}">继续领红包</a>
    	</c:if>
    	</center>
  </body>
</html>
