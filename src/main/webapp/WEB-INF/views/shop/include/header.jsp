<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- header -->
<div class="header">
	<!-- container -->
	<div class="container">
		<!-- header-top -->
		<div class="header-top">
			<div class="header-logo">
				<a href="/"><img src="/frontstyle/images/logo.png" alt="" /></a>
			</div>
			<div class="header-right">
				<ul>
					<li class="phone">+371 282 20 760</li>
					<li class="mail"><a href="mailto:example@mail.com">eony321v@gmail.com</a></li>
					<c:if test="${isSellLogin }">
						<li class=""><a href="/front/sell/productListPage">${t.t_bussiness_center }</a></li>
					</c:if>
					<c:if test="${isBuyLogin }">
						<li class=""><a href="/front/buy/basicInfo">${t.t_welcome } ${user.username }</a></li>
					</c:if>
					<c:if test="${isAdminLogin }">
						<li class=""><a href="/backend/index">${t.t_welcome } ${user.username }</a></li>
					</c:if>
					<c:if test="${!isLogin }">
						<li class=""><a href="/back/login">${t.sign_in }</a></li>
					</c:if>
					<c:if test="${isLogin }">
						<li class=""><a href="/back/logout">${t.sign_out }</a></li>
					</c:if>
					
					<!-- <li class="checkout">
						<a href="/addToCart">
							<span class="cart">$ 99.54</span>
							<span class="check">Checkout</span>
						</a>
						<div class="shopping">
							<h5>Your Shopping Cart is empty.</h5>
							<p>Give it purpose—fill it with books, movies, mobiles, cameras, toys and fashion jewellery.</p>
						</div>
					</li> -->
				</ul>
			</div>
			<div class="clearfix"> </div>
		</div>
		<!-- //header-top -->
		<div class="top-nav">
			<span class="menu"><img src="/frontstyle/images/menu.png" alt=""></span>
			<ul class="nav">
				<%-- <li class="dropdown1"><a href="/">${t.t_home }</a>
					<ul class="dropdown2">
						<li><a href="single.html">lorem</a></li>
						<li><a href="single.html">dorem sia</a></li>
						<li><a href="single.html">erik</a></li>
						<li><a href="single.html">ipsum padamans</a></li>
						<li><a href="single.html">behance</a></li>
					</ul>
				</li> --%>
				<li><a href="/shopIndex">${t.t_sqe_mall }</a></li>
				
				<li>
					<a href="/product/list" target="_blank">${t.t_product_intro }</a>
					<c:if test="${not empty productTypeList }">
					<ul class="dropdown2">
					<c:forEach var="item" items="${productTypeList }">
						<li>
							<a href="/product/list?parentType=${item.key.id }"  target="_blank">${item.key.typeName }</a>
							<%-- <c:if test="${not empty item.value }">
								<ul class="dropdown2">
									<c:forEach var="subitem" items="${item.value }">
										<li><a href="/product/list?typeName=${item.key.typeName }&parentType=${item.key.id }&childType=${subitem.id }">${subitem.typeName }</a></li>
									</c:forEach>
								</ul>
							</c:if>		 --%>	
						</li>
					</c:forEach>
					</ul>
				</c:if>
				</li>
				
				<li><a href="/news/list"  target="_blank">${t.t_news }</a></li>            
				<li><a href="/bbs/index"  target="_blank">${t.t_bbs }</a></li>
				<li><a href="/about"  target="_blank">${t.t_aboutus }</a></li>            
				<li><a href="/help"  target="_blank">${t.t_help }</a></li>
			</ul>
		</div>
		
		<div class="search">
			<form method="post" action="/product/search">
				<input type="text" placeHolder="${t.b_search }..." name="productName" >
				<%-- <input type="text" value="${t.b_search }..." name="productName" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '${t.b_search }...';}" required=""> --%>
			</form>
		</div>
		<div class="clearfix"></div>
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