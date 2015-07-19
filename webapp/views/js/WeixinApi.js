
var WeixinApi = (function () {

    function weixinShareTimeline(data, callbacks) {
        callbacks = callbacks || {};
        var shareTimeline = function (theData) {
            WeixinJSBridge.invoke('shareTimeline', {
                "appid":theData.appId ? theData.appId : '',
                "img_url":theData.imgUrl,
                "link":theData.link,
                "desc":theData.title,
                "title":theData.desc,
                "img_width":"120",
                "img_height":"120"
            }, function (resp) {
                switch (resp.err_msg) {
            
                    case 'share_timeline:cancel':
                        callbacks.cancel && callbacks.cancel(resp);
                        break;
                  
                    case 'share_timeline:fail':
                        callbacks.fail && callbacks.fail(resp);
                        break;
              
                    case 'share_timeline:confirm':
                        callbacks.confirm && callbacks.confirm(resp);
                        break;
                }
        
                callbacks.all && callbacks.all(resp);
            });
        };
        WeixinJSBridge.on('menu:share:timeline', function (argv) {
            if (callbacks.async && callbacks.ready) {
                if(!callbacks.__dataLoadedFuncInited) {
                    var loadedCb = callbacks.dataLoaded || new Function();
                    callbacks.dataLoaded = function (newData) {
                        loadedCb(newData);
                        shareTimeline(newData);
                    };
                    callbacks.__dataLoadedFuncInited = true;
                }
            
                callbacks.ready && callbacks.ready(argv);
            } else {
            
                callbacks.ready && callbacks.ready(argv);
                shareTimeline(data);
            }
        });
    }
    function weixinSendAppMessage(data, callbacks) {
        callbacks = callbacks || {};
		
        var sendAppMessage = function (theData) {
            WeixinJSBridge.invoke('sendAppMessage', {
                "appid":theData.appId ? theData.appId : '',
                "img_url":theData.imgUrl,
                "link":theData.link,
                "desc":theData.desc,
                "title":theData.title,
                "img_width":"120",
                "img_height":"120"
            }, function (resp) {
                switch (resp.err_msg) {
                  
                    case 'send_app_msg:cancel':
                        callbacks.cancel && callbacks.cancel(resp);
                        break;
                  
                    case 'send_app_msg:fail':
                        callbacks.fail && callbacks.fail(resp);
                        break;
                  
                    case 'send_app_msg:confirm':
                        callbacks.confirm && callbacks.confirm(resp);
                        break;
                }
              
                callbacks.all && callbacks.all(resp);
            });
        };
        WeixinJSBridge.on('menu:share:appmessage', function (argv) {
            if (callbacks.async && callbacks.ready) {
                if(!callbacks.__dataLoadedFuncInited) {
                    var loadedCb = callbacks.dataLoaded || new Function();
                    callbacks.dataLoaded = function (newData) {
                        loadedCb(newData);
                        sendAppMessage(newData);
                    };
                    callbacks.__dataLoadedFuncInited = true;
                }
              
                callbacks.ready && callbacks.ready(argv);
            } else {
               
                callbacks.ready && callbacks.ready(argv);
                sendAppMessage(data);
            }
        });
    }

    function weixinShareWeibo(data, callbacks) {
        callbacks = callbacks || {};
        var shareWeibo = function (theData) {
            WeixinJSBridge.invoke('shareWeibo', {
                "content":theData.desc,
                "link":theData.link,
                "img_url":theData.imgUrl,
                "title":theData.title,
                "img_width":"120",
                "img_height":"120"
            }, function (resp) {
                switch (resp.err_msg) {
                   
                    case 'share_weibo:cancel':
                        callbacks.cancel && callbacks.cancel(resp);
                        break;
                   
                    case 'share_weibo:fail':
                        callbacks.fail && callbacks.fail(resp);
                        break;
                  
                    case 'share_weibo:confirm':
                        callbacks.confirm && callbacks.confirm(resp);
                        break;
                }
          
                callbacks.all && callbacks.all(resp);
            });
        };
        WeixinJSBridge.on('menu:share:weibo', function (argv) {
            if (callbacks.async && callbacks.ready) {
                if(!callbacks.__dataLoadedFuncInited) {
                    var loadedCb = callbacks.dataLoaded || new Function();
                    callbacks.dataLoaded = function (newData) {
                        loadedCb(newData);
                        shareWeibo(newData);
                    };
                    callbacks.__dataLoadedFuncInited = true;
                }
              
                callbacks.ready && callbacks.ready(argv);
            } else {
            
                callbacks.ready && callbacks.ready(argv);
                shareWeibo(data);
            }
        });
    }
    function showOptionMenu() {
        WeixinJSBridge.call('showOptionMenu');
    }
    function hideOptionMenu() {
        WeixinJSBridge.call('hideOptionMenu');
    }
    function showToolbar() {
        WeixinJSBridge.call('showToolbar');
    }
    function hideToolbar() {
        WeixinJSBridge.call('hideToolbar');
    }
    function getNetworkType(callback) {
        if (callback && typeof callback == 'function') {
            WeixinJSBridge.invoke('getNetworkType', {}, function (e) {
                // 在这里拿到e.err_msg，这里面就包含了所有的网络类型
                callback(e.err_msg);
            });
        }
    }
    function wxJsBridgeReady(readyCallback) {

        if (readyCallback && typeof readyCallback == 'function') {
            var Api = this;
            var wxReadyFunc = function () {
                readyCallback(Api);
            };
            if (typeof window.WeixinJSBridge == "undefined"){
                if (document.addEventListener) {
					
					document.removeEventListener('WeixinJSBridgeReady', wxReadyFunc, false);
                    document.addEventListener('WeixinJSBridgeReady', wxReadyFunc, false);
                } else if (document.attachEvent) {
                    document.attachEvent('WeixinJSBridgeReady', wxReadyFunc);
                    document.attachEvent('onWeixinJSBridgeReady', wxReadyFunc);
                }
            }else{
                wxReadyFunc();
            }
        }
    }
    return {
        version         :"1.2",
        ready           :wxJsBridgeReady,
        shareToTimeline :weixinShareTimeline,
        shareToWeibo    :weixinShareWeibo,
        shareToFriend   :weixinSendAppMessage,
        showOptionMenu  :showOptionMenu,
        hideOptionMenu  :hideOptionMenu,
        showToolbar     :showToolbar,
        hideToolbar     :hideToolbar,
        getNetworkType  :getNetworkType
    };
})();