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
<meta charset="utf-8">
<title>城市选择</title>
<link rel="stylesheet" type="text/css" href="${ctx}/views/css/main.css">
	<script src="${ctx}/views/xdfjs/jquery.1.7.2.min.js"></script>
	<script src="${ctx}/views/xdfjs/WeixinApi.js"></script>
	
	<script src="${ctx}/views/xdfjs/common.js"></script>
	<script src="${ctx}/views/xdfjs/iniH.js"></script>
	<%@include file="/views/include/share.jsp" %>
<!-- 	<script type="text/javascript"> -->
<!-- 		function onload(){ -->
<!-- 			var aa = ${type}; -->
<!-- 			if(aa==1){ -->
<!-- 				alert("您没有上报当前的地理位置，请选择城市！"); -->
<!-- 			} -->
<!-- 		} -->
<!-- 	</script> -->
</head>
<body>
<div class="box">
	<div class="page msg">
    	<div class="c"><div class="share"><img src="${ctx }/images/share.gif" ></div></div>
    	<div class="d"></div>
    </div>
	<div class="page main" onclick="">
    	<a href="javascript:;" class="logo"></a>

        <div class="t t13"></div>
        <div class="cityList">
        	<ul>
        		<c:forEach items="${list}" var="i">
	            	<li>
						<a href="${i.urlN }">${i.cityV }</a><br><br>
					</li>
				</c:forEach>
            </ul>
        
        </div>
		
    </div>
    <div class="page di">
    </div>
</div>

</body>
</html>
