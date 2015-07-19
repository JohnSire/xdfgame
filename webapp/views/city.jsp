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
  
  <body onload="getLocation()">
    <p id="demo">点击这个按钮，获得您的坐标：</p>
	<script>
	var x=document.getElementById("demo");
	function getLocation()
	  {
	  if (navigator.geolocation)
	    {
	    navigator.geolocation.getCurrentPosition(showPosition);
	    }
	  else{x.innerHTML="Geolocation is not supported by this browser.";}
	  }
	function showPosition(position)
	  {
	  x.innerHTML="Latitude: " + position.coords.latitude + 
	  "<br />Longitude: " + position.coords.longitude; 
	  var jingdu = position.coords.longitude;
	  var weidu = position.coords.latitude;
	  $.get("${ctx}/bag/city?jingdu="+jingdu+"&weidu="+weidu+"", function(data){
	  	
	  })
	  }
	</script>
  </body>
</html>
