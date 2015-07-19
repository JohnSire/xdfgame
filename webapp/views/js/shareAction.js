// JavaScript Document
// 微信分享的数据
	var wxData = {
		"appId": appid, // 服务号可以填写appId
		"imgUrl" : imgUrl,
		"link" : lineLink,
		"desc" : descContent,
		"title" : shareTitle
	};
WeixinApi.ready(function(Api) {
  				
	var wxCallbacks = {
            ready : function() {
            },
            cancel : function(resp) {
            },
            fail : function(resp) {
            },
            // 分享成功
            confirm : function(resp) {
            	$.get(hdurl, function(){});
    			if(!shareEndUrl){
    				
    			}else{
    				location.href=shareEndUrl;
    			}
            },
            // 整个分享过程结束
            all : function(resp) {
            }
        };
	Api.shareToFriend(wxData, wxCallbacks);
	Api.shareToTimeline(wxData, wxCallbacks);
	Api.shareToWeibo(wxData, wxCallbacks);
});
