<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>${t.t_product_upload}</title>
	<link href="/frontstyle/sell/css/upload_product.css" rel="stylesheet" type="text/css">
	<script src="/frontstyle/sell/js/upload_product.js"></script>

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
				<div class="header"><span>${t.m_product } > ${t.t_product_upload} </span></div>
				<div class="product_message">
					<div class="g-tabWrap">
						<div class="g-tabHdWrap">
							<ul class="g-tabHd">
								<li class="f-active"><span>${t.t_product_upload }</span></li>
								<!-- <li class=""><span>å±æ§éæ©</span></li> -->
							</ul>
						</div>
						<div class="g-tabMn">
							<form enctype="multipart/form-data" method="POST" id="form">
							<input type="hidden" value="${entity.id }" name="id">
							<div class="g-tabMnItem f-active">
								<div class="basic">
									<div class="classify">
										<div class="row">
											<div class="col-xs-12 col-md-12 commodity_terrace"><!-- åååç±» -->
												<div class="m-form-item">
													<label class="m-form-hd">
														<span class="u-hd-require">*</span>
														<span class="u-hd-txt">${t.t_product_type }:</span>
													</label>
													<select class="u-ipt-select" name="productTypeId" id="productTypeId" onchange="jQuery.common.refreshSelect('#productTypeId','#productSubtypeId','/getProductTypeTwoLevel')">
														<option value="-1">-- ${t.t_select } --</option>
														<c:forEach items="${typeList}" var="item">
														<option value="${item.id}">${item.typeName}</option>
														</c:forEach>
													</select>
													<select class="u-ipt-select" name="productSubtypeId" id="productSubtypeId">
														<option value="-1">-- ${t.t_select } --</option>
														<c:forEach items="${subtypeList}" var="item">
														<option value="${item.id}">${item.typeName}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
									</div>
									<div  class="message">
										<div class="row">
										  <div class="col-xs-6 col-md-6">
										  	<div class="m-form-item">
												<label class="m-form-hd">
													<span class="u-hd-require">*</span>
													<span class="u-hd-txt">${t.t_product_name }(${t.t_zh }):</span>
												</label>
												<input type="text" class="u-ipt-text" name="productName" value="${entity.productName }">
											</div>
										  </div>
										  <div class="col-xs-6 col-md-6">
										  	<div class="m-form-item">
												<label class="m-form-hd">
													<span class="u-hd-require">*</span>
													<span class="u-hd-txt">${t.t_product_name }(${t.t_en }):</span>
												</label>
												<input type="text" class="u-ipt-text" name="productEnName" value="${entity.productEnName }">
											</div>
										  </div>
										</div>
										<div class="row">
											<div class="col-xs-6 col-md-6">
											  	<div class="m-form-item">
													<label class="m-form-hd">
														<span class="u-hd-require">*</span>
														<span class="u-hd-txt">${t.t_product_count }:</span>
													</label>
													<input type="number" class="u-ipt-text" name="productCount" value="${entity.productCount }">
												</div>
											  </div>
											  <div class="col-xs-6 col-md-6">
													<div class="m-form-item">
														<label class="m-form-hd">
															<span class="u-hd-require">*</span>
															<span class="u-hd-txt">${t.t_price }:</span>
														</label>
														<input type="number" class="u-ipt-text" name="productPrice" value="${entity.productPrice }">
													</div>
											  </div>
										</div>
										<div class="row">
											<div class="col-xs-6 col-md-6">
											  	<div class="m-form-item">
													<label class="m-form-hd">
														<span class="u-hd-require">*</span>
														<span class="u-hd-txt">${t.t_product_url }:</span>
													</label>
													<input type="text" class="u-ipt-text" name="productUrl" value="${entity.productUrl }">
												</div>
											  </div>
										</div>
										<div class="row">
										  <div class="col-xs-6 col-md-6">
											<div class="m-form-item">
												<label class="m-form-hd">
													<span class="u-hd-require"></span>
													<span class="u-hd-txt">${t.t_product_tag }:</span>
												</label>
												<div class="m-checkboxgroup">
													<div class="u-checkboxitem">
														<input type="checkbox" class="u-ipt-checkbox" value="t_product_tag_new" name="productTag" id="checkbox1" checked>
														<label for="checkbox1">
															<div class="i-checkbox z-checked">
																<i></i>
															</div>
															<span class="i-checkboxtxt">${t.t_product_tag_new }</span>
														</label>
													</div>
													<div class="u-checkboxitem">
														<input type="checkbox" class="u-ipt-checkbox" value="t_product_tag_hot" name="productTag" id="checkbox2">
														<label for="checkbox2">
															<div class="i-checkbox">
																<i></i>
															</div>
															<span class="i-checkboxtxt">${t.t_product_tag_hot }</span>
														</label>
													</div>
													<div class="u-checkboxitem">
														<input type="checkbox" class="u-ipt-checkbox" value="t_product_tag_half" name="productTag" id="checkbox3">
														<label for="checkbox3">
															<div class="i-checkbox">
																<i></i>
															</div>
															<span class="i-checkboxtxt">${t.t_product_tag_half }</span>
														</label>
													</div>
												</div>
											</div>
										  </div> 
										</div> 
									</div>
									<%-- <div class="audit">
										<div class="m-form-item putaway">
											<label class="m-form-hd">
												<span class="u-hd-txt">${t.t_img_desc }</span>
											</label>
											<div class="m-radiogroup">
												<div class="u-radioitem">
													<input type="radio" class="u-ipt-radio" name="goodsWay" id="radio1" checked="">
													<label for="radio1">
														<div class="i-radio  z-checked">
															<i></i>
														</div>
														<span class="i-radiotxt">æ¯</span>
													</label>
												</div>
												<div class="u-radioitem">
													<input type="radio" class="u-ipt-radio" name="goodsWay" id="radio2">
													<label for="radio2">
														<div class="i-radio">
															<i></i>
														</div>
														<span class="i-radiotxt">å¦</span>
													</label>
												</div>
											</div>
										</div>
									</div> --%>
								</div>
								<div class="img_describe">
									<div class="heade">${t.t_img_desc }</div>
									<div class="imgDescribe">
										<%-- <div class="show_img">
											<span class="labe">${t.t_show_img }: </span>
											<div class="upimg">
												<c:if test="${not empty img}">
													<img alt="" src="${img.imagePath}" width="100%" height="100%" style="padding:0;">
												</c:if>
												<c:if test="${empty img}">
													<input type="file" name="indexFile" id="indexFile">
													<span>${t.t_select }</span>
												</c:if>
											</div>
										</div> --%>
										
										<div class="photo_album">
											<span class="labe">${t.t_show_img }:</span>
											<c:if test="${not empty img}">
											<div class="upimg">
												<img alt="" src="${img.imagePath}" width="100%" height="100%" style="padding:0;">
											</div>
											</c:if>
											<div class="upimg">
												<input type="file" name="indexFile" id="indexFile">
													<span>${t.t_select }</span>
											</div>
										</div>
										
										<div class="photo_album">
											<span class="labe">${t.t_img_list }: </span>
											<c:forEach var="item" items="${imgList }">
												<div class="upimg">
													<img alt="" src="${item.imagePath}" width="100%" height="100%" style="padding:0;">
													<span class="deleteImg" imgid="${item.id }" style="backgroung:#aaa;z-index:10;position:relative;float:right;width:30px">x</span>
												</div>
											</c:forEach>
											<c:forEach var="i" begin="1" end="${inputCount }" step="1">
												<div class="upimg">
													<input type="file" name="listFile" id="listFile">
													<span>${t.t_select }</span>
												</div>
											</c:forEach>
										</div>
										<div class="product_description">
											<span class="labe">${t.t_desc }(${t.t_zh }): </span>
											<div class="text_editing">
												<textarea rows="15" cols="83" name="productDescripton" style="padding:0;margin:0;border:0">${entity.productDescripton }</textarea>
											</div>
										</div>
										<div class="product_description">
											<span class="labe">${t.t_desc }(${t.t_en }): </span>
											<div class="text_editing">
												<textarea rows="15" cols="83" name="productEnDescription" style="padding:0;margin:0;border:0">${entity.productEnDescription }</textarea>
											</div>
										</div>
									</div>
								</div>

									
								<div class="button">
									<div class="submit" onclick="jQuery.common.ajaxFileSubmit('#form','/front/sell/doSaveProduct',true,'/front/sell/productListPage')">${t.b_submit }</div>
									<%-- <div class="cancel">${t.b_cancel }</div> --%>
								</div>
							</div>
							</form>
							<!-- <div class="g-tabMnItem">
								选择属性
							</div> -->
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

<style>
.deleteImg{}
</style>
<script type="text/javascript">
$(function(){
	var confirmDelTip="${t.t_confirm_delete}";
	$('.menu_box .menu_list div.module a').eq(0).addClass('active');
	
	$('#productTypeId').optionSelect({
		compare:'${entity.productTypeId}',
		backFn : function(p) {
		}
	});
	
	$('#productSubtypeId').optionSelect({
		compare:'${entity.productSubtypeId}',
		backFn : function(p) {
		}
	});
	
	//删除
	$('.deleteImg').click(function(){
		 if(confirm(confirmDelTip)){
			 debugger;
			 var productId=$('input[name="id"]').val();
			 var imgId = $(this).attr('imgid');
			 var parm={};
			 parm.productId=productId;
			 parm.imgId=imgId;
			jQuery.common.deleteByParm(parm,'/front/sell/deleteByParm',true,'')	 
		 }
	});
	
});

</script>
</body>
</html>