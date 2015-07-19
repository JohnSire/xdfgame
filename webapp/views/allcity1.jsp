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
    
    <title>城市选择</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/views/style/style.css">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<script src="${ctx}/views/js/jquery-1.7.2.min.js"></script>
	<script src="${ctx}/views/js/jquery.cookie.js"></script>
	<script src="${ctx}/views/js/WeixinApi.js"></script>
	<script src="${ctx}/views/js/iniH.js"></script>
	<script src="${ctx}/views/js/jquery.Jcrop.min.js"></script>
	<%@include file="/views/include/share.jsp" %>
	<script type="text/javascript">
 		imgUrl = '${TEACHER_FENXIANG}';
		shareTitle = '${TEACHER_FENXIANG_SHARETITLE}';
		descContent = '${TEACHER_FENXIANG_DESCCONTENT_ER}';
	</script>
	<script type="text/javascript">
		function onload(){
			var aa = ${type};
			if(aa==1){
				alert("您没有上报当前的地理位置，请选择城市！");
			}
		}
	</script>
  </head>
  
  <body onload="onload()">
  <center>
  		你所在的城市没有参加活动，请选择就近城市！<br>温馨提示：红包全国通用哦！<br>
	    <c:forEach items="${list }" var="i">
	    	<a href="${i.urlN }">${i.cityV }</a><br><br>
	    </c:forEach>
    </center>
  </body>
</html>
