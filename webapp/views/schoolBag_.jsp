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
		descContent = "${TEACHER_FENXIANG_DESCCONTENT_ER}".replace("(冠名)", "${schoolbag.headImage}");
		wxData["link"] = "${TEACHER_FENXIANG_URL_PK}".replace("(bagId)", "${bagId}");
	</script>
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
       
       <c:if test="${not empty dbagId }">
	       <a href="${ctx}/drawbag/openbag?bagId=${bagId}&dbagId=${dbagId}&openId=${openId}&title=${schoolbag.headImage}" class="btn_open t720"></a>
	       <a href="${ctx}/drawbag/mybag?openId=${openId}&bagId=${dbagId}&title=${schoolbag.headImage}" class="b_1 b_1w"></a>
	       <a href="${ctx}/views/description_.jsp?title=${schoolbag.headImage}&bagId=${dbagId}" class="b_2 b_2w"></a>
       </c:if>
       <c:if test="${empty dbagId }">
        <a href="${ctx}/drawbag/openbag?bagId=${bagId}&openId=${openId}&title=${schoolbag.headImage}" class="btn_open t720"></a>
        <a href="${ctx}/drawbag/mybag?openId=${openId}&bagId=${bagId}&title=${schoolbag.headImage}" class="b_1 b_1w"></a>
        <a href="${ctx}/views/description_.jsp?title=${schoolbag.headImage}&bagId=${bagId}" class="b_2 b_2w"></a>
       </c:if>
        
    </div>
    <div class="page di">
    	<div class="d t100"></div>
    </div>
</div>
</body>

</html>
