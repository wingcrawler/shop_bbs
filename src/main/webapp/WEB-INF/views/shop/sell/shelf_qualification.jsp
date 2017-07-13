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
										
										<c:if test="${not empty shop.shelfQualificationImg }">
											<div class="upload" id="showImg" style="text-align:center;margin-bottom:20px;">
												<span class="lab"></span>
												<img alt="" src="${shop.shelfQualificationImg}" width="60%" height="60%" style="margin-top:-15px;padding:0;">
												<span class="deleteImg" onclick="deleteImg('${shop.id}')" imgid="${shop.id }">x</span>
											</div>
										</c:if>
										<c:if test="${empty shop.shelfQualificationImg }">
										<div class="logo" style="height:200px;">
											<span class="lab" style="margin-right:20px;">${t.t_business_qualif }: </span>
											<div class="upload" id="upload">
												<span style="position:relative;left:50%;top:40px;">+</span>
												<div style="margin:10px; width:200px;">
													<input type="file"  name="file" id="doc" multiple="multiple" onchange="javascript:setImagePreviews();" accept="image/*" />
													<div id="dd" style="height:200px;margin-top:-60px;margin-left:-15px;z-index:99999"></div>
												</div>
												<div style="text-align:center;">${t.t_select }</div>
											</div>
										</div>
										</c:if>
										
										<div class="intro" style="margin-top:80px;">
											<span class="lab">${t.t_desc }</span>
											<textarea class="textarea_intro" name="shelfQualificationDesc">${shop.shelfQualificationDesc }</textarea>
										</div>
										<div class="save">
											<input type="button" onclick="jQuery.common.ajaxFileSubmit('#form','/front/sell/saveShelfQualification',true,'')">
											${t.b_submit }
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
	
	$('#upload').click(function(){
		$('input[name="file"]').trigger('click');
	});
	
	$('#showImg').hover(function(){
		$('.deleteImg').show();
	},function(){
		$('.deleteImg').hide();
	});
	
})
function deleteImg(id){
	if(confirm('${t.t_confirm_delete}')){
		debugger;
		var parm = {};
		parm.id=id;
		parm.type="shelfQualificationImg";
		jQuery.common.updateObjByParm(parm,'/front/sell/deleteImg',true,'');
	}
}
</script>	
<style>
.deleteImg{ background:#ff0000;color:#fff;padding:10px;position:relative;left:-30px;}
</style>		
</body>
</html>