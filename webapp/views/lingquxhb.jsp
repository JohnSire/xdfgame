<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>成功领取红包</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
    <link rel="stylesheet" type="text/css" href="${ctx}/views/css/main.css">
	<script type="text/javascript" src="${ctx}/views/xdfjs/jquery.1.7.2.min.js"></script>
	<script src="${ctx}/views/xdfjs/common.js"></script>
	<script src="${ctx}/views/xdfjs/iniW.js"></script>
	
    <%@include file="/views/include/share.jsp" %>
</head>
<body>
<div class="box">
	<div class="page msg">
    	<div class="c">
    		<div class="share"><img src="${ctx}/views/images/share2.gif" ></div>
    	</div>
    	<div class="d"></div>
    </div>
	<div class="page main">
    	<a href="javascript:;" class="logo"></a>
        <div class="t10"><span>${baglog.moneyN }</span></div>
    	<div class="p1"></div>
        <a href="${ctx}/drawbag/schoolbag?openId=${openId}&bagId={dbagId}" class="btn btn_mo l200"></a>
		<div class="hbList">
        	<div class="my">
            	<div class="face"><img src="${headImg}" width="75" height="75"></div>
                <span>${baglog.moneyN }</span>
            </div>
            <c:forEach items="${list }" var = "i">
	            <div class="friend">
		            	<div class="face"><img src="${i.himg }" width="75" height="75"></div>
		                <div class="l">${i.nameV }<br/>收到红包，一起棒棒哒~</div>
		                <div class="r">${i.moneys }元</div>
	            </div>
            </c:forEach>
            
        </div>
    </div>
    <div class="page di">
    	<div class="d "></div>
    </div>
</div>

</body>
</html>
