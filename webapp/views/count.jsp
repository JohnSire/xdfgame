<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1" />
<title>统计页</title>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<style type="text/css"> 
body { color:"#ffffff"; 
font-family: "宋体"; 
font-size: 14px; 
margin-top : 4; 
} 
.t { 
border: #000000; border-style: solid; border-width: 1px 
} 
td { 
font-family: "Tahoma", "MS Shell Dlg"; font-size: 15px 
} 
textarea {border: 1 solid #000000"} 
</style>
</head>

<body>
总用户数：${countNum }人<br/>
<table border="1" width="100%" cellpadding="0" cellspacing="0">
	<tr>
		<td>用户名</td><td>成绩</td><td>进球数</td><td>参与次数</td><td>邮箱</td><td>状态</td>
	</tr>
	<c:forEach items="${list }" var="i">
		<tr>
			<td>${i.nameV }</td><td>${i.creditN }</td><td>${i.boatnum }</td><td>${i.totalNum }</td><td style="color: orange;">${i.emailV }</td><td>${i.statusV }</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>

