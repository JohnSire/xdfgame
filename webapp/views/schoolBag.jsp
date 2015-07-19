<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- diy红包 -->
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>新东方红包</title>
<link rel="stylesheet" type="text/css" href="${ctx }/views/css/main.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

<script src="${ctx }/views/xdfjs/jquery.1.7.2.min.js"></script>
<script src="${ctx }/views/xdfjs/WeixinApi.js"></script>

<script src="${ctx }/views/xdfjs/common.js"></script>
<script src="${ctx }/views/xdfjs/iniH.js"></script>

<script src="${ctx }/views/xdfjs/MegaPixImage.js"></script>
<script src="${ctx }/views/xdfjs/jquery.Jcrop.min.js"></script>

<%@include file="/views/include/share.jsp" %>
</head>
<body>
<div class="box">
	<form id="myform" action="${ctx }/schoolBag/upload" method="post" enctype="multipart/form-data">
	<div class="page msg">
    	<div class="c">
       	  	<div class="share"><img src="${ctx }/views/images/share.gif" ></div>
            <div class="ts">
            
            	<a href="javascript:;" class="btn_1"></a>
                <a href="javascript:;" class="btn_2"></a>
                <a href="javascript:;" class="btn_3"></a>
                <a href="javascript:;" class="btn_4"></a>
               
            </div>
     	 </div>
    	<div class="d"></div>
    </div>
	<div class="page main">
    	<a href="javascript:;" class="logo"></a>
        
      <div class="BJpage">
        <div style="display:none">
   	   	 	<img src="${ctx }/views/images/p0_1.gif" width="408" height="412"> 
            <img src="${ctx }/views/images/p1_1.gif" width="408" height="412"> 
        </div>
        <div class="pr">
        <div class="t3"></div>
        <div class="t5" style="display:none"></div>
        <div class="t6" style="display:none"></div>
        <div class="bao bao1 w1">
            <div class="pr">
                <div class="t "><span>逢考必过包</span><input maxlength="8" name="t1" type="text" value="逢考必过包" ></div>
                <div class="f "><span>一起愉快的“玩转”考试吧！</span><textarea maxlength="25" name="f1" >一起愉快的“玩转”考试吧！</textarea></div>
               
            </div>
        </div>
        
        <div class="bao bao2 w2" style="left:720px;">
        	 <div class="pr">
   	   			 <div class="t "><span>梦想叉叉包</span><input maxlength="8" name="t2"  type="text" value="梦想叉叉包" ></div>
                 <div class="f "><span>梦想一路同行，送你超级红包一枚，加油哒！</span><textarea maxlength="25" name="f2" >梦想一路同行，送你超级红包一枚，加油哒！</textarea></div>
             </div>
         </div>
        
         <div class="bao bao3 w3" style="left:720px">
            <div class="pr">
            	<div class="cc">
                    <div class="t "><span>逢考必过包</span><input maxlength="8"  name="t3" type="text" value="逢考必过包" ></div>
                    <div class="f "><span>一起愉快的“玩转”考试吧！</span><textarea maxlength="25"  name="f3" >一起愉快的“玩转”考试吧！</textarea></div>
                    <span class="addT"></span>
                    <a href="javascript:;" class="add" id="add">
                    	<input type="file" accept="${ctx }/views/image/*"  id="fileupload" name="fileupload" onChange="fileSelectHandler()" onpropertychange="fileSelectHandler()"/>
                    </a>
                </div>
                <div class="cd">
                <img src="${ctx }/views/images/face.gif" width="210" height="290" id="face">
                <!--	<canvas id="face" width="210" height="290"></canvas>-->
                </div>
            </div>
        	
        </div>
     	<div class="t4"></div>
        <a href="javascript:;" class="goLeft" style="display:none"></a>
        <a href="javascript:;" class="goRight"></a>
        
        </div>
        </div>
        
        <!-------------红包模板文案结束--------------------->
     
        <div class="JEpage">
        <div class="pr">
        <div class="t7"></div>
        <ul class="listJE">
<!--         	<li> -->
<!--             	<span class="st1" data-num="10">10元</span> -->
<!--             	<div class="tool"><div class="pr"><span><p>100</p></span></div></div> -->
<!--                 <span class="st2">0</span> -->
<!--             	<span class="st4">3000</span> -->
<!--                 <input type="hidden" value="100" name="test"> -->
<!--             </li> -->
        	<c:forEach items="${bingos}" var="bingo" varStatus="status">
        	<li>
            	<span class="st1" data-num="${bingo.moneyN}">${bingo.moneyN}元</span>
            	<div class="tool"><div class="pr"><span><p>0</p></span></div></div>
                <span class="st2">0</span>
                <input type="hidden" value="0" name="${bingo.id}">
            	<span class="st4">${bingo.numN}</span>
            </li>
           </c:forEach>
            <li>
                <div class="t22">
                    <p class="sl">111</p>
                    <p class="je">111</p>
                </div>
            </li>
       
        </ul>
        </div>
        </div>
        
        <!----------------------红包预览---------------------------->
        <div class="YLpage">
        <div class="pr">
            <div class="t8"></div>
            <div class="j1"><img src="${ctx }/views/images/i4.gif" width="211" height="134"></div>
            <div class="bao bao1 baoB">
                <div class="pr">
                    <div class="t" style="word-wrap: break-word;">逢考必过包</div>
                    <div class="f" style="word-wrap: break-word;">一起愉快的“玩转”考试吧！</div>
                    <div class="mb"></div>
                </div>
            </div>
            <div class="bao bao2 baoB">
                <div class="pr">
                    <div class="t" style="word-wrap: break-word;">梦想叉叉包</div>
                    <div class="f" style="word-wrap: break-word;">梦想一路同行，送你超级红包一枚，加油哒！</div>
                    <div class="mb"></div>
                </div>
            </div>
            <div class="bao bao3 baoB">
                <div class="pr">
                    <div class="cc">
                        <div class="t" style="word-wrap: break-word;">逢考必过包</div>
                    	<div class="f" style="word-wrap: break-word;">一起愉快的“玩转”考试吧！</div>
                        <div class="mb"></div>
                    </div>
                    <div class="cd">
                    	<img src="${ctx }/views/images/face.gif" width="210" height="290" id="face2">
                        <!--<canvas id="face2" width="210" height="290"></canvas>-->
                    </div>
                </div>
            
            </div>
            <div class="j2"><span></span><div></div></div>
            <div class="jeList">
<!--                 <p>XXX元，XXXX张</p> -->
<!--                 <p>XXX元，XXXX张</p> -->
            </div>
             <a href="javascript:submit1();" class="btn_creat"></a>
        </div>
		</div>
        
        <a href="javascript:;" class="btn_next">
        <img src="${ctx }/views/images/btn_next.gif" width="226" height="97">
        </a>
		
    </div>
    <div class="page di" >
    	<div class="d"></div>
    </div>
    	
    	<input type="text" id="moban" name="moban" value="1" />
    	<input type="text" id="guanming" name="guanming" />
    	<input type="text" id="msgV" name="msgV" />
    	<input type="text" name="userId" value="${userId}" />
	   	<input type="text" name="schoolId" value="${schoolId}" />
	   	<input type="text" name="sum" value="0" />
	   	<input type="text" id="moban1" value="0" />
	</form>
</div>
<script>
function submit1(){
	$("#myform").submit();
	$.post("${ctx}/count/addhd",function(){});
}
$(document).ready(function(e) {
    $(".msg").unbind("click")
	$(".msg").click(function(){
		$(this).hide()
	})
	
});
var step=1
var bid=1;
$(".btn_next").click(function(){
	if((step==1 || step==2) && isClick){
		for(i=1;i<4;i++){
			if(i==bid){
				$(".BJpage .bao"+i).show()
			}else{
				$(".BJpage .bao"+i).hide()
			}	
		}
		$(".YLpage .bao"+bid+" .t").html($(".BJpage input[name=t"+bid+"]").val())
		$(".YLpage .bao"+bid+" .f").html($(".BJpage textarea[name=f"+bid+"]").val())
		$(".t6").fadeOut();
		$(".BJpage").animate({"left":-720},500)
		$(".YLpage").css({"left":720}).animate({"left":0},500)
		$(".d").fadeIn()
		$(".btn_next").animate({"left":-720},500)	
		iniPageW();
		step=4;	
		return;
	}
	if(step==1){
		
		$.post("${ctx}/count/addhd",function(){});
		if($("#moban").val() == "3"){
			if($("#fileupload").val() == ""){
				alert("请上传模板图！也可以换一个模板！");
				return;
			}
			$("#add").removeClass("add");
		}
		
		$(".goLeft").fadeOut();
		$(".goRight").fadeOut();
		$(".t3").animate({"left":-720},500)
		$(".t4").fadeOut()
		$(".t5").animate({"left":-720},500)
		$(".t6").css({"left":720}).show().animate({"left":50},500)
		
		$(".bao .t span").hide()
		$(".bao .f span").hide()
		$(".bao .t input").show()
		$(".bao .f textarea").show()
		//$(".BJpage .bao1").addClass("bao1_edit")
		//$(".BJpage .bao3").addClass("bao3_edit")
		step=2;	
	}else if(step==2){
		$.post("${ctx}/count/addhd",function(){});
		
		$("#guanming").val($(".BJpage input[name=t"+bid+"]").val());
		$("#msgV").val($(".BJpage textarea[name=f"+bid+"]").val());
		
		//修改金额
		for(i=1;i<4;i++){
			if(i==bid){
				$(".BJpage .bao"+i).show()
			}else{
				$(".BJpage .bao"+i).hide()
			}	
		}
		$(".YLpage .bao"+bid+" .t").html($(".BJpage input[name=t"+bid+"]").val())
		$(".YLpage .bao"+bid+" .f").html($(".BJpage textarea[name=f"+bid+"]").val())
		
		
		$(".BJpage").animate({"left":-720},500)
		$(".JEpage").css({"left":720}).animate({"left":0},500)
		
		$(".d").fadeOut()
		 iniPageW();
		//$(window).resize(function(e) {  iniPageW();});
		$(".btn_next").animate({"top":1550},500)	
		step=3;	
		   
	}else if(step==3){
		
		$.post("${ctx}/count/addhd",function(){});
		
		var sum = 0;
		$(".jeList p").remove();
		$("p").each(function(i,n){
			if(parseInt(n.innerHTML) > 0){
		 		 $(".st1").each(function(l,nn){
				 	if(i == l){
				 		sum+=parseInt(n.innerHTML);
				 		$(".jeList").append("<p>"+nn.innerHTML+"，"+n.innerHTML+"张</p>");
				 	}
				 });
				 
		 	}
		});
		if(sum != 0){
			$("input[name='sum']").val(sum);
		}else{
			alert("请选择金额");
			return;
		}
		
		//预览
		$(".JEpage").animate({"left":-720},500)
		$(".d").fadeIn()
		$(".btn_next").animate({"left":-720},500)	
		$(".YLpage").css({"left":720}).animate({"left":0},500)
		$(".msg .d").css({"height":$(document).height()/phoneScale})
		window.scroll(0,0)
	
		step=4;	
		
	}
	
})
var isClick=false
$(".YLpage .j2 span").click(function(){
	isClick=true
	//修改金额
	$(".d").fadeOut()
	 $(".msg").hide()
	 $(".YLpage").animate({"left":720},500)
	 $(".JEpage").animate({"left":0},500)
	 $(".btn_next").animate({"left":208,"top":1550},500)
	 iniPageW();
	
	 step=3;
	
})
$(".YLpage .bao .mb").click(function(){
	 $("#add").addClass("add");
    $(".t input").val($("#guanming").val());
    $(".f textarea").val($("#msgV").val());
	
	
	isClick=true
	//修改模板
	
	   $(".msg").hide()
	 $(".YLpage").animate({"left":720},500)
	 $(".BJpage").animate({"left":0},500)
	 if(bid>1){	 $(".goLeft").fadeIn();}
	 if(bid<3){	 $(".goRight").fadeIn();}
	 $(".t3").css({"left":50})
	 $(".t4").fadeIn()
	 $(".t5").css({"left":210})
	 $(".t6").css({"left":720})
	
	$(".bao .t span").show()
	$(".bao .f span").show()
	$(".bao .t input").hide()
	$(".bao .f textarea").hide()
		
	
	 //$(".BJpage .bao1").removeClass("bao1_edit")
	// $(".BJpage .bao3").removeClass("bao3_edit")
	 $(".btn_next").animate({"top":794,"left":208},300)
	 iniPageW2();
	 step=1;	
})
$(".YLpage .bao .t").click(function(){iniBJ();})
$(".YLpage .bao .f").click(function(){iniBJ();})
function iniBJ(){
	$("#add").removeClass("add");
	//编辑冠名
	isClick=true
	$(".goLeft").fadeOut();
	$(".goRight").fadeOut();
	$(".bao .t span").hide()
		$(".bao .f span").hide()
		$(".bao .t input").show()
		$(".bao .f textarea").show()
		//$(".BJpage .bao1").addClass("bao1_edit")
		//$(".BJpage .bao3").addClass("bao3_edit")
		
		
	 $(".msg").hide()
	 $(".YLpage").animate({"left":720},500)
	 $(".BJpage").animate({"left":0},500)
	 $(".btn_next").animate({"top":794,"left":208},300)
	 step=2;	
	 
	 iniPageW2();
}
function iniBao(id){
	for(i=1;i<4;i++){
		if(i==id){
			$(".BJpage .bao"+i).show()
			$(".YLpage .bao"+i).show()
		}else{
			//$(".BJpage .bao"+i).fadeOut()
			$(".YLpage .bao"+i).hide()
		}	
	}
}
iniBao(1)
//红包左右选择
	$(".goLeft").click(function(){bid--;goSelect(bid)})
	$(".goRight").click(function(){bid++;goSelect(bid)})
	function goSelect(bid){
		if(bid<2){
			$.post("${ctx}/count/addhd",function(){});
			$("#moban").val("1");
			bid=1;
			iniBao(1)
			$(".t5").fadeOut();
			$(".goLeft").fadeOut();
			$(".goRight").fadeIn();
			$(".w1").show().animate({"left":116},500)
			$(".w2").show().animate({"left":720},500)
			$(".w3").show().animate({"left":720},500)
			
			
		}else if(bid==2){
			$.post("${ctx}/count/addhd",function(){});
			$("#moban").val("2");
			bid=2
			iniBao(2)
			$(".t5").fadeOut();
			$(".goLeft").fadeIn();
			$(".goRight").fadeIn();
			$(".w1").show().animate({"left":-720},500)
			$(".w2").show().animate({"left":116},500)
			$(".w3").show().animate({"left":720},500)
			
		}else{
			$.post("${ctx}/count/addhd",function(){});
			$("#moban").val("3");
			bid=3
			iniBao(3)
			$(".t5").fadeIn();
			$(".goLeft").fadeIn();
			$(".goRight").fadeOut();
			$(".w1").show().animate({"left":-720},500)
			$(".w2").show().animate({"left":-720},500)
			$(".w3").show().animate({"left":116},500)
			
		}
	}
	
//上传图片相关
$("#fileupload").on("change",function(){fileSelectHandler();})
function fileSelectHandler() {
    var oFile = $('#fileupload')[0].files[0];
    if (oFile.size > 3*1024 * 1024) {
      	alert('选择文件大于3M，请重新选择');
        return;
    }

 // var oImage = new Image()
  oImage=document.getElementById("face")
  oImage2=document.getElementById("face2")
  var oReader = new FileReader();
  	  oReader.onload = function(e) {
	  oImage.src = e.target.result;
	  oImage2.src = e.target.result;
	  oImage.onerror=function(){
			alert("图片加载失败~")	
	  }
	
	  oImage.onload = function () {
		 oNh=oImage.naturalHeight
		 oNw=oImage.naturalWidth
		 face=document.createElement("canvas")
		 ctx=face.getContext("2d");
	   	 ctx.fillStyle="#ff3333";
		 ctx.fillRect(0,0,oNw,oNh);
		 ctx.drawImage(oImage,0,0,oNw,oNh,0,0,oNw,oNh)
		 //上传图片
        };
		
    };
   oReader.readAsDataURL(oFile);
  // oFile.reset();
	$(".addT").fadeOut()
}

var moveObj
var isDown
	$(".listJE li").each(function(){
		$(".listJE li .tool span").on("mousedown touchstart",function(){
			xy=$(this).offset()
			moveObj=$(this)
			isDown=true
		})
	})
	$(document).on("mousedown touchstart",down)
	$(document).on("mousemove touchmove",draw)
	var startX,endX
	function down(e){
		e=e.originalEvent
		_xy=getXY(e)
		startX=_xy.x
		isDown=true
	}
	function draw(e){
		if(isDown){
		e=e.originalEvent
		_xy=getXY(e)
		endX=_xy.x
		if(step==1){
			//document.title=Math.abs(endX-startX)
			if( Math.abs(endX-startX)>50){
				//document.title=endX+":"+startX
				if(endX>startX){
					//向右
					bid--;goSelect(bid)
				}else{
					bid++;goSelect(bid)
				}
				isDown=false
			}
		}
		if(moveObj!=null){
			 var num = moveObj.parent().parent().parent().children().last().text();
			_xy2=moveObj.parent().parent().offset()
			//document.title=Math.floor(_xy.x-_xy2.left)+":"+Math.floor(_xy.x)+":"+Math.floor(_xy2.left)
			gx=Math.floor(_xy.x-_xy2.left/phoneScale)-15
			if(gx<-10){gx=-10;}
			if(gx>330){gx=330;}
				moveObj.css({"left":gx})
				n=Math.floor(((gx+10)*num/340)+0)
				moveObj.children("p").html(n)
				moveObj.parent().parent().parent().children("input").val(n)
				iniDataNum()
			
			}
		}
	}
	$(document).on("mouseup touchend",function(){
		moveObj=null
		isDown=false
	})
	function iniDataNum(){
		sl=0
		je=0
		$(".listJE .st1").each(function(){
			sl+=Math.floor($(this).parent().children("input").val())
		
			je+=$(this).data("num")*$(this).parent().children("input").val()
		})
		
		$(".t22 .sl").html("红包总数："+sl)
		$(".t22 .je").html("红包总金额："+je)
		
	}
	iniDataNum()
	function getXY(e){
	var _x=0,_y=0
	
	if (/(iPhone|iPad|iPod|iOS|Android)/i.test(navigator.userAgent)){
		if(e.targetTouches[0] != undefined){
			_y = e.targetTouches[0].pageY
			_x = e.targetTouches[0].pageX;
		}else if(e.changedTouches[0] != undefined){
			_y= e.changedTouches[0].pageY
			_x= e.changedTouches[0].pageX;
		}else if(e.touches[0] != undefined){
			_y=e.touches[0].pageY
			_x=e.touches[0].pageX
		}
	}else{
		_y=e.pageY/phoneScale
		_x=e.pageX/phoneScale
	}
	return {x:Math.floor(_x),y:Math.floor(_y)}
}
	
var clickID=1
//不满意按钮
$(".j1").click(function(){
	$(".msg .ts").show()
    $(".msg").show()
	clickID=2
	iniJclick()
})
//查看金额相关
$(".j2 div").click(function(){ iniJclick();})
function iniJclick(){
	if(clickID==1){
		clickID=2
		$(".YLpage .bao"+bid).fadeOut()
		$(".d").fadeOut()
		$(".j2").animate({"top":370},500)
		$(".jeList").delay(400).fadeIn(400)
		$(".btn_creat").animate({"top":1164},500)
	}else{
		clickID=1
		$(".YLpage .bao"+bid).delay(500).fadeIn()
		$(".d").delay(500).fadeIn()
		$(".j2").animate({"top":730},500)
		$(".jeList").fadeOut(400)
		$(".btn_creat").animate({"top":824},500)
	}
}
</script>
</body>
</html>
