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
<title>还没领红包</title>
<link rel="stylesheet" type="text/css" href="${ctx }/views/css/main.css">
<script src="${ctx }/views/xdfjs/jquery.1.7.2.min.js"></script>
<script src="${ctx }/views/xdfjs/WeixinApi.js"></script>

<script src="${ctx }/views/xdfjs/common.js"></script>
<script src="${ctx }/views/xdfjs/iniH.js"></script>
<%@include file="/views/include/share.jsp" %>
</head>
<body>
<div class="box">
	<div class="page msg">
    	<div class="c">
        	<div class="share"><img src="${ctx }/views/images/share.gif" ></div>
            <div class="noCity"><img src="${ctx }/views/images/noCity.gif" ></div>
            <div class="noMore"><img src="${ctx }/views/images/noMore.gif" ></div>
        </div>
    	<div class="d"></div>
    </div>
	<div class="page main">
    	<a href="javascript:;" class="logo"></a>
    	<c:if test="${bagId!=null}">
	    	<c:if test="${qufen==1}">
				<a href="${ctx }/oauth/index?bagId=${bagId}&openId=${openId}&panduan=1" class="btn btn_wo"></a>
			</c:if>
			<c:if test="${qufen!=1}">
				<a href="${ctx }/oauth/index?bagId=${bagId}&openId=${openId}" class="btn btn_wo"></a>
			</c:if>
		</c:if>
		<c:if test="${bagId==null}">
		 <!-- 
			<a href="${ctx }/bag/allcity" class="btn btn_wo"></a>
			
			 <a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1f070cea69191f8a&redirect_uri=http%3A%2F%2Fszxdf.gaokaosheng.cn%2Fxdfgame%2Foauth%2Fxdfbag&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect" class="btn btn_wo"></a>
		 -->
		</c:if>
       
  </div>
    <div class="page di"><div class="kuai"><img src="${ctx }/views/images/p6.gif"></div></div>
</div>
 <script type="text/javascript">
 		$.post("${ctx}/count/addhd",function(){});
</script>
</body>
</html>
