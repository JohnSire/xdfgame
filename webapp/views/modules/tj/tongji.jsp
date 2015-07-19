<%@page import="java.util.*,java.lang.*,com.huass.weixin.entity.Suggest,java.text.SimpleDateFormat;"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/views/include/taglib.jsp"%>
<!DOCTYPE>
<html>
<head>
<title>数据统计</title>
<script type="text/javascript">
	var test = '${loginuser}';
	if (test == null || test == ''){
		location.href="${ctx}/views/login.jsp";
	}
</script>
</head>
<body>
	<h1>数据统计 <a href="${ctx}/a/tongji/bag" class="btn btn-primary">详情</a></h1>
	<table  border="1" width="100%" cellpadding="0" cellspacing="0">
		<thead>
			<th>
				学校名称
			</th>
			<th>
				发出红包数量
			</th>
			<th>
				红包领取数量
			</th>
			<th>
				分享次数
			</th>
		</thead>
		<c:forEach items="${list}" var="t" >
			<tr>
				<th>${t.xuexiao }</th>
				<th>${t.sum }</th>
				<th>${t.lingqu }</th>
				<th></th>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<br/>
	<h1>学校红包分享数</h1>
	<table  border="1" width="100%" cellpadding="0" cellspacing="0">
		<thead>
			<th>
				学校名称
			</th>
			<th>
				分享次数
			</th>
		</thead>
		<%List<Object[]> shareList = (List<Object[]>)request.getAttribute("schoolShare"); 
			for(Object[] objArr : shareList)
			{
				%>
				<tr>
					<td><%=objArr[0] %></td>
					<td><%=objArr[1] == null ? "0" : objArr[1]%></td>
				</tr>
			<%}
		%>
	</table>
	
	<br/>
	<br/>
	
	<div>
		<div>活动总参与人数：${renshu }</div>
		<div>活动总参与人次：${renci }</div>
		<div>页面点击次数：${dianji }</div>
		<div>页面分享次数：${fenxiang }</div>
		
	</div>
	
	
</body>
</html>