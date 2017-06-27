<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="include/meta.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="include/header.jsp"></jsp:include>
	
	<!-- support -->
	<div class="support-info">
		<!-- container -->
		<div class="container">	
			<h1>${t.reset }</h1>
			<h2>重置密码</h2>
			<a href="/">${t.t_back_home }</a>
		</div>	
		<!-- //container -->
	</div>
	<!-- //support -->
	

	<jsp:include page="include/footer.jsp"></jsp:include>
</body>
</html>