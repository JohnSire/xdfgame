<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>错误操作</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
    <link rel="stylesheet" type="text/css" href="${ctx}/views/css/main.css">
	<script type="text/javascript" src="${ctx}/views/js/jquery-1.7.2.min.js"></script>
	<script src="${ctx}/views/js/common.js"></script>
	<script src="${ctx}/views/js/iniH.js"></script>
	
    <%@include file="/views/include/share.jsp" %>
  </head>
<body>
	<div class="box">
		<div class="page msg">
	    	<div class="c">
	        	<div class="share"><img src="${ctx}/views/images/share.gif" ></div>
	            <div class="noCity"><img src="${ctx}/views/images/noCity.gif" ></div>
	            <div class="noMore"><img src="${ctx}/views/images/noMore.gif" ></div>
	        </div>
	    	<div class="d"></div>
	    </div>
		<div class="page main">
	    	<a href="javascript:;" class="logo"></a>
	       
	  </div>
	    <div class="page di"><img src="${ctx}/views/images/p4.gif" width="640">
	    </div>
	</div>
<script type="text/javascript">
	wxData["link"] = "${TEACHER_FENXIANG_URL_PK}".replace("(bagId)", "${bagId}");
	wxData["desc"] = "${TEACHER_FENXIANG_DESCCONTENT_ER}".replace("(冠名)", "${title}");
</script>
</body>
</html>
