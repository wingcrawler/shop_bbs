<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>${t.t_applay_shop }</title>
	<link href="/frontstyle/buy/css/user_center.css" rel="stylesheet" type="text/css">
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
				<div class="header"><span>${t.t_user_center } > ${t.t_applay_shop }</span></div>
				
				<div class="company_data_edit">
					<c:if test="${shop.shopStatus==2 }">
					<div class="alert alert-warning" role="alert">${t.t_pending }</div>
					</c:if>
					<c:if test="${shop.shopStatus==3 }">
					<div class="alert alert-danger" role="alert">{t.t_not_pass }<em>:</em>${shop.failedReason }</div>
					</c:if>
					
					<div class="company_forms">
						<form enctype="multipart/form-data" method="POST" id="form">
							<div class="name">
								<input type="hidden" value="${shop.id }" name="id">
								<span class="lab">${t.t_shop_name }<em>:</em></span>
								<input type="text" name="shopTitle" value="${shop.shopTitle }">
							</div>
							<c:if test="${not empty img }">
								<div class="logo">
									<span class="lab" style="margin-right:20px;">${t.t_shop_logo }: </span>
									<div class="upload" id="showImg" >
										<img alt="" src="${img}" width="100%" height="100%" style="margin-top:-15px;padding:0;">
										<span class="deleteImg" onclick="deleteImg('${shop.id}')" imgid="${shop.id }" style="">x</span>
									</div>
								</div>
							</c:if>
							<c:if test="${empty img }">
								<div class="logo" style="height:220px;">
									<span class="lab" style="margin-right:20px;">${t.t_shop_logo }<em>:</em></span>
									<div class="upload" id="upload">
										<span style="position:relative;left:50%;top:40px;">+</span>
										<div style="margin:10px; width:200px;">
											<input type="file"  name="file" id="doc" multiple="multiple" onchange="javascript:setImagePreviews();" accept="image/*" />
											<div id="dd" style="height:200px;margin-top:-60px;margin-left:-15px;z-index:99999"></div>
										</div>
									</div>
								</div>
							</c:if>
							
							<div class="number">
								<span class="lab">${t.t_mobile }<em>:</em></span>
								<input type="text" name="shopPhone" value="${shop.shopPhone }" >
							</div>
							<div class="work_time">
								<span class="lab">${t.t_work_time }<em>:</em></span>
								<input type="text" class="margin_left_minus" name="workFrom" value="${shop.workFrom }"> - 
								<input type="text" name="workTo" value="${shop.workTo }">
								<span class="gray">7*24h</span>
							</div>
							<div class="service">
								<span class="lab">${t.t_qq }<em>:</em></span>
								<input type="text" name="shopQq" value="${shop.shopQq }">
							</div>
							<div class="address">
								<div>
									<span  class="lab">${t.t_address }<em>:</em></span>
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
								<input type="button" onclick="jQuery.common.ajaxFileSubmit('#form','/front/buy/doApplayShop',true,'/front/buy/applayShop')">
								${t.b_submit }
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
		parm.type="logo";
		jQuery.common.updateObjByParm(parm,'/front/sell/deleteImg',true,'');
	}
}
</script>	
<style>
.deleteImg{display:none; background:#ff0000;color:#fff;padding:10px;z-index:10;position:relative;float:right;width:30px;top:-70%;}
</style>
</body>
</html>