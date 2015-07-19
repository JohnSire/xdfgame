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
<title>数据统计</title>
</head>
<body>

<h1>
数据统计
</h1>
所有页面点击次数:<%=request.getAttribute("hdNum") %> <br/>
所有页面分享次数:<%=request.getAttribute("shareNum") %> <br/>
活动参与人数：<%=request.getAttribute("userNum") %> <br/>
活动参与人次：<%=request.getAttribute("allNum") %> <br/>
<div style="border: 3px solid #000">
<h1>发红包统计</h1>
<h2>新东方梦想叉叉包：</h2>
	<table  border="1" width="100%" cellpadding="0" cellspacing="0">
		<thead><th>城市</th><th>数量</th></thead>
		<%List<Object[]> selfsend = (List<Object[]>)request.getAttribute("selfsend"); 
			for(Object[] obj : selfsend)
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
	
	<h2>DIY红包：</h2>
	<table  border="1" width="100%" cellpadding="0" cellspacing="0">
		<thead><th>城市</th><th>数量</th></thead>
		<%List<Object[]> teachersend = (List<Object[]>)request.getAttribute("teachersend"); 
			for(Object[] obj : teachersend)
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
	
	<h2>超级红包：</h2>
	<table  border="1" width="100%" cellpadding="0" cellspacing="0">
		<thead><th>城市</th><th>数量</th></thead>
		<%List<Object[]> commobsend = (List<Object[]>)request.getAttribute("commobsend"); 
			for(Object[] obj : commobsend)
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
<div style="border: 3px solid #000; margin-top: 10px;">
<h1>领红包统计</h1>
<h2>新东方梦想叉叉包：</h2>
<table  border="1" width="100%" cellpadding="0" cellspacing="0">
		<thead><th>城市</th><th>数量</th></thead>
		<%List<Object[]> selfrec = (List<Object[]>)request.getAttribute("selfrec"); 
			for(Object[] obj : selfrec)
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
	
	<h2>DIY红包：</h2>
	<table  border="1" width="100%" cellpadding="0" cellspacing="0">
		<thead><th>城市</th><th>数量</th></thead>
		<%List<Object[]> teacherrec = (List<Object[]>)request.getAttribute("teacherrec"); 
			for(Object[] obj : teacherrec)
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
	
	<h2>超级红包：</h2>
	<table  border="1" width="100%" cellpadding="0" cellspacing="0">
		<thead><th>城市</th><th>数量</th></thead>
		<%List<Object[]> commobrec = (List<Object[]>)request.getAttribute("commobrec"); 
			for(Object[] obj : commobrec)
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