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
    
    <title>红包领取成功</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/views/style/style.css">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<script src="${ctx}/views/js/jquery-1.7.2.min.js"></script>
	<script src="${ctx}/views/js/jquery.cookie.js"></script>
	<script src="${ctx}/views/js/WeixinApi.js"></script>
	<script src="${ctx}/views/js/iniH.js"></script>
	<script src="${ctx}/views/js/jquery.Jcrop.min.js"></script>
	<%@include file="/views/include/share.jsp" %>
	<script type="text/javascript">
		imgUrl = '${TEACHER_FENXIANG}';
		shareTitle = '${TEACHER_FENXIANG_SHARETITLE}';
	</script>
</head>
<body>
<div class="mBox" style="display:none">
        	<div class="mBoxC">
                <div class="msgTobig yes"><img src="${ctx}/views/images/share.gif" height="261"></div>
                <div class="msgTobig no" style="display:none">
                	<img src="${ctx}/views/images/tday/msg_2.gif" width="626" height="250">
                </div>
            </div>
        	<div class="mBoxD"></div>
        </div>
        <input type="text" value=${bagId } />
<center>成功领取${money }元！<br><br><br>
<a href="javascript:goStep(2);">发红包</a>
<input type="text" value="${dbagId }" />
<input type="text" value="${bagId }" />
 <script src="${ctx}/views/js/shareAction.js"></script>
 <script type="text/javascript">
		wxData["link"] = "${XDF_KEJIANCAO}".replace("(bagId)", "${bagId}").replace("(dbagId)", "${dbagId}");
		wxData["desc"] = "${TEACHER_FENXIANG_DESCCONTENT_ER1}".replace("(money)", "${money}").replace("(冠名)", "${hengimage}");
		shareEndUrl = "${ctx}/bag/xiaofenxiangsuccess?xbagId=${bagId}&dbagId=${dbagId}&openId=${openId}";
	</script>
	<script>
		function goStep(id){
			if(id==2){
				$(".mBox .yes").show();
				$(".mBox .no").hide();
				$(".mBox").show();
			}
		}
	</script>
</body>
</html>