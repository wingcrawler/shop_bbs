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

			<!--圈子信息-->
			<div id="qms">
				<img src="images/xzQ.gif" class="ico" /> <span class="m1">星座圈(32786)人</span>
				<span class="m2">昨日话题:5558&nbsp;|&nbsp;今日话题:3844</span>
				<!--是圈圈公务圈,则不显示加入和签到按钮-->


				<a class="b1 collect" href="javascript:void(0);"
					data-url="http://m.100bt.com/JoinToCategory.action?ttqId=20"></a> <a
					href="javascript:void(0);"
					data-url="http://m.100bt.com/CheckIn.action?ttqId=20"
					class="b1 sign hid"></a>
			</div>
			<!--话题筛选-->

			<div id="op">
				<!--展开的内容-->
				<div class="h2">
					<c:if test="${not empty section.list }">
						<c:forEach var="item" items="${section.list }">
							<li><a href="sectionindex?sectionId=${item.id }"
								class="qb wb">${item.sectionTitle }</a></li>
						</c:forEach>
					</c:if>
					<br class="c" />
				</div>
				<!--END 展开的内容-->
			</div>
			<!--数据列表,通过AJAX请求回来-->
			<div id="ajaxList"></div>






			<ul class="list">

				<!-- 		<li>
			
			
				<em class="at">全站置顶</em>
			
			
			<a href="zhengwen.html" class="tx">【手机圈圈】体验活动第二波，奥币大奖等你拿！</a>
			
			
			
			
			<br/>
			<div class="tR">
				<span class="qq l">377</span>
				
				
					<a class="sd" href="../Home/1806745.html">小编燕子</a>
				
				&nbsp;|&nbsp;
				<span class="tm">
				
				
					<a href="../Home/15045622.html">147716445</a>
				&nbsp;&nbsp;刚刚
				</span>
			</div>
		</li>
	
		<li>
			
			
				<em class="at">全站置顶</em>
			
			
			<a href="zhengwen.html" class="tx">【圈圈大招募】王者之战，最强工作室选拔赛！</a>
			
			
			
			<br/>
			<div class="tR">
				<span class="qq l">751</span>
				
				
					<a class="sd" href="../Home/1806745.html">小编燕子</a>
				
				&nbsp;|&nbsp;
				<span class="tm">
				
				
					<a href="../Home/27614943.html">岛是海的心&deg;</a>
				&nbsp;&nbsp;刚刚
				</span>
			</div> -->
				<!-- </li> -->

				<li><em class="t">置顶</em> <a href="zhengwen.html" class="tx">[水泽]新人孩子们不要顾着排名,和我一起了解下家史可好</a>



					<br />
					<div class="tR">
						<span class="qq l">406</span> <a class="sd"
							href="../Home/1808520.html">水泽</a> &nbsp;|&nbsp; <span class="tm">


							<a href="../Home/1686708.html">高端大气上档次</a> &nbsp;&nbsp;1小时前
						</span>
					</div></li>

				<li><em class="t">置顶</em> <a href="zhengwen.html" class="tx">【圈务】12月申精&amp;举报贴</a>



					<br />
					<div class="tR">
						<span class="qq l">25</span> <a class="sd"
							href="../Home/1806745.html">小编燕子</a> &nbsp;|&nbsp; <span
							class="tm"> <a href="../Home/2372933.html">smile、夏弥&deg;</a>
							&nbsp;&nbsp;2小时前
						</span>
					</div></li>

				<c:if test="${not empty thread.list }">
					<c:forEach var="item" items="${thread.list }">
						<li><a href="thread?threadId=${item.id }" class="tx">${item.threadTitle }</a>
							<br />
							<div class="tR">
								<span class="qq l">4</span> <a href="#" class="qq l">${item.section_title }</a>
								&nbsp;|&nbsp; <span class="tm">${item.timeAgo }</span>
							</div></li>
					</c:forEach>
				</c:if>

				<li><a href="zhengwen.html" class="tx">十二星座那些事.......</a> <br />
					<div class="tR">
						<span class="qq l">4</span> <a class="sd"
							href="../Home/755355.html">95912180</a> &nbsp;|&nbsp; <span
							class="tm"> <a href="../Home/755355.html">95912180</a>
							&nbsp;&nbsp;刚刚
						</span>
					</div></li>

				<li><a href="zhengwen.html" class="tx">来测测你的动漫形象~~0 0</a> <br />
					<div class="tR">
						<span class="qq l">14</span> <a class="sd"
							href="../Home/29429949.html">迷路、</a> &nbsp;|&nbsp; <span
							class="tm"> <a href="../Home/29429949.html">迷路、</a>
							&nbsp;&nbsp;刚刚
						</span>
					</div></li>

				<li><a href="zhengwen.html" class="tx">【莫子】二次元</a> <br />
					<div class="tR">
						<span class="qq l">24</span> <a class="sd"
							href="../Home/255222.html">嗲妹</a> &nbsp;|&nbsp; <span class="tm">


							<a href="../Home/1692542.html">☪我是阿起</a> &nbsp;&nbsp;刚刚
						</span>
					</div></li>

				<li><a href="zhengwen.html" class="tx">趣味测试：手指测试你心中最要的人？</a> <br />
					<div class="tR">
						<span class="qq l">83</span> <a class="sd"
							href="../Home/3040110.html">小编町子</a> &nbsp;|&nbsp; <span
							class="tm"> <a href="../Home/8294252.html">229132183</a>
							&nbsp;&nbsp;刚刚
						</span>
					</div></li>

				<li><a href="zhengwen.html" class="tx">星座最全的秘密，戳进！！！</a> <br />
					<div class="tR">
						<span class="qq l">39</span> <a class="sd"
							href="../Home/3198599.html">浅唱灬幸福</a> &nbsp;|&nbsp; <span
							class="tm"> <a href="../Home/3198599.html">浅唱灬幸福</a>
							&nbsp;&nbsp;刚刚
						</span>
					</div></li>

				<li><a href="zhengwen.html" class="tx">【原点】周年回忆之人物篇</a> <br />
					<div class="tR">
						<span class="qq l">46</span> <a class="sd"
							href="../Home/295673.html">原 点</a> &nbsp;|&nbsp; <span class="tm">


							<a href="../Home/14852120.html">翔幽</a> &nbsp;&nbsp;刚刚
						</span>
					</div></li>

				<li><a href="zhengwen.html" class="tx">【子白】=转=电梯里的十大禁忌！不能不看！</a>



					<br />
					<div class="tR">
						<span class="qq l">80</span> <a class="sd"
							href="../Home/1999158.html">故人归。</a> &nbsp;|&nbsp; <span
							class="tm"> <a href="../Home/2239121.html">☪我是阿肖</a>
							&nbsp;&nbsp;刚刚
						</span>
					</div></li>

				<li><a href="zhengwen.html" class="tx">【浅浅】一天一种花
						365天你的生日是哪天</a> <em class="jh">精</em> <br />
					<div class="tR">
						<span class="qq l">635</span> <a class="sd"
							href="../Home/34695329.html">夜夜夜夜未央、</a> &nbsp;|&nbsp; <span
							class="tm"> <a href="../Home/118609.html">Strx 高傲-╰＇</a>
							&nbsp;&nbsp;刚刚
						</span>
					</div></li>

				<li><a href="zhengwen.html" class="tx">【阿昱】十二星座的各种性格，超 准！</a> <br />
					<div class="tR">
						<span class="qq l">233</span> <a class="sd"
							href="../Home/12934107.html">阿昱♫阿双</a> &nbsp;|&nbsp; <span
							class="tm"> <a href="../Home/25826503.html">318884320</a>
							&nbsp;&nbsp;刚刚
						</span>
					</div></li>

				<li><a href="zhengwen.html" class="tx">【小小】恐怖童谣，你看懂了吗？</a> <br />
					<div class="tR">
						<span class="qq l">12</span> <a class="sd"
							href="../Home/45991490.html">筱筱滴、小小</a> &nbsp;|&nbsp; <span
							class="tm"> <a href="../Home/45991490.html">筱筱滴、小小</a>
							&nbsp;&nbsp;1分钟前
						</span>
					</div></li>

				<li><a href="zhengwen.html" class="tx">星座控。世界最准的星座排名！截进来！！</a>



					<br />
					<div class="tR">
						<span class="qq l">112</span> <a class="sd"
							href="../Home/1785420.html">艾莎尔&middot;铁塔</a> &nbsp;|&nbsp; <span
							class="tm"> <a href="../Home/1785420.html">椅子</a>
							&nbsp;&nbsp;2分钟前
						</span>
					</div></li>

				<li><a href="zhengwen.html" class="tx">【小染】星座们 匹配的 花</a> <br />
					<div class="tR">
						<span class="qq l">150</span> <a class="sd"
							href="../Home/33342186.html">小染</a> &nbsp;|&nbsp; <span
							class="tm"> <a href="../Home/441692.html">溅溅丶</a>
							&nbsp;&nbsp;2分钟前
						</span>
					</div></li>

				<li><a href="zhengwen.html" class="tx">【木测】你拥有哪种潜在超能力？</a> <em
					class="jh">精</em> <br />
					<div class="tR">
						<span class="qq l">315</span> <a class="sd"
							href="../Home/12141164.html">下一道_彩虹</a> &nbsp;|&nbsp; <span
							class="tm"> <a href="../Home/6168949.html">113227002</a>
							&nbsp;&nbsp;4分钟前
						</span>
					</div></li>

				<li><a href="zhengwen.html" class="tx">【月弦】你认为你的星座是最顶尖的吗？一戳见分晓。</a>


					<em class="jh">精</em> <br />
					<div class="tR">
						<span class="qq l">481</span> <a class="sd"
							href="../Home/1682135.html">Baek Hyun</a> &nbsp;|&nbsp; <span
							class="tm"> <a href="../Home/2377431.html">初々『柔』</a>
							&nbsp;&nbsp;5分钟前
						</span>
					</div></li>

				<li><a href="zhengwen.html" class="tx">十二星座专属的物品</a> <br />
					<div class="tR">
						<span class="qq l">298</span> <a class="sd"
							href="../Home/14320118.html">习惯社、冰蓝</a> &nbsp;|&nbsp; <span
							class="tm"> <a href="../Home/6168949.html">113227002</a>
							&nbsp;&nbsp;6分钟前
						</span>
					</div></li>

			</ul>


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
		<!--END 数据列表-->








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
						- More Templates <a href="http://www.cssmoban.com/"
							target="_blank" title="模板之家">模板之家</a>
					</p>
					</p>
				</div>
				<!-- // container -->
			</div>
			<!-- //footer -->
</body>
</html>