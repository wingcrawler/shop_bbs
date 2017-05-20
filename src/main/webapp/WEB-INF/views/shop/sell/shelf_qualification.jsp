<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>${t.t_business_qualif }</title>
	<link href="/frontstyle/sell/css/business_license.css" rel="stylesheet" type="text/css">
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
				<div class="header"><span>${t.t_bussiness_about } >  ${t.t_business_qualif }</span></div>
				<div class="product_message">
					<div class="g-tabWrap">
						<div class="g-tabMn">
							<div class="img_describe">
								<div class="heade">${t.t_business_qualif }</div>
								<div class="company_forms">
									<form enctype="multipart/form-data" method="POST" id="form">
										<input type="hidden" value="${shop.id }" name="id">
										<div class="logo">
											<span class="lab">${t.t_business_qualif }</span>
											<div class="upload">
												<c:if test="${empty shop.shelfQualificationImg }">
													<input type="file" name="attachFile">
													<p class="one">${t.t_drag_file }</p>
													<p class="two">OR</p>
													<p class="three">${t.t_select }</p>
												</c:if>
												<c:if test="${not empty shop.shelfQualificationImg }">
													<img alt="" src="${shop.shelfQualificationImg}" width="100%" height="100%" style="margin-top:-15px;padding:0;">
												</c:if>
											</div>
										</div>
										<div class="intro">
											<span class="lab">${t.t_desc }</span>
											<textarea class="textarea_intro" name="shelfQualificationDesc">${shop.shelfQualificationDesc }</textarea>
										</div>
										<div class="save">
											<input type="button" onclick="jQuery.common.ajaxFileSubmit('#form','/front/sell/saveShelfQualification',true,'')">
											${t.b_submit }
										</div>
									</form>
								</div>
							</div>
						</div>
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
	$('.menu_box .menu_list div.module a').eq(6).addClass('active');
})
</script>	
</body>
</html>