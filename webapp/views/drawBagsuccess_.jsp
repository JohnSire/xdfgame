<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>红包领取成功</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="${ctx}/views/css/main.css">
	<script type="text/javascript" src="${ctx}/views/xdfjs/jquery.1.7.2.min.js"></script>
	<script src="${ctx}/views/xdfjs/common.js"></script>
	<script src="${ctx}/views/xdfjs/iniH.js"></script>

  <%@include file="/views/include/share.jsp" %>
	
  </head>
<body>
<div class="box">
	<div class="page msg">
    	<div class="c"><div class="share"><img src="${ctx}/views/images/share2.gif" ></div></div>
    	<div class="d"></div>
    </div>
	<div class="page main">
    	<a href="javascript:;" class="logo"></a>
        <div class="t11"><span>${money}</span></div>
    	<div class="p1"></div>
        <div class="t12"><span>5</span></div>
        <a href="javascript:showShare();" class="btn btn_fa"></a>
        <a href="${ctx}/drawbag/schoolbag?bagId=${bagId}&openId=${openId}" class="btn btn_lin"></a>
		
    </div>
    <div class="page di">
    	<div class="d "></div>
    </div>
</div>
	<script type="text/javascript">
		wxData["link"] = "${XDF_XHB_PK}".replace("(bagId)", "${xbag.id}").replace("(dbagId)", "${bagId}");
		wxData["desc"] = '${TEACHER_FENXIANG_DESCCONTENT_ER1}'.replace("(money)", "${money}").replace("(冠名)", "${title}");
		shareEndUrl = "${ctx}/drawbag/xbagsuccess?xbagId=${xbag.id}&dbagId=${bagId}&openId=${openId}&title=${title}";
	</script>
</body>

</html>
