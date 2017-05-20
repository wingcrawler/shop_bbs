<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="include/meta.jsp"></jsp:include>
	<title>${shop.shopTitle }</title>
	<link rel="stylesheet" href="/frontstyle/css/synopsis.css">
	<script src="/frontstyle/js/product_details.js"></script>
</head>
<body>
	<!-- alertView -->
	<section id="alertView" class='markbg alertToggle' onclick="hideBigImg()">
		<div class="popup">
			<img id="bigImgC" src="">
		</div>
	</section>
	<jsp:include page="include/header.jsp"></jsp:include>
	
	<!-- men -->
	<div class="men">
		<!-- container -->
		<div class="container">
			<div class="col-md-9 fashions">
				<div class="store_name">
					<div>
						<div class="store_left">
							<img src="${shop.shopLogoImg}" alt="">
						</div>
						<div class="store_right">
							<p class="name">${shop.shopTitle }</p>
						</div>
					</div>
				</div>
				
				<!-- 商家环境 -->
				<div class="environment">
					<p>${t.t_shop_envir}:</p>
					<div>
						<c:forEach var="item" items="${imageList.list }">
							<img src="${item.imagePath}" onClick="showBigImg(this)">
						</c:forEach>
					</div>
				</div>
				<!-- 商家环境 -->
				
				<!-- 商家环简介-->
				<div class="synopsis">
					<p>${t.t_desc}:</p>
					<div>
						<span>${shop.shopDescription}</span>
					</div>
				</div>
				<!-- 商家环简介-->
				
			</div>
			
			<div class="col-md-3 side-bar">
				<div class="categories">
					<h3>${t.t_product_type }</h3>
					<ul>
						<c:forEach var="item" items="${productTypeList }">
							<li>
							<a href="/product/list?parentType=${item.key.id }"  target="_blank">${item.key.typeName }</a>
							</li>
						</c:forEach>
					</ul>
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