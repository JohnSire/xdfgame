<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>订制专属红包</title>
<link rel="stylesheet" type="text/css" href="${ctx }/views/css/main.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<script src="${ctx }/views/xdfjs/jquery.1.7.2.min.js"></script>
<script src="${ctx }/views/xdfjs/common.js"></script>
<script src="${ctx }/views/xdfjs/iniH.js"></script>
<script src="${ctx }/views/xdfjs/jquery.cookie.js"></script>
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
        <div class="t2"></div>
        <div class="bao bao1 t350">
        <div class="pr">
        	<div class="t">逢考必过包</div>
            <div class="f">一起愉快哒“玩转”考试吧！</div>
        </div>
        </div>
        <form id="myform_1" action="${ctx }/schoolBag/showBag" method="post" style="float: left;">
 			<input type="hidden" id="schoolId" value="<%=request.getParameter("schoolId") %>">
 			<input type="hidden" id="userId" value="<%=request.getParameter("userId") %>">
 			<input type="hidden" name="schoolId" value="${schoolId}">
 			<input type="hidden" name="userId" value="${userId}" />
 		</form>
        <a href="javascript:void(0)" class="btn_view" onclick="sel()"></a>
        
        <form id="myform_2" action="${ctx }/bingo/findBySchoolId" method="post" style="float: left;">
 			<input type="hidden" name="schoolId" value="${schoolId }">
 			<input type="hidden" name="userId" value="${userId}" />
 		</form>
        <a href="javascript:void(0)" class="btn_agin" onclick="jixuDIY()"></a>
    </div>
    <div class="page di">
    	<div class="d t100"></div>
    </div>
    <script type="text/javascript">
    	$(function(){
    		var schoolId = $("input[name='schoolId']").val();
			var userId = $("input[name='userId']").val();
			if(schoolId == "" || schoolId == null){
				$("input[name='schoolId']").val($("#schoolId").val());
			}
			if(userId == "" || userId == null){
				$("input[name='userId']").val($("#userId").val());
			}
    	});
		function sel(){
			$.post("${ctx}/count/addhd",function(){});
			$("#myform_1").submit();
		}
		function jixuDIY(){
			$.post("${ctx}/count/addhd",function(){});
			$("#myform_2").submit();
		}
	</script>
</div>
</body>
</html>

