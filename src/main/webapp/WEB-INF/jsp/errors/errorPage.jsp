<%@page language="java" contentType="text/html; charset=utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta name="description" content="">
<meta http-equiv="x-dns-prefetch-control" content="on">

<title>错误</title>
<style type="text/css">
html{
	font-size: 18px;
	font-family:Arial, Microsoft Yahei, "微软雅黑", sans-serif;
	color:#333;
	background-color: #5cc37a;
}
body{margin:0;}
.wrap{
	position:relative;
	width:94%;
	max-width: 400px;
	margin: 10px auto;
	overflow: hidden;
	background-color: #fff;
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 10px;
	padding-top: 10px;
	padding-bottom: 10px;
	margin-top: 6rem;
}
.followinfo{
    margin-left: auto;
	margin-right: auto;
	width: 90%;
	margin-top: 10px;
	line-height: 22px;
	font-size: 13px;
}
.followcode{
    margin-left: auto;
	margin-right: auto;
	width: 90%;
	line-height: 20px;
	font-size: 14px;
	text-align: center;
}
.follwwarning{
	width: 90px;
	height: 90px;
	margin-left: auto;
	margin-right: auto;
	margin-top: 3rem;
}
.signoktext{
	font-size: 18px;
	text-align: center;
	padding-bottom: 10px;
	margin-left: auto;
	margin-right: auto;
	width: 90%;
	margin-top: 20px;
	font-weight: bold;
	line-height: 24px;
}
.blue{
	color: #0079c0;
}
.btn {
    background-color: #f56042;
    -webkit-border-radius: 0.2rem;
    -moz-border-radius: 0.2rem;
    border-radius: 0.2rem;
    color: #fff;
    cursor: pointer;
    border: 0;
}

.btn:hover {
    background-color: #ce5137;
    -webkit-border-radius: 0.2rem;
    -moz-border-radius: 0.2rem;
    border-radius: 0.2rem;
    color: #fff;
    cursor: pointer;
    border: 0;
}
.normal{
	width: 100%;
	height: 2.4rem;
	margin-top: 4rem;
	font-size: 1.1rem;
}
</style>
</head>
<script src="<%=request.getContextPath()%>/moibleStyle/js/jquery-2.1.4.min.js"></script>
<script src="<%=request.getContextPath()%>/moibleStyle/js/common.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
	var browser = {
	    versions: function () {
	        var u = navigator.userAgent, app = navigator.appVersion;
	        return {         //移动终端浏览器版本信息
	            trident: u.indexOf('Trident') > -1, //IE内核
	            presto: u.indexOf('Presto') > -1, //opera内核
	            webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
	            gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
	            mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
	            ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
	            android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或uc浏览器
	            iPhone: u.indexOf('iPhone') > -1, //是否为iPhone或者QQHD浏览器
	            iPad: u.indexOf('iPad') > -1, //是否iPad
	            webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
	        };
	    }(),
	    language: (navigator.browserLanguage || navigator.language).toLowerCase()
	};
	
	function clickMe(){
		if (browser.versions.mobile) {//判断是否是移动设备打开。browser代码在下面
		    var ua = navigator.userAgent.toLowerCase();//获取判断用的对象
		    if (ua.match(/MicroMessenger/i) == "micromessenger") {
		        //在微信中打开
		    	WeixinJSBridge.call('closeWindow');
		    }
		    else if (ua.match(/WeiBo/i) == "weibo") {
		        //在新浪微博客户端打开
		        history.go(-1);
		    }
		    else if (ua.match(/QQ/i) == "qq") {
		        //在QQ空间打开
		    	history.go(-1);
		    }
		    else if (browser.versions.ios) {
		        //是否在IOS浏览器打开
		    	history.go(-1);
		    } 
		    else if(browser.versions.android){
		        //是否在安卓浏览器打开
		    	history.go(-1);
		    }else {
			    //否则就是PC浏览器打开
			    history.go(-1);
			}
		} else {
		    //否则就是PC浏览器打开
		    history.go(-1);
		}
	}
</script>
<body>
<!--wrap start-->
<div class="wrap">
    <div class="follwwarning"><img src="<%=request.getContextPath()%>/moibleStyle/img/sorry.jpg" width="100%" height="100%"/></div>
    <div class="signoktext">
             实在抱歉，我们把您给弄丢了，点击下面按钮，再来联系组织吧!
    </div>
    <% 
    String debugFlag=(String)session.getAttribute("DebugFlag"); 
    if (debugFlag==null || "".equals(debugFlag) || "false".equals(debugFlag)){
    %>
    <div style="display:none;">
    <%}else{%>
    <div>
    <%}%>
	<%
		Exception ex = (Exception)request.getAttribute("ex");
		out.println(ex.getMessage());
	    ex.printStackTrace(new java.io.PrintWriter(out));
		ex.printStackTrace();
	%>
	</div>
    <div class="followcode">
        <button class="btn normal" onclick="javascript:clickMe();">点我吧</button>
    </div>
</div>
</body>
</html>
