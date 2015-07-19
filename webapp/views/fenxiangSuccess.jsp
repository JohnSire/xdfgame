<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<title>红包分享成功</title>
<link rel="stylesheet" type="text/css" href="${ctx }/views/css/main.css">
<script src="${ctx }/views/xdfjs/jquery.1.7.2.min.js"></script>
<script src="${ctx }/views/xdfjs/WeixinApi.js"></script>

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
    	<div class="c"><div class="share"><img src="${ctx }/images/share.gif" ></div></div>
    	<div class="d"></div>
    </div>
	<div class="page main">
    	<a href="javascript:;" class="logo"></a>

        <a href="${ctx }/oauth/index?bagId=${bagId}&openId=${openId}&panduan=1" class="btn btn_lin l200 t720"></a>
		
    </div>
    <div class="page di">
    	<div class="d2"></div>
    </div>
</div>
		<script src="${ctx}/views/xdfjs/shareAction.js"></script>
		<script>
			wxData["link"] = "${TEACHER_FENXIANG_URL}".replace("(bagId)", "${bagId}");
			wxData["desc"] = "${TEACHER_FENXIANG_DESCCONTENT_ER}".replace("(冠名)", "${hengimage}");
			shareEndUrl = "${ctx}/bag/xiaofenxiangsuccess?dbagId=${bagId}&openId=${openId}";
		</script>
</body>
</html>
