<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>主页</title>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta name="format-detection" content="telephone=no" />
<link rel="apple-touch-startup-image" href="qq/zn/icon.png" />
<link rel="apple-touch-icon" href="qq/zn/icon57.png" />
<link rel="apple-touch-icon" sizes="72x72" href="qq/zn/icon72.png" />
<link rel="apple-touch-icon" sizes="114x114" href="qq/zn/icon114.png" />
<link rel="apple-touch-icon" sizes="144x144" href="qq/zn/icon144.png" />
<link href="qq/zn/style/common.css" rel="Stylesheet" type="text/css" />
<link href="qq/zn/style/index.css" rel="Stylesheet" type="text/css" />
</head>
<body>
	<!--头部-->

	<script type="text/javascript" src="qq/zn/script/zepto.min.js"></script>
	<script type="text/javascript" src="qq/zn/script/common.js"></script>
	<script type="text/javascript" src="qq/zn/script/index.js"></script>


	<div id="dd_more">
		<a href="liebiao.html" class="dd_bt1 dd_bt">全部圈子</a> <a rel="nofollow"
			href="#" class="dd_bt2 dd_bt">我的圈圈</a> <a rel="nofollow" href="#"
			class="dd_bt3 dd_bt">我的好友</a> <a rel="nofollow" href="#"
			class="dd_bt4 dd_bt">退出登陆</a>
	</div>
	<div id="dd_msg">

		<script type="text/javascript" src="qq/zn/script/msg.js"></script>

		<div class="msgLoading"></div>
		<p class="tC">
			<a href="zn/ShowMessage.html">查看更多&gt;</a>
		</p>
	</div>
	<header id="header" class="ch m">

		<a class="logo index_logo" href="index.html" title="百田圈圈"></a>




		<!--未登录-->

		<div class="nolog">
			<a rel="nofollow" href="#">登录</a> | <a rel="nofollow" href="#">注册</a>
		</div>

		<!--已经登录-->

	</header>


	<section id="main" class="m">
		<!--首页广告-->

		<!--首页广告-->
		<div id="ad">
			<div class="adwp">
				<ul id="ad_ul">
					<li><a href="zhengwen.html" class="a" title="你拥有哪种潜在超能力？">
							<img src="upload/articleResource/20131206/1386312728227.jpg" />
					</a></li>
					<li><a href="zhengwen.html" class="a" title="夏目的各种美图，口水~">
							<img src="upload/articleResource/20131206/1386312612817.jpg" />
					</a></li>
					<li><a href="zhengwen.html" class="a" title="新奇东西，绝对有你没看过的！">
							<img src="upload/articleResource/20131206/1386312490951.jpg" />
					</a></li>
				</ul>
			</div>
			<div class="zz"></div>
			<div class="dot">
				<div class="rd r">
					<em class="on"> <!--圆点-->
					</em> <em> <!--圆点-->
					</em> <em> <!--圆点-->
					</em>
				</div>
				<!--只显示15字-->
				<a href="zhengwen.html" class="tx">你拥有哪种潜在超能力？</a>
			</div>
		</div>
		<!--end首页广告-->

		<!--圈子推荐-->
		<div id="tj_t" class="h2">
			<span class="ico ico_qztj txt">一级板块</span> <a href="liebiao.html"
				class="more">更多圈子&gt;</a> <br class="c" />
		</div>
		<ul id="tj_m">
			<c:if test="${not empty section.list }">
				<c:forEach var="item" items="${section.list }">
					<li><a href="bbs/section?sectionId=${item.id }" class="qb wb">${item.sectionTitle }</a></li>
				</c:forEach>
			</c:if>
		</ul>
		<!--END 圈子推荐-->

		<!--精彩推荐-->
		<div class="h2">
			<span class="ico ico_jctj txt">精彩推荐</span> <br class="c" />
		</div>
		<div id="jc_l">
			<div id="ajaxdata">
				<!--10条数据-->
				<ul class="list">

					<li><a href="zhengwen.html" class="tx">【暴漫】有原创的也有各处抱来的。。</a> <br />
						<div class="tR">
							<a href="liebiao.html" class="qq l">雷人圈</a> <span class="rp">494</span>
							&nbsp;|&nbsp; <span class="tm">3分钟前</span>
						</div></li>

					<li><a href="zhengwen.html" class="tx">【造句活动】若你喜欢怪人，其实我很美。</a>



						<br />
						<div class="tR">
							<a href="liebiao.html" class="qq l">小说圈</a> <span class="rp">235</span>
							&nbsp;|&nbsp; <span class="tm">1小时前</span>
						</div></li>

					<li><a href="zhengwen.html" class="tx">【香槟】当你准备放弃的时候，看看这个吧</a>



						<br />
						<div class="tR">
							<a href="liebiao.html" class="qq l">田田圈</a> <span class="rp">102</span>
							&nbsp;|&nbsp; <span class="tm">37分钟前</span>
						</div></li>

					<li><a href="zhengwen.html" class="tx">日本动漫中的相似点～</a> <br />
						<div class="tR">
							<a href="liebiao.html" class="qq l">动漫圈</a> <span class="rp">664</span>
							&nbsp;|&nbsp; <span class="tm">2分钟前</span>
						</div></li>

					<li><a href="zhengwen.html" class="tx">【敏姑】音悦台MV播放量最高前二十名，有你家的吗？</a>



						<br />
						<div class="tR">
							<a href="zn/TTQInfo/23_0_0_0.html" class="qq l">明星圈</a> <span
								class="rp">87</span> &nbsp;|&nbsp; <span class="tm">6小时前</span>
						</div></li>

					<li><a href="zhengwen.html" class="tx">【忧桑】如果你回到家发现窝睡在你家床上，你会说什么</a>



						<br />
						<div class="tR">
							<a href="liebiao.html" class="qq l">奥雅之光粉丝圈</a> <span class="rp">667</span>
							&nbsp;|&nbsp; <span class="tm">5分钟前</span>
						</div></li>

					<li><a href="zhengwen.html" class="tx">【彤彤】明星们的撞脸</a> <br />
						<div class="tR">
							<a href="zn/TTQInfo/23_0_0_0.html" class="qq l">明星圈</a> <span
								class="rp">55</span> &nbsp;|&nbsp; <span class="tm">1小时前</span>
						</div></li>

					<li><a href="zhengwen.html" class="tx">韩国SM旗下艺人的悲惨内幕 【2】</a> <br />
						<div class="tR">
							<a href="liebiao.html" class="qq l">明星圈</a> <span class="rp">42</span>
							&nbsp;|&nbsp; <span class="tm">7小时前</span>
						</div></li>

					<li><a href="zhengwen.html" class="tx">【男神】致吾辈最爱的男神们？有你的他么！</a>



						<br />
						<div class="tR">
							<a href="liebiao.html" class="qq l">动漫圈</a> <span class="rp">286</span>
							&nbsp;|&nbsp; <span class="tm">13分钟前</span>
						</div></li>

					<li><a href="zhengwen.html" class="tx">你的腹黑指度有多少</a> <em
						class="jh">精</em> <br />
						<div class="tR">
							<a href="liebiao.html" class="qq l">星座圈</a> <span class="rp">324</span>
							&nbsp;|&nbsp; <span class="tm">28分钟前</span>
						</div></li>

				</ul>


			</div>
			<!--相关按钮-->
			<div class="info hyh">
				<a href="javascript:void(0);"
					data-url="http://m.100bt.com/zn/LoadIndexTopics.html?limit=10"
					class="changeb wb">换一换</a>
			</div>
		</div>
		<!--END 精彩推荐-->
		<div class="info">
			<a href="#l" class="loginb bb">登录</a> <a href="#" class="regb gb">注册</a>
			<br class="c" />
		</div>
	</section>



	<footer id="footer">
		温馨提示：本信息仅供提供演示方便链接，与原网站无关！ <a href="index.html">首页</a> <a
			href="liebiao.html">文章列表页</a> <a href="zhengwen.html">正文页</a>
	</footer>
	<a href="#header" id="bTop" title="返回顶部"></a>

</body>
</html>