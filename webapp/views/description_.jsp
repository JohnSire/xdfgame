<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>活动说明</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
    <link rel="stylesheet" type="text/css" href="${ctx}/views/css/main.css">
	<script type="text/javascript" src="${ctx}/views/xdfjs/jquery.1.7.2.min.js"></script>
	<script src="${ctx}/views/xdfjs/common.js"></script>
	<script src="${ctx}/views/xdfjs/iniH.js"></script>
	
    <%@include file="/views/include/share.jsp" %>
	
  </head>
  <body>
<img src="${ctx}/views/images/intro_1.gif" width="100%" >
<img src="${ctx}/views/images/intro_2.gif" width="100%" >
<img src="${ctx}/views/images/intro_3.gif" width="100%" >
<img src="${ctx}/views/images/intro_4.gif" width="100%" >
<img src="${ctx}/views/images/intro_5.gif" width="100%" >
<img src="${ctx}/views/images/intro_6.gif" width="100%" >
<img src="${ctx}/views/images/intro_7.gif" width="100%" >
<img src="${ctx}/views/images/intro_8.gif" width="100%" >
<img src="${ctx}/views/images/intro_9.gif" width="100%" >
<img src="${ctx}/views/images/intro_10.gif" width="100%" >
	<script type="text/javascript">
		wxData["link"] = "${TEACHER_FENXIANG_URL_PK}".replace("(bagId)", "${bagId}");
		wxData["desc"] = "${TEACHER_FENXIANG_DESCCONTENT_ER}".replace("(冠名)", "${title}");
	</script>
</body>
</html>
