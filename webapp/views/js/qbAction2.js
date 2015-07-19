// JavaScript Document
var box  = $(".qb");
var movePoint_x; // 当前有变换图片，move的位置X轴点点
var isMove=false
var isOpen=false;
box.on('mousedown touchstart', function(e) {
	
	 if (e.type == "touchstart") {
		 movePoint_x = window.event.touches[0].pageX;
	 } else {
		 movePoint_x = e.pageX||e.x;
	 }
	 isMove=true
});

box.on('mousemove touchmove', function(e){
	e.preventDefault();
	var aL=Math.floor($(".jd img").attr("mL"))
	 var pageX,pageY;
	 if (e.type == "touchmove") {
		 pageX = window.event.targetTouches[0].pageX;
	 } else {
		 pageX = e.pageX||e.x;
	 }
	
	 if(isMove && ( aL>=0 && aL<=230)){
		 aL+=Math.floor(pageX-movePoint_x)*2
		 if(aL<0){aL=0}
		 if(aL>210){ jDend();return; }
		 $(".jd img").attr("mL",aL)
		 $(".jd img").css("margin-left",aL)
	 }
	 movePoint_x = pageX;
	
})
box.on('mouseup touchend mouseout', function() {
	movePoint_x = null;
	isMove = false;
});

function faceEvent(){
	box.off('mousedown touchstart');
	box.off('mousemove touchmove');
	box.off('mouseup touchend mouseout');
	$(".qbT1").fadeOut();
	$(".qbT2").fadeOut();
	$(".qbT3").addClass("item_show")
	$(".t2").addClass("item_rightIn")
	
	$(".jd").fadeOut();
	$(".qbT").show()
	$(".face").addClass("jbBig")
	$(".t1").animate({"left":-832},600)
	$(".ts").animate({"left":-832},600)
	$(".yBox").addClass("yun_up")
	$(".yun .btn").show();
}	
