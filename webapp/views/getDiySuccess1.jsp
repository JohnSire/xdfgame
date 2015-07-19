<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>获得红包金额</title>
<link rel="stylesheet" type="text/css" href="${ctx}/views/style/style.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<script src="${ctx}/views/js/jquery-1.7.2.min.js"></script>
<script src="${ctx}/views/js/jquery.cookie.js"></script>
<script src="${ctx}/views/js/iniW.js"></script>
<%@include file="/views/include/share.jsp" %>
</head>
<body> 
<div class="box">

    <div class="page pageBg2">
      
       
        <div class="cBox2">
           <div class="t">
	           <c:if test="${not empty money}">
			  	 <span class="num3">你获得啦${money }元！</span>
			  </c:if>
			  <c:if test="${empty money }">
			  	<span class="num3">你获得啦0元！</span>
			  </c:if>
                <span class="num4">可用于支付任意新东方课程报名~</span>
         
          </div>
         
           
       	  <div class="userMsg">
            <div class="uqb mt20">
             	 <div class="maozi"></div>
             	 <div class="face "><img src="${bagHeadImg }" width="200" height="200"></div>
                 <div class="ts">
                 	 <p>${msgV }</p>
                 </div>
            </div>
          </div>
          <div class="hb">
   		  		<c:if test="${bagtype=='qiang'}">
   		  			<div class="hbJb"><img src="${ctx }/views/images/jb.gif" width="340" height="338"></div>
	   		  		<div class="qbT"><img src="${ctx }/views/images/i3.png" width="464" height="318"></div>
	          		<div class="qbD"><img src="${ctx }/views/images/i12.gif" width="466" height="394"></div>
   		  		</c:if>
   		  		<c:if test="${bagtype=='diy'}">
   		  			<div class="hbJb"><img src="${ctx }/views/images/jb.gif" width="340" height="338"></div>
   		  			<img src="${ctx }/views/images/h1.gif" width="474" height="488">
   		  		</c:if>
   		  		<c:if test="${bagtype=='tec'}">
   		  			<div class="hbJb2"><img src="${ctx }/views/images/i13.gif" width="323" ></div>
          			<div class="qbT2"><img src="${ctx }/views/images/i3_2.png" width="464" height="386"></div>
          			<div class="qbD2"><img src="${ctx }/views/images/i5_1.gif" width="534" height="386"></div>
   		  		</c:if>
          </div>
         	 <div class="yun mtf150 h400">
   		    	<p class="ac" style="padding: 250px 0 0 105px">
   		    	<!--  
                 	<a href="${ctx }/bag/tzjxfx?openId=${openId }" class="btn w320">我还要抢</a>
                 -->
                 <input type="text" value="${openId }" />
                 <input type="text" value="${dbagId }" />
                 <a href="${ctx }/oauth/index?openId=${openId }&bagId=${dbagId}" style="display: inline-block; float:left; margin-left:30px;" class="btn w250">我还要抢</a>
                 <a href="${ctx }/diyBag/myBags?openId=${openId }" style="display: inline-block; float:left; margin-left:50px;" class="btn w250">红包使用</a>
   		    	</p>
   		    	<div class="yunD">
                	<img src="${ctx }/views/images/d.gif" width="832" height="214">
               		<div class="h200"></div>
                </div>
            </div>
            
            <div class="ulist">
                 <div class="m">
                    	      
                        <ul>
                       		<ol>
                            	<li>
                              		<div class="face"><img src="${headImg }" width="64" height="64"></div>
                                </li>
                                <li class="ut1">我抢到的红包金额</li>
                                <c:if test="${not empty money}">
							  	 	<li class="ut2" style="width:120px;">  ${money }元</li>
							    </c:if>
							  	<c:if test="${empty money }">
							  		 <li class="ut2">  0元</li>
							  	</c:if>
                            </ol>
                            <ol>
                            	<li class="ul1"></li>
                                <li class="ut3">看朋友们手气如何</li>
                                <li class="ul1"></li>
                            </ol>
                       </ul>
                       <ul class="lists">
	                       	<c:forEach items="${list }" var = "i">
	                        	<ol>
	                            	<li>
	                              		<div class="face"><img src="${i.himg }" width="64" height="64"></div>
	                                </li>
	                                <li class="ut4"><p class="n">${i.nameV } <span>${i.getDate } ${i.getTime }</span></p>
	                                	<p class="w"> 谢谢~~拿到红包后感觉整个人都萌萌哒！</p>
	                                </li>
	                                <li class="ut5"> ${i.moneys }元</li>
	                            </ol>
	                        </c:forEach>    
                        </ul>
                        <p class="clear h50"></p>
                 </div>
   
            </div>
       </div>
       <div class="dBox">
            <div class="xg"></div>
        </div>
       
  </div>
</div>
</body>
</html>