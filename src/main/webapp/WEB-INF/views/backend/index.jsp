<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

	<jsp:include page="include/meta.jsp"></jsp:include>
	<title>${t.title_backend_index }</title>
	
<style>
.linecons-cloud:before{position:relative;top:10px;}
.xe-icon .linecons-user:before{position:relative;top:10px;}
.linecons-desktop:before{position:relative;top:10px;}
.linecons-cup:before{position:relative;top:10px;}
</style>
</head>
<body class="page-body">
	<div class="page-container">
		<!-- 左边栏 -->
		<jsp:include page="include/left.jsp"></jsp:include>
		<div class="main-content">
			<!-- 头部栏 -->
			<jsp:include page="include/header.jsp"></jsp:include>	
			
			<div class="row">
				<div class="col-sm-12">
					<h1>Welcome: ${user.username }!</h1>
				</div>
			</div>
			
			
		</div>
	</div>
</body>
</html>