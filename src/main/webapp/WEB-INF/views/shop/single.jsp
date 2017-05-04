<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="include/meta.jsp"></jsp:include>
	<title>Single</title>

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
							<li class="etalage_thumb thumb_1" style="display: none; opacity: 0; background-image: none;">
								<a href="optionallink.html">
									<img class="etalage_thumb_image" src="/frontstyle/images/d1.jpg" style="display: inline; width: 300px; height: 400px; opacity: 1;">
									<img class="etalage_source_image" src="/frontstyle/images/d1.jpg" title="">
								</a>
							</li>
							<li class="etalage_thumb thumb_2" style="display: none; opacity: 0; background-image: none;">
								<img class="etalage_thumb_image" src="/frontstyle/images/d2.jpg" style="display: inline; width: 300px; height: 400px; opacity: 1;">
								<img class="etalage_source_image" src="/frontstyle/images/d2.jpg" title="">
							</li>
							<li class="etalage_thumb thumb_3" style="display: none; opacity: 0; background-image: none;">
								<img class="etalage_thumb_image" src="/frontstyle/images/d3.jpg" style="display: inline; width: 300px; height: 400px; opacity: 1;">
								<img class="etalage_source_image" src="/frontstyle/images/d3.jpg">
							</li>
						    <li class="etalage_thumb thumb_4 etalage_thumb_active" style="display: list-item; opacity: 1; background-image: none;">
								<img class="etalage_thumb_image" src="/frontstyle/images/d4.jpg" style="display: inline; width: 300px; height: 400px; opacity: 1;">
								<img class="etalage_source_image" src="/frontstyle/images/d4.jpg">
							</li>
						</ul>
						<div class="clearfix"></div>		
					</div>
					<div class="single-left-right">
						<div class="single-left-info">
							<h3>PRODUCT NAME HERE</h3>
							<a href="#" class="view">View product details</a>
							<p>$ 20 <a href="#" class="view">CLICK FOR OFFER</a></p>
						</div>
						<div class="select-size">
							<p>Select a size</p>
								<ul>
									 <li><a href="#">S</a></li>
									 <li><a href="#">M</a></li>
									 <li><a href="#">L</a></li>
									 <li><a href="#">XL</a></li>
								</ul>
							<div class="buy-now">
								<a href="#">BYE NOW</a>
							</div>
							<div class="wishlist">
								<a class="play-icon popup-with-zoom-anim" href="#small-dialog2">Add to Wishlist</a>
								<!-- pop-up-box -->
								<script type="text/javascript" src="/frontstyle/js/modernizr.custom.min.js"></script>    
								<link href="/frontstyle/css/popuo-box.css" rel="stylesheet" type="text/css" media="all" />
								<script src="/frontstyle/js/jquery.magnific-popup.js" type="text/javascript"></script>
								<!--//pop-up-box -->
								<div id="small-dialog2" class="mfp-hide">
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
								</script>	
							</div>
							<div class="clearfix"> </div>
							<div class="free">
								<p>20 day returns Free Delivery *</p>
							</div>
							<div class="delivery">
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
							</div>
						</div>
					</div>
					<div class="clearfix"> </div>
					<div class="product-details">
						<h3>PRODUCT DETAILS</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras a est at leo dictum 
							pharetra vel sit amet tellus. Vivamus vitae enim sit amet orci euismod consequat. 
							Nulla porttitor vitae arcu ut porttitor. Pellentesque auctor enim eros, et malesuada 
							ipsum suscipit quis. Aliquam sed ex risus. Etiam hendrerit velit luctus facilisis 
							mollis. Integer auctor consequat est. Integer viverra fringilla finibus. Nunc id 
							dignissim enim. 
						</p>
					</div>
					<div class="related">
						<h3>RELATED PRODUCTS</h3>
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