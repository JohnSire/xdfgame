<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>生成红包</title>
<link rel="stylesheet" type="text/css" href="${ctx }/views/css/main.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

<script src="${ctx }/views/xdfjs/jquery.1.7.2.min.js"></script>

<script src="${ctx }/views/xdfjs/common.js"></script>
<script src="${ctx }/views/xdfjs/iniH.js"></script>
<%@include file="/views/include/share.jsp" %>
</head>
<body>
<div class="box">
	<div class="page msg">
    	<div class="c"><div class="share"><img src="${ctx }/views/images/share.gif" ></div></div>
    	<div class="d"></div>
    </div>
	<div class="page main">
    	<a href="javascript:;" class="logo"></a>
        <div class="t9"></div>
       
     <c:if test="${moban==1 }">
        <div class="bao bao1 baoS" style="display:inline;">
        <div class="pr">
        	<div class="t"  style="word-wrap: break-word;">${guanming }</div>
            <div class="f"  style="word-wrap: break-word;">${msgV }</div>
        </div>
        </div>
      </c:if>
      <c:if test="${moban==2 }">   
        <div class="bao bao2 baoS" style="display:inline">
        <div class="pr">
        	<div class="t"  style="word-wrap: break-word;">${guanming }</div>
            <div class="f"  style="word-wrap: break-word;">${msgV }</div>
        </div>
        </div>
       </c:if>
       <c:if test="${moban==3 }"> 
      	 <div class="bao bao3 baoS">
            <div class="pr">
            	<div class="cc">
                    <div class="t"  style="word-wrap: break-word;">${guanming }</div>
            		<div class="f"  style="word-wrap: break-word;">${msgV }</div>
                </div>
                <div class="cd">
                	<img src="${ctx }/${image}" width="210" height="290">
                </div>
            </div>
        </div>
        </c:if>
        <div class="kuang">
        	<div><p id="http1" style="font-size: 15px"></p></div>
        </div>
        
		
    </div>
    <div class="page di">
    	<div class="d"></div>
    </div>
</div>
<script type="text/javascript">
	wxData["link"] = "${TEACHER_FENXIANG_URL}".replace("(bagId)", "${bagId}");
	//shareEndUrl = "${ctx}/user/secret?openId=${openId}";
	$(function(){
	  $("#http1").html(wxData["link"]);
	});
</script>
</body>
</html>
