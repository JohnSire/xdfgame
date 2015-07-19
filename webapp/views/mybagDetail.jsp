<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>红包使用</title>
<link rel="stylesheet" type="text/css" href="${ctx }/views/css/main.css">
<script src="${ctx }/views/xdfjs/jquery.1.7.2.min.js"></script>

<script src="${ctx }/views/xdfjs/common.js"></script>
<script src="${ctx }/views/xdfjs/iniH.js"></script>
<style type="text/css">
	.tt11{ color:#ffe91e; position:absolute; left:100px; top:0; font-size:30px;}
</style>
<%@include file="/views/include/share.jsp" %>
</head>
<body>
<div class="box">
	<div class="page msg">
    	<div class="c"><div class="share"><img src="${ctx }/views/images/share.gif" ></div></div>
    	<div class="d"></div>
    </div>
	<div class="page main">
    	<a href="javascript:;" class="logo"></a>
        <div class="t t21"><span>${money}</span></div>
    	<div class="w7"><div class="pr">
    	  <!-- 
        	<div class="tt11">${oName }</div>
        	 -->
        	 <div class="tt11">深圳新东方</div>
            <div class="tt2">${code}</div>
        </div></div>
        <c:if test="${qufen==1}">
        	<a href="${ctx }/oauth/index?bagId=${bagId}&openId=${openId}&panduan=1" class=" btn_jx"></a>
        </c:if>
        <c:if test="${qufen!=1}">
        	<a href="${ctx }/oauth/index?bagId=${bagId}&openId=${openId}" class=" btn_jx"></a>
        </c:if>
		
    </div>
    <div class="page di">
    </div>
</div>

</body>
</html>
