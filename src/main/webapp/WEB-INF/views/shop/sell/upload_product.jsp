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
				<div class="header"><span>äº§åç®¡ç > äº§åä¸ä¼ </span></div>
				<div class="product_message">
					<div class="g-tabWrap">
						<div class="g-tabHdWrap">
							<ul class="g-tabHd">
								<li class="f-active"><span>åºæ¬ä¿¡æ¯</span></li>
								<li class=""><span>å±æ§éæ©</span></li>
							</ul>
						</div>
						<div class="g-tabMn">
							<div class="g-tabMnItem f-active">
								<div class="basic">
									<div class="classify">
										<div class="row">
											<div class="col-xs-6 col-md-6 commodity_terrace"><!-- åååç±» -->
												<div class="m-form-item">
													<label class="m-form-hd">
														<span class="u-hd-require">*</span>
														<span class="u-hd-txt">åååç±»(å¹³å°):</span>
													</label>
													<select class="u-ipt-select">
														<option>ä¸çº§åç±»1</option>
														<option>ä¸çº§åç±»2</option>
														<option>ä¸çº§åç±»3</option>
													</select>
													<select class="u-ipt-select">
														<option>äºçº§åç±»1</option>
														<option>äºçº§åç±»2</option>
														<option>äºçº§åç±»3</option>
													</select>
												</div>
											</div>
										    <div class="col-xs-6 col-md-6 brand"><!-- åç -->
												<div class="m-form-item">
													<label class="m-form-hd">
														<span class="u-hd-require"></span>
														<span class="u-hd-txt">åç:</span>
													</label>
													<select class="u-ipt-select">
														<option>111</option>
														<option>222</option>
														<option>333</option>
													</select>
												</div>
											</div>
										</div>
										<div class="row">
										    <div class="col-xs-6 col-md-12 commodity_store"><!-- åååç±» -->
												<div class="m-form-item">
													<label class="m-form-hd">
														<span class="u-hd-require">*</span>
														<span class="u-hd-txt">åååç±»(åºéº):</span>
													</label>
													<select class="u-ipt-select">
														<option>ä¸çº§åç±»1</option>
														<option>ä¸çº§åç±»2</option>
														<option>ä¸çº§åç±»3</option>
													</select>
													<select class="u-ipt-select">
														<option>äºçº§åç±»1</option>
														<option>äºçº§åç±»2</option>
														<option>äºçº§åç±»3</option>
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
													<span class="u-hd-txt">åååç§°:</span>
												</label>
												<input type="text" class="u-ipt-text">
											</div>
										  </div>
										  <div class="col-xs-6 col-md-6">
										  	<div class="m-form-item">
												<label class="m-form-hd">
													<span class="u-hd-require"></span>
													<span class="u-hd-txt">å¯æ é¢:</span>
												</label>
												<input type="text" class="u-ipt-text">
											</div>
										  </div>
										</div>
										<div class="row">
										  <div class="col-xs-6 col-md-6">
												<div class="m-form-item">
													<label class="m-form-hd">
														<span class="u-hd-require">*</span>
														<span class="u-hd-txt">xxxx:</span>
													</label>
													<input type="text" class="u-ipt-text">
												</div>
										  </div>
										  <div class="col-xs-6 col-md-6">
												<div class="m-form-item">
													<label class="m-form-hd">
														<span class="u-hd-require">*</span>
														<span class="u-hd-txt">xxxx:</span>
													</label>
													<input type="text" class="u-ipt-text">
												</div>
										  </div>
										</div>
										<div class="row">
										  <div class="col-xs-6 col-md-6">
												<div class="m-form-item">
													<label class="m-form-hd">
														<span class="u-hd-require">*</span>
														<span class="u-hd-txt">xxxx:</span>
													</label>
													<input type="text" class="u-ipt-text">
												</div>
										  </div>
										  <div class="col-xs-6 col-md-6">
												<div class="m-form-item">
													<label class="m-form-hd">
														<span class="u-hd-require">*</span>
														<span class="u-hd-txt">xxxx:</span>
													</label>
													<input type="text" class="u-ipt-text">
												</div>
										  </div>
										</div>
										<div class="row">
										  <div class="col-xs-6 col-md-6">
											<div class="m-form-item">
												<label class="m-form-hd">
													<span class="u-hd-require"></span>
													<span class="u-hd-txt">æ ç­¾:</span>
												</label>
												<div class="m-checkboxgroup">
													<div class="u-checkboxitem">
														<input type="checkbox" class="u-ipt-checkbox" name="" id="checkbox1" checked="">
														<label for="checkbox1">
															<div class="i-checkbox z-checked">
																<i></i>
															</div>
															<span class="i-checkboxtxt">æ°å</span>
														</label>
													</div>
													<div class="u-checkboxitem">
														<input type="checkbox" class="u-ipt-checkbox" name="" id="checkbox2">
														<label for="checkbox2">
															<div class="i-checkbox">
																<i></i>
															</div>
															<span class="i-checkboxtxt">ç­å</span>
														</label>
													</div>
													<div class="u-checkboxitem">
														<input type="checkbox" class="u-ipt-checkbox" name="" id="checkbox3">
														<label for="checkbox3">
															<div class="i-checkbox">
																<i></i>
															</div>
															<span class="i-checkboxtxt">5æ</span>
														</label>
													</div>
												</div>
											</div>
										  </div>
										  <div class="col-xs-6 col-md-6">
												<div class="m-form-item">
													<label class="m-form-hd">
														<span class="u-hd-require"></span>
														<span class="u-hd-txt">å»ºè®®ä»·:</span>
													</label>
													<input type="text" class="u-ipt-text">
												</div>
										  </div>
										</div>
									</div>
									<div class="audit">
										<div class="m-form-item putaway"><!-- æ¯å¦å®¡æ ¸å®æ¯ä¸æ¶ -->
											<label class="m-form-hd">
												<span class="u-hd-txt">æ¯å¦å®¡æ ¸å®æ¯ä¸æ¶ï¼</span>
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
									</div>
								</div>
								<div class="img_describe">
									<div class="heade">å¾çä¸æè¿°</div>
									<div class="imgDescribe">
										<div class="show_img">
											<span class="labe">å±ç¤ºå¾çï¼</span>
											<div class="upimg">
												<input type="file">
												<span>éæ©æä»¶</span>
											</div>
										</div>
										<div class="photo_album">
											<span class="labe">ç¸åï¼</span>
											<div class="upimg">
												<input type="file">
												<span>éæ©æä»¶</span>
											</div>
											<div class="upimg">
												<input type="file">
												<span>éæ©æä»¶</span>
											</div>
											<div class="upimg">
												<input type="file">
												<span>éæ©æä»¶</span>
											</div>
											<div class="upimg">
												<input type="file">
												<span>éæ©æä»¶</span>
											</div>
											<div class="upimg">
												<input type="file">
												<span>éæ©æä»¶</span>
											</div>
										</div>
										<div class="product_description">
											<span class="labe">ååæè¿°ï¼</span>
											<div class="text_editing">
												<span class="test">æå­ç¼è¾æä»¶åºå</span>
											</div>
										</div>
									</div>
								</div>

									
										<div class="button">
											<div class="submit">æäº¤å®¡æ ¸</div>
											<div class="cancel">åæ¶</div>
										</div>
							</div>
							<div class="g-tabMnItem">
								å±æ§éæ©çåå®¹
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