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
    <base href="<%=basePath%>">
	<meta charset="utf-8">
	<title>领取红包</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/views/css/main.css">
	<script src="${ctx}/views/xdfjs/jquery.1.7.2.min.js"></script>
	
	<script src="${ctx}/views/xdfjs/common.js"></script>
	<script src="${ctx}/views/xdfjs/iniH.js"></script>
	<%@include file="/views/include/share.jsp" %>
	<script type="text/javascript">
 		imgUrl = '${TEACHER_FENXIANG}';
		shareTitle = '${TEACHER_FENXIANG_SHARETITLE}';
	</script>
	<script type="text/javascript">
		function cishu(){
			$.get("${ctx}/bag/cishu?openId=${openId}", function(data){
				if(data.fanhui == '-1'){
					showMsg(".noMore");
				}else{
					location.href="${ctx }/bag/open?openId=${openId}&bagId=${bagId}&qufen=1";
				}
			});
		}
	</script>
</head>
<body>
<div class="box">
	<div class="page msg">
    	<div class="noMore"><img src="${ctx}/views/images/noMore.gif" ></div>
    </div>
	<div class="page main">
    	<a href="javascript:;" class="logo"></a>
        <div class="t2"></div>
        <c:if test="${moban==1}">
	        <div class="bao bao1 baoS t300">
	        <div class="pr">
	        	<div class="t">${hengimage }</div>
	            <div class="f">${msg }</div>
	           	<input type="hidden" value="${openId}" />
	            <input type="hidden" value="${bagId}" />
	        </div>
	        </div>
       	</c:if>
       	<c:if test="${moban==2}">
       		 <div class="bao bao2 baoSS2">
	        <div class="pr">
	        	<div class="t">${hengimage }</div>
	            <div class="f">${msg }</div>
	            <input type="hidden" value="${openId}" />
	            <input type="hidden" value="${bagId}" />
	        </div>
	        </div>
       	</c:if>
       	<c:if test="${moban==3}">
       		<div class="bao bao3 baoS t300">
            <div class="pr">
            	<div class="cc">
                    <div class="t ">${hengimage}</div>
                    <div class="f ">${msg }</div>
                    <input type="hidden" value="${openId}" />
	           		<input type="hidden" value="${bagId}" />
                </div>
                <div class="cd">
                	<img src="${ctx }/${beijing}" width="210" height="290">
                </div>
            </div>
        </div>
       	</c:if>
       	<a href="javascript:cishu()" class="btn_open"></a>
<!--         <a href="${ctx }/bag/open?openId=${openId}&bagId=${bagId}" class="btn_open"></a> -->
        <a href="${ctx }/bag/mybag?openId=${openId}&bagId=${bagId}&qufen=1" class="b_1"></a>
        <a href="${ctx }/views/intro.jsp" class="b_2"></a>
        <!-- 其他城市 
        <a href="${ctx }/bag/allcity" class="b_3"></a>
         -->
        <!-- 其他城市
        <div class="ts2">不是你的城市？没关系！点击【其他城市】直达</div>
         -->
    </div>
    <div class="page di">
    	<div class="d t100"></div>
    </div>
</div>
   	<script type="text/javascript">
   		$.post("${ctx}/count/addhd",function(){});
		wxData["link"] = "${TEACHER_FENXIANG_URL}".replace("(bagId)", "${bagId}");
		wxData["desc"] = "${TEACHER_FENXIANG_DESCCONTENT_ER}".replace("(冠名)", "${hengimage}");
	</script>
</body>
</html>
