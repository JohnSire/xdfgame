<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>新东方红包</title>
<link rel="stylesheet" type="text/css" href="${ctx}/views/style/style.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<script src="${ctx}/views/js/jquery-1.7.2.min.js"></script>
<script src="${ctx}/views/js/jquery.cookie.js"></script>
<script src="${ctx}/views/js/iniH.js"></script>
<%@include file="/views/include/share.jsp" %>
</head>
<body> 
<div class="box">
	
	 <form action="${ctx }/diyBag/getDiyBag" method="post" id="shareform">
    	<input type="hidden" name="openId" value="${openId }">
   		<input type="hidden" name="bagId" value="${bagId }">
   		<input type="hidden" name="voucherId" value="${voucherId }">
   		<input type="hidden" name="money" value="${money }">
    </form>

    <div class="page">
        <div class="mBox" style="display:none">
        	<div class="mBoxC">
            	<div class="msgShare"><img src="${ctx }/views/images/share.gif" width="595" height="261"></div>
            </div>
        	<div class="mBoxD"></div>
        </div>
        <div class="yBox3">
        	<div class="yun">
            	 <P class="obtn"><a href="javascript:shareform.submit();" class="btn2 w320">马上领取</a></P>
           	</div>	 
           
        </div>
        <div class="cBox">
            <div class="tBox">
            	<div class="t6">
                   
                    <div class="num">恭喜您获得了<span>${money }元</span></div>
                    <div class="t1_2">收到来自${masterName}的${bagName}红包！</div>
                    <div class="t1_1">抢到靠人品，金额凭运气~</div>
                    <div class="fjBox l190"><div class="fj"></div></div>
                </div>
            </div>
            <div class="tBox">
            	<div class="t2">
                    <div><img src="${ctx }/views/images/t3_0.gif" width="580" height="125"></div>
                    <div><img src="${ctx }/views/images/t2.gif" width="428" height="29"></div>
                </div>
            </div>
            <div class="ts ts2">
                <p><textarea cols="12" rows="2" readonly="readonly">${msgV }</textarea></p>
            </div>
            <div class="qbZhe"><img src="${ctx }/views/images/i4.png" width="390" height="342"></div>
            <div class="qb sqb">
             
             	  <div class="face "><img src="${headImg }" width="200" height="200"></div>
                  <div class="qbT4">
                        <p><input type="text" value="${bagName}"><span>红包~</span></p>
                  </div>
                  <div class="qbT"><img src="${ctx }/views/images/i3.png" width="464" height="318"></div>
                  <div class="qbD"><img src="${ctx }/views/images/${bgType}" width="466" height="394"></div>
            </div>
        </div>
        <div class="dBox">
           	<div class="diyD"></div>
        </div>
    </div>
</div>

</body>
</html>