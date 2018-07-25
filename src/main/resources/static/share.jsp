<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<meta name="viewport" content="width=750 ,  user-scalable=no">
		<link rel="stylesheet" type="text/css" href="/css/share.css" />
		<link rel="stylesheet" type="text/css" href="/css/loding.css" />
		<script src="/js/jquery-3.3.1.min.js"></script>
	</head>

	<body>
		<div class="loding ">
			<div class="spinner ">
				<div class="rect1"></div>
				<div class="rect2"></div>
				<div class="rect3"></div>
				<div class="rect4"></div>
				<div class="rect5"></div>
			</div>
		</div>
		<div id="content">
			<div class="title">
				<!--<button id="zc">注册</button>-->
				<div class="logo">
					<img src="img/RectangleCopy.png" />
				</div>
				<div class="describe">
					<p>有信钱包</p>
					<p>让有信用的人，过的更好！</p>
				</div>
				<button onclick="submitFn()" id="submitFn ">立即打开</button>
			</div>
			<div class="html">
				<h1>这是标题</h1>
				<div class="explain">
					<h2 class="anthor"><span class="name">大卡司</span><span class="date">2018-01-01 12:00:00</span></h2>
					<p class="source">123</p>
				</div>
				<div class="innerHtml">
					这是内容
				</div>
				<p class="anthor">作者：<span class="name">大卡司</span></p>
			</div>
		</div>
	</body>
	<script src="/js/share.js"></script>
	<script type="application/javascript">
		function submitFn() {
			//判断浏览器
			var u = navigator.userAgent;
			if(/MicroMessenger/gi.test(u)) {
				// 引导用户在浏览器中打开
				alert('请在浏览器中打开');
				return;
			}
			var d = new Date();
			var t0 = d.getTime();
			if(u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {
				//Android
				if(openApp('youxin://jp.app/openwith')) {
					openApp('youxin://jp.app/openwith');
					return;
				} else {
					//由于打开需要1～2秒，利用这个时间差来处理－－打开app后，返回h5页面会出现页面变成app下载页面，影响用户体验
					var delay = setInterval(function() {
						var d = new Date();
						var t1 = d.getTime();
						if(t1 - t0 < 3000 && t1 - t0 > 2000) {
							alert('请下载安卓APP');
							window.location.href = "";
						}
						if(t1 - t0 >= 3000) {
							clearInterval(delay);
						}
					}, 1000);
				}
			} else if(u.indexOf('iPhone') > -1) {
				//IOS
				if(openApp('ios--scheme', 'iPhone')) {
					openApp('ios--scheme', 'iPhone');
				} else {
					// var delay = setInterval(function(){
					//     var d = new Date();
					//     var t1 = d.getTime();
					//     if( t1-t0<3000 && t1-t0>2000){+
					alert('请下载IOS APP');
					window.location.href = "https://itunes.apple.com/us/app/e-smart-home/id%@?l=zh&ls=1&mt=8";
					//     }
					//     if(t1-t0>=3000){
					//         clearInterval(delay);
					//     }
					// },1000);
				}
			} else {
				alert("请在手机中打开此网页");
			}
		};

		function openApp(src, type) {
			// 通过iframe的方式试图打开APP，如果能正常打开，会直接切换到APP，并自动阻止a标签的默认行为
			// 否则打开a标签的href链接
			if(type == 'iPhone') {
				window.location.href = "MBK.com.CreditPurse.CreditPurse://";
				var ifr = document.createElement('iframe');
				ifr.src = src;
				ifr.style.display = 'none';
				document.body.appendChild(ifr);
				window.setTimeout(function() {
					document.body.removeChild(ifr);
				}, 2000);
			} else {
				var ifr = document.createElement('iframe');
				ifr.src = src;
				ifr.style.display = 'none';
				document.body.appendChild(ifr);
				window.setTimeout(function() {
					document.body.removeChild(ifr);
				}, 2000);
			}
		}
	</script>

</html>
<style type="text/css">

</style>