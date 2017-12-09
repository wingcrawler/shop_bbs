<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../include/meta.jsp"></jsp:include>
<title>${t.t_product_upload}</title>
<link href="/frontstyle/sell/css/upload_product.css" rel="stylesheet"
	type="text/css">
<link href="/frontstyle/css/bootstrap.css" rel="stylesheet"
	type="text/css">
<link href="/frontstyle/css/layer.css" rel="stylesheet" type="text/css">
<script src="/frontstyle/sell/js/upload_product.js"></script>
<script src="/frontstyle/js/bootstrap.min.js"></script>
<script src="/frontstyle/js/layer.js"></script>
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
					<span>${t.m_product } > ${t.t_product_upload} </span>
				</div>
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
								<input type="hidden" id="id" value="${entity.id }" name="id">
								<div class="g-tabMnItem f-active">
									<div class="basic">
										<div class="classify">
											<div class="row">
												<div class="col-xs-12 col-md-12 commodity_terrace">
													<!-- åååç±» -->
													<div class="m-form-item">
														<label class="m-form-hd"> <span
															class="u-hd-require">*</span> <span class="u-hd-txt">${t.t_product_type }:</span>
														</label> <select class="u-ipt-select" name="productTypeId"
															id="productTypeId"
															onchange="jQuery.common.refreshSelect('#productTypeId','#productSubtypeId','/getProductTypeTwoLevel')">
															<option value="-1">-- ${t.t_select } --</option>
															<c:forEach items="${typeList}" var="item">
																<option value="${item.id}">${item.typeName}</option>
															</c:forEach>
														</select> <select class="u-ipt-select" name="productSubtypeId"
															id="productSubtypeId">
															<option value="-1">-- ${t.t_select } --</option>
															<c:forEach items="${subtypeList}" var="item">
																<option value="${item.id}">${item.typeName}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
										</div>
										<div class="message">
											<div class="row">
												<div class="col-xs-6 col-md-6">
													<div class="m-form-item">
														<label class="m-form-hd"> <span
															class="u-hd-require">*</span> <span class="u-hd-txt">${t.t_product_name }(${t.t_zh }):</span>
														</label> <input type="text" class="u-ipt-text" id="productName"
															placeholder="产品名称不为空字数少于30字" name="productName"
															value="${entity.productName }">
													</div>
												</div>
												<div class="col-xs-6 col-md-6">
													<div class="m-form-item">
														<label class="m-form-hd"> <span
															class="u-hd-require">*</span> <span class="u-hd-txt">${t.t_product_name }(${t.t_en }):</span>
														</label> <input type="text" class="u-ipt-text" id="productEnName"
															name="productEnName" placeholder="${t.p_product_name }"
															value="${entity.productEnName }">
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-xs-6 col-md-6">
													<div class="m-form-item">
														<label class="m-form-hd"> <span
															class="u-hd-require">*</span> <span class="u-hd-txt">${t.t_product_count }:</span>
														</label> <input type="number"
															oninput="if(value.length>9)value=value.slice(0,9)"
															class="u-ipt-text" name="productCount" id="productCount"
															value="${entity.productCount }">
													</div>
												</div>
												<div class="col-xs-6 col-md-6">
													<div class="m-form-item">
														<label class="m-form-hd"> <span
															class="u-hd-require">*</span> <span class="u-hd-txt">${t.t_price }:</span>
														</label> <input oninput="if(value.length>9)value=value.slice(0,9)"
															type="number" class="u-ipt-text" id="productPrice"
															name="productPrice" value="${entity.productPrice }">
														<span class="u-hd-txt">${t.t_price_type }:</span><select
															class="u-ipt-select" name="currenciesType"
															id="currenciesType"
															onchange="jQuery.common.refreshSelect('#productTypeId','#productSubtypeId','/getProductTypeTwoLevel')">
															<option value="-1">chose</option>

															<option value="rmb">RMB</option>

															<option value="cad" selected="selected">CAD</option>

															<option value="usd">USD</option>

														</select>

													</div>
												</div>
												<div class="row">
													<div class="col-xs-6 col-md-6">
														<div class="m-form-item">
															<label class="m-form-hd"> <span
																class="u-hd-require">*</span> <span class="u-hd-txt">${t.t_product_url}:</span>
															</label> <input type="text" class="u-ipt-text" name="productUrl"
																value="${entity.productUrl }">
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-xs-6 col-md-6">
														<div class="m-form-item">
															<label class="m-form-hd"> <span
																class="u-hd-require"></span> <span class="u-hd-txt">${t.t_product_tag }:</span>
															</label>
															<div class="m-checkboxgroup">
																<div class="u-checkboxitem">
																	<input type="checkbox" class="u-ipt-checkbox"
																		<c:if test="${fn:contains(entity.productTag, 't_product_tag_new')}">checked</c:if>
																		value="t_product_tag_new" name="productTag"
																		id="checkbox1"> <label for="checkbox1">
																		<div
																			class="i-checkbox <c:if test="${fn:contains(entity.productTag, 't_product_tag_new')}">z-checked</c:if>">
																			<i></i>
																		</div> <span class="i-checkboxtxt">${t.t_product_tag_new }</span>
																	</label>
																</div>
																<div class="u-checkboxitem">
																	<input type="checkbox" class="u-ipt-checkbox"
																		<c:if test="${fn:contains(entity.productTag, 't_product_tag_hot')}">checked</c:if>
																		value="t_product_tag_hot" name="productTag"
																		id="checkbox2"> <label for="checkbox2">
																		<div
																			class="i-checkbox <c:if test="${fn:contains(entity.productTag, 't_product_tag_hot')}">z-checked</c:if>">
																			<i></i>
																		</div> <span class="i-checkboxtxt">${t.t_product_tag_hot }</span>
																</div>
																<div class="u-checkboxitem">
																	<input type="checkbox" class="u-ipt-checkbox"
																		<c:if test="${fn:contains(entity.productTag, 't_product_tag_half')}">checked</c:if>
																		value="t_product_tag_half" name="productTag"
																		id="checkbox3"> <label for="checkbox3">
																		<div
																			class="i-checkbox <c:if test="${fn:contains(entity.productTag, 't_product_tag_half')}">z-checked</c:if>">
																			<i></i>
																		</div> <span class="i-checkboxtxt">${t.t_product_tag_half }</span>
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
											<div>
												<span class="u-hd-txt">${t.p_image }</span>
											</div>
											<div class="imgDescribe"
												style="display: none; padding-left: 1rem;" id="frame_div">
												<iframe src="/front/sell/goCutPicture" width="650px"
													height="600px"></iframe>
											</div>

											<div class="imgDescribe" id="form_div">

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

												<!-- 单张图片 -->
												<div class="photo_album">
													<span class="labe">${t.t_show_img }:</span>
													<div class="upimg">
														<input type="hidden" id="targetImgVal" name="targetImgVal">
														<img id="targetImg" alt="" src="${img.imagePath}"
															width="100%" height="100%" style="padding: 0;"> <span
															onclick="getImage('targetImg');"
															style="backgroung: #aaa; z-index: 10; position: relative; float: right; width: 50px">选择</span>
													</div>
													<!--<c:if test="${not empty img}">
												<div class="upimg">
													<img id="targetImg" alt="" src="${img.imagePath}" width="100%" height="100%" style="padding:0;">
													<span class="deleteImg" imgid="${img.id }" style="backgroung:#aaa;z-index:10;position:relative;float:right;width:30px">x</span>
												</div>
											</c:if>-->
													<!--<c:if test="${empty img}">
												<div class="upimg">
													<input type="hidden" id="targetImgVal" name="targetImgVal">
													<img id="targetImg" alt="" src="${img.imagePath}" width="100%" height="100%" style="padding:0;">
													<span onclick="getImage('targetImg');"   style="backgroung:#aaa;z-index:10;position:relative;float:right;width:50px">选择</span>
												</div>
											</c:if>-->
												</div>

												<!-- 图片列表 -->
												<div class="photo_album">
													<span class="labe">${t.t_img_list }: </span>
													<!--<c:forEach var="item" items="${imgList }">
												<div class="upimg">
													<img alt="" id="targetImg${i }" src="${item.imagePath}" width="100%" height="100%" style="padding:0;">
													<span class="deleteImg" imgid="${item.id }" style="backgroung:#aaa;z-index:10;position:relative;float:right;width:30px">x</span>
												</div>
											</c:forEach>-->
													<!--<c:forEach var="i" begin="1" end="5" step="1"> ${inputCount } -->
													<div class="upimg">
														<input type="hidden" id="targetImg${i}Val"
															name="targetImg${i}Val"> <img alt=""
															id="targetImg${i}" src="" width="100%" height="100%"
															style="padding: 0;"> <span class="deleteImg"
															onclick="getImage('targetImg'+${i});"
															style="backgroung: #aaa; z-index: 10; position: relative; float: right; width: 50px">选择</span>
													</div>
													<!--</c:forEach>-->
												</div>

												<div class="product_description">
													<span class="labe">${t.t_desc }(${t.t_zh }): </span>
													<div class="text_editing">
														<textarea rows="15" cols="83" id="productDescripton"
															name="productDescripton" placeholder="${t.p_description}"
															style="padding: 0; margin: 0; border: 0">${entity.productDescripton }</textarea>
													</div>
												</div>
												<div class="product_description">
													<span class="labe">${t.t_desc }(${t.t_en }):</span>
													<div class="text_editing">
														<textarea rows="15" cols="83" id="productEnDescription"
															name="productEnDescription"
															placeholder="${t.p_description}"
															style="padding: 0; margin: 0; border: 0">${entity.productEnDescription }</textarea>
													</div>
												</div>
											</div>
										</div>
									</div>
							</form>
							<div class="button">
								<button
									onclick="submitPoduct('#form','/front/sell/doSaveProduct1',true,'/front/sell/productListPage')"
									type="button" class="btn btn-danger btn-lg">${t.b_submit }</button>
							</div>
							<!-- <div class="g-tabMnItem">
								选择属性
							</div> -->
							<input type="hidden" id="curtImg">
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
.deleteImg {
	
}
</style>
	<script type="text/javascript">
$(function(){
	var confirmDelTip="${t.t_confirm_delete}";
	$('.menu_box .menu_list div.module a').eq(2).addClass('active');
	
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
	/*$('.deleteImg').click(function(){
		 if(confirm(confirmDelTip)){
			 debugger;
			 var productId=$('input[name="id"]').val();
			 var imgId = $(this).attr('imgid');
			 var parm={};
			 parm.productId=productId;
			 parm.imgId=imgId;
			jQuery.common.deleteByParm(parm,'/front/sell/deleteByParm',true,window.location.href)	 
		 }
	});*/
	//初始化图片信息
	var curId=$("#id").val();
	if(curId){
		$.ajax({
			type:"get",
			dateType:"json",
			url:"/front/sell/doEditProduct?id="+curId,
			data:{},
			success:function(ret){
				console.log(ret);
					$("#targetImg").attr('src',ret.imgCover.imagePath);
					$("#targetImgVal").val(ret.imgCover.imagePath);
					var picList=ret.imgList;
					for(var i=0;i<picList.length;i++){
						console.log(picList[i].indexShow);
						var _ind=picList[i].indexShow-1;
						$("#targetImg"+_ind).attr('src',picList[i].imagePath);
						$("#targetImg"+_ind+"Val").val(picList[i].imagePath);
					}
				}
	   });
	}
	
	
});
function submitPoduct(elem ,_submitUrl,_isRefrush, _jumpUrl) {
	var flag=checkProductData();
	if(!flag){
		return;
	}
	var maxImg=1024*1024*4;
	var imgStr=$("#targetImgVal").val();
	if(imgStr.length>maxImg){
		alert(1+"${t.product_img_large}");
		return;
	}
	imgStr=$("#targetImg1Val").val();
	if(imgStr.length>maxImg){
		alert(2+"${t.product_img_large}");
		return;
	}
	 imgStr=$("#targetImg2Val").val();
	if(imgStr.length>maxImg){
		alert(3+"${t.product_img_large}");
		return;
	}
	 imgStr=$("#targetImg3Val").val();
	if(imgStr.length>maxImg){
		alert(4+"${t.product_img_large}");
		return;
	}
	 imgStr=$("#targetImg4Val").val();
	if(imgStr.length>maxImg){
		alert(5+"${t.product_img_large}");
		return;
	}
	 imgStr=$("#targetImg5Val").val();
	if(imgStr.length>maxImg){
		alert(6+"${t.product_img_large}");
		return;
	}
	$(elem).ajaxSubmit({  
        type:'post',  
        cache: false,  
        url: _submitUrl, 
        dataType : 'json', //返回值类型 一般设置为json  
        success : function(data, status) {
        	console.log(data);
        	console.log(status);
        	if(data.errorNo==0){
        		if(_isRefrush){
        			self.location= _jumpUrl;
        		}
			} else {
				alert("System error");
			}
        },  
        error : function(data, status, e) {  
        	alert("System exception"); 
        }   
    });
}
function checkProductData(){
	var productType=$("#productTypeId").val();
	if(productType==-1){
		alert('${t.error_productType_empty}');
		return false;
	}
	var productName=$("#productName").val();
	if(productName==""){
		alert('${t.error_productNameCH_empty}');
		return false;
	}
	var productEnName=$("#productEnName").val();
	if(productEnName==""){
		alert('${t.error_productNameUS_empty}');
		return false;
	}
	if(productName.length>80){
		alert('${t.error_productNameCH_length}');
		return false;
	}
	if(productEnName.length>80){
		alert('${t.error_productNameUS_length}');
		return false;
	}
	var targetImgSrc=$("#targetImg").attr("src");
	if(targetImgSrc==""){
		alert('${t.error_productCover_empty}');
		return false;
	}
	var productCount=$("#productCount").val();
	var productCountPar=/^\d+$/;
	if(!productCountPar.test(productCount)){
		alert('${t.error_productNum}');
		return false;
	}
	var productPrice=$("#productPrice").val();
	var pricePar=/^-?\d+\.?\d+$/;
	if(!pricePar.test(productPrice)){
		alert('${t.error_productPrice}');
		return false;
	}
	var productDescripton=$("#productDescripton").val();
	if(productDescripton.length>3000){
		alert('${t.error_productCH_des}');
		return false;
	}
	var productEnDescription=$("#productEnDescription").val();
	if(productEnDescription.length>3000){
		alert('${t.error_productUS_des}');
		return false;
	}
	return true;
}
function triggerSelect(elem){
	$(elem).trigger('click');
	
}
//<div style="float:left"> <img id="img0doc" style="display: block; width: 145px; height: 145px;" src=""> </div>
function getImage(_id){
	$("#curtImg").val(_id);
	$("#form_div").hide();
	$("#frame_div").show();
}
</script>
</body>
</html>