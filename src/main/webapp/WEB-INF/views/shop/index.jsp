<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="include/meta.jsp"></jsp:include>
	<title>${t.t_sqe_mall } -- ${t.t_home }</title>
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
						<div class="banner-bg" style="background:url(${item.imagePath}) no-repeat 0px 0px;background-size:cover;">
							<div class="container">
								<div class="banner-info">
									<h2></h2>
									<p>${item.description }</p>
									<a href="${item.imageUrl}" target="_blank">SHOP BRASIL</a>
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
				<c:forEach var="item" items="${newsList }">
					<div class="col-md-4 bottom-grid">
						<a href="/news/detail?newsId=${item.id }" target="_blank"><img src="${item.imagePath }" alt="" /></a>
						<div class="bottom-grid-info">
							<a href="/news/detail?newsId=${item.id }" target="_blank" title="${item.newsTitle }">${item.newsShortTitle }</a>
							<p>${item.newsContent }</p>
						</div>
					</div>
				</c:forEach>
				<div class="clearfix"> </div>
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
			<div class="products-grids">
				<c:forEach var="item" items="${hotProductList }">
					<div class="col-md-3 product-left-grid">	
						<div class="product-grid">
							<div class="product-grid-text">
								<a href="/product/single?productId=${item.id }" target="_blank"><img src="${item.imagePath }" alt="" /></a>
								<div class="products-grid-info">
									<h3>${item.productName }</h3>
									<p>${item.productDesc }</p>
									<div class="price">
										<p>$ ${item.productPrice }</p>
									</div>
									<!-- <div class="like">
										<a href="#"><img src="/frontstyle/images/like.png" alt="" /></a>
									</div> -->
									<div class="clearfix"> </div>
								</div>
								<div class="plus">
									<a href="/single?productId=${item.id }" target="_blank"><img src="/frontstyle/images/plus.png" alt="" /></a>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
				<div class="clearfix"> </div>
			</div>
		</div>
		<!-- //container -->
	</div>
	<!-- // products -->
	
	<!-- products-bottom -->
	<div class="products-bottom">
		<!-- container -->
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
		<!-- //container-->
	</div>
	<!-- //products-bottom -->
	
	<!-- sign-up -->
	<div class="sign-up">
		<!-- container -->
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
		<!-- //container -->
	</div>
	<!-- //sign-up -->
	
	<jsp:include page="include/footer.jsp"></jsp:include>
</body>
</html>