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
    
    <title>学校红包页</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/views/style/style.css">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<script src="${ctx}/views/js/jquery-1.7.2.min.js"></script>
	<script src="${ctx}/views/js/jquery.cookie.js"></script>
	<script src="${ctx}/views/js/iniH.js"></script>
	<%@include file="/views/include/share.jsp" %>
	<script type="text/javascript">
 		imgUrl = '${TEACHER_FENXIANG}';
		shareTitle = '${TEACHER_FENXIANG_SHARETITLE}';
		descContent = '${TEACHER_FENXIANG_DESCCONTENT_ER}';
	</script>
  </head>
  
  <body onload="getLocation()">
  	<input type="hidden" id="demo" value="点击这个按钮，获得您的坐标" />
  	<input type="text" value="${openId }" />
  	<input type="hidden" id="num" />
  	<input type="hidden" id="bagId" value="${bagId }" />
    <center>学校红包页<br><br><br>
    <span id="hendimage"></span><br>
    <span id="msg"></span><br>
    <img src="${ctx}" /><br><br>
    <input type="button" value="点击打开" onclick="dianji()" /><br><br><br>
   	<a href="${ctx }/bag/mybag?openId=${openId}&bagId=${bagId}&qufen=1">我的红包</a>————<a href="${ctx }/views/intro.html">活动说明</a>————<a href="${ctx }/bag/allcity">其他城市</a></center>
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
	  	if(data.city == '-1'){
	  		var bagId = $("#bagId").val();
	  		if(bagId==""){
	  			var num = $("#num").val();
		  		if(num==""){
		  			$("#num").val("1");
		  			location.href="${ctx}/bag/allcity";
		  			alert("你所在的城市没有参加活动！");
		  		}
	  		}
	  	}else{
	  		$("#bagId").val(data.bagId);
	  		var aa = data.bagId;
	  		$.get("${ctx}/bag/bagdetail?bagId="+aa+"", function(data){
	  			$("#hendimage").html(data.hendInage);
	  			$("#msg").html(data.msg);
	  			$("img").attr("src", "${ctx}/"+data.beijing+"");
	  		});
	  		wxData["link"] = lineLink.replace("(bagId)", data.bagId);
	  	}
	  });
	}
	lineLink = '${TEACHER_FENXIANG_URL}';
	function dianji(){
		var bagId = $("#bagId").val();
		if(bagId){
			location.href="${ctx }/bag/open?openId=${openId}&bagId="+bagId+"";
		}else{
			alert("您所在的城市没有参加活动,请在城市页选择就近城市.");
		}
	}
	</script>
	
	
	<script src="${ctx}/views/js/shareAction.js"></script>
  </body>
</html>
