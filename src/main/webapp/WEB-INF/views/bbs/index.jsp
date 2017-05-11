<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Biruang Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript">
	
	
	
	
	
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 






</script>
<!-- bootstarp-css -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<!--// bootstarp-css -->
<!-- css -->
<link rel="stylesheet" href="css/common.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
<!--// css -->
<script src="js/jquery.min.js"></script>
<!--fonts-->
<!--/fonts-->
<!-- dropdown -->
<script src="js/jquery.easydropdown.js"></script>
<link href="css/nav.css" rel="stylesheet" type="text/css" media="all" />
<script src="js/scripts.js" type="text/javascript"></script>
<!--js-->
<!--/js-->
<script src="js/easyResponsiveTabs.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#horizontalTab').easyResponsiveTabs({
			type : 'default', //Types: default, vertical, accordion           
			width : 'auto', //auto or any width like 600px
			fit : true
		// 100% fit in a container
		});
	});
</script>
<!-- start-smoth-scrolling -->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event) {
			event.preventDefault();
			$('html,body').animate({
				scrollTop : $(this.hash).offset().top
			}, 1000);
		});
	});
</script>
</head>
<body>
	<!-- header -->
	<div class="header">
		<!-- container -->
		<div class="container">
			<!-- header-top -->
			<div class="header-top">
				<div class="header-logo">
					<a href="index.html"><img src="images/logo.png" alt="" /></a>
				</div>
				<div class="header-right">
					<ul>
						<li class="phone">+371 282 20 760</li>
						<li class="mail"><a href="mailto:example@mail.com">eony321v@gmail.com</a></li>
						<li class="checkout"><input type="text" value="Search..."
							onFocus="this.value = '';"
							onBlur="if (this.value == '') {this.value = 'Search...';}"
							required="">
							<div class="shopping">
								<h5>Your Shopping Cart is empty.</h5>
								<p>Give it purpose—fill it with books, movies, mobiles,
									cameras, toys and fashion jewellery.</p>
							</div></li>
					</ul>
				</div>
				<div class="clearfix"></div>
			</div>
			<!-- //header-top -->
			<div class="top-nav">
				<span class="menu"><img src="images/menu.png" alt=""></span>
				<ul class="nav">
					<li class="dropdown1"><a href="index.html">首页</a>
						<ul class="dropdown2">
							<li><a href="single.html">lorem</a></li>
							<li><a href="single.html">dorem sia</a></li>
							<li><a href="single.html">erik</a></li>
							<li><a href="single.html">ipsum padamans</a></li>
							<li><a href="single.html">behance</a></li>
						</ul></li>
					<li class="dropdown1"><a href="men.html">产品介绍</a>
						<ul class="dropdown2">
							<li><a href="men.html">Clothing</a></li>
							<li><a href="men.html">Footwear</a></li>
							<li><a href="men.html">Watches</a></li>
							<li><a href="men.html">Accessories</a></li>
						</ul></li>
					<li class="dropdown1"><a href="women.html">新闻资讯</a>
						<ul class="dropdown2">
							<li><a href="women.html">Ethnic Wear</a></li>
							<li><a href="women.html">Western Wear</a></li>
							<li><a href="women.html">All Jewellery</a></li>
							<li><a href="women.html">Beauty & Wellness</a></li>
						</ul></li>
					<li class="dropdown1"><a href="women.html" class="on">论坛</a>
						<ul class="dropdown2">
							<li><a href="women.html">Clothing</a></li>
							<li><a href="women.html">Footwear</a></li>
							<li><a href="women.html">Accessories</a></li>
						</ul></li>
					<li class="dropdown1"><a href="men.html">个人中心</a>
						<ul class="dropdown2">
							<li><a href="men.html">Clothing</a></li>
							<li><a href="men.html">Footwear</a></li>
							<li><a href="men.html">Watches</a></li>
							<li><a href="men.html">Accessories</a></li>
						</ul></li>
				</ul>
			</div>
			<div class="clearfix"></div>
			<!-- script-for-menu -->
			<script>
				$("span.menu").click(function() {
					$(" ul.nav").slideToggle("slow", function() {
					});
				});
			</script>
		</div>
		<!-- //container -->
	</div>
	<!-- //header -->
	<!-- 新增的圈子和精彩 -->
	<div class="tj_t_q">
		<div class="container">
			<!--圈子推荐-->
			<div id="tj_t" class="h2">
				<span class="ico ico_qztj txt">圈子推荐</span> <a href="liebiao.html"
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
						<c:if test="${not empty thread.list }">
							<c:forEach var="item" items="${thread.list }">
								<li><a href="thread?threadId=${item.id }" class="tx">${item.threadTitle }</a>
									<br />
									<div class="tR">
										<a href="liebiao.html" class="qq l">${item.section_title }</a>
										<span class="rp">494</span> &nbsp;|&nbsp; <span class="tm">${item.timeAgo }</span>
									</div></li>
							</c:forEach>
						</c:if>
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
						<li><a href="zhengwen.html" class="tx">韩国SM旗下艺人的悲惨内幕 【2】</a>



							<br />
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
		</div>
	</div>
	<!-- 新增的圈子和精彩end -->
	<!-- footer -->
	<div class="footer">
		<!-- container -->
		<div class="container">
			<div class="col-md-3 stores-grid">
				<div class="stores">
					<h3>Our Stores</h3>
					<ul>
						<li>Feel free to visit our stores or contact us.</li>
						<li>1401 South Grand Avenue</li>
						<li>Los Angeles, CA 90015</li>
						<li>(213) 748-2411</li>

						<span>
							<li class="drive">100 Fairview Drive</li>
							<li>Franklin, VA 23851</li>
							<li>(757) 569-6100</li>
						</span>
					</ul>
				</div>
				<div class="social-icons white-icons">
					<ul>
						<li><a href="#" class="facebook"></a></li>
						<li><a href="#" class="twitter"></a></li>
						<li><a href="#" class="chrome"></a></li>
						<li><a href="#" class="vimeo"></a></li>
						<li><a href="#" class="rss"></a></li>
				</div>
			</div>
			<div class="col-md-3 blog">
				<h3>Blog posts</h3>
				<a href="#">Justin Bieber confirmed that he is gay.</a>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
					Donec sed auctor elit.</p>
				<a href="#">New sexy sport clothes are here!</a>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
					Donec sed auctor elit.</p>
				<a href="#">Summer sales are coming!</a>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
					Donec sed auctor elit.</p>
			</div>
			<div class="col-md-3 support">
				<h3>Support</h3>
				<div class="support-grids">
					<div class="support-left">
						<ul>
							<li><a href="#">Terms & Conditions</a></li>
							<li><a href="#">FAQ</a></li>
							<li><a href="#">Payment</a></li>
							<li><a href="#">Refunds</a></li>
							<li><a href="#">Track Order</a></li>
							<li><a href="#">Services</a></li>
							<li><a href="#">Privacy & Security</a></li>
							<li><a href="#">Careers</a></li>
							<li><a href="#">Press</a></li>
							<li><a href="#">Corporate Information</a></li>
						</ul>
					</div>
					<div class="support-left support-right">
						<ul>
							<li><a href="#">Sizing</a></li>
							<li><a href="#">Ordering</a></li>
							<li><a href="#">Shipping</a></li>
							<li><a href="#">Return Policy</a></li>
							<li><a href="#">Affiliates</a></li>
							<li><a href="#">Find A Store </a></li>
							<li><a href="#">Site Map</a></li>
							<li><a href="#">Sign Up & Save</a></li>
						</ul>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
			<div class="col-md-3 contact">
				<h3>Contact us</h3>
				<form>
					<input type="text" value="your e-mail..."
						onFocus="this.value = '';"
						onBlur="if (this.value == '') {this.value = 'your e-mail...';}"
						required="">
					<textarea value="your text...:"
						onFocus="if(this.value == 'your text...') this.value='';"
						onBlur="if(this.value == '') this.value='your text...';">your text...</textarea>
					<input type="submit" value="Send MESSAGE">
				</form>
			</div>
			<div class="clearfix"></div>
			<div class="copyright">
				<p>
					Copyright &copy; 2015.Company name All rights reserved.<a
						target="_blank" href=" ">&#x7F51;&#x9875;&#x6A21;&#x677F;</a> -
					More Templates <a href=" " target="_blank" title="">BJ</a>
				</p>
				</p>
			</div>
			<!-- // container -->
		</div>
		<!-- //footer -->
</body>
</html>