<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>打开方式不对1</title>
<link rel="stylesheet" type="text/css" href="${ctx }/views/css/main.css">
<script src="${ctx }/views/xdfjs/jquery.1.7.2.min.js"></script>
<script src="${ctx }/views/xdfjs/WeixinApi.js"></script>

<script src="${ctx }/views/xdfjs/common.js"></script>
<script src="${ctx }/views/xdfjs/iniH.js"></script>

</head>
<body>
<div class="box">
	<div class="page msg">
    	<div class="c">
        	<div class="share"><img src="${ctx }/views/images/share.gif" ></div>
            <div class="noCity"><img src="${ctx }/views/images/noCity.gif" ></div>
            <div class="noMore"><img src="${ctx }/views/images/noMore.gif" ></div>
        </div>
    	<div class="d"></div>
    </div>
	<div class="page main">
    	<a href="javascript:;" class="logo"></a>

       
  </div>
    <div class="page di"><img src="${ctx }/views/images/p4.gif" width="640">
    </div>
</div>

</body>
</html>
