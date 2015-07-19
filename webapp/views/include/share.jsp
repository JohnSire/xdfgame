<%@ page contentType="text/html;charset=UTF-8" %>
<script type="text/javascript">

/** 
 * imgUrl 分享的图片链接
 * linkLink 用户点击的链接
 * descContent 描述
 * shareTitle 标题
 * appid 
 * 
 * */

var imgUrl = '${GUANZHU_MOREN_PIC}';
var lineLink = '${GUANZHU_MOREN_URL}';
var descContent = '${GUANZHU_MOREN_DESC}';
var shareTitle = '${GUANZHU_MOREN_TITLE}';
var appid = '${appid}';
var hdurl = "${ctx}/count/addshare?schoolId=${schoolId}";
</script>
<script type="text/javascript" src="${ctx }/views/xdfjs/WeixinApi.js"></script>
<script type="text/javascript" src="${ctx }/views/xdfjs/shareAction.js"></script>