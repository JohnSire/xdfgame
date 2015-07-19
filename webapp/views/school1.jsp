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
    <input type="text" value="${bagId }" /><br>
    <input type="text" value="${dbagId }" /><br>
    <input type="text" value="${openId }" /><br>
    <span>${hendimage }</span><br>
    <span>${msg }</span><br>
    <img src="${ctx }/${bgtype}" width="250" height="300"><br>
    <c:if test="${dbagId==null}">
    	<a href="${ctx }/bag/open?openId=${openId}&bagId=${bagId}">点击打开</a><br><br><br>
    </c:if>
    
    <c:if test="${dbagId!=null}">
    	<a href="${ctx }/bag/open?openId=${openId}&bagId=${bagId}&dbagId=${dbagId}">点击打开</a><br><br><br>
    </c:if>
    
    <c:if test="${dbagId==null}">
    	<a href="${ctx }/bag/mybag?openId=${openId}&bagId=${bagId}&panduan=1">我的红包</a>
    </c:if>
    <c:if test="${dbagId!=null}">
    	<a href="${ctx }/bag/mybag?openId=${openId}&bagId=${bagId}&dbagId=${dbagId}">我的红包</a>
    </c:if>
   	————活动说明</center>
   	
   	
   	
   	
   	<script src="${ctx}/views/js/shareAction.js"></script>
 	<script type="text/javascript">
		wxData["link"] = "${TEACHER_FENXIANG_URL}".replace("(bagId)", "${bagId}");
		wxData["desc"] = "${TEACHER_FENXIANG_DESCCONTENT_ER}".replace("(冠名)", "${hengimage}");
		shareEndUrl = "${ctx}/bag/xiaofenxiangsuccess?dbagId=${bagId}&openId=${openId}";
	</script>
  </body>
</html>
