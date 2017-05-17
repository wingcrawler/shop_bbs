<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>${t.t_user_center}</title>
	<link href="/frontstyle/buy/css/user_center.css" rel="stylesheet" type="text/css">
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
				<div class="header"><span>${t.t_user_center } >  ${t.t_basic_info }</span></div>
				<div class="product_message">
					<div class="basic_information">
						<div class="title">${t.t_basic_info }</div>
						<div class="company_forms">
							<form enctype="multipart/form-data" method="POST" id="form">
								<div class="name input_text">
									<input type="hidden" value="${entity.id }" name="id">
									<span class="lab">${t.t_username}：</span>     
									<input type="text" name="username" value="${entity.username }"  class="u-ipt">
								</div>
								<div class="logo input_text">
									<span class="lab">${t.t_change_avatar}：</span>
									<c:if test="${empty img }">
										<div class="upload">
											<input type="file" name="attachFile">
											<p class="one">${t.t_drag_file }</p>
											<p class="two">OR</p>
											<p class="three">${t.t_select }</p>
										</div>
									</c:if>
									<c:if test="${not empty img }">
										<div class="upload">
											<img alt="" src="${img}" width="100%" height="100%" style="margin-top:-15px;padding:0;">
										</div>
									</c:if>
								</div>
								<div class="number input_text">
									<span class="lab">${t.t_email}：</span>
									<input type="text" name="userMail" value="${entity.userMail}" class="u-ipt">
								</div>
								<div class="service input_text">
									<span class="lab">${t.t_mobile }：</span>
									<input type="text" name="userPhone" value="${entity.userPhone }"  class="u-ipt">
								</div>
								<div class="myqq input_text">
									<span class="lab">${t.t_qq }: </span>
								<input type="text" name="userQq" value="${entity.userQq }"  class="u-ipt">
								</div>
								<div class="address input_text">
									<div>
										<span  class="lab">${t.t_address}: </span>
										<input type="text" name="userAddress" value="${entity.userAddress }"  class="u-ipt">
										<!-- <select class="margin_left_minus">
											<option>广东</option>
											<option>xxxx</option>
											<option>xxxx</option>
											<option>xxxx</option>
											<option>xxxx</option>
											<option>xxxx</option>
										</select>
										<select>
											<option>东莞</option>
											<option>xxxx</option>
											<option>xxxx</option>
											<option>xxxx</option>
											<option>xxxx</option>
											<option>xxxx</option>
										</select>
										<select>
											<option>南山</option>
											<option>xxxx</option>
											<option>xxxx</option>
											<option>xxxx</option>
											<option>xxxx</option>
											<option>xxxx</option>
										</select> -->
									</div>	
								</div>

								<div class="save">
									<input type="button" onclick="jQuery.common.ajaxFileSubmit('#form','/front/buy/doSaveBasicInfo',true,'')">
									${t.b_submit}
								</div>
							</form>
						</div>
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
	$('.menu_list ul li a').eq(1).addClass('active');
});
</script>
</body>
</html>