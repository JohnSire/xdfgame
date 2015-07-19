<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>领取红包</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
    <link rel="stylesheet" type="text/css" href="${ctx}/views/css/main.css">
    <script type="text/javascript" src="${ctx}/views/xdfjs/jquery.1.7.2.min.js"></script>
	<script src="${ctx}/views/xdfjs/common.js"></script>
	<script src="${ctx}/views/xdfjs/iniH.js"></script>
    
    <%@include file="/views/include/share.jsp" %>
	
    <script type="text/javascript">
	  	var bb = "${num}";
	  	if(bb=='true'){
	  		showMsg(".noMore");
	  	}
    </script>
  </head>
  <body>
<div class="box">
	<div class="page msg">
    	<div class="c"><div><img src="${ctx}/views/images/share.gif" ></div></div>
    	<div class="d"></div>
    	<div class="noMore"><img src="${ctx}/views/images/noMore.gif" ></div>
    </div>
	<div class="page main">
    	<a href="javascript:;" class="logo"></a>
        <div class="t2"></div>
        <c:if test="${schoolbag.moban==1 }">
        <div class="bao bao1 baoS t300">
	        <div class="pr">
	        	<div class="t">${schoolbag.headImage }</div>
	            <div class="f">${schoolbag.msgV }</div>
	        </div>
        </div>
        </c:if>
        <c:if test="${schoolbag.moban==2 }">
		<div class="bao bao2 baoSS2">
	        <div class="pr">
	        	<div class="t">${schoolbag.headImage }</div>
	            <div class="f">${schoolbag.msgV }</div>
	        </div>
        </div>
        </c:if>
        <c:if test="${schoolbag.moban==3 }">
        <div class="bao bao3 baoS t300">
            <div class="pr">
            	<div class="cc">
                    <div class="t ">${schoolbag.headImage }</div>
                    <div class="f ">${schoolbag.msgV }</div>
                </div>
                <div class="cd">
                	<img src="${ctx }${schoolbag.bgtype }" width="210" height="290">
                </div>
            </div>
        </div>
        </c:if>
       
        <a href="${ctx}/drawbag/openbag?bagId=${bagId}&openId=${openId}&title=${schoolbag.headImage}" class="btn_open"></a>
        <a href="${ctx}/drawbag/mybag?openId=${openId}&bagId=${bagId}&title=${schoolbag.headImage}" class="b_1"></a>
        <a href="${ctx}/views/description_.jsp?title=${schoolbag.headImage}&bagId=${bagId}" class="b_2"></a>
        <a href="${ctx}/drawbag/allcity?title=${schoolbag.headImage}&bagId=${bagId}" class="b_3"></a>
        <div class="ts2">不是你的城市？没关系！点击【其他城市】直达</div>
    </div>
    <div class="page di">
    	<div class="d t100"></div>
    </div>
</div>
	<script type="text/javascript">
		wxData["link"] = "${TEACHER_FENXIANG_URL_PK}".replace("(bagId)", "${bagId}");
		wxData["desc"] = "${TEACHER_FENXIANG_DESCCONTENT_ER}".replace("(冠名)", "${schoolbag.headImage}");
	</script>
</body>

</html>
