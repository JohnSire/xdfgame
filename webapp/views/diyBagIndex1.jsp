<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!doctype html>
<html>
<head>
</head>

<body>
	<p>专属红包来啦！梦想叉叉包,点击可以IDY红包哟~</p>
	<form action="${ctx }/diyBag/createDiyBag" method="post" enctype="multipart/form-data">
		<!-- 
		<form action="${ctx }/diyBag/upload" method="post" enctype="multipart/form-data">
		-->
		<textarea name="msg"></textarea>
			<input type="file" id="fileElem" name="fileupload" multiple  accept="image/*" onchange="handleFiles(this)">
				<div id="fileList" style="width:200px;height:200px;">
				<img src="${headImage }">
				<input type="hidden" name="headImage" value="${headImage }">
				</div>
		<script>
			window.URL = window.URL || window.webkitURL;
			var fileElem = document.getElementById("fileElem"), fileList = document
					.getElementById("fileList");
			function handleFiles(obj) {
				
				var files = obj.files, img = new Image();
				if (window.URL) {
					//File API
					img.src = window.URL.createObjectURL(files[0]);
					img.width = 200;
					img.onload = function(e) {
						window.URL.revokeObjectURL(this.src); 
					}
					fileList.appendChild(img);
				} else if (window.FileReader) {
					var reader = new FileReader();
					reader.readAsDataURL(files[0]);
					reader.onload = function(e) {
						alert(files[0].name + "," + e.total + " bytes");
						img.src = this.result;
						img.width = 200;
						fileList.appendChild(img);
					}
				} else {
					//ie
					obj.select();
					obj.blur();
					var nfile = document.selection.createRange().text;
					document.selection.empty();
					img.src = nfile;
					img.width = 200;
					img.onload = function() {
					}
					fileList.appendChild(img);
				}
			}
		</script>
		<a id="chageBM" href="#"><img alt="点击可以替换背景哟~" src=""> </a>
		<p>
			<input type="text" value="${userName }哒红包" name="bagName" />
		</p>
		  	<input type="hidden" name="bgType" value=""> <input type="hidden"
			name="openId" value="${openId }"> <input id="endBag"
			type="submit" value="生成专属红包">
	</form>
</body>
</html>
