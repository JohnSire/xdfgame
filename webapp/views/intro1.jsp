<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta charset="utf-8">
<title>活动说明</title>
<link rel="stylesheet" type="text/css" href="${ctx}/views/style/style.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<script src="${ctx}/views/js/jquery-1.7.2.min.js"></script>
<%@include file="/views/include/share.jsp" %>
<script type="text/javascript">
$(document).ready(function(e) {
	$.post("${ctx}/count/addhd",function(){});
});
</script>
</head>
<body> 
<img src="${ctx}/views/images/intro/i1.gif" width="100%">
<img src="${ctx}/views/images/intro/i2.gif" width="100%">
<img src="${ctx}/views/images/intro/i3.gif" width="100%">
<img src="${ctx}/views/images/intro/i4.gif" width="100%">
<img src="${ctx}/views/images/intro/i5.gif" width="100%">
<img src="${ctx}/views/images/intro/i6.gif" width="100%">
<img src="${ctx}/views/images/intro/i7.gif" width="100%">
</body>
</html>