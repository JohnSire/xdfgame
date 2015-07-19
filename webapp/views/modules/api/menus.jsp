<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/views/include/taglib.jsp"%>
<!DOCTYPE>
<html>
<head>
<%@include file="/views/include/head.jsp" %>
<title>菜单</title>
</head>
<body>
<div class="navbar  navbar-inverse">
  <div class="navbar-inner">
    <a class="brand" href="#">微信公共平台API</a>
    <ul class="nav">
      <li><a href="#">首页</a></li>
      <li class="active"><a href="${ctx}/a/api/menu/index">菜单</a></li>
      <li><a href="${ctx}/a/api/msg/index">消息</a></li>
    </ul>
  </div>
</div>

<div class="container">
	<div class="hero-unit">
	  <h2>微信公共平台-菜单</h2>
	   <p>获取accessToken、当前菜单；自定义菜单的创建</p>
	</div>
	<c:if test="${not empty message}">
	<div id="messageBox" class="alert alert-info">
	     <button data-dismiss="alert" class="close">×</button>${message}</div> 
	</c:if>
	<fieldset>
		<legend>当前菜单</legend>
		<p><small>${menus}</small></p>
	</fieldset>
	<fieldset>
		<legend>创建菜单</legend>
	<form action="${ctx}/a/api/menu/create" method="POST" class="form-horizontal">
	  <div class="control-group">
	    <label class="control-label" for="">一、接口类型：</label>
	    <div class="controls">
	      <input type="text" placeholder="自定义菜单">
	    </div>
	  </div>
	  <div class="control-group">
	    <label class="control-label" for="">二、接口列表：</label>
	    <div class="controls">
	      <input type="text" placeholder="自定义菜单创建接口">
	    </div>
	  </div>
	  <div class="control-group">
	    <label class="control-label" for="">  三、参数列表</label>
	     <div class="controls"></div>
	  </div>
	    <div class="control-group">
	    <label class="control-label" for="">* access_token：</label>
	    <div class="controls">
	      <input type="text" name="access_token" value="${access_token}">
	    </div>
	  </div>
	    <div class="control-group">
	    <label class="control-label" for="">* BODY：</label>
	    <div class="controls">
	     	<textarea name="body" rows="6" cols="6"></textarea>
	    </div>
	  </div>
	  <div class="control-group">
	    <div class="controls">
	      <button type="submit" class="btn  btn-primary">提交</button>
	    </div>
	  </div>
	</form>
	</fieldset>
</div>
</body>
</html>