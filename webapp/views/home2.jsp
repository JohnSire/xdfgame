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
    
    <title>领取红包</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/views/style/style.css">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<script src="${ctx}/views/js/jquery-1.7.2.min.js"></script>
	<script src="${ctx}/views/js/jquery.cookie.js"></script>
	<script src="${ctx}/views/js/iniH.js"></script>
	<%@include file="/views/include/share.jsp" %>
	<script type="text/javascript">
 		imgUrl = '${TEACHER_FENXIANG}';
		shareTitle = '${TEACHER_FENXIANG_SHARETITLE}';
	</script>
  </head>
  
  <body>
    <center>学校红包页<br><br><br>
    ${openId}<br>
    ${bagId}<br>
    ${hengimage }<br>
    ${msg }<br>
    <img src="${ctx}/${beijing }" width="250" height="300" /><br><br>
    <a href="${ctx }/bag/open?openId=${openId}&bagId=${bagId}">打开红包</a><br><br><br>
   	<a href="${ctx }/bag/mybag?openId=${openId}&bagId=${bagId}&qufen=1">我的红包</a>————<a href="${ctx }/views/intro.html">活动说明</a>————<a href="${ctx }/bag/allcity">其他城市</a></center>
   	<script src="${ctx}/views/js/shareAction.js"></script>
   	<script>
		wxData["link"] = "${TEACHER_FENXIANG_URL}".replace("(bagId)", "${bagId}");
		wxData["desc"] = "${TEACHER_FENXIANG_DESCCONTENT_ER}".replace("(冠名)", "${hengimage}");
		shareEndUrl = "${ctx}/bag/xiaofenxiangsuccess?dbagId=${bagId}&openId=${openId}";
	</script>
  </body>
</html>
