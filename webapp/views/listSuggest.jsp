<%@page import="java.util.*,java.lang.*,com.huass.weixin.entity.Suggest,java.text.SimpleDateFormat;"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<title>用户建议</title>
<script type="text/javascript">

</script>
</head>
<body>
	<h1>用户建议</h1>
	<table  border="1" width="100%" cellpadding="0" cellspacing="0">
		<thead>
			<th>
				昵称
			</th>
			<th>
				建议内容
			</th>
			<th>
				建议时间
			</th>
		</thead>
		 <c:forEach items="${list }" var = "s">
			<tr>
				<td>
					${s.userName }
				</td>
				<td>
					${s.contentV}
				</td>
				<td>
					${s.dateStrD }
				</td>
				</tr>
		  </c:forEach>
	</table>
</body>
</html>