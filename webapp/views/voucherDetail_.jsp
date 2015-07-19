<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
  <head>
    <title>红包使用</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
    <link rel="stylesheet" type="text/css" href="${ctx}/views/css/main.css">
	<script type="text/javascript" src="${ctx}/views/xdfjs/jquery.1.7.2.min.js"></script>
	<script src="${ctx}/views/xdfjs/common.js"></script>
	<script src="${ctx}/views/xdfjs/iniW.js"></script>

    <%@include file="/views/include/share.jsp" %>
	<script type="text/javascript">
		descContent = '${TEACHER_FENXIANG_DESCCONTENT_ER}'.replace("(冠名)", "${title}");
		wxData["link"] = "${TEACHER_FENXIANG_URL_PK}".replace("(bagId)", "${bagId}");
	</script>
  </head>
<body>
<div class="box">
	<div class="page msg">
    	<div class="c">
    		<div class="share"><img src="${ctx}/views/images/share.gif" ></div>
    	</div>
    	<div class="d"></div>
    </div>
	<div class="page main">
    	<a href="javascript:;" class="logo"></a>
        <div class="t t21"><span>${voucher.moneyN}</span></div>
    	<div class="w7"><div class="pr">
        	<div class="tt1">${schoolName}</div>
        	<p class="clear"></p>
            <div class="tt2">${voucher.codeV}</div>
        </div></div>
        <a href="${ctx}/drawbag/schoolbag?openId=${openId}&bagId=${bagId}" class=" btn_qiang"></a>
		
    </div>
    <div class="page di">
    </div>
</div>
</body>

</html>
