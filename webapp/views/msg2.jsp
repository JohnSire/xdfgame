<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>新东方红包</title>
<link rel="stylesheet" type="text/css" href="${ctx }/views/style/style.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<script src="${ctx }/views/js/jquery-1.7.2.min.js"></script>
<script src="${ctx }/views/js/jquery.cookie.js"></script>
<script src="${ctx }/views/js/WeixinApi.js"></script>
<script src="${ctx }/views/js/iniH.js"></script>
<%@include file="/views/include/share.jsp" %>
</head>
<body> 

<div class="box" onclick="javascript:window.location.href='${ctx}/diyBag/diyIndexAgain/${openId}'">
  
  <div class="page">
        <div class="mBox">
        	<div class="mBoxC">
            	<div  style="width:472px; margin:250px 120px;"><img src="${ctx }/views/images/msg_7.gif" width="472" ></div>
            </div>
        	<div class="mBoxD"></div>
        </div>
  </div>
</div>
</body>
</html>