<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/views/include/taglib.jsp"%>
<!DOCTYPE>
<html>
<head>
<%@include file="/views/include/head.jsp" %>
<title>数据统计</title>
</head>
<body>
<div class="container">
	<div class="hero-unit">
	  <h2>数据统计-红包</h2>
	<p><a href="${ctx}/a/tongji/tongji" class="btn btn-primary">统计主页</a>
	   	<a href="${ctx}/a/tongji/tjuser" class="btn btn-primary">统计领取人</a>
	  	<a href="${ctx}/a/tongji/tjbag" class="btn btn-primary">统计领取红包人次</a>
	 	<a href="${ctx}/a/tongji/bag" class="btn btn-primary">总红包</a>
	 	<a href="${ctx}/a/tongji/schoolBag" class="btn btn-primary">学校红包</a>
	  </p>
	</div>
	<form:form id="searchForm" modelAttribute="schoolBag"  action="${ctx}/a/tongji/bag" method="POST" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="orderBy" name="orderBy" type="hidden" value="${page.orderBy}"/>
		<div>
			<label>用户ID：</label>
			    <form:input path="userId" htmlEscape="false" class="input-medium"/>
			&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
		</div>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
		<th>ID</th>
		<th>用户ID</th>
		<th>内容</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="entity">
			<tr>
				<td>${entity.id}</td>
				<td>${entity.userId}</td>
				<td>${entity.msgV}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>