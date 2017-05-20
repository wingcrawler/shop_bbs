<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>${t.t_product_detail }</title>
	<link href="/frontstyle/sell/css/product_details.css" rel="stylesheet" type="text/css">
	<script src="/frontstyle/sell/js/product_details.js"></script>
</head>
<body>
	<!-- alertView -->
	<section id="alertView" class='markbg alertToggle' onclick="hideBigImg()">
            <div class="popup">
                 <img id="bigImgC" src="images/img1001.jpg">
            </div>
     </section>
	
	<jsp:include page="../include/header.jsp"></jsp:include>
	
	<!-- main -->
	<div class="main">
		<!-- container -->
		<div class="container">
			<jsp:include page="include/left.jsp"></jsp:include>
			
			<!-- companyData -->
			<div class="companyData">
				<div class="header"><span>${t.m_product } >  ${t.m_product_list } > ${t.t_product_detail }</span></div>
				<div class="product_message">
					<div class="g-tabWrap">
						<div class="g-tabHdWrap">
							<ul class="g-tabHd">
								<li class="f-active"><span>${t.t_basic_info }</span></li>
							</ul>
						</div>
						<div class="g-tabMn">
							<div class="g-tabMnItem f-active">
								<div class="basic">
									<div class="classify">
										<div class="row">
											<div class="col-xs-6 col-md-6 commodity_terrace">
												<div class="m-form-item">
													<label class="m-form-hd">
														<span class="u-hd-txt">${t.t_product_type }:</span>
													</label>
													<div class="exhibition">
														<span>${entity.typeOne }</span>
														<span>${entity.typeTwo }</span>
													</div>
												</div>
											</div>
										    <div class="col-xs-6 col-md-6 commodity_store">
												<div class="m-form-item">
													<label class="m-form-hd">
														<span class="u-hd-txt">${t.t_product_status }:</span>
													</label>
													<div class="exhibition">
														<span class="red">${entity.statusStr }</span>
													</div>
												</div>
										    </div>
										</div>
									</div>
									<div class="message">
										<div class="row">
										  <div class="col-xs-6 col-md-6">
										  	<div class="m-form-item">
												<label class="m-form-hd">
													<span class="u-hd-txt">${t.t_product_name }(${t.t_zh }):</span>
												</label>
												<span class="u-text">${entity.productName }</span>
											</div>
										  </div>
										  <div class="col-xs-6 col-md-6">
										  	<div class="m-form-item">
												<label class="m-form-hd">
													<span class="u-hd-txt">${t.t_product_count }:</span>
												</label>
												<span class="u-text">${entity.count }</span>
											</div>
										  </div>
										</div>
										<div class="row">
										  <div class="col-xs-6 col-md-6">
												<div class="m-form-item">
													<label class="m-form-hd">
														<span class="u-hd-txt">${t.t_product_view }:</span>
													</label>
													<span class="u-text">${entity.view }</span>
												</div>
										  </div>
										  <div class="col-xs-6 col-md-6">
												<div class="m-form-item">
													<label class="m-form-hd">
														<span class="u-hd-txt">${t.t_product_url_click }:</span>
													</label>
													<span class="u-text">${entity.urlClick }</span>
												</div>
										  </div>
										</div>
										<div class="row">
										  <div class="col-xs-6 col-md-6">
												<div class="m-form-item">
													<label class="m-form-hd">
														<span class="u-hd-txt">${t.t_price }:</span>
													</label>
													<span class="u-text">${entity.price }</span>
												</div>
												<div class="col-xs-6 col-md-6">
												  	<div class="m-form-item">
														<label class="m-form-hd">
															<span class="u-hd-txt">${t.t_product_name }(${t.t_en }):</span>
														</label>
														<span class="u-text">${entity.productNameEn }</span>
													</div>
												  </div>
										  </div>
										</div>
									</div>
								</div>
								<div class="img_describe">
									<div class="heade">${t.t_img_desc }</div>
									<div class="imgDescribe">	
										<div class="show_img">
											<span class="labe">${t.t_show_img }: </span>
											<div class="upimg">
												<img src="${entity.showImg.imagePath }" onClick="showBigImg(this)">
											</div>
										</div>
										<c:if test="${not empty entity.imgList }">
										<div class="photo_album">
											<span class="labe">${t.t_img_list }: </span>
											<c:forEach var="item" items="${entity.imgList }">
												<div class="upimg">
												<img src="${item.imagePath }" onClick="showBigImg(this)">
											</div>	
											</c:forEach>
										</div>
										</c:if>
										<div class="product_description">
											<span class="labe">${t.t_desc }(${t.t_zh }): </span>
											<div class="text_editing">
												<span class="test">${entity.description }</span>
											</div>
										</div>
										<div class="product_description">
											<span class="labe">${t.t_desc }(${t.t_en }): </span>
											<div class="text_editing">
												<span class="test">${entity.descriptionEn }</span>
											</div>
										</div>
									</div>
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
</body>

</html>