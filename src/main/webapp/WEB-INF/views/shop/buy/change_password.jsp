<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html><head>
	<title>${t.t_change_pwd }</title>
	<jsp:include page="../include/meta.jsp"></jsp:include>
	<link href="/frontstyle/buy/css/change_password.css" rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="../include/header.jsp"></jsp:include>
	
	<!-- main -->
	<div class="main">
		<!-- container -->
		<div class="container">
			<jsp:include page="include/left.jsp"></jsp:include>
			
			<!-- companyData -->
			<div class="companyData">
				<div class="header"><span>${t.t_user_center } >  ${t.t_change_pwd }</span></div>
				<div class="product_message">
					<div class="password_box">
						<form method="POST" id="form">
							<div class="caution">${t.tip_change_pwd }</div>
							<div class="old_password password">
								<span>${t.t_old_pwd }: </span>
								<input type="password" name="oldPwd">
							</div>
							<div class="new_password password">
								<span>${t.t_new_pwd }: </span>
								<input type="password" name="newPwd">
							</div>
							<div class="affirm_password password">
								<span>${t.t_confirm_pwd }: </span>
								<input type="password" name="confirmPwd">
							</div>
							<div class="commit">
								<span>${t.b_submit }</span>
								<input type="button" onclick="jQuery.common.saveObj('#form','/front/buy/doChangePwd',true,'')">
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- //companyData -->
		</div>
		<!-- //container -->
	</div>
	<!-- //main -->

<script type="text/javascript">
$(function(){
	$('.menu_list ul li a').eq(5).addClass('active');
});
</script>
</body>
</html>