<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>学校已发红包</title>
<link rel="stylesheet" type="text/css" href="${ctx }/views/css/main.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

<script src="${ctx }/views/xdfjs/jquery.1.7.2.min.js"></script>
<script src="${ctx }/views/xdfjs/WeixinApi.js"></script>

<script src="${ctx }/views/xdfjs/common.js"></script>
<script src="${ctx }/views/xdfjs/iniW.js"></script>

<%@include file="/views/include/share.jsp" %>
<style type="text/css">
	.myReceive2 p{ background:url(${ctx }/views/images/t16.gif) no-repeat;  padding-left:60px;width:468px ; }
</style>
</head>
<body>
<div class="box">
	<div class="page msg">
    	<div class="c"><div class="share"><img src="${ctx }/views/images/share.gif" ></div></div>
    	<div class="d"></div>
    </div>
	<div class="page main">
    	<a href="javascript:;" class="logo"></a>
    	<div class="myReceive2">
       	     <c:forEach items="${lists }" var="list">
       	    	<p><a href="javascript:void(0)">${list.date}，${list.headImage}，${list.numN }/${list.leavess}</a></p>
    		</c:forEach>
        </div>
        <a href="javascript:showMore();" class="myMore"></a>
    	<div class="myMoreP"></div>
        <a href="javascript:select();" class=" btn_jx2"></a>
	
    </div>
<!--     <div class="page di  " style=" height:1150px; overflow:hidden"> -->
<!--     	<div class="d t300">  </div> -->
<!--     </div> -->
    <form id="diyBag" action="${ctx }/bingo/findBySchoolId" method="post">
		<input type="hidden" id="schoolId" name="schoolId" value="${schoolId }">
		<input type="hidden" id="userId" name="userId" value="${userId}" />
		<!-- 继续领 ${ctx }/schoolBag/findById-->
    </form>
</div>
<script>
var sh=$(".myReceive2").height();
var xy1=$(".di .d").offset();
var sgh=xy1.top;
function showMore(){
	// 	$(".myReceive2 .m").slideDown();
// 	$(".di .d").fadeOut();
// 	setTimeout(function(){
// 		var dh=58*$(".myReceive2 .m").children("p").length;
	
// 		$(".di").css({"height":dh+1150});
// 		xy=$(".myMoreP").offset();
		
// 		$(".di .d").animate({"top":sgh+dh});
// 	},100);
	showhongbao();
}
</script>
<script type="text/javascript">
	$(function(){
		$(".myReceive2 p").css("font-size","20px");
	});
	function select(){
		$.post("${ctx}/count/addhd",function(){}); 
		$("#diyBag").submit();
	}
	var n=1;
	function showhongbao(){
		$.post("${ctx}/count/addhd",function(){}); 
		n++;
		$.post("${ctx }/schoolBag/findAll",{"schoolId":$("#schoolId").val(),"page":n},function(data){
				if(data[0] == undefined){
					alert("全部显示完啦！");
					return;
				}
			//$(".myReceive2 p").remove();
			$.each(data,function(i){
  				$(".myReceive2").append("<p><a href=\"javascript:void(0)\">"+data[i].date+"，"+data[i].headImage+"，"+data[i].numN+"/"+data[i].leavess+" </a></p>");
				$(".myReceive2 p").css("font-size","20px");
			});
		});
	}
</script>
</body>
</html>
