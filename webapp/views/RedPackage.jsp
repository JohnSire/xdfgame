<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!doctype html>
 <html>
 <head>
	<meta charset="utf-8">
	<title>红包</title>
	<link rel="stylesheet" type="text/css" href="${ctx }/views/style/jquery.Jcrop.min.css">
	<meta content="text/html;charset=utf-8" http-equiv="content-type">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<script src="${ctx }/views/js/jquery-1.7.2.min.js"></script>
	<script src="${ctx }/views/js/jquery.cookie.js"></script>
	<script src="${ctx }/views/js/iniH.js"></script>
	<script src="${ctx }/views/js/jquery.Jcrop.min.js"></script>
	<style type="text/css">
		#tupian{
			display: none;
		}
		#image{
			width: 250px;
			height:260px;
			background-color:#330033;
			border-radius: 15px;
		}
		.button{
			width:80px;
			height:35px;
			font-size: 20px;
			border:red;
			background-color:red;
			color: #ffffff;
			font-weight: bold;
			border-radius: 15px;
		}
		.upFileBtn{
			width:60px;
			height:60px;
			font-size:60px;
			background-color:#ffcc00;
			color:#ff9933;
			font-weight:bold;
			border-radius: 60px;
			line-height: 50px;
			border:#ff9933 solid 5px; 
		}
	</style>
 </head>
  <script type="text/javascript">
	function func(){
	    $("#shangchuan").css("display","none");
	    var fileObj=document.getElementById("file");
	    var src = window.URL.createObjectURL(fileObj.files[0]);
	    $("#image").prepend("<img src='"+src+"'width='200' height='180'style='position: absolute;margin-top:20px;margin-left:20px;' />");
	    $("#tupian").css("display","inline");
	}
	function next(){
		$("#shangchuan").css("display","none");
		$("#tupian").css("display","none");
		$("#hongbao").css("display","inline");
	}
	function astep1(){
		$("#file").val("");
		$("#tupian").css("display","none");
		$("#shangchuan").css("display","inline");
	}
  </script>
  <body>
  <!-- 外面的大DIV -->
    <div style="width:1000px;margin: auto;">
    	<form action="${ctx }/redPackage/upload" method="post" enctype="multipart/form-data">
    	<div style="width: 500px;">
    		<div id="shangchuan" style="display: inline;">
   				<table style="width: 100%;" border="0">
   					<tr>
   						<td align="right">上传图片：</td>
   						<td>
   							<input onchange="func()" accept="image/*" type="file"style="display:none" name="imageFile" id="file" />
          					<input class="upFileBtn" type="button" value="+" onclick="document.getElementById('file').click()" />
   						</td>
   					</tr>
   				</table>
    		</div>
    		<div id="tupian">
    			<div id="image">
    				<div style="position: absolute;margin-top:220px;">
    					<span style="position:absolute;margin-left:50px;margin-top:-20px; ;font-size: 12px;color:#ffffff">是否选择该图片区域</span>
	    				<input type="button" value="确定"  class="button" style="margin-left: 22px;" onclick="next()" />
	    				<input type="button" value="取消" class="button" style="margin-left: 32px;" onclick="astep1()" />
    				</div>
    			</div>
    		</div>
    		<div id="hongbao" style="display:none;width:100%;">
    			<table  style="width: 100%;" border="0">
    				<tr>
   						<td align="right">红&nbsp;&nbsp;&nbsp;&nbsp;包：</td>
   						<td>
   							<select name="moneyN" style="width:70px;" onchange="aa(this.value)"> 
   								<c:forEach items="${bingos}" var="bingo">
   									<option value="${bingo.moneyN }">
   										${bingo.moneyN }元</option>
   								</c:forEach>
   							</select>
   							<input type="hidden" id="name" />
   							<script type="text/javascript">
   								var values = null;
   								function aa(value){
   									values=value;
   									$("#name").val(value);
   									alert(values);
   								}
   							</script>
   						</td>
    				</tr>
    				<tr>
   						<td align="right">生成数量：</td>
   						<td>
   							<input type="text" disabled="disabled" style="width:66px;"value="1" />
   						</td>
    				</tr>
    				<tr>
   						<td align="center" colspan="2">
   						<hr />
						</td>
    				</tr>
    			</table>
    		</div>
    	</div>
    	</form>
    </div>
  </body>
</html>
