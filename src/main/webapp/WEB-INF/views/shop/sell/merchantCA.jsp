<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>${t.t_business_info }</title>
	<link href="/frontstyle/sell/css/merchantCA.css" rel="stylesheet" type="text/css">
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
				<div class="header"><span>${t.t_bussiness_about } > ${t.t_business_info }</span></div>
				
				<c:if test="${not empty shop}">
					<div class="company_data">
						<div class="img" style="background: #fff url(${img})no-repeat ;"></div>
						<div>
							<p>${t.t_shop_name }: <span class="cyan">${shop.shopTitle }</span></p>
							<p>${t.t_star }: <span>${shop.shopLevel }</span></p>
							<p>${t.t_shop_account }: <span>${user.username }</span></p>
							<p>${t.t_mobile }: <span>${shop.shopPhone }</span></p>
							<%-- <p>${t.t_shop_name }: <span>2016/8/3 18:27:13</span></p> --%>
						</div>
						<div class="enter_shop">${t.t_shop_enter }</div>
					</div>
				</c:if>
				
				<div class="company_data_edit">
					<h2 class="companyTitle">${t.t_business_info }</h2>
					<div class="company_forms">
						<form enctype="multipart/form-data" method="POST" id="form">
							<div class="name">
								<input type="hidden" value="${shop.id }" name="id">
								<span class="lab">${t.t_shop_name }: </span>
								<input type="text" name="shopTitle" value="${shop.shopTitle }">
							</div>
							<c:if test="${not empty img }">
							<div class="logo">
								<span class="lab"> </span>
								<div class="upload">
									<img alt="" src="${img}" width="100%" height="100%" style="margin-top:-15px;padding:0;">
								</div>
							</div>
							</c:if>
							<div class="logo">
								<span class="lab">${t.t_shop_logo }: </span>
								<div class="upload">
									<input type="file" name="attachFile">
									<%-- <p class="one">${t.t_drag_file }</p>
									<p class="two">OR</p> --%>
									<p class="three">${t.t_select }</p>
								</div>
								<%-- <c:if test="${not empty img }">
									<div class="upload">
										<img alt="" src="${img}" width="100%" height="100%" style="margin-top:-15px;padding:0;">
									</div>
								</c:if> --%>
							</div>
							<div class="number">
								<span class="lab">${t.t_mobile }: </span>
								<input type="text" name="shopPhone" value="${shop.shopPhone }" >
							</div>
							<div class="work_time">
								<span class="lab">${t.t_work_time }: </span>
								<input type="text" class="margin_left_minus" name="workFrom" vlaue="${shop.workFrom }"> - 
								<input type="text" name="workTo" vlaue="${shop.workTo }">
								<span class="gray">7*24h</span>
							</div>
							<div class="service">
								<span class="lab">${t.t_qq }: </span>
								<input type="text" name="shopQq" value="${shop.shopQq }">
							</div>
							<div class="address">
								<div>
									<span  class="lab">${t.t_address}: </span>
									<input type="text" name="shopAddress" value="${shop.shopAddress }">
									<!-- <select class="margin_left_minus">
										<option>xxxx</option>
										<option>xxxx</option>
										<option>xxxx</option>
										<option>xxxx</option>
										<option>xxxx</option>
									</select>
									<select>
										<option>xxxx</option>
										<option>xxxx</option>
										<option>xxxx</option>
										<option>xxxx</option>
										<option>xxxx</option>
									</select>
									<select>
										<option>xxxx</option>
										<option>xxxx</option>
										<option>xxxx</option>
										<option>xxxx</option>
										<option>xxxx</option>
									</select> -->
								</div>	
							</div>

							<div  class="save">
								<input type="button" onclick="jQuery.common.ajaxFileSubmit('#form','/front/sell/doSaveMerchant',true,'')">
								${t.b_submit }
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
	
	<jsp:include page="../include/footer.jsp"></jsp:include>
	
<script type="text/javascript">
$(function(){
	$('.menu_box .menu_list div.module a').eq(4).addClass('active');
})
</script>	
</body>
</html>