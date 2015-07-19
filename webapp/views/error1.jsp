<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>错误TIP</title>
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1" />
<link href="${ctx }/views/css/css.css" rel="stylesheet" type="text/css" />
</head>
<body class="n01">
<div class="head" style="position:absolute; z-index:10">
<div class="header"></div>
</div>
<div class="n03">
<button type="submit" class="xxwl" onclick="javascript:window.location.href='${ctx}/oauth/toindex'"></button>
</div>
</body>
</html>
