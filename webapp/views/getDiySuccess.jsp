<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>获得红包金额</title>
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
        <div class="t10">
	        <span>
	        	<c:if test="${not empty money}">
			  	 	${money }
			  	</c:if>
			  	<c:if test="${empty money }">
			  		0
			  	</c:if>
	        </span>
        </div>
    	<div class="p1"></div>
        <a href="${ctx }/oauth/index?openId=${openId }&bagId=${dbagId}" class="btn btn_mo l200"></a>
		<div class="hbList">
        	<div class="my">
            	<div class="face"><img src="${headImg }" width="75" height="75"></div>
                <span>
                	<c:if test="${not empty money}">
						${money }
					</c:if>
					<c:if test="${empty money }">
						0
					</c:if>
                </span>
            </div>
            <c:forEach items="${list }" var = "i">
	            <div class="friend">
		            	<div class="face"><img src="${i.himg }" width="75" height="75"></div>
		                <div class="l">${i.nameV }<br/>收到红包，一起棒棒哒~</div>
		                <div class="r">${i.moneys }元</div>
	            </div>
            </c:forEach>
        </div>
    </div>
    <div class="page di">
    	<div class="d "></div>
    </div>
</div>
<script src="${ctx}/views/xdfjs/shareAction.js"></script>
 <script type="text/javascript">
 		$.post("${ctx}/count/addhd",function(){});
</script>
</body>
</html>
