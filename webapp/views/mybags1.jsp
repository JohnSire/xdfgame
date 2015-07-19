<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta charset="utf-8">
<title>我的红包</title>
<link rel="stylesheet" type="text/css" href="${ctx}/views/style/style.css">
<style type="text/css">
.list{
	width:680px;
}
.list li{
	height:50px;
}
.list li .i2{
	padding:0 10px 0 10px;
}
.list li .i3{
	width:330px;
}

.list li .i4{
	width:90px;
}
</style>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<script src="${ctx}/views/js/jquery-1.7.2.min.js"></script>
<script src="${ctx}/views/js/jquery.cookie.js"></script>
<script src="${ctx}/views/js/iniW.js"></script>
<%@include file="/views/include/share.jsp" %>
</head>
<body> 
<div class="box">

    <div class="page pageBg2">

       
        <div class="cBox2">
           <div class="t"> <img src="${ctx}/views/images/t6.gif" width="448" height="100">
           <span style="float:right;padding-right: 10px;font-size: 20px;">点击即可使用哦~  ↓↓</span>
           </div>
           <ul id="datalist" class="list">
           <c:forEach items="${list }" var = "i">
           		<c:if test="${qufen==1}">
	           		<li><a href="${ctx }/bag/bagDetail?voucherId=${i.voucherId }&bagId=${i.bagId}&openId=${openId}&qufen=1"><span class="i1"></span>
	                    <span class="i2">${i.getDate} ${i.getTime}</span><span class="i3">来自${i.userName }的${i.bagName }红包</span>
	                    <span class="i4">${i.money }元</span><span class="i5"></span></a>
	                </li>
                </c:if>
                <c:if test="${qufen!=1}">
                <li>
                	<a href="${ctx }/bag/bagDetail?voucherId=${i.voucherId }&bagId=${i.bagId}&openId=${openId}&bagIds=${bagIds}"><span class="i1"></span>
	                    <span class="i2">${i.getDate} ${i.getTime}</span><span class="i3">来自${i.userName }的${i.bagName }红包</span>
	                    <span class="i4">${i.money }元</span><span class="i5"></span></a>
	                </li>
	            </c:if>
           </c:forEach>
           </ul>
           <input type="text" value="${qufen }" />
           <c:if test="${qufen==1}">
           		<p class="pr"><a href="javascript:getMore();" class="more2">+点击查看查看更多</a></p>
           </c:if>
           
           <c:if test="${qufen!=1}">
           		<p class="pr"><a href="javascript:nogetMore();" class="more2">+点击查看查看更多</a></p>
           </c:if>
           
           <div class="yun">
            	 <p>
            	 <c:if test="${qufen==1}">
            	 	<a href="${ctx }/oauth/index?bagId=${bagIds}&openId=${openId}&panduan=1">继续领红包</a>
            	 </c:if>
            	 <c:if test="${qufen!=1}">
            	 	<a href="${ctx }/oauth/index?bagId=${bagIds}&openId=${openId}">继续领红包</a>
            	 </c:if>
                 </p>
           		 <div class="yunD"><img src="${ctx}/views/images/d.gif" width="832" height="214"></div>
                 <div class="h250"></div>
            </div>
       </div>
  </div>
</div>
<script type="text/javascript">
	var bagpageNum=1;
	function getMore(){
		var list = $("#datalist");	
		$.get("${ctx}/bag/findBagNextPage?openId=${openId}&bagIds=${bagIds}&qufen=1&pageNum="+bagpageNum, function(data){
			if(data != null && data != ''){
				bagpageNum++;
				list.append(data);
				iniPage();
			}
		});
	}
	function nogetMore(){
		var list = $("#datalist");	
		$.get("${ctx}/bag/findBagNextPage?openId=${openId}&bagIds=${bagIds}&qufen=0&pageNum="+bagpageNum, function(data){
			if(data != null && data != ''){
				bagpageNum++;
				list.append(data);
				iniPage();
			}
		});
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