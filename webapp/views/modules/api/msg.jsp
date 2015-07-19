<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/views/include/taglib.jsp"%>
<!DOCTYPE>
<html>
<head>
<%@include file="/views/include/head.jsp" %>
<title>消息</title>
</head>
<body>
<div class="navbar  navbar-inverse">
  <div class="navbar-inner">
    <a class="brand" href="#">微信公共平台API</a>
    <ul class="nav">
      <li><a href="#">首页</a></li>
      <li><a href="${ctx}/a/api/menu/index">菜单</a></li>
      <li  class="active"><a href="${ctx}/a/api/msg/index">消息</a></li>
    </ul>
  </div>
</div>

<div class="container">
	<div class="hero-unit">
	   <h2>微信公共平台-消息</h2>
	   <p>用户分组；发送消息</p>
	</div>
	<c:if test="${not empty message}">
		<div id="messageBox" class="alert alert-info">
	     <button data-dismiss="alert" class="close">×</button>${message}</div> 
	</c:if>
	<fieldset>
		<legend>当前分组</legend>
		<table class="table table-striped table-bordered table-condensed">
		<thead><tr>
			<th>ID</th>
			<th>分组名称</th>
			<th>该分组人数</th>
			<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${groups}" var="entity">
			<tr>
				<td>${entity.id}</td>
				<td>${entity.name}</td>
				<td>${entity.count}</td>
				<td>
				<a href="javascript:void(0)"  onclick="sendMessage('${entity.id}','${entity.name}')" data-toggle="modal">发消息</a>
				<a href="#">查看用户</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</fieldset>
	<!--  -->
	<fieldset>
		<legend>关注用户</legend>
		<table class="table table-striped table-bordered table-condensed">
		<thead><tr>
			<th>用户名</th>
			<th>性别</th>
			<th>城市</th>
			<th>省份</th>
			<th>国家</th>
			<th>语言</th>
			<th>OPENID</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${users}" var="entity">
			<tr>
				<td><img  width=25 height=25 src="${entity.headImg}">&nbsp;&nbsp;${entity.name}</td>
				<td>${entity.sex}</td>
				<td>${entity.city}</td>
				<td>${entity.province}</td>
				<td>${entity.country}</td>
				<td>${entity.language}</td>
				<td>${entity.openId}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</fieldset>
</div>
<!-- 发送消息 -->
<div id="sendMessageModel" class="modal hide fade" tabindex="-1" 
        role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">向【<span id="groupName"></span>】发送文本消息</h3>
  </div>
  <div class="modal-body">
    <textarea id="sendContent" rows="3" cols="3"></textarea>
  </div>
  <div class="modal-footer">
    <input type="hidden" id="groupId" value="">
    <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
    <button class="btn btn-primary" onclick="send()">发送</button>
  </div>
</div>
<script type="text/javascript">
function sendMessage(id,name){
	 $('#sendMessageModel').modal('show').on('shown',function(){
		 $("#groupName").html(name);
		 $("#groupId").val(id);
     }).on('hidden',function(){
    	 $('#sendMessageModel .modal-body').html("<textarea id='sendContent' rows='3' cols='3'></textarea>");
		 $("#groupId").val('');
     })
}
function send(){
	var sendContent = $("#sendContent").val();
	var groupId = $("#groupId").val();
	$('#sendMessageModel .modal-body').html("发送中...");
	$.ajax({
	     type: 'POST',
	     dataType: 'json',
	     url: "${ctx}/a/api/msg/send" ,
	     data:{sendContent:sendContent,
	    	 groupId:groupId},
	     success:function(data){
	    	 $('#sendMessageModel .modal-body').html("发送成功！");
	    	 setTimeout(function(){
	    		 $('#sendMessageModel').modal('hide');
	    	 },1000);
	     },
	     error : function() {    
	    	 $('#sendMessageModel .modal-body').html("sorry!! 发送失败...");  
	     } 
	     
	});
}
</script>
</body>
</html>