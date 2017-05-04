<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="include/meta.jsp"></jsp:include>
	<title>${product.productName }</title>

	<link rel="stylesheet" href="/frontstyle/css/etalage.css">
	<script src="/frontstyle/js/jquery.etalage.min.js"></script>
	<script>
		jQuery(document).ready(function($){
			$('#etalage').etalage({
				thumb_image_width: 300,
				thumb_image_height: 400,
				source_image_width: 800,
				source_image_height: 1000,
				show_hint: true,
				click_callback: function(image_anchor, instance_id){
					alert('Callback example:\nYou clicked on an image with the anchor: "'+image_anchor+'"\n(in Etalage instance: "'+instance_id+'")');
				}
			});
		});
	</script>
	
</head>
<body>
	<jsp:include page="include/header.jsp"></jsp:include>
	
	<!-- single -->
	<div class="single">
		<!-- container -->
		<div class="container">
			<div class="single-grids">
				<div class="col-md-9">
					<div class="single-left-left">
						<ul id="etalage" class="etalage" style="display: block; width: 300px; height: 533px;">
							<c:forEach var="item" items="${imgList }" varStatus="index">
								<c:if test="${index.count==1 }">
									<li class="etalage_thumb thumb_${index.count } etalage_thumb_active" style="display: list-item; opacity: 1; background-image: none;">
										<img class="etalage_thumb_image" src="${item.imagePath }" style="display: inline; width: 300px; height: 400px; opacity: 1;">
										<img class="etalage_source_image" src="${item.imagePath }" title="">
									</li>
								</c:if>
								<c:if test="${index.count>1 }">
									<li class="etalage_thumb thumb_${index.count } etalage_thumb_active" style="display: none; opacity: 0; background-image: none;">
										<img class="etalage_thumb_image" src="${item.imagePath }" style="display: inline; width: 300px; height: 400px; opacity: 1;">
										<img class="etalage_source_image" src="${item.imagePath }" title="">
									</li>
								</c:if>
							</c:forEach>
						</ul>
						<div class="clearfix"></div>		
					</div>
					<div class="single-left-right">
						<div class="single-left-info">
							<h3>${product.productName }</h3>
							<a href="#product-details" class="view">${t.t_view_detail }</a>
							<p>$ ${product.productPrice }
								<!-- <a href="#" class="view">CLICK FOR OFFER</a> -->
							</p>
						</div>
						<div class="select-size">
							<p>${t.t_select_size }</p>
								<ul>
									 <li><a href="#">S</a></li>
									 <li><a href="#">M</a></li>
									 <li><a href="#">L</a></li>
									 <li><a href="#">XL</a></li>
								</ul>
							<div class="buy-now">
								<a href="#">${t.t_buynow }</a>
							</div>
							<div class="wishlist">
								<a class="play-icon popup-with-zoom-anim" href="#small-dialog2">${t.t_add_wishlist }</a>
								<!-- <div id="small-dialog2" class="mfp-hide">
									<h3>Create Account</h3> 
									<div class="social-sits">
										<ul>
											<li><a class="fb" href="#">Connect with Facebook</a></li>
											<li><a class="fb google" href="#">Connect with Google</a></li>
										</ul>
									</div>
									<div class="signup">
										<form>
											<input type="text" class="email" placeholder="Email" required="required" pattern="([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?" />
											<input type="password" placeholder="Password" required="required" pattern=".{6,}" title="Minimum 6 characters required" autocomplete="off" />
											<input type="text" class="email" placeholder="Mobile Number" maxlength="10" pattern="[1-9]{1}\d{9}" title="Enter a valid mobile number" />
											<input type="submit"  value="Sign In"/>
										</form>
									</div>
									<div class="clearfix"> </div>
								</div>	
								<script>
									$(document).ready(function() {
										$('.popup-with-zoom-anim').magnificPopup({
											type: 'inline',
											fixedContentPos: false,
											fixedBgPos: true,
											overflowY: 'auto',
											closeBtnInside: true,
											preloader: false,
											midClick: true,
											removalDelay: 300,
											mainClass: 'my-mfp-zoom-in'
										});
									});
								</script>	 -->
							</div>
							<div class="clearfix"> </div>
							<!-- <div class="free">
								<p>20 day returns Free Delivery *</p>
							</div> -->
							<!-- <div class="delivery">
								<a class="play-icon popup-with-zoom-anim" href="#small-dialog">Check delivery options</a>
								<div id="small-dialog" class="mfp-hide">
									<h3>DELIVERY TIME & COD AVAILABILITY</h3>
									<div class="social-sits">
										<p>Please enter your PIN Code to check delivery time & Cash On Delivery availability</p>
									</div>
									<div class="signup">
											<form>
												<input type="text" class="email" placeholder="Pin" maxlength="6" required="required" pattern="[1-9]{1}\d{5}"/>
												<input type="submit"  value="Submit"/>
											</form>
									</div>
									<div class="clearfix"> </div>
								</div>
							</div> -->
						</div>
					</div>
					<div class="clearfix"> </div>
					
					<div class="product-details" id="product-details" name="product-details">
						<h3>${t.t_product_detail }</h3>
						<p>${product.productDescripton }</p>
					</div>
					
					<div class="related">
						<h3>${t.t_related_product }</h3>
						<div class="related-grids">
							<div class="related-grid">
								<div class="col-md-9 related-left">
									<div class="col-md-3 related-left-left">
										<img src="/frontstyle/images/c1.jpg" alt="" />
									</div>
									<div class="col-md-9 related-left-right">
										<h5>Vestibulum</h5>
										<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras a est at leo dictum 
											pharetra vel sit amet tellus.
										</p>
									</div>
									<div class="clearfix"> </div>
								</div>
								<div class="col-md-3 related-right">
									<p>$ 19</p>
									<a href="#">Add to cart</a>
								</div>
								<div class="clearfix"> </div>
							</div>
							<div class="related-grid">
								<div class="col-md-9 related-left">
									<div class="col-md-3 related-left-left">
										<img src="/frontstyle/images/c2.jpg" alt="" />
									</div>
									<div class="col-md-9 related-left-right">
										<h5>Vestibulum</h5>
										<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras a est at leo dictum 
											pharetra vel sit amet tellus.
										</p>
									</div>
									<div class="clearfix"> </div>
								</div>
								<div class="col-md-3 related-right">
									<p>$ 19</p>
									<a href="#">Add to cart</a>
								</div>
								<div class="clearfix"> </div>
							</div>
							<div class="related-grid">
								<div class="col-md-9 related-left">
									<div class="col-md-3 related-left-left">
										<img src="/frontstyle/images/c3.jpg" alt="" />
									</div>
									<div class="col-md-9 related-left-right">
										<h5>Vestibulum</h5>
										<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras a est at leo dictum 
											pharetra vel sit amet tellus.
										</p>
									</div>
									<div class="clearfix"> </div>
								</div>
								<div class="col-md-3 related-right">
									<p>$ 19</p>
									<a href="#">Add to cart</a>
								</div>
								<div class="clearfix"> </div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-md-3 side-bar">
					<div class="categories">
						<h3>CATEGORIES</h3>
						<ul>
							<li><a href="#">accessories</a></li>
							<li><a href="#">basics</a></li>
							<li><a href="#">jackets</a></li>
							<li><a href="#">jeans</a></li>
							<li><a href="#">knits</a></li>
							<li><a href="#">overalls</a></li>
							<li><a href="#">over coats</a></li>
							<li><a href="#">shoes</a></li>
							<li><a href="#">sweatshirts</a></li>
							<li><a href="#">trousers</a></li>
							<li><a href="#"><del>tops</del></a></li>
							<li><a href="#">watersuits</a></li>
						</ul>
					</div>
				</div>
				<div class="clearfix"> </div>
			
			</div>
		</div>
		<!-- //container -->
	</div>
	<!-- //single -->
	
	<jsp:include page="include/footer.jsp"></jsp:include>
</body>
</html>