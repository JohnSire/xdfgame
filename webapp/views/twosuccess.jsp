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
<title>新东方红包分享</title>
<script src="${ctx}/views/xdfjs/jquery.1.7.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx }/views/css/main1.css">
<script>
var phoneWidth = parseInt(window.screen.width);
var phoneHeight = parseInt(window.screen.height);
var phoneScale = phoneWidth/640;

var ua = navigator.userAgent;
if (/Android (\d+\.\d+)/.test(ua)){
	var version = parseFloat(RegExp.$1);
	// andriod 2.3
	if(version>2.3){
		document.write('<meta name="viewport" content="width=640, minimum-scale = '+phoneScale+', maximum-scale = '+phoneScale+', target-densitydpi=device-dpi">');
	// andriod 2.3以上
	}else{
		document.write('<meta name="viewport" content="width=640, target-densitydpi=device-dpi">');
	}
	// 其他系统
} else {
	document.write('<meta name="viewport" content="width=640, user-scalable=no, target-densitydpi=device-dpi">');
}

</script>
</head>
<body>
<div class="boxM">
<div class="box">
    <div class="page share">
        	<div><img src="${ctx }/views/images/shareh.png" width="569" height="788"></div>
            <a href="${bbb}" class="btnS1"><img src="${ctx}/views/images/btnS1.png" width="265" height="112"></a>
            <a href="${url}" class="btnS2"><img src="${ctx}/views/images/btnS2.png" width="265" height="112"></a>
        </div>
    </div>
</div>
</div>
<script>

iniPage()

$(document).ready(function(e) {
	iniPage()
	
	
	$(".btnS1").click(function(){
		//分享成功
	})
	$(".btnS2").click(function(){
	//领取好友红包
	})

});	
$(window).resize(function(){
	iniPage()
})


function iniPage(){
	phoneWidth=$(window).width()
	phoneHeight=$(window).height()
	phoneScaleX = Math.ceil(phoneWidth*100/640)/100

	pageHeight = 1100*phoneScaleX
	
	

	//alert(pageHeight)
	$(".boxM").css({"width":phoneWidth,"height":phoneHeight,"overflow":"hidden"})
	
	$(".box").css({"-webkit-transform":"scale(" + phoneScaleX + "," + phoneScaleX + ")","transform":"scale(" + phoneScaleX + "," + phoneScaleX + ")"})
}

</script>
</body>
</html>
