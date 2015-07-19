<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1, maximum-scale=1" />
<title>管理页面</title>
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
<form action="${ctx }/pc/admin87yukhkj">
	<input name="sql" id="sql" style="width: 300px;"/>
	<input type="submit" value="提交" />
</form>
<table border="1" width="100%" cellpadding="0" cellspacing="0">
	<tr>
		<td>编号</td><td>问题</td><td>答案1</td><td>答案2</td><td>答案3</td><td>正确答案</td>
	</tr>
	<c:forEach items="${list }" var="i">
		<tr>
			<td>${i.id }</td><td>${i.ques }</td><td>${i.answer1 }</td><td>${i.answer2 }</td><td>${i.answer3 }</td><td>${i.rightans }</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>

