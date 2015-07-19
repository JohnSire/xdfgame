<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>我的红包</title>
<link rel="stylesheet" type="text/css" href="${ctx }/views/css/main.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<script src="${ctx }/views/xdfjs/jquery.1.7.2.min.js"></script>

<script src="${ctx }/views/xdfjs/common.js"></script>
<script src="${ctx }/views/xdfjs/iniW.js"></script>
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
        <div class="t t14"></div>
        <div class="myReceive">
        	<ul id="datalist">
	        	<c:forEach items="${list }" var = "i">
	        		<c:if test="${qufen==1}">
		           		<p><a style="font-size: 24px;" href="${ctx }/bag/bagDetail?voucherId=${i.voucherId }&bagId=${i.bagId}&openId=${openId}&school=${i.schoolName }&qufen=1">${i.bagName },来自${i.schoolName }哒红包,${i.money }元</a></p>
                	</c:if>
                	<c:if test="${qufen!=1}">
		           		<p><a style="font-size: 24px;" href="${ctx }/bag/bagDetail?voucherId=${i.voucherId }&bagId=${i.bagId}&openId=${openId}&school=${i.schoolName }">${i.bagName },来自${i.schoolName }哒红包,${i.money }元</a></p>
                	</c:if>
	        	</c:forEach>
        	</ul>
        </div>
        <c:if test="${qufen==1}">
        	<a href="javascript:getMore();" class="myMore"></a>
        </c:if>
        <c:if test="${qufen!=1}">
        	<a href="javascript:nogetMore();" class="myMore"></a>
        </c:if>
    	<div class="myMoreP"></div>
    	<c:if test="${bagId!=null}">
			<c:if test="${qufen==1}">
	            <a href="${ctx }/oauth/index?bagId=${bagIds}&openId=${openId}&panduan=1" class=" btn_jx"></a>
	        </c:if>
	        <c:if test="${qufen!=1}">
	            <a href="${ctx }/oauth/index?bagId=${bagIds}&openId=${openId}" class=" btn_jx"></a>
	        </c:if>
		</c:if>
		<c:if test="${bagId==null}">
			<c:if test="${url==null}">
				<!-- 
				<a href="${ctx }/bag/allcity?openId=${openId}" class=" btn_jx"></a>
				 -->
			</c:if>
			<c:if test="${url!=null}">
				<a href="${url}" class=" btn_jx"></a>
			</c:if>
		</c:if>
    </div>
<!--     <div class="page di" style=" height:1150px; overflow:hidden"> -->
<!--     	<div class="d t250">  </div> -->
<!--     </div> -->
</div>
<script type="text/javascript">
	var bagpageNum=1;
	function getMore(){
		var list = $("#datalist");	
		$.get("${ctx}/bag/findBagNextPage?openId=${openId}&bagIds=${bagIds}&qufen=1&pageNum="+bagpageNum, function(data){
			if(data == '1'){
				alert("全部显示完了！");
				return false;
			}
			if(data != null && data != ''){
				bagpageNum++;
				list.append(data);
				iniPage();
			}
		});
		//$(".di .d").fadeOut()
		$(".myReceive .m").slideDown()
		setTimeout(function(){
			xy=$(".myMoreP").offset()
			
			$(".di .d").animate({"top":xy.top+200})
		},100)
	}
	function nogetMore(){
		var list = $("#datalist");	
		$.get("${ctx}/bag/findBagNextPage?openId=${openId}&bagIds=${bagIds}&qufen=0&pageNum="+bagpageNum, function(data){
			if(data == '1'){
				alert("全部显示完了！");
				return false;
			}
			if(data != null && data != ''){
				bagpageNum++;
				list.append(data);
				iniPage();
			}
		});
		//$(".di .d").fadeOut()
		$(".myReceive .m").slideDown()
		setTimeout(function(){
			xy=$(".myMoreP").offset()
			
			$(".di .d").animate({"top":xy.top+200})
		},100)
	}
	
	function toqiangbag()
	{
		$.post("${ctx}/count/addhd",function(){});
		window.location.href='${ctx}/bag/tzjxfx?openId=${openId }';
	}
	
	function todiybag()
	{
		$.post("${ctx}/count/addhd",function(){});
		window.location.href='${ctx}/diyBag/dbIndex?openId=${openId }';
	}
</script>
</body>
</html>
