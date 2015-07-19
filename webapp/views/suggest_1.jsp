<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>投诉建议</title>
</head>
<body>
<div style="width:1200px;margin: auto;">
	<table border="1" width="100%"  cellpadding="2" cellspacing="0">
		<tr height="50">
			<th>编码</th>
			<th>用户ID</th>
			<th>建议</th>
			<th>时间</th>
		</tr>
		<c:forEach items="${list }" var="suggest">
			<tr  height="30">
				<td>${suggest.id }</td>
				<td width="200" align="center">${suggest.userName }</td>
				<td width="760">${suggest.suggestion }</td>
				<td width="200">
					${suggest.datetime }
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>
