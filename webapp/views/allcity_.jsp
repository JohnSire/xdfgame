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
    <title>选择城市</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
    <link rel="stylesheet" type="text/css" href="${ctx}/views/css/main.css">
    <script type="text/javascript" src="${ctx}/views/xdfjs/jquery.1.7.2.min.js"></script>
	<script src="${ctx}/views/xdfjs/common.js"></script>
	<script src="${ctx}/views/xdfjs/iniH.js"></script>
    
    <%@include file="/views/include/share.jsp" %>
  </head>
  
<body>
  <div class="box">
	<div class="page msg">
    	<div class="c">
    		<div class="share"><img src="images/share2.gif" ></div>
    	</div>
    	<div class="d"></div>
    </div>
	<div class="page main">
    	<a href="javascript:;" class="logo"></a>
        <div class="t t13"></div>
        <div class="cityList">
        	<ul>
            	<c:if test="${not empty list }">
	            	<c:forEach items="${list }" var="i">
            		<li>
		    			<a href="${i.urlN }">${i.cityV }</a><br><br>
					</li>
		    		</c:forEach>
				</c:if>
            </ul>
        </div>
    </div>
    <div class="page di"></div>
  </div>
	<script type="text/javascript">
		wxData["link"] = "${TEACHER_FENXIANG_URL_PK}".replace("(bagId)", "${bagId}");
		wxData["desc"] = "${TEACHER_FENXIANG_DESCCONTENT_ER}".replace("(冠名)", "${title}");
	</script>
</body>
</html>
