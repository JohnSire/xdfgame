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
<title>红包领取成功</title>
	<link rel="stylesheet" type="text/css" href="${ctx }/views/css/main.css">
	<script src="${ctx }/views/xdfjs/jquery.1.7.2.min.js"></script>
	<script src="${ctx }/views/xdfjs/WeixinApi.js"></script>
	
	<script src="${ctx }/views/xdfjs/common.js"></script>
	<script src="${ctx }/views/xdfjs/iniH.js"></script>
	<%@include file="/views/include/share.jsp" %>
	<script>
			function onBridgeReady(){
			 WeixinJSBridge.call('hideOptionMenu');
			}
			
// 			if (typeof WeixinJSBridge == "undefined"){
// 			    if( document.addEventListener ){
// 			        document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
// 			    }else if (document.attachEvent){
// 			        document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
// 			        document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
// 			    }
// 			}else{
// 			    onBridgeReady();
// 			}
	</script>
	<script type="text/javascript">
		imgUrl = '${TEACHER_FENXIANG}';
		shareTitle = '${TEACHER_FENXIANG_SHARETITLE}';
		
	</script>
</head>
<body onload="onBridgeReady()">
<div class="box">
	<div class="page msg">
    	<div class="c"><div class="share"><img src="${ctx}/views/images/share2.gif" ></div></div>
    	<div class="d"></div>
    </div>
	<div class="page main">
    	<a href="javascript:;" class="logo"></a>
        <div class="t11"><span>${money }</span></div>
    	<div class="p1"></div>
        <div class="t12"><span>5</span></div>
        <!--  -->
        <a href="${ctx }/bag/saveUrl?bagId=${bagId}&dbagId=${dbagId}&openId=${openId}" class="btn btn_fa l200"></a>
		
    </div>
    <div class="page di">
    	<div class="d "></div>
    </div>
</div>
<script src="${ctx}/views/xdfjs/shareAction.js"></script>
 <script type="text/javascript">
 		$.post("${ctx}/count/addhd",function(){});
		wxData["link"] = "${XDF_KEJIANCAO}".replace("(bagId)", "${bagId}").replace("(dbagId)", "${dbagId}");
		wxData["desc"] = "${TEACHER_FENXIANG_DESCCONTENT_ER1}".replace("(money)", "${money}").replace("(冠名)", "${hengimage}");
		shareEndUrl = "${ctx}/bag/xiaofenxiangsuccess?xbagId=${bagId}&dbagId=${dbagId}&openId=${openId}";
	</script>
</body>
</html>
