<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

<jsp:include page="include/meta.jsp"></jsp:include>
<title>${t.m_bbs_list }</title>
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
<link rel="stylesheet" href="css/topic.css" type="text/css" media="all" />
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
		$('.rbt').click(function(e) {
			$(this).parent(".tItem").find(".hid").css('display', 'block')
		});
		$('.cl').click(function(e) {
			$(this).parents(".hid").css('display', 'none')
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
	<!-- 新增的樓層-->
	<div class="container">
		<section class="m" id="content"> <!--楼层-->
		<div id="ajaxList">
			<!--楼层-->
			<c:if test="${not empty user }">
				<div class="tItem cnt" quotedCommentId="45470309" id="45470309">
					<a href="#" class="name">${user.username }</a> <em class="isLz"></em>
					<div class="tmain">
						<p>
							<img src="${user.userImage }" />
						</p>
			</c:if>
			<!--1楼的顶-->
			<div class="dwp">
				<a href="#ajax#" class="dbt bb"><span class="num">0</span>顶</a>
			</div>
		</div>
		<a href="#bReply" class="rbt wb first">回复</a> <span class="msg">1楼2013-12-08
			15:30</span> <br class="c" />
	</div>
	<!--楼层-->
	<div class="tItem cnt" quotedCommentId="45470767" id="45470767">
		<a href="../Home/755355.html" class="name">95912180</a> <span
			class="lv">LV6</span> <em class="isLz"></em>
		<div class="tmain">
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">【发火最狂暴的星座排名】</p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">冠军(天蝎座）、亚军(狮子座）、季军(白羊座）、第4名(魔羯座）、第5名(金牛座）、第6名(射手座）、第7名(双鱼座）、第8名(水瓶座）、第9名(处女座）、第10名(巨蟹座）、第11名(天秤座）、第12名(双子座）</p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;"></p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">~(≥◇≤)~</p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">【天秤座的几桩“最”】</p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">最可爱星座——天秤座；最聪明星座——天秤座；最善良型星座——天秤座；最好玩星座——天秤座；最严谨星座——天秤座；最无私星座——天秤座；最宅星座——天秤座；最勇敢星座——天秤座；最有才星座——天秤座；最温柔星座——天秤座。</p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;"></p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">~(≥◇≤)~</p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;"></p>

		</div>
		<a href="javascript:void(0);" class="rbt wb" notClick="true">回复(0)</a>
		<span class="msg">2楼2013-12-08 15:35</span> <br class="c" />
		<!--楼中楼内容-->
		<div class="lzl hid">
			<em class="ico"></em>
			<!--刷新和收起-->
			<div class="op">
				<a href="javascript:;" class="cl">收起回复</a> <a
					href="javascript:void(0);"
					data-url="http://m.100bt.com/zn/LoadReply.html?topicId=10899981&commentId=45470767"
					class="rf">刷新</a> <br class="c" />
			</div>
			<!--楼中楼数据-->
			<ul class="llist">
				<li>唐伯虎回顧秋香：</li>
				<li>這是什麽a唐伯虎回顧秋香這是什麽a唐伯虎回顧秋香這是什麽a唐伯虎回顧秋香這是什麽a唐伯虎回顧秋香這是什麽a唐伯虎回顧秋香這是什麽a唐伯虎回顧秋香</li>
			</ul>
		</div>
		<!--END 楼中楼内容-->
	</div>
	<!--楼层-->
	<div class="tItem cnt" quotedCommentId="45470737" id="45470737">
		<a href="../Home/755355.html" class="name">95912180</a> <span
			class="lv">LV6</span> <em class="isLz"></em>

		<div class="tmain">
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">【十二星座女生叛逆排行】</p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">冠军（天蝎座）、亚军（双子座）、季军（水瓶座）、第四名（射手座）、第五名（天秤座）、第六名（狮子座）、第七名（白羊座）、第八名（双鱼座）、第九名（处女座）、第十名（魔羯座）、第十一名（巨蟹座）、第十二名（金牛座）</p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;"></p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">~(≥◇≤)~</p>
			<p></p>

		</div>


		<a href="javascript:void(0);" class="rbt wb" notClick="true">回复(0)</a>

		<span class="msg">3楼2013-12-08 15:35</span> <br class="c" />

		<!--楼中楼内容-->
		<div class="lzl hid">
			<em class="ico"></em>
			<!--刷新和收起-->
			<div class="op">
				<a href="javascript:;" class="cl">收起回复</a> <a
					href="javascript:void(0);"
					data-url="http://m.100bt.com/zn/LoadReply.html?topicId=10899981&commentId=45470737"
					class="rf">刷新</a> <br class="c" />
			</div>
			<!--楼中楼数据-->
			<ul class="llist">
				<li>唐伯虎回顧秋香：</li>
				<li>這是什麽a唐伯虎回顧秋香這是什麽a唐伯虎回顧秋香這是什麽a唐伯虎回顧秋香這是什麽a唐伯虎回顧秋香這是什麽a唐伯虎回顧秋香這是什麽a唐伯虎回顧秋香</li>
			</ul>
		</div>
		<!--END 楼中楼内容-->

	</div>
	<!--楼层-->
	<div class="tItem cnt" quotedCommentId="45470874" id="45470874">


		<a href="../Home/755355.html" class="name">95912180</a> <span
			class="lv">LV6</span> <em class="isLz"></em>

		<div class="tmain">
			<p></p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">【12星座的情人节】</p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">★感情甜蜜榜—NO.1魔羯座</p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">NO.2处女座</p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">NO.3射手座</p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">★意外惊喜榜—NO.1白羊座</p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">NO.2天秤座</p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">NO.3天蝎座</p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">★迷离暧昧榜—NO.1双鱼座</p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">NO.2巨蟹座</p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">NO.3金牛座</p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">★心事纠结榜—NO.1狮子座</p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">NO.2水瓶座</p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">NO.3双子座</p>

		</div>


		<a href="javascript:void(0);" class="rbt wb" notClick="true">回复(0)</a>

		<span class="msg">4楼2013-12-08 15:35</span> <br class="c" />

		<!--楼中楼内容-->
		<div class="lzl hid">
			<em class="ico"></em>
			<!--刷新和收起-->
			<div class="op">
				<a href="javascript:;" class="cl">收起回复</a> <a
					href="javascript:void(0);"
					data-url="http://m.100bt.com/zn/LoadReply.html?topicId=10899981&commentId=45470874"
					class="rf">刷新</a> <br class="c" />
			</div>
			<!--楼中楼数据-->
			<ul class="llist">
				<li>唐伯虎回顧秋香：</li>
				<li>這是什麽a唐伯虎回顧秋香這是什麽a唐伯虎回顧秋香這是什麽a唐伯虎回顧秋香這是什麽a唐伯虎回顧秋香這是什麽a唐伯虎回顧秋香這是什麽a唐伯虎回顧秋香</li>
			</ul>
		</div>
		<!--END 楼中楼内容-->

	</div>
	<!--楼层-->
	<div class="tItem cnt" quotedCommentId="45470923" id="45470923">


		<a href="../Home/755355.html" class="name">95912180</a> <span
			class="lv">LV6</span> <em class="isLz"></em>

		<div class="tmain">
			<p></p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">【12星座最大的缺点是什么?】</p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">白羊座-心太软</p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">金牛座-不太现实</p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">双子座-不够坚强</p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">巨蟹座-脾气不太好</p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">狮子座-容易相信他</p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">处女座-太依赖别人</p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">天秤座-心肠太好</p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">天蝎座-太追求完美</p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">射手座-有点任性</p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">摩羯座-性格刚烈</p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">水瓶座-对自己不够好</p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">双鱼座-太过于追求时尚</p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;"></p>
			<p
				style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">~(≥◇≤)~</p>

		</div>
		<a href="javascript:void(0);" class="rbt wb" notClick="true">回复(0)</a>

		<span class="msg">5楼2013-12-08 15:35</span> <br class="c" />

		<!--楼中楼内容-->
		<div class="lzl hid">
			<em class="ico"></em>
			<!--刷新和收起-->
			<div class="op">
				<a href="javascript:;" class="cl">收起回复</a> <a
					href="javascript:void(0);"
					data-url="http://m.100bt.com/zn/LoadReply.html?topicId=10899981&commentId=45470923"
					class="rf">刷新</a> <br class="c" />
			</div>
			<!--楼中楼数据-->
			<ul class="llist">
				<li>唐伯虎回顧秋香：</li>
				<li>這是什麽a唐伯虎回顧秋香這是什麽a唐伯虎回顧秋香這是什麽a唐伯虎回顧秋香這是什麽a唐伯虎回顧秋香這是什麽a唐伯虎回顧秋香這是什麽a唐伯虎回顧秋香</li>
			</ul>
		</div>
		<!--END 楼中楼内容-->

	</div>

	<!--分页-->
	</div>
	<div class="page-toolbar">
		<div class="ajax-page">
			<ul class="page-pagination">
				<li class="first-page"><span>首页</span></li>
				<li class="previous-page"><span>上一页</span></li>
				<li class="active"><span>1</span></li>
				<li><span>2</span></li>
				<li><span>3</span></li>
				<li><span>4</span></li>
				<li><span>5</span></li>
				<li><span>6</span></li>
				<li><span>7</span></li>
				<li><span>8</span></li>
				<li><span>9</span></li>
				<li><span>10</span></li>
				<li class="omit"><span><i>...</i>15</span></li>
				<li class="skip"><input type="text" value="1">/ <span>15</span>页
				</li>
				<li class="next-page"><span>下一页</span></li>
			</ul>
		</div>
	</div>

	</section>
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
						target="_blank" href="http://www.cssmoban.com/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a>
					- More Templates <a href="http://www.cssmoban.com/" target="_blank"
						title="模板之家">模板之家</a>
				</p>
				</p>
			</div>
			<!-- // container -->
		</div>
		<!-- //footer -->
</body>
</html>