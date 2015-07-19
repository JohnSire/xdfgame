var phoneWidth = parseInt(window.screen.width);
var phoneHeight = parseInt(window.screen.height);
var phoneScale = phoneWidth/640;

var ua = navigator.userAgent;
if (/Android (\d+\.\d+)/.test(ua)){
	var version = parseFloat(RegExp.$1);
	// andriod 2.3
	if(version>2.3){
		document.write('<meta name="viewport" content="width=640, minimum-scale = '+phoneScale+', maximum-scale = '+phoneScale+', target-densitydpi=device-dpi">');
	// andriod 2.3以上
	}else{
		document.write('<meta name="viewport" content="width=640, target-densitydpi=device-dpi">');
	}
	// 其他系统
} else {
	document.write('<meta name="viewport" content="width=640, user-scalable=no, target-densitydpi=device-dpi">');
}


$(document).ready(function(e) {  iniPage();});
//$(window).resize(function(e) {  iniPage();});

function iniPage(){
	
	phoneWidth  = $(window).width();
	phoneHeight = $(window).height();

	phoneScale = phoneWidth/640;
	
	//,"overflow":"auto"
	$(".msg").css({"width":640})
	$(".msg .d").css({"width":640})
	$(".box").css({"width":phoneWidth,"height":"100%","overflow":"visible"})
	$(".page").css({"-webkit-transform":"scale(" + phoneScale + "," + phoneScale + ")","transform":"scale(" + phoneScale + "," + phoneScale + ")"})

}