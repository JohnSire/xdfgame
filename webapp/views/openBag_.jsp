<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>打开红包</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
    <link rel="stylesheet" type="text/css" href="${ctx}/views/css/main.css">
	<script src="${ctx}/views/xdfjs/jquery.1.7.2.min.js"></script>
	<script src="${ctx}/views/xdfjs/common.js"></script>
	<script src="${ctx}/views/xdfjs/iniH.js"></script>
	
  	<%@include file="/views/include/share.jsp" %>
  </head>
  <script type="text/javascript">
/**
  	function drawbag(openid, bagid){
  		$.ajax({
  			url:"${ctx}/drawbag/isFirst?openId="+openid,
  			type:'post',
  			dataType:'text',
  			success:function(data, textStatus){
  				if(data=='0'){
  					showShare();
  				}else{
		  			window.location.href="${ctx}/drawbag/drawbag?openId="+openid+"&bagId="+bagid+"&title=${title}";
  				}
  			}
  		});
  	}*/
  </script>
	<script type="text/javascript">
		descContent = '${TEACHER_FENXIANG_DESCCONTENT_ER}'.replace("(冠名)", "${title}"); 
		shareEndUrl = "${ctx}/drawbag/drawbag?openId=${temp.openId}&bagId=${temp.bagId}&title=${title}";
		wxData["link"] = "${TEACHER_FENXIANG_URL_PK}".replace("(bagId)", "${temp.bagId}");
	</script>
<body>
<div class="box">
	<div class="page msg">
    	<div class="c"><div class="share"><img src="${ctx}/views/images/share.gif" ></div></div>
    	<div class="d"></div>
    </div>
	<div class="page main">
    	<a href="javascript:;" class="logo"></a>
        <div class="t10"><span>${temp.moneyN}</span></div>
        <div class="bao bao1 baoB_open">
        <div class="pr">
        	<div class="t">逢考必过包</div>
            <div class="f">一起愉快哒“玩转”考试吧！</div>
            <div class="star star1"></div>
            <div class="star star2"></div>
            <div class="star star3"></div>
            <div class="plane"></div>
            <div class="jbZhe"></div>
            <div class="jb"></div>
        </div>
        </div>
       
        <a href="javascript:showShare();" class="btn_get"></a>
		
    </div>
    <div class="page di">
    	<div class="d t100"></div>
    </div>
</div>
	
</body>
</html>
