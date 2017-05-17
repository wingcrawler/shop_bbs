<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="include/meta.jsp"></jsp:include>
	<title>${title }</title>
</head>
<body>
	<jsp:include page="include/header.jsp"></jsp:include>
	
	<!-- men -->
	<div class="men">
		<!-- container -->
		<div class="container">
			<div class="col-md-9 fashions">
				<div class="title">
					<h3>TOPS - TITLE</h3>
				</div>
				<div class="fashion-section">
					<div class="fashion-grid1">
					<c:forEach var="item" items="${page.list }" varStatus="status">
						 <div class="col-md-3 fashion-grid">
							 <a href="single.html"><img src="${item.imagePath }" width="190" height="292" alt=""/>
								 <div class="product <c:if test="${item.productCount==0 }">not-avaliable</c:if>">
									 <h3>${item.productName }</h3>
									 <p>
									 	<c:if test="${item.productCount==0 }">
											<del><span></span> ${item.productPrice } &euro;</del>										 	
									 	</c:if>
									 	<c:if test="${item.productCount!=0 }">
									 		<span></span> ${item.productPrice } &euro;
									 	</c:if>
									 </p>
								 </div>
							 </a>
							 <div class="fashion-view"><span></span>
								<div class="clearfix"></div>
								<c:if test="${item.productCount==0 }">
									<h4 class="sold-out"><a href="/product/single?productId=${item.id }"><del>Sold Out</del></a></h4>									 	
							 	</c:if>
							 	<c:if test="${item.productCount!=0 }">
							 		<h4><a href="/product/single?productId=${item.id }">Quick View</a></h4>
							 	</c:if>
							 </div>
						 </div>
						 <c:if test="${status.count/4==1 }">
							<div class="clearfix"></div>
							<br>
						</c:if>
					</c:forEach>
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
				<div class="sales">
					<h3>SALE</h3>
					 <div class="pricing">
						 <h4>Price range</h4>
						 <input type="text" placeholder="price from" required/>
						 <input type="text" placeholder="price to" required/>
						 <div class="clearfix"></div>
					 </div>
					 <div class="size">
						 <h4>Size</h4>
						 <ul>
							 <li><a href="#">XS</a></li>
							 <li><a href="#">S</a></li>
							 <li><a href="#">M</a></li>
							 <li><a href="#">L</a></li>
							 <li><a href="#">XL</a></li>
						 </ul>
					 </div>
				</div>
			</div>

			<div class="clearfix"> </div>
		</div>
		<!-- //container -->
	</div>
	<!-- //men -->
	
	<jsp:include page="include/footer.jsp"></jsp:include>
</body>
</html>