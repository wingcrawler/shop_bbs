<!DOCTYPE html>
<html>
<head>
	<jsp:include page="include/meta.jsp"></jsp:include>
	<title>${t.t_shopcar }</title>
</head>
<body>
	<jsp:include page="include/header.jsp"></jsp:include>
		
	<!-- cart -->
	<div class="add-to-cart">
		<!-- container -->
		<div class="container">
			<h3>${t.t_tip_cart_empty }</h3>
			<p>${t.t_tip_cart_empty_desc}
			</p>
		</div>
		<!-- container -->
	</div>
	<!-- //cart -->
	
	<jsp:include page="include/footer.jsp"></jsp:include>
</body>
</html>