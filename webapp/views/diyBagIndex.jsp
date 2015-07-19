<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>DIY专属红包</title>
<link rel="stylesheet" type="text/css" href="${ctx }/views/style/style.css">
<link rel="stylesheet" type="text/css" href="${ctx }/views/style/jquery.Jcrop.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<script src="${ctx }/views/js/jquery-1.7.2.min.js"></script>
<script src="${ctx }/views/js/jquery.cookie.js"></script>
<script src="${ctx }/views/js/iniH.js"></script>
<script src="${ctx }/views/js/jquery.Jcrop.min.js"></script>
<%@include file="/views/include/share.jsp" %>
<script>
function showMsgShare(){
	$(".mBoxD").css({"width":$(".page").width(),"height":$(".page").height()})
	$(".msgClick").show();
	$(".msgTobig").hide();
	$(".mBox").show();
}
function showMsgTobig(){
	$(".mBoxD").css({"width":$(".page").width(),"height":$(".page").height()})
	$(".msgClick").hide();
	$(".msgTobig").show();
	$(".mBox").show();
}
</script>
</head>
<body> 
<div class="box">
<form action="${ctx }/diyBag/createDiyBag" method="post" id="diyform" enctype="multipart/form-data" name="diyform">
	<!-- 红包背景 -->
	<input type="hidden" name="bgType" id="bgType" value="i2.gif">
	<input type="hidden" name="openId" value="${openId }">
	<input type="hidden" name="headImage" value="${headImage }">
	<input type="hidden" id="fName" name="fName" value="">
    <div class="page">
    
        <div class="mBox" style="display:none">
        	<div class="mBoxC">
            	<div class="msgClick" style="display:none"><img src="${ctx}/views/images/msg_1.gif" width="662" height="744"></div>
                <div class="msgTobig" style="display:none"><img src="${ctx}/views/images/msg_tobig.gif" width="595" height="261"></div>
            </div>
        	<div class="mBoxD"></div>
        </div>
        
        <div class="main">
            <div class="yBox3">
                <div class="yun">
                     <P class="obtn"><a href="javascript:diyform.submit();"  class="btn2 w320" form="diyform">生成专属红包</a></P>
                </div>	 
               
            </div>
            <div class="cBox">
                <div class="tBox">
                    <div class="t1">
                        <div class="t1_0"><img src="${ctx}/views/images/t0_2.gif" width="256" height="79"></div>
                        <div class="t1_1 zyD"><img src="${ctx}/views/images/diy.gif" width="360"/></div>
                        <div class="fjBox l190"><div class="fj"></div></div>
                    </div>
                </div>
                <div class="tBox">
                    <div class="t2">
                        <div><img src="${ctx}/views/images/t3_0.gif" width="580" height="125"></div>
                        <div><img src="${ctx}/views/images/t2.gif" width="428" height="29"></div>
                    </div>
                </div>
                <div class="ts ts2">
                    <p><textarea id="tt" cols="12" rows="2" name="msg" form="diyform">还能一起愉快的玩耍吗？梦想分红，我们先拼拼手气吧!</textarea></p>
                </div>
               <div class="qz1"></div>
               <div class="qz2"></div>
               <div class="qz3"></div>
                <div class="qb sqb">
     	  			
                     <div class="face" style="width:200px; height:200px; overflow:hidden;">
                     	 <img src="${headImage }" width="200" height="200"  id="faceImg">
                     	 <canvas id="face" width="200" height="200"></canvas>
                     </div>
                      <script type="text/javascript">
                        	var faceImg = document.getElementById('faceImg');
							var face = document.getElementById("face");  
							
							var faceCanvas = face.getContext("2d");
						
							faceCanvas.drawImage(faceImg,0,0,156,156,0,0,200,200)
                      </script>
                      <div class="qbT4">
                            <p><input type="text" value="${userName }" name="bagName" form="diyform" onfocus="toaddhd()"><span>红包~</span></p>
                      </div>
                      <div class="qbT"><img src="${ctx}/views/images/i3.png" width="464" height="318"></div>
                      <div class="qbD"><img src="${ctx}/views/images/i2_0.gif" width="466" height="394"></div>
                </div>
            </div>
            <div class="dBox">
                <div class="diyD"></div>
            </div>
        
        
        </div>
        
        
        
        <div class="setHB">
            <div class="cBox select">
                <div class="t9"></div>
              
                <div class="qb ">
                 
          
                      <div class="qbT4">红包样式 </div>
                      <div class="qbT"><img src="${ctx}/views/images/i3.png" width="464" height="318"></div>
                      <div class="qbD"><img src="${ctx}/views/images/i2_0.gif" width="466" height="394"></div>
                </div>
                <div class="item itemA">
                    <a href="javascript:setQB(0);"></a>
                    <a href="javascript:setQB(1);"></a>
                    <a href="javascript:setQB(2);"></a>
                    <a href="javascript:setQB(3);"></a>
                    <a href="javascript:setQB(4);"></a>
                </div>
                <a href="javascript:backMain();" class="btn2 w320" style="display: inline-block; margin-left:80px;">更换样式</a>
                <a href="javascript:backMain();" class="btn2 w320" style="display: inline-block;width:250px;" >返回</a>
            </div>
            <div class="dBox">
                <div class="xg mt250"></div>
            </div>
      </div>  
  
        
      <div class="setFace">
    
            <div class="cBox upload">
            	<div class="uploadFrame">
                	<div class="uploadM">
                    <div class="tool">
                    <p>请选择图片区域</p>
                     <a href="javascript:hideUploadFrame();" class="btnSure">确定</a>
                      <a href="javascript:noUploadFrame();" class="btnSure">放弃</a>
                    </div>
                
                
               	 <div class="preview">
                	<span>图片加载中。。。</span>
                  	<canvas   id="preview" width="640" height="900"></canvas>
                 </div>

                  <div class="info">
                      <label>文件大小</label> <input type="text" id="filesize" name="filesize" />
                      <label>类型</label> <input type="text" id="filetype" name="filetype" />
                      <label>图像尺寸</label> <input type="text" id="filedim" name="filedim" />
                      <label>宽度</label> <input type="text" id="w" name="w" />
                      <label>高度</label> <input type="text" id="h" name="h" />
                  </div>
				   </div>
              		<div class="mBoxD"></div>
                </div>
                <div class="t8"></div>
                <div class="maozi"></div>
                <div  class="upface" >
                	 <canvas   id="upface" width="300" height="300"></canvas>
                </div>
                <a href="javascript:;" class="add">
                	<input type="file" accept="image/*"   id="fileupload" name="fileupload" onChange="fileSelectHandler()" form="diyform"/>
				</a>
                <a href="javascript:uploadFace();" class="btn2 w320" style="display: inline-block; margin-left:80px;">提交头像</a>
                <a href="javascript:backtoMain();" class="btn2 w320"  style="display: inline-block;width:250px;">返回</a>
                    <input type="hidden" id="x1" name="x1" />
                    <input type="hidden" id="y1" name="y1" />
                    <input type="hidden" id="x2" name="x2" />
                    <input type="hidden" id="y2" name="y2" />

            </div>
            <div class="dBox">
                <div class="xg mt250"></div>
            </div>
    
      </div>
      <div class="logo2"></div>
    </div>
  </form>
</div>

<script src="${ctx }/views/js/faceAction.js"></script>
<script>
					
	var hbKind=0
	$(document).ready(function(e) {
    
		$(".face").click(function(){ changeFace();})
		$(".qbD").click(function(){ changeQB();})
		$(".t1_1").click(function(){ 
			showMsgShare();
			$.post("${ctx}/count/addhd",function(){});
		})
		$("#tt").click(function(){
			$.post("${ctx}/count/addhd",function(){});
		});
    });
	function toaddhd()
	{
		$.post("${ctx}/count/addhd",function(){});
	}
	function changeFace(){
		
		$(".main").animate({"left":-832},600)
		$(".setFace").animate({"left":0},600)
		$.post("${ctx}/count/addhd",function(){});//设置图像+1
			
	}
	function changeQB(){
		
		$(".main").animate({"left":-832},600)
		$(".setHB").animate({"left":0},600)
		$.post("${ctx}/count/addhd",function(){});//设置钱包+1	
	}
	function backMain(){
		$(".main").animate({"left":0},600)
		$(".setHB").animate({"left":832},600)
		$(".setFace").animate({"left":832},600)
	}
	
	function backtoMain(){
		backMain();
	}
	
	function setQB(hbKind){

		if(hbKind>4){hbKind=0;}
		$(".qbD img").attr("src","${ctx}/views/images/i2_"+hbKind+".gif")
		$("#bgType").val("i2_"+hbKind+".gif");
		$.post("${ctx}/count/addhd",function(){});//背景设置+1
	}
	
        function uploadFace(){
        	var canvas = document.getElementById("face");  
    		var imgData = canvas.toDataURL("image/jpeg");
    		$("#fName").val(imgData);
        	backMain();
        } 

</script>
</body>
</html>