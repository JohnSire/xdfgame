$(document).ready(function(e) {  iniPage();});
$(window).resize(function(e) {  iniPage();});

function iniPage(){
	phoneWidth  = $(window).width();
	phoneHeight = $(window).height();

	phoneScale = phoneWidth/640;
	
	//
	$(".box").css({"width":phoneWidth,"height":"100%","overflow":"visible"})
	$(".page").css({"-webkit-transform":"scale(" + phoneScale + "," + phoneScale + ")","transform":"scale(" + phoneScale + "," + phoneScale + ")"})

}