<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>红包使用</title>
<link rel="stylesheet" type="text/css" href="${ctx}/views/style/style.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<script src="${ctx}/views/js/jquery-1.7.2.min.js"></script>
<script src="${ctx}/views/js/jquery.cookie.js"></script>
<script src="${ctx}/views/js/iniH.js"></script>
<%@include file="/views/include/share.jsp" %>
<script type="text/javascript">
$(document).ready(function(e) {
	$.post("${ctx}/count/addhd",function(){});
});
</script>

</head>
<body> 
<div class="box">

    <div class="page pageBg2">

       
        <div class="cBox2">
           <div class="t">
           		<span class="i1"></span>
             	<span class="num1">${money }元！</span>
             	<c:if test="${bagType ==0 }">
             		<span class="num2">来自新东方的梦想叉叉包</span>
             	</c:if>
             	<c:if test="${bagType ==1 }">
             		<span class="num2">来自${oName }的${bagName }红包</span>
             	</c:if>
                <c:if test="${bagType ==2 }">
             		<span class="num2">来自${oName }的${bagName }红包</span>
             	</c:if>
             	<c:if test="${bagType ==3 }">
             		<span class="num2">来自9.10教师节限量版梦想叉叉包</span>
             	</c:if>
          </div>
           <div class="dh">
           		<div class="dh_t1">兑换码：<p>${code }</p></div>
                <div class="dh_t2">有效时间截止至2014年10月31日24时</div>
               
           </div>
           <div class="intro">
<h2>温馨提示：</h2>
                <ul>
                <li>
                	<input type="text" value="${bagIds }" />
                	<c:if test="${qufen==1}">
                		<a href="${ctx }/oauth/index?bagId=${bagId}&openId=${openId}&panduan=1">继续领红包</a>
                	</c:if>
                	<c:if test="${qufen!=1}">
                		<a href="${ctx }/oauth/index?bagId=${bagIds}&openId=${openId}">继续领红包</a>
                	</c:if>
                </li>
                </ul>
           </div>
           
           <c:if test="${bagType ==0 or bagType==3}">
           		<div class="t7"><img src="${ctx }/views/images/t7.gif" width="420" ></div>
           </c:if>
          
          <c:if test="${bagType ==1 or bagType==2 }">
	          <div class="userMsg">
	            <div class="uqb uqb2">
	             	 <div class="maozi"></div>
	             	 <div class="face "><img src="${headImg }" width="200" height="200"></div>
	                 <div class="ts">
	                 	 <p>${msgV}</p>
	                 </div>
	            </div>
	          </div>
          </c:if>
		  
		  <c:if test="${bagType ==0 or bagType == 3 }">
		  	<div class="yun mtf150">
		  </c:if>
		  <c:if test="${bagType ==1 or bagType==2 }">
		  	<div class="yun mtf220">
		  </c:if>
            
                 <p class="ac">
                 	<c:if test="${bagType ==0 or bagType ==3}">
                 		<a href="javascript:toqiangbag();" class="btn w320">我还要抢</a>
                 	</c:if>
                     <c:if test="${bagType ==1 or bagType==2 }">
                     	<a href="javascript:toqiangbag2();" class="btn w320">我还要抢</a>
                     </c:if>
                 </p>
   		   		 <div class="yunD"><img src="${ctx }/views/images/d.gif" width="832" height="214"><div class="h500"></div></div>
            </div>
       </div>
       
  </div>
</div>
<script type="text/javascript">
	function toqiangbag(){
		$.post("${ctx}/count/addhd",function(){});
		window.location.href='${ctx}/bag/tzjxfx?openId=${openId }';
	}
	
	function toqiangbag2(){
		$.post("${ctx}/count/addhd",function(){});
		window.location.href='${ctx}/diyBag/diyIndexAgain/${openId }';
	}
</script>
</body>
</html>