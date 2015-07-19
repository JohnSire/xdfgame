<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/views/include/taglib.jsp"%>
<html>
<head>
<%@include file="/views/include/head.jsp" %>
<title>数据统计</title>
</head>
<body>
<div class="container">
	<div class="hero-unit">
	  <h2>数据统计-统计领取人</h2>
	  <p>参与有效人数（空数据排除掉）： ${userAmount}</p>
	    <p><a href="${ctx}/a/tongji/tongji" class="btn btn-primary">统计主页</a>
	   	<a href="${ctx}/a/tongji/tjuser" class="btn btn-primary">统计领取人</a>
	  	<a href="${ctx}/a/tongji/tjbag" class="btn btn-primary">统计领取红包人次</a>
	 	<a href="${ctx}/a/tongji/bag" class="btn btn-primary">总红包</a>
	 	<a href="${ctx}/a/tongji/schoolBag" class="btn btn-primary">学校红包</a>
	  </p>
	</div>
	<form:form id="searchForm" modelAttribute="user" action="${ctx}/a/tongji/tjuser" method="POST" 
	class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="orderBy" name="orderBy" type="hidden" value="${page.orderBy}"/>
		<div>
			<label>用户名：</label>
			    <form:input path="nameV" htmlEscape="false" class="input-medium search-query"/>
			<label>用户ID：</label>
			    <form:input path="id" htmlEscape="false" class="input-medium search-query "/>
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
		</div>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
		<th>用户名</th>
		<th class="sort shareNum">分享次数</th>
		<th>分享时间</th>
		<th>用户ID</th>
		<th>OPENID</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="entity">
			<tr>
				<td><img  width=25 height=25 src="${entity.headImage}">&nbsp;&nbsp;${entity.nameV}</td>
				<td>${entity.shareNum}</td>
				<td>${entity.shareDate}</td>
				<td>${entity.id}</td>
				<td>${entity.openId}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>