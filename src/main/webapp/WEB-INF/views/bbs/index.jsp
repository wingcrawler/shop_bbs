<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/meta.jsp"%>
<title>主页</title>
</head>

<body>
	<div class="container">

		<div class="jumbotron">
			<h1>bbs 主页 展示</h1>

			<p>一级板块</p>
			<p>二级板块</p>

			<p>
			${message}
			</p>
		</div>

	</div>
	<!-- /container -->
	<%@include file="/common/footer.jsp"%>
</body>
</html>