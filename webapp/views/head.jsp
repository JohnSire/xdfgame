<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>输入密语</title>
<link rel="stylesheet" type="text/css" href="${ctx }/views/css/main.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<script src="${ctx }/views/xdfjs/jquery-1.7.2.min.js"></script>
<script src="${ctx }/views/xdfjs/jquery.1.7.2.min.js"></script>
<script src="${ctx }/views/xdfjs/WeixinApi.js"></script>
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
        <div class="t1"></div>
        <div class="bao bao1">
        <div class="pr">
        	<div class="t">逢考必过包</div>
            <div class="f">一起愉快哒“玩转”考试吧！</div>
        </div>
        </div>
        <div class="input_yz">
        	<input type="text" value="输入验证码" name="secret" id="secret1">
        </div>
        <input id="userId" type="hidden" value="${userId }">
    	 <a href="javascript:void(0)" class="btn_diy" onclick="submit1()"></a>
    </div>
    <div class="page di">
    	<div class="d"></div>
    </div>
</div>
 <script type="text/javascript">
  	$(function(){
		$("#secret1").bind({ 
			focus:function(){ 
				if (this.value == this.defaultValue){ 
					this.value=""; 
				}
				$.post("${ctx}/count/addhd",function(){}); 
			}, 
			blur:function(){ 
				if (this.value == ""){ 
					this.value = this.defaultValue; 
				} 
			} 
		}); 
  	});
  	function submit1(){
  		$.post("${ctx}/count/addhd",function(){}); 
  		$.post("${ctx }/user/updateBySecret",{"secret":$("#secret1").val(),"userId":$("#userId").val()},function(data){
  			if(data.error == "1"){
  				alert("输入验证码不正确！");
  				$("#secret1").val("");
  				$("#secret1").focus();
  			}else if(data.error == "error"){
  				window.location.href="${ctx}/views/error.jsp";
  			}else{
  				window.location.href="${ctx}/views/diyShow.jsp?schoolId="+data.schoolId+"&userId="+data.userId;
  			}
  		});
  	}
  </script>
</body>
</html>
