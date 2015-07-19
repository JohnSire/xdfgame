
$(document).ready(function(e) {
    $(".msg").click(function(){
		$(this).hide();
	})
});
function showShare(){
	$(".msg .c div").hide()
	$(".msg .share").show()
    $(".msg").show()
}
function showMsg(id){
	$(".msg .c div").hide()
	$(".msg "+id).show()
    $(".msg").show()
}
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
	
	




var ua = window.navigator.userAgent.toLowerCase();
var isWeiXin=false

if(ua.match(/MicroMessenger/i) == 'micromessenger'){
	isWeiXin= true;
}


