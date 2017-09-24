<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>${t.title_product_edit }</title>
</head>
<body class="page-body">
	<div class="page-container">
		<!-- 左边栏 -->
		<jsp:include page="../include/left.jsp"></jsp:include>
		<div class="main-content">
			<!-- 头部栏 -->
			<jsp:include page="../include/header.jsp"></jsp:include>	
			
			<!-- 编辑区 -->
			<div class="row">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<a><i class="fa-location-arrow"> ${t.title_product_edit }</i></a> 
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-horizontal form" action="javascript:void(0);">
					        <div class="form-group">
					          	<div class="col-sm-3">
					          		<input type="hidden" value="${entity.id}" name="id" />
					          		<p>${t.t_product_name }(${t.t_zh })</p>
					            	<input class="form-control" type="text" name="productName"
					            	oninput="if(value.length>90)value=value.slice(0,90)" value="${entity.productName}">
					          	</div>
					          	<div class="col-sm-3">
					          		<p>${t.t_product_name }(${t.t_en })</p>
					            	<input class="form-control" type="text" name="productEnName" 
					            	oninput="if(value.length>90)value=value.slice(0,90)" value="${entity.productEnName}">
					          	</div>
					          	<div class="col-sm-3">
					          		<p>${t.t_price }</p>
					            	<input class="form-control" type="text" name="productPrice" oninput="if(value.length>9)value=value.slice(0,9)"
					            	 value="${entity.productPrice}">
					          	</div>
					          	<div class="col-sm-3">
					          		<p>${t.t_weight }</p>
					            	<input class="form-control" type="text" name="productRank" oninput="if(value.length>9)value=value.slice(0,9)"
					            	 value="${entity.productRank}">
					          	</div>
					          	
					        </div>
					        <div class="form-group">
					        	<div class="col-sm-3">
					          		<p>${t.t_product_view }</p>
					            	<input class="form-control" type="text" name="productView"
					            		oninput="if(value.length>9)value=value.slice(0,9)"
					            	 value="${entity.productView}">
					          	</div>
					        	<div class="col-sm-3">
					          		<p>${t.t_product_status }</p>
					          		<select class="form-control select" name="productStatus" id="StatusSelect">
										<option value="0">${t.t_pending }</option>
										<option value="1">${t.t_product_on }</option>
										<option value="2">${t.t_product_off }</option>
									</select>
					          	</div>
					          	<div class="col-sm-3">
					          		<p>${t.t_product_count }</p>
					            	<input class="form-control" type="text" oninput="if(value.length>9)value=value.slice(0,9)"  
					            	name="productCount" value="${entity.productCount}">
					          	</div>
					          	<div class="col-sm-3">
					          		<p>${t.t_shop_name }</p>
					            	<input class="form-control" type="text" readonly="readonly" value="${entity.shopName}">
					          	</div>
					          	
					        </div>
					        <div class="form-group">
					          	<div class="col-sm-6">
					          		<p>${t.t_product_type }</p>
					            	<%-- <input class="form-control" type="text" readonly="readonly" value="${entity.typeName}"> --%>
						          	<select class="" style="width:150px;height:30px;" name="productTypeId" id="productTypeId" onchange="jQuery.common.refreshSelect('#productTypeId','#productSubtypeId','/getProductTypeTwoLevel')">
						          		<%-- <option value="-1">-- ${t.t_select } --</option> --%>
										<c:forEach items="${typeList}" var="item">
										<option value="${item.id}">${item.typeName}</option>
										</c:forEach>
									</select>
									<select class="" style="width:150px;height:30px;" name="productSubtypeId" id="productSubtypeId">
										<option value="-1">-- ${t.t_select } --</option>
										<c:forEach items="${subtypeList}" var="item">
										<option value="${item.id}">${item.typeName}</option>
										</c:forEach>
									</select>
								</div>
					        </div>
					        <div class="form-group">
					        	<div class="col-sm-6">
					          		<p>${t.t_desc }(${t.t_zh })</p>
					          		<textarea rows="6" cols="80" oninput="if(value.length>1000)value=value.slice(0,1000)"
					          		 name="productDescripton">${entity.productDescripton}</textarea>
					          	</div>
					        </div>
					        <div class="form-group">
					          	<div class="col-sm-6">
					          		<p>${t.t_desc }(${t.t_en })</p>
					          		<textarea rows="6" cols="80" oninput="if(value.length>1000)value=value.slice(0,1000)" 
					          		name="productEnDescription">${entity.productEnDescription}</textarea>
					          	</div>
					        </div>
					        <div class="form-group">
					        	<div class="col-sm-12">
					        		<div class="mail-single-attachments">
										<h3>
											<i class="linecons-attach"></i>${t.t_img_list }
										</h3>
										<ul class="list-unstyled list-inline">
										<c:if test="${empty imgList }">
										No Images
										</c:if>
										<c:forEach items="${imgList}" var="item">
											<li>
												<a href="#" class="thumb">
													<img src="${item.imagePath }" class="img-thumbnail">
												</a>
												<!-- <a href="#" class="name">
													IMG_007.jpg
													<span>14KB</span>
												</a> -->
											</li>
										</c:forEach>
										</ul>
									</div>
					        	</div>
					        </div>
					        <div class="form-group">
					        	<div class="col-sm-12">
					        		<a id="submit" class="btn btn-info" href="javascript:void(0);">${t.b_submit }</a>
					    		</div>
					        </div>
					     </form>
					</div>
				</div>
			</div>
			<!-- 编辑区结束 -->
			
		</div>
	</div>
	
<script type="text/javascript">
$(function(){
	$('#main-menu li.li').removeClass('active').removeClass('opened');
	$('#main-menu li.li').eq(2).addClass('active').addClass('opened');
	$('#main-menu li.li').eq(2).find('ul li').eq(0).addClass('active');
	
	$('#StatusSelect').optionSelect({
		compare:'${entity.productStatus}',
		backFn : function(p) {
		}
	});
	
	$('#submit').click(function(){
        var parm = $.fn.getFormJson('.form');
		$.fn.doSave(parm,'/backend/product/doSave','/backend/product/edit?id=${entity.id}');
	});
	
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
});
</script>
</body>
</html>