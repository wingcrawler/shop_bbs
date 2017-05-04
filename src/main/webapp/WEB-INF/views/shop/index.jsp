<jsp:include page="/common/global.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="include/meta.jsp"></jsp:include>
	<title>Home</title>
</head>
<body>
	<!-- header -->
	<div class="header">
		<!-- container -->
		<div class="container">
			<!-- header-top -->
			<div class="header-top">
				<div class="header-logo">
					<a href="index.html"><img src="/frontstyle/images/logo.png" alt="" /></a>
				</div>
				<div class="header-right">
					<ul>
						<li class="phone">+371 282 20 760</li>
						<li class="mail"><a href="mailto:example@mail.com">eony321v@gmail.com</a></li>
						<li class="checkout">
							<a href="add-to-cart.html">
								<span class="cart">$ 99.54</span>
								<span class="check">Checkout</span>
							</a>
							<div class="shopping">
								<h5>Your Shopping Cart is empty.</h5>
								<p>Give it purpose—fill it with books, movies, mobiles, cameras, toys and fashion jewellery.</p>
							</div>
						</li>
					</ul>
				</div>
				<div class="clearfix"> </div>
			</div>
			<!-- //header-top -->
			<div class="top-nav">
				<span class="menu"><img src="/frontstyle/images/menu.png" alt=""></span>
				<ul class="nav">
					<li class="dropdown1"><a href="index.html">首页</a>
						<ul class="dropdown2">
							<li><a href="single.html">lorem</a></li>
							<li><a href="single.html">dorem sia</a></li>
							<li><a href="single.html">erik</a></li>
							<li><a href="single.html">ipsum padamans</a></li>
							<li><a href="single.html">behance</a></li>
						</ul>
					</li>
					<li class="dropdown1"><a href="men.html">MEN</a>
						<ul class="dropdown2">
							<li><a href="men.html">Clothing</a></li>
							<li><a href="men.html">Footwear</a></li>
							<li><a href="men.html">Watches</a></li>
							<li><a href="men.html">Accessories</a></li>
						</ul>
					</li>     
					<li class="dropdown1"><a href="women.html">WOMEN</a>
						<ul class="dropdown2">
							<li><a href="women.html">Ethnic Wear</a></li>
							<li><a href="women.html">Western Wear</a></li>
							<li><a href="women.html">All Jewellery</a></li>
							<li><a href="women.html">Beauty & Wellness</a></li>
						</ul>
					</li>              
					<li class="dropdown1"><a href="women.html">KIDS</a>
						<ul class="dropdown2">
							<li><a href="women.html">Clothing</a></li>
							<li><a href="women.html">Footwear</a></li>
							<li><a href="women.html">Accessories</a></li>
						</ul>
					</li>  
					<li class="dropdown1"><a href="men.html">SALE</a>
						<ul class="dropdown2">
							<li><a href="men.html">Clothing</a></li>
							<li><a href="men.html">Footwear</a></li>
							<li><a href="men.html">Watches</a></li>
							<li><a href="men.html">Accessories</a></li>
						</ul>
					</li>  					           
					<li><a href="about.html">ABOUT US</a></li>            
					<li><a href="404.html">SUPPORT</a></li>
				</ul>
			</div>
			<div class="search">
				<form>
					<input type="text" value="Search..." onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = 'Search...';}" required="">
				</form>
			</div>
			<div class="clearfix"> </div>
			<!-- script-for-menu -->
		 <script>
				$("span.menu").click(function(){
					$(" ul.nav").slideToggle("slow" , function(){
					});
				});
		 </script>
		</div>
		<!-- //container -->
	</div>
	<!-- //header -->
	<!-- banner -->
    <div class="copyrights">Collect from <a href="http://www.cssmoban.com/" >手机网站模板</a></div>
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
							<li>
								<div class="banner-bg">
									<div class="container">
										<div class="banner-info">
											<h2>RIGHT IS WHITE.<span>LEFT IS BLACK</span></h2>
											<p>Inspired by Brasil’s bold colors and matching up to football’s on-pitch
												playmakers, these kicks are ready to stand out.
											</p>
											<a href="#">SHOP BRASIL</a>
										</div>
									</div>
								</div>
							</li>
							<li>
								<div class="banner-bg banner-img2">
									<div class="container">
										<div class="banner-info">
											<h2>RIGHT IS BLUE.<span>LEFT IS PINK</span></h2>
											<p>Inspired by bold colors and matching up to football’s on-pitch
												playmakers, these kicks are ready to stand out.
											</p>
											<a href="#">SHOP BRASIL</a>
										</div>
									</div>
								</div>
							</li>
							<li>
								<div class="banner-bg banner-img">
									<div class="container">
										<div class="banner-info">
											<h2>RIGHT IS PINK.<span>LEFT IS BLUE</span></h2>
											<p>Inspired by Brasil’s bold colors and matching up to football’s on-pitch
												playmakers, these Brasil’s kicks are ready to stand out.
											</p>
											<a href="#">SHOP BRASIL</a>
										</div>
									</div>
								</div>
							</li>
							<li>
								<div class="banner-bg">
									<div class="container">
										<div class="banner-info">
											<h2>RIGHT IS WHITE.<span>LEFT IS BLACK</span></h2>
											<p>Inspired by bold colors and matching up to football’s on-pitch
												playmakers, these kicks are ready to stand out.
											</p>
											<a href="#">SHOP BRASIL</a>
										</div>
									</div>
								</div>
							</li>
							<li>
								<div class="banner-bg banner-img2">
									<div class="container">
										<div class="banner-info">
											<h2>RIGHT IS BLUE.<span>LEFT IS PINK</span></h2>
											<p>Inspired by Brasil’s bold colors and matching up to football’s on-pitch
												playmakers, these kicks Brasil’s are ready to stand out.
											</p>
											<a href="#">SHOP BRASIL</a>
										</div>
									</div>
								</div>
							</li>
						</ul>
					</div>
	</div>
	<!-- //banner -->
	<!-- banner-bottom -->
	<div class="banner-bottom">
		<div class="container">
			<div class="banner-bottom-grids">
				<div class="col-md-4 bottom-grid">
					<img src="/frontstyle/images/3.jpg" alt="" />
					<div class="bottom-grid-info">
						<a href="#">FOOTBALL SHOES</a>
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing,
							vivamus congue nulla leo, quis imperdiet magna.
						</p>
					</div>
				</div>
				<div class="col-md-4 bottom-grid">
					<img src="/frontstyle/images/7.jpg" alt="" />
					<div class="bottom-grid-info">
						<a href="#">MODERN CLOTHES</a>
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing,
							vivamus congue nulla leo, quis imperdiet magna.
						</p>
					</div>
				</div>
				<div class="col-md-4 bottom-grid">
					<img src="/frontstyle/images/5.jpg" alt="" />
					<div class="bottom-grid-info">
						<a href="#">BAGS & ACCESSORIES</a>
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing,
							vivamus congue nulla leo, quis imperdiet magna.
						</p>
					</div>
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
	</div>
	<!-- banner-bottom -->
	<!-- products -->
	<div class="products">
		<!-- container -->
		<div class="container">
			<div class="products-heading">
				<h3>POPULAR PRODUCTS</h3>
			</div>
			<div class="products-grids">
				<div class="col-md-3 product-left-grid">
					<div class="product-grid">
						<div class="sap_tabs">	
							<div id="horizontalTab" style="display: block; width: 100%; margin: 0px;">
								<div class="resp-tabs-container">
									<div class="tab-1 resp-tab-content" aria-labelledby="tab_item-0">
										<div class="facts">
											<img src="/frontstyle/images/t1.jpg" class="img-responsive" alt=""/> 
										</div>
									</div>	
									<div class="tab-1 resp-tab-content" aria-labelledby="tab_item-1">
										<div class="facts">
											<img src="/frontstyle/images/t2.jpg" class="img-responsive" alt=""/>   
										</div>
									</div>	
									<div class="tab-1 resp-tab-content" aria-labelledby="tab_item-2">
										<div class="facts">
											<img src="/frontstyle/images/t3.jpg" class="img-responsive" alt=""/> 
										</div>
									</div>	         	  
								</div>
								<ul class="resp-tabs-list">
									<li class="resp-tab-item" aria-controls="tab_item-0" role="tab"><span><img src="/frontstyle/images/t1.jpg" class="img-responsive" alt=""/></span></li>
									<li class="resp-tab-item" aria-controls="tab_item-1" role="tab"><span><img src="/frontstyle/images/t2.jpg" class="img-responsive" alt=""/></span></li>
									<li class="resp-tab-item" aria-controls="tab_item-2" role="tab"><span><img src="/frontstyle/images/t3.jpg" class="img-responsive" alt=""/></span></li>
									<div class="clearfix"> </div>
								</ul>	
							</div>
						</div>
						<div class="products-grid-info">
							<h3>Nike Tailwind Loose</h3>
							<h4>Running Tank Top</h4>
							<p>The Nike Tailwind Loose Women's Running Tank 
								Top is made with sweat-wicking fabric to help you 
								stay dry and comfortable on your run.
							</p>
							<div class="price">
								<p>$ 36.99</p>
							</div>
							<div class="like">
								<a href="#"><img src="/frontstyle/images/like.png" alt="" /></a>
							</div>
							<div class="clearfix"> </div>
						</div>
					</div>
				</div>
				<div class="col-md-3 product-left-grid">
					<div class="product-grid">
						<div class="product-grid-text">
							<a href="single.html"><img src="/frontstyle/images/t4.jpg" alt="" /></a>
							<div class="products-grid-info">
								<h3>Nike g87</h3>
								<h4>Training Tank Top</h4>
								<p>The Nike Tailwind Loose Women's Running Tank 
									Top is made with sweat-wicking fabric to help you 
									stay dry and comfortable on your run.
								</p>
								<div class="price">
									<p>$ 49.99</p>
								</div>
								<div class="like">
									<a href="#"><img src="/frontstyle/images/like.png" alt="" /></a>
								</div>
								<div class="clearfix"> </div>
							</div>
							<div class="plus">
								<a href="single.html"><img src="/frontstyle/images/plus.png" alt="" /></a>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-3 product-left-grid">
					<div class="product-grid">
						<div class="product-grid-text">
							<a href="single.html"><img src="/frontstyle/images/t5.jpg" alt="" /></a>
							<div class="products-grid-info">
								<h3>Nike I-Beam Swoosh</h3>
								<h4>Sports Top</h4>
								<p>The Nike Tailwind Loose Women's Running Tank 
									Top is made with sweat-wicking fabric to help you 
									stay dry and comfortable on your run.
								</p>
								<div class="price">
									<p>$ 70.00</p>
								</div>
								<div class="like">
									<a href="#"><img src="/frontstyle/images/like.png" alt="" /></a>
								</div>
								<div class="clearfix"> </div>
							</div>
							<div class="plus">
								<a href="single.html"><img src="/frontstyle/images/plus.png" alt="" /></a>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-3 product-left-grid">	
					<div class="product-grid">
						<div class="product-grid-text">
							<a href="single.html"><img src="/frontstyle/images/t6.jpg" alt="" /></a>
							<div class="products-grid-info">
								<h3>Nike lux</h3>
								<h4>Running Top</h4>
								<p>The Nike Tailwind Loose Women's Running Tank 
									Top is made with sweat-wicking fabric to help you 
									stay dry and comfortable on your run.
								</p>
								<div class="price">
									<p>$ 28.49</p>
								</div>
								<div class="like">
									<a href="#"><img src="/frontstyle/images/like.png" alt="" /></a>
								</div>
								<div class="clearfix"> </div>
							</div>
							<div class="plus">
								<a href="single.html"><img src="/frontstyle/images/plus.png" alt="" /></a>
							</div>
						</div>
					</div>
				</div>
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
	<!-- footer -->
	<div class="footer">
		<!-- container -->
		<div class="container">
			<div class="col-md-3 stores-grid">
				<div class="stores">
					<h3>Our Stores</h3>
					<ul>
						<li>Feel free to visit our stores or contact us.</li>
						<li>1401 South Grand Avenue </li>
						<li>Los Angeles, CA 90015 </li>
						<li>(213) 748-2411</li>

						<span>
						<li class="drive">100 Fairview Drive </li>
						<li>Franklin, VA 23851</li> 
						<li>(757) 569-6100 </li>
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
				<p>Lorem ipsum dolor sit amet, consectetur
					adipiscing elit. Donec sed auctor elit.
				</p>
				<a href="#">New sexy sport clothes are here!</a>
				<p>Lorem ipsum dolor sit amet, consectetur
					adipiscing elit. Donec sed auctor elit.
				</p>
				<a href="#">Summer sales are coming!</a>
				<p>Lorem ipsum dolor sit amet, consectetur
					adipiscing elit. Donec sed auctor elit.
				</p>
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
					<div class="clearfix"> </div>
				</div>
			</div>
			<div class="col-md-3 contact">
				<h3>Contact us</h3>
				<form>
					<input type="text" value="your e-mail..." onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = 'your e-mail...';}" required="">
					<textarea value="your text...:" onFocus="if(this.value == 'your text...') this.value='';" onBlur="if(this.value == '') this.value='your text...';">your text...</textarea>
					<input type="submit" value="Send MESSAGE">
				</form>
			</div>
			<div class="clearfix"> </div>
			<div class="copyright">
				<p>Copyright &copy; 2015.Company name All rights reserved.<a target="_blank" href="http://www.cssmoban.com/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a> - More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a></p>
			</p>
		</div>
		<!-- // container -->
	</div>
	<!-- //footer -->
</body>
</html>