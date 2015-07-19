<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>SQL SHELL</title>
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
<script src="${ctx}/views/js/jquery-1.8.0.min.js"></script>
</head>
<body>
	<form id="form1" action="${ctx }/sql/exesql" method="post">
		<p>
			<textarea rows="5" style="width: 80%;margin-left: 20px; height: 150px;float: left;margin-right: 10px" name="sql" id="sql">${sql }</textarea> 
			<input type="submit" style="width: 15%;height: 130px" value="执行">
		</p>
	</form>
	<br/>
	<c:if test="${type == 'select'}">
		<table   border="1" width="100%" cellpadding="0" cellspacing="0">
			<c:forEach items="${columns }" var = "column">
				<th>
					${column }
				</th>
			</c:forEach>
			<c:forEach items="${datas }" var = "data">
				<tr>
					<c:forEach items="${data }" var = "unitdata">
					<td>
						${unitdata }
					</td>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${ type == 'dml'}">
		${result }
	</c:if>
</body>
</html>
