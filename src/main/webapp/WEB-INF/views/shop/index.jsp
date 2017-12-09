<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="include/meta.jsp"></jsp:include>
	<title>${t.t_sqe_mall } -- ${t.t_home }</title>
	<link rel="shortcut icon" type="image/x-icon" href="/favicon.ico">
</head>
<body>
	<jsp:include page="include/header.jsp"></jsp:include>
	
	<!-- banner -->
    <div class="copyrights">Collect from <a href="http://www.cssmoban.com/" >ææºç½ç«æ¨¡æ¿</a></div>
	<div class="banner">
		<!-- banner Slider starts Here -->
		<script src="/frontstyle/js/responsiveslides.min.js"></script>
		<script>
			// You can also use "$(window).load(function() {"
			$(function () {
			  // Slideshow 4
			  $("#slider3").responsiveSlides({
				auto: true,
				pager: true,
				nav: false,
				speed: 500,
				namespace: "callbacks",
				before: function () {
				  $('.events').append("<li>before event fired.</li>");
				},
				after: function () {
				  $('.events').append("<li>after event fired.</li>");
				}
			  });
			});
		</script>
		<!--//End-slider-script -->
		<div  id="top" class="callbacks_container">
			<ul class="rslides" id="slider3">
				<c:forEach var="item" items="${adList }">
					<li>
						<div class="banner-bg" style="background:url(${item.imagePath}) no-repeat 0px 0px;background-size:100% 100%;">
							<div class="container">
								<div class="banner-info">
									<h2></h2>
									<p>${item.description }</p>
									<a href="${item.imageUrl}" target="_blank">${t.t_forward }</a>
								</div>
							</div>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<!-- //banner -->
	<!-- banner-bottom 新闻资讯 -->
	<div class="banner-bottom">
		<div class="container">
			<div class="banner-bottom-grids">
				<div class="products-heading information_head">
					<h3>${t.t_hot_news }</h3>
				</div>
				<div class="lookmore"><a href="/news/list">${t.t_view_more }</a></div>				
				<c:forEach var="item" items="${newsList }">
					<div class="col-md-4 bottom-grid">
						<a href="/news/detail?newsId=${item.id }" target="_blank"><img src="${item.imagePath }" alt="" /></a>
						<div class="bottom-grid-info">
							<a href="/news/detail?newsId=${item.id }" target="_blank" title="${item.newsTitle }"><p style="color: #f45a40;font-family: 'Roboto Condensed', sans-serif;font-size: 26px;width:100%;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">${item.newsTitle }</p></a>
							<p>${item.newsContent }</p>
						</div>
					</div>
				</c:forEach>
				<div class="clearfix"> </div>
				<br>
			</div>
		</div>
	</div>
	<!-- banner-bottom 新闻资讯 -->
	
	<div class="clearfix"></div>
	
	<!-- products -->
	<div class="products">
		<!-- container -->
		<div class="container">
			<div class="products-heading">
				<h3>${t.t_hot_product }</h3>
			</div>
			<div class="lookmore"><a href="/product/list">${t.t_view_more }</a></div>			
			<div class="products-grids">
				<c:forEach var="item" items="${hotProductList }">
					<div class="col-md-3 product-left-grid">	
						<div class="product-grid">
							<div class="product-grid-text">
								<a href="/product/single?productId=${item.id }" target="_blank">
									<img src="${item.imagePath }" alt="" style="height:235px;margin-top:-16px;" />
								</a>
								<div class="products-grid-info">
									<h3><a href="/product/single?productId=${item.id }" target="_blank">${fn:substring(item.productName, 0, 10) }</a></h3>
									<p>${fn:substring(item.productDesc, 0, 15)}</p>
									<div class="price">
										<p>${item.currencies_type}  ${item.productPrice }</p>
									</div>
									<!-- <div class="like">
										<a href="#"><img src="/frontstyle/images/like.png" alt="" /></a>
									</div> -->
									<div class="clearfix"> </div>
								</div>
								<div class="plus">
									<a href="/product/single?productId=${item.id }" target="_blank">
										<img src="/frontstyle/images/plus.png" alt="" style="margin-top:-30px;" />
									</a>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
				<div class="clearfix"> </div>
				<br>
			</div>
		</div>
		<!-- //container -->
	</div>
	<!-- // products -->
	
	<!-- products-bottom -->
	<!-- <div class="products-bottom">
		container
		<div class="container">
			<div class="col-md-3 products-bottom-left">
				<h3>Biruang <span>Free PSD</span></h3>
				<p>NEQUE PORRO  
					ERIKE SUPERUS DA
					DOLOREM IPSUM
					GOOD LUCK
				</p>
				<div class="social-icons">
					<ul>
						<li><a href="#" class="facebook"></a></li>
						<li><a href="#" class="twitter"></a></li>
						<li><a href="#" class="chrome"></a></li>
						<li><a href="#" class="vimeo"></a></li>
						<li><a href="#" class="rss"></a></li>
					</ul>
				</div>
			</div>
			<div class="col-md-5">
				<div class="products-bottom-middle">
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam dictum lectus 
						sit amet varius pulvinar. Proin vitae dui tincidunt nibh facilisis pellentesque. 
						Fusce tortor turpis, facilisis ut condimentum eu, sagittis at est.

						<span>Praesent egestas posuere urna a egestas. Maecenas facilisis orci vitae ante 
						tempor accumsan. Aenean aliquam justo ac sagittis vehicula. Nam mattis 
						pretium odio sit amet vulputate.
						</span>
						
						Quisque non lobortis orci. Morbi augue mauris, ultrices at fermentum ac, 
						consequat vitae magna. Pellentesque non cursus mi, eu cursus nunc. Nullam
						 et odio tristique, volutpat urna vitae, dignissim orci. Fusce eu nulla urna.
						This template was created by Erik Padamans.
					</p>
				</div>
			</div>
			<div class="col-md-4 map">
				
				<div class="location">
					<p>Stacijas laukums 2, Riga</p>
				</div>
			</div>
			<div class="clearfix"> </div>
		</div>
		//container
	</div> -->
	<!-- //products-bottom -->
	
	<!-- sign-up -->
	<!--<div class="sign-up">
		
		<div class="container">
			<div class="sign-up-left">
				<p><span>Sign up</span> for exclusive sales and product news</p>
			</div>
			<div class="sign-up-right">
				<form>
					<input type="text" value="eony321v@gmail.com" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = 'eony321v@gmail.com';}" required="">
					<input type="submit" value="sign up">
				</form>
			</div>
			<div class="clearfix"> </div>
		</div>
		
	</div> -->
	<!-- //sign-up -->
	
	<!-- footer -->
		<jsp:include page="include/footer.jsp"></jsp:include>
	
	
	<!-- //footer -->
</body>
</html>
