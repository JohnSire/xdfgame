<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>我的红包</title>
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
    		<div class="share"><img src="${ctx}/views/images/share.gif" ></div>
    	</div>
    	<div class="d"></div>
    </div>
	<div class="page main">
    	<a href="javascript:;" class="logo"></a>
        <div class="t t14"></div>
        <div class="myReceive">
            <c:if test="${not empty bagloglist }">
				<c:forEach items="${bagloglist }" var="list">
		            <p><a href="${ctx}/drawbag/voucherdetail?voucher_id=${list.voucher_id}&openId=${openId}&bagId=${list.bagId}&schoolName=${list.schoolName}&title=${title}">${list.bagName },来自${list.schoolName }哒红包,${list.moneyN }元</a></p>
				</c:forEach>
			</c:if>
        </div>
        <a href="${ctx}/drawbag/mybag?openId=${openId}&bagId=${bagId}&page=${page+1}&title=${title}" class="myMore"></a>
    	<div class="myMoreP"></div>
        <a href="${ctx}/drawbag/schoolbag?openId=${openId}&bagId=${bagId}" class="btn_jx"></a>
	
    </div>
    <div class="page di  " style=" height:1150px; overflow:hidden">
    	<div class="d t250">  </div>
    </div>
</div>
<script type="text/javascript">
	wxData["link"] = "${TEACHER_FENXIANG_URL_PK}".replace("(bagId)", "${bagId}");
	wxData["desc"] = "${TEACHER_FENXIANG_DESCCONTENT_ER}".replace("(冠名)", "${title}");

</script>
</body>
</html>
