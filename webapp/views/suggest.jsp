<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>留言页</title>
<link rel="stylesheet" type="text/css" href="${ctx }/views/css/main.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<script src="${ctx }/views/xdfjs/jquery.1.7.2.min.js"></script>
<script src="${ctx }/views/xdfjs/WeixinApi.js"></script>

<script src="${ctx }/views/xdfjs/common.js"></script>
<script src="${ctx }/views/xdfjs/iniW.js"></script>
<%@include file="/views/include/share.jsp" %>
</head>
<body>
<div class="box">

	<div class="page msg">
    	<div class="c">
            <div class="ok"><img src="${ctx }/views/images/ok.gif" ></div>
        </div>
    	<div class="d"></div>
    </div>
	
	<div class="page main">
    	<a href="javascript:;" class="logo"></a>
       	<div class="w9">
        	<textarea id="suggest" placeholder="我想说..." onfocus="addhd()"></textarea>
        </div>
       
        <a href="javascript:gbook();" class="btn_ly"></a>
     
    </div>

</div>
<script>
	function gbook(){
		$.post("${ctx}/count/addhd",function(){});
		var suggest = $("#suggest").val();
		if(suggest == null || $.trim(suggest) == '')
		{
			alert("请填写建议...");
			return;
		}
		$.post("${ctx}/sc/save",{"openId":"${openId}","suggest":suggest}, function(){
			showMsg(".ok");
			$("#suggest").val("我想说");
		});
	}
</script>
</body>
</html>
