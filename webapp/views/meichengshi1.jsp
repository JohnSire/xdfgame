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
    
    <title>城市未参加</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/views/style/style.css">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<script src="${ctx}/views/js/jquery-1.7.2.min.js"></script>
	<script src="${ctx}/views/js/jquery.cookie.js"></script>
	<script src="${ctx}/views/js/WeixinApi.js"></script>
	<script src="${ctx}/views/js/iniH.js"></script>
	<script src="${ctx}/views/js/jquery.Jcrop.min.js"></script>
	<%@include file="/views/include/share.jsp" %>
  </head>
  
  <body onload="tishi()">
    	<center>
    		您所在的城市没有参加活动！<br><br><br><br>
    		<a href="${ctx }/bag/allcity?type=0">选择就近城市</a><br><br><br>
    		<input type="button" value="上报地理位置" onclick="getLocation()" />
    	</center>
    	<input type="hidden" id="demo" />
    	<script>
    	function tishi(){
    	var demo = $("#demo").val();
	    	if(!demo){
	    		alert("您还没有上报地理位置。");
	    		$("#demo").val("1");
	    		return false;
	    	}
    	}
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
			  $.get("${ctx}/bag/city?jingdu="+jingdu+"&weidu="+weidu+"&openId=${openId}", function(data){
			  	if(data.city == '-1'){
			  		alert("您所在的城市没有参加活动，请选择更多城市！");
			  		return false;
			  	}else{
			  		location.href = data.url;
			  	}
			  });
		  }
	</script>
    <script src="${ctx}/views/js/shareAction.js"></script>
  </body>
</html>
