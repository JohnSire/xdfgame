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
<meta charset="utf-8">
<title>城市未参加</title>
<link rel="stylesheet" type="text/css" href="${ctx }/views/css/main.css">
<script src="${ctx }/views/xdfjs/jquery.1.7.2.min.js"></script>
<script src="${ctx }/views/xdfjs/WeixinApi.js"></script>

<script src="${ctx }/views/xdfjs/common.js"></script>
<script src="${ctx }/views/xdfjs/iniH.js"></script>

</head>
<body onload="tishi()">
<div class="box">
	<div class="page msg">
    	<div class="c"><div class="share"><img src="${ctx }/views/images/share.gif" ></div></div>
    	<div class="d"></div>
    </div>
	<div class="page main">
    	<a href="javascript:;" class="logo"></a>

        <a href="${ctx }/bag/allcity?type=0" class="btn btn_select"></a>
		
    </div>
    <div class="page di">
    	<div class="d3"></div>
    </div>
</div>
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
</body>
</html>
