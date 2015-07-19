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
	<title>打开红包</title>
	<link rel="stylesheet" type="text/css" href="${ctx }/views/css/main.css">
	<script src="${ctx }/views/xdfjs/jquery.1.7.2.min.js"></script>
	
	<script src="${ctx }/views/xdfjs/common.js"></script>
	<script src="${ctx }/views/xdfjs/iniH.js"></script>
	<%@include file="/views/include/share.jsp" %>
	<script type="text/javascript">
		imgUrl = '${TEACHER_FENXIANG}';
		shareTitle = '${TEACHER_FENXIANG_SHARETITLE}';
	</script>
</head>
<body>
<div class="box">
	<div class="page msg">
    	<div class="c"><div class="share"><img src="${ctx }/views/images/share.gif" ></div></div>
    	<div class="d"></div>
    </div>
	<div class="page main">
    	<a href="javascript:;" class="logo"></a>
        <div class="t10"><span>${money }</span></div>
        <div class="bao bao1 baoB_open">
        <div class="pr">
        	<div class="t">逢考必过包</div>
            <div class="f">${msg }</div>
            
            <div class="star star1"></div>
            <div class="star star2"></div>
            <div class="star star3"></div>
            <div class="plane"></div>
            <div class="jbZhe"></div>
            <div class="jb"></div>
        </div>
        </div>
       <!-- 
        <a href="javascript:showShare();" class="btn_get"></a>
         -->
		 <a href="javascript:toOpen();" class="btn_get"></a>
    </div>
    <div class="page di">
    	<div class="d t100"></div>
    </div>
</div>
	<script src="${ctx}/views/xdfjs/shareAction.js"></script>
   <script type="text/javascript">
   		
   		$.post("${ctx}/count/addhd",function(){});
		wxData["link"] = "${TEACHER_FENXIANG_URL}".replace("(bagId)", "${bagId}");
		wxData["desc"] = "${TEACHER_FENXIANG_DESCCONTENT_ER}".replace("(冠名)", "${hengimage}");
		shareEndUrl = "${ctx}/bag/Success?openId=${openId}&bagId=${bagId}&money=${money}&voucherId=${voucherId}";
		function toOpen(){
   			location.href=shareEndUrl;
   		}
	</script>
</body>
</html>
