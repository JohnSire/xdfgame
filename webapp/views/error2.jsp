<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>打开方式不对2</title>
<link rel="stylesheet" type="text/css" href="${ctx }/views/style/style.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<script src="${ctx }/views/js/jquery-1.7.2.min.js"></script>
<script src="${ctx }/views/js/jquery.cookie.js"></script>
<script src="${ctx }/views/js/iniH.js"></script>
<%@include file="/views/include/share.jsp" %>
</head>
<body>

<div class="box">
  
    <div class="page "  style="display:none">
   
      <div class="yBox2">
        	<div class="yun">
            	 <P class="obtn"><a href="javascript:window.location.href='${ctx}/oauth/toindex'" class="btn w320">回到首页</a></P>
           		 <div class="yunD"><img src="${ctx }/views/images/d.gif" width="832" height="214"><div class="h500"></div></div>
            </div>
        </div>
        <div class="cBox">
           
	   	 	<div class="err"><img src="${ctx }/views/images/err.gif" width="601" > </div>
        
        </div>
    </div>
</div>
</body>
</html>