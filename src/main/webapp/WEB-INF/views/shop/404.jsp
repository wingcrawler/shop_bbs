<!DOCTYPE html>
<html>
<head>
	<jsp:include page="include/meta.jsp"></jsp:include>
	<title>404</title>
</head>
<body>
	<jsp:include page="include/header.jsp"></jsp:include>
	
	<!-- support -->
	<div class="support-info">
		<!-- container -->
		<div class="container">	
			<h1>${t.error_page404 }</h1>
			<h2>Error!! 404</h2>
			<a href="/">${t.t_back_home }</a>
		</div>	
		<!-- //container -->
	</div>
	<!-- //support -->
	

	<jsp:include page="include/footer.jsp"></jsp:include>
</body>
</html>