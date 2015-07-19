<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>抢梦想叉叉包</title>
<link rel="stylesheet" type="text/css" href="${ctx}/views/style/style.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<script src="${ctx}/views/js/jquery-1.7.2.min.js"></script>
<script src="${ctx}/views/js/jquery.cookie.js"></script>
<script src="${ctx}/views/js/iniH.js"></script>
<%@include file="/views/include/share.jsp" %>
</head>
<body>
<form action="${ctx }/bag/lingqu" method="post" id="shareform">
    	<input type="hidden" name="openId" value="${openId }">
   		<input type="hidden" name="bagId" value="${bagId }">
   		<input type="hidden" name="voucherId" value="${voucherId }">
   		<input type="hidden" name="money" value="${money }">
    </form>

<div class="load"><div class="c"><img src="${ctx }/views/images/loading.gif" width="124" height="124"><br/>页面加载中...</div></div>
<div class="box">
  
    <div class="page "  style="display:none">
        <div class="mBox" style="display:none">
        	<div class="mBoxC">
            	<div class="msgShare"><img src="${ctx }/views/images/share.gif" width="595" height="261"></div>
            </div>
        	<div class="mBoxD"></div>
        </div>
         <div class="yBox3">
        	<div class="yun" style="display:none">
            	 <P class="obtn mt20"><a href="javascript:shareform.submit();" class="btn2 w320">马上领取</a></P>
           	</div>	 
           
        </div>
        <div class="cBox">
            <div class="tBox">
            	<div class="t1 t11 right_in">
                    <div class="t1_0"><img src="${ctx }/views/images/t0_1.gif" width="227" height="96"></div>
                    <div class="fjBox l170"><div class="fj"></div></div>
                </div>
            </div>
            <div class="tBox">
            	<div class="t2" style="left:720px;">
                	 <div class="num">恭喜您获得了<span>${money }元</span></div>
                   <!-- <div><img src="${ctx }/views/images/t3_0.gif" width="580" height="125"></div>-->
                    <div><img src="${ctx }/views/images/t2.gif" width="428" height="29"></div>
                </div>
            </div>
            <div class="ts ts5">
                <p class="tsT1">红包由新东方免费提供，</p>
                <p class="tsT2">金额随机，<span>惊喜多多哒！</span></p>
            </div>
            <div class="ts ts4" style="display:none">
                <p><textarea cols="12" rows="2" >还能一起愉快的玩耍吗？梦想分红，我们先拼拼手气吧!</textarea></p>
            </div>
            <div class="qbZhe qbZheB"><img src="${ctx }/views/images/i42.png" width="390" height="342"></div>
            <div class="qb sqb3 ">
                  <div class="qbT1">拖动小剪刀拆开哦~</div>
                  <div class="jd"><img src="${ctx }/views/images/jd.png" height="46"  mL=0></div>
				  <div class="qbJb"><img src="${ctx }/views/images/i13.gif" width="323" ></div>
                  <div class="face sface2"><img src="${img }" width="200" height="200"></div>
                  <div class="qbT4 mt40">
                        <p><span> <font size=5>新东方梦想叉叉红包~</font></span></p>
                  </div>
                  <div class="qbT" style="display:none;"><img src="${ctx }/views/images/i3.png" width="464" height="318"></div>
                  <div class="qbD"><img src="${ctx }/views/images/i12.gif" width="466" height="394"></div>
            </div>
        </div>
        <div class="dBox">
            <div class="diyD"></div>
        </div>
        <div class="logo2"></div>
    </div>
</div>
<script src="${ctx }/views/js/qbAction.js"></script>
<script>
function jDend(){
	box.off('mousedown touchstart');
	box.off('mousemove touchmove');
	box.off('mouseup touchend mouseout');
	$(".qbT1").fadeOut();
	$(".qbT2").fadeOut();
	$(".qbT3").addClass("item_show")
	$(".t2").css("left",0).addClass("item_rightIn")
	$(".yun").show()
	$(".qbZheB").show().addClass("item_show");
	$(".jd").fadeOut();
	$(".qbT").show()
	$(".qbJb").show()
	$(".ts4").show()
	$(".face").fadeOut()
	$(".t1").animate({"left":-832},600)
	$(".ts5").animate({"left":-832},600)
	$(".yBox").addClass("yun_up")
	$(".yun .btn").show();
}
</script>
</body>
</html>