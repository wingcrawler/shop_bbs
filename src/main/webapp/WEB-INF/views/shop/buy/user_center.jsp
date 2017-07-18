<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../include/meta.jsp"></jsp:include>
<title>${t.t_user_center}</title>
<link href="/frontstyle/buy/css/user_center.css" rel="stylesheet"
	type="text/css">
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
				<div class="header">
					<span>${t.t_user_center } > ${t.t_basic_info }</span>
				</div>
				<div class="product_message">
					<div class="basic_information">
						<div class="title">${t.t_basic_info }</div>
						<div class="company_forms">
							<form enctype="multipart/form-data" method="POST" id="form">
								<div class="name input_text">
									<input type="hidden" value="${entity.id }" name="id"> <span
										class="lab">${t.t_username}：</span> <input type="text"
										name="username" value="${entity.username }" class="u-ipt" readonly>
								</div>
								<c:if test="${not empty img }">
									<div class="logo input_text">
										<span class="lab" style="margin-right: 20px;">${t.t_change_avatar }:
										</span>
										<div class="upload" id="showImg" style="border: 0px;">
											<img alt="" src="${img}" width="100%" height="100%"
												style="margin-top: -15px; padding: 0;"> <span
												class="deleteImg" onclick="deleteImg('${entity.id}')"
												style="">x</span>
										</div>
									</div>
								</c:if>
								<c:if test="${empty img }">
									<div class="logo input_text" style="height: 220px;">
										<span class="lab" style="margin-right: 20px;">${t.t_change_avatar }:
										</span>
										<div class="upload" id="upload">
											<span style="position: relative; left: 50%; top: 40px;">+</span>
											<div style="margin: 10px; width: 200px;">
												<input type="file" name="file" id="doc" multiple="multiple"
													onchange="javascript:setImagePreviews();" accept="image/*" />
												<div id="dd"
													style="height: 200px; margin-top: -60px; margin-left: -15px; z-index: 99999"></div>
											</div>
										</div>
									</div>
								</c:if>

								<div class="number input_text">
									<span class="lab">${t.t_email}：</span> <input type="text"
										name="userMail" value="${entity.userMail}" class="u-ipt">
								</div>
								<div class="service input_text">
									<span class="lab">${t.t_mobile }：</span> <input type="text"
										name="userPhone" value="${entity.userPhone }" class="u-ipt">
								</div>
								<div class="myqq input_text">
									<span class="lab">${t.t_qq }：</span> <input type="text"
										name="userQq" value="${entity.userQq }" class="u-ipt">
								</div>
								<div class="address input_text">
									<div>
										<span class="lab">${t.t_address}：</span> <input type="text"
											name="userAddress" value="${entity.userAddress }"
											class="u-ipt">
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
									<input type="button"
										onclick="jQuery.common.ajaxFileSubmit('#form','/front/buy/doSaveBasicInfo',true,window.location.href)">
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
		$(function() {
			$('.menu_list ul li a').eq(1).addClass('active');

			$('#upload').click(function() {
				$('input[name="file"]').trigger('click');
			});
			$('#dd').click(function() {
				$('input[name="file"]').trigger('click');
			});

			$('#showImg').hover(function() {
				$('.deleteImg').show();
			}, function() {
				$('.deleteImg').hide();
			});
		})
		function deleteImg(id) {
			if (confirm('${t.t_confirm_delete}')) {
				var parm = {};
				parm.id = id;
				parm.type = "avatar";
				jQuery.common.updateObjByParm(parm, '/front/buy/deleteUserImg',
						true, window.location.href);
			} 
		}
	</script>
	<style>
.deleteImg {
	display: none;
	background: #ff0000;
	color: #fff;
	padding: 10px;
	z-index: 10;
	position: relative;
	float: right;
	width: 30px;
	top: -70%;
}
</style>
</body>
</html>