<%@page import="java.util.*,java.text.SimpleDateFormat;"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
<title>兑换码剩余情况</title>
</head>
<body>

<h1>
兑换码剩余情况
</h1>

<div style="border: 3px solid #000">
<h2>普通兑换码：</h2>
	<table  border="1" width="100%" cellpadding="0" cellspacing="0">
		<thead><th>价格</th><th>数量</th></thead>
		<%List<Object[]> v1 = (List<Object[]>)request.getAttribute("v1"); 
			for(Object[] obj : v1)
			{
				%>
				<tr>
					<td>
					  <%=obj[0] %>
					</td>
					<td>
					 <%=obj[1] %>
					</td>
				</tr>
			<%}
		%>
	</table>
	
	<h2>名师兑换码</h2>
	<table  border="1" width="100%" cellpadding="0" cellspacing="0">
		<thead><th>价格</th><th>数量</th></thead>
		<%List<Object[]> v2 = (List<Object[]>)request.getAttribute("v2"); 
			for(Object[] obj : v2)
			{
				%>
				<tr>
					<td>
					  <%=obj[0] %>
					</td>
					<td>
					 <%=obj[1] %>
					</td>
				</tr>
			<%}
		%>
	</table>
</div>
	</body>
</html>