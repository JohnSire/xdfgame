<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>常见问题</title>
<link rel="stylesheet" type="text/css" href="${ctx }/views/css/main.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<script src="${ctx }/views/xdfjs/jquery.1.7.2.min.js"></script>

<script src="${ctx }/views/xdfjs/common.js"></script>
<script src="${ctx }/views/xdfjs/iniW.js"></script>
<%@include file="/views/include/share.jsp" %>
</head>
<body>
<img src="${ctx }/views/images/faq_1.gif" width="100%" >
<img src="${ctx }/views/images/faq_2.gif" width="100%" >
<img src="${ctx }/views/images/faq_3.gif" width="100%" >
</body>
</html>

