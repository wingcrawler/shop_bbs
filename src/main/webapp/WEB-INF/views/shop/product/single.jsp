<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>${product.productName }</title>

	<link rel="stylesheet" href="/frontstyle/css/etalage.css">
	<link rel="stylesheet" href="/frontstyle/css/single.css">
	<script src="/frontstyle/js/jquery.etalage.min.js"></script>
	<script>
		jQuery(document).ready(function($){
			$('#etalage').etalage({
				thumb_image_width: 300,
				thumb_image_height: 300,
				source_image_width: 800,
				source_image_height: 1000,
				show_hint: true,
				click_callback: function(image_anchor, instance_id){
					alert('Callback example:\nYou clicked on an image with the anchor: "'+image_anchor+'"\n(in Etalage instance: "'+instance_id+'")');
				}
			});
		});
	</script>
	
</head>
<body>
	<jsp:include page="../include/header.jsp"></jsp:include>
	
	<!-- single -->
	<div class="single">
		<!-- container -->
		<div class="container">
			<div class="single-grids">
				<div class="col-md-9">
					<div class="single-left-left">
						<ul id="etalage" class="etalage" style="display: block; width: 300px; height: 533px;">
							<c:forEach var="item" items="${imgList }" varStatus="index">
								<c:if test="${index.count==1 }">
									<li class="etalage_thumb thumb_${index.count } etalage_thumb_active" style="display: list-item; opacity: 1; background-image: none;">
										<img class="etalage_thumb_image" src="${item.imagePath }" style="display: inline; width: 300px; height: 300px; opacity: 1;">
										<img class="etalage_source_image" src="${item.imagePath }" title="">
									</li>
								</c:if>
								<c:if test="${index.count>1 }">
									<li class="etalage_thumb thumb_${index.count } etalage_thumb_active" style="display: none; opacity: 0; background-image: none;">
										<img class="etalage_thumb_image" src="${item.imagePath }" style="display: inline; width: 300px; height: 300px; opacity: 1;">
										<img class="etalage_source_image" src="${item.imagePath }" title="">
									</li>
								</c:if>
							</c:forEach>
						</ul>
						<div class="clearfix"></div>		
					</div>
					<div class="single-left-right">
						<div class="single-left-info">
							<h3>${product.productName }</h3>
							<a href="#product-details" class="view">${t.t_view_detail }</a>
							<p>${product.currenciesType} ${product.productPrice }</p>
							<p><a href="/shop/product?shopId=${shop.id }" class="view">${t.t_shop_name }: ${shop.shopTitle }</a></p>
						</div>
						<div class="select-size">
							<div class="buy-now">
								<a href="${product.productUrl }">${t.t_buynow }</a>
							</div>
							<div class="clearfix"> </div>
							<!-- <div class="free">
								<p>20 day returns Free Delivery *</p>
							</div> -->
							<!-- <div class="delivery">
								<a class="play-icon popup-with-zoom-anim" href="#small-dialog">Check delivery options</a>
								<div id="small-dialog" class="mfp-hide">
									<h3>DELIVERY TIME & COD AVAILABILITY</h3>
									<div class="social-sits">
										<p>Please enter your PIN Code to check delivery time & Cash On Delivery availability</p>
									</div>
									<div class="signup">
											<form>
												<input type="text" class="email" placeholder="Pin" maxlength="6" required="required" pattern="[1-9]{1}\d{5}"/>
												<input type="submit"  value="Submit"/>
											</form>
									</div>
									<div class="clearfix"> </div>
								</div>
							</div> -->
						</div>
					</div>
					<div class="clearfix"> </div>
					
					<!-- 产品描述 -->
					<div class="product-details" id="product-details" name="product-details">
						<h3>${t.t_product_detail }</h3>
						<p>${product.productDescripton }</p>
					</div>
					<!-- 产品描述 -->
					
					<!-- 相关产品 -->
					<div class="related">
						<h3>${t.t_related_product }</h3>
						<div class="related-grids">
						<c:forEach var="item" items="${relateList }" varStatus="index">
							<div class="related-grid">
								<div class="col-md-9 related-left">
									<div class="col-md-3 related-left-left">
										<img src="${item.imagePath }" alt="" />
									</div>
									<div class="col-md-9 related-left-right">
										<h5>${item.productName }</h5>
										<p>${item.productDescripton }
										</p>
									</div>
									<div class="clearfix"> </div>
								</div>
								<div class="col-md-3 related-right">
									<p>${item.currencies_type} ${item.productPrice }</p>
									<a href="/product/single?productId=${item.id }">${t.t_view_detail }</a>
								</div>
								<div class="clearfix"> </div>
							</div>
						</c:forEach>
						</div>
					</div>
					<!-- 相关产品 -->
					
					<!-- 产品评论 -->
					<div class="related">
						<h3>${t.t_product_comment }</h3>
						<c:if test="${isLogin }">
						<div id="answerArea" class="edit">
							<textarea id="textArea" name="context"></textarea>
							<div class="edit_btn">
								<span id="cancel_myLetter_2"><input id="letter" type="checkbox" name="letterCheck" value="1" >${t.t_private_letter }</span>
								<span id="cancel_myLetter_2" class="cancel" onclick="clickCancel(this)">${t.b_cancel }</span>
								<span id="sure_myLetter_2" class="affirm" onclick="clickSure(this)">${t.b_confirm }</span>
							</div>
						</div>
						</c:if>
						<div class="related-grids">
							<ul>
								<c:forEach var="item" items="${commentPage.list }">
									<li class="list">
										<div class="item">
											<div class="headline">
												<div class="head_text">
													<div class="one">${item.content }</div>
													<c:forEach var="reply" items="${item.replyList }">
														<div class="single_reply">${reply.postName } ${t.t_reply }：${reply.content }</div>
														<div>${reply.date }</div>
													</c:forEach>	
												</div>
												<div class="head_portrait">
													<div class="img_head_portrait">
														<c:if test="${not empty item.userImage }">
														
														</c:if>
														<img src="${item.userImage }">
													</div>
													<div class="name_time">
														<p class="name">${item.postName }</p>
														<p class="time">${item.date }</p>
													</div>
	
												</div>
											</div>
										</div>
									</li>		
								</c:forEach>	
							</ul>
						</div>
					</div>
				<!-- 产品评论 -->
				</div>
				
				<!-- 产品类别 -->
				<div class="col-md-3 side-bar">
					<div class="categories">
						<h3>${t.t_product_type }</h3>
						<ul>
							<c:forEach var="item" items="${productTypeList }">
								<li>
								<a href="/product/list?parentType=${item.key.id }"  target="_blank">${item.key.typeName }</a>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<!-- 产品类别 -->
				
			<div class="clearfix"> </div>
			</div>
		</div>
		<!-- //container -->
	</div>
	<!-- //single -->
	
	<jsp:include page="../include/footer.jsp"></jsp:include>
	
<script type="text/javascript">
var productId = '${product.id}';
function clickCancel(event) {
    $("#textArea").val("");
}

function clickSure(event) {
	var letterCheck = $('input[name="letterCheck"]:checked').val();
    var context = $("#textArea").val();
    var parm = {};
    parm.context = context;
    parm.productId = productId;
	if(letterCheck==1){
		parm.messageContext=context;
		$("#textArea").val('');
		jQuery.common.saveObjByParm(parm,'/product/postMessage',false,'');
	} else {
		jQuery.common.saveObjByParm(parm,'/comment/doComment',true,'');	
	}
}
</script>
</body>
</html>