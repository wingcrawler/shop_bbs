<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>${t.t_store_edit }</title>
	<script src="/ue/ueditor.config.js"></script>
	<script src="/ue/ueditor.all.js"></script>
	<!-- <script type="text/javascript" charset="utf-8" src="/ue/lang/en/en.js"></script> -->
	<script type="text/javascript" charset="utf-8" src="/ue/lang/zh-cn/zh-cn.js"></script>
	<link href="/ue/themes/default/css/ueditor.css" rel="stylesheet">
</head>
<body class="page-body">
	<div class="page-container">
		<!-- 左边栏 -->
		<jsp:include page="../include/left.jsp"></jsp:include>
		<div class="main-content">
			<!-- 头部栏 -->
			<jsp:include page="../include/header.jsp"></jsp:include>	
			
			<!-- 内容区 -->
			<div class="row">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<a><i class="fa-location-arrow"> ${t.t_store_edit }</i></a> 
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-horizontal form" method="post" enctype="multipart/form-data">
					        <div class="form-group">
					          	<div class="col-sm-3">
					          		<input type="hidden" value="${entity.id}" name="id" />
					          		<p>${t.t_shop_name }<span style="color:#f00">*</span></p>
					            	<input class="form-control" type="text" name="shopTitle"  value="${entity.shopTitle}">
					          	</div>
					        	<div class="col-sm-3">
					          		<p>${t.t_status }</p>
					          		<select class="form-control select" id="shopStatus" name="shopStatus">
										<option value="0">${t.t_pending }</option>
										<option value="1">${t.t_on }</option>
									</select>
					          	</div>
					          	<div class="col-sm-3">
					          		<p>${t.t_star }</p>
					          		<select class="form-control select" id="shopLevel" name="shopLevel">
										<option value="0">${t.t_select }</option>
										<option value="1">${t.t_star1 }</option>
										<option value="2">${t.t_star2 }</option>
										<option value="3">${t.t_star3 }</option>
										<option value="4">${t.t_star4 }</option>
										<option value="5">${t.t_star5 }</option>
									</select>
					          	</div>
					          	<div class="col-sm-3">
					        		<p>${t.t_weight }</p>
					            	<input class="form-control" type="text" name="shopRank" value="${entity.shopRank}">
					    		</div>
					        </div>
					        
					        <!-- 商家logo -->
					        <div class="form-group">
					        	<div class="col-sm-12">
					        		<p>${t.t_shop_logo }</p>
					        		<img border="0" width="30%" src="${entity.shopLogoImg}" />
					    		</div>
					        </div>
					        <!-- 商家logo -->
					        
					        <!-- 商家营业执照 -->
					        <div class="form-group">
					        	<div class="col-sm-12">
					        		<p>${t.t_shop_licenes_desc }</p>
					        		<textarea name="shopLicenesDesc" cols="80" rows="6">${entity.shopLicenesDesc }</textarea>
					        	
					        		<p>${t.t_business_license }</p>
					        		<img border="0" width="30%" src="${entity.shopLicenesImg}" />
					    		</div>
					        </div>
					        <!-- 商家营业执照 -->
					        
					         <!-- 上架资质 -->
					        <div class="form-group">
					        	<div class="col-sm-12">
					        		<p>${t.t_shelf_qualification_desc }</p>
					        		<textarea name="shelfQualificationDesc" cols="80" rows="6">${entity.shelfQualificationDesc }</textarea>
					        	
					        		<p>${t.t_business_qualif }</p>
					        		<img border="0" width="30%" src="${entity.shelfQualificationImg}" />
					    		</div>
					        </div>
					        <!-- 上架资质 -->
					        
					        <!-- 商家环境 -->
					        <div class="form-group">
					        	<div class="col-sm-12">
									<div class="panel panel-default">
										<div class="panel-heading">${t.t_shop_envir }</div>
										<div class="panel-body">
											<p>${t.t_shop_desc_envir }</p>
					        				<textarea name="shopDescription" cols="80" rows="6">${entity.shopDescription }</textarea>
											
											<p>${t.t_shop_img_envir }</p>
											<c:forEach var="item" items="${imageList }">
												<img border="0" width="30%" src="${item.imagePath}" />
											</c:forEach>
										</div>
									</div>
					    		</div>
					        </div>
					        <!-- 商家环境 -->
					        
					        <div class="form-group">
					        	<div class="col-sm-12">
					        		<a id="submit" class="btn btn-info" href="javascript:void(0);">${t.b_submit }</a>
					    		</div>
					        </div>
					     </form>
					</div>
				</div>
			</div>
			<!-- 内容区结束 -->
			
		</div>
	</div>
	
<script type="text/javascript">
$(function(){
	$('#main-menu li.li').removeClass('active').removeClass('opened');
	$('#main-menu li.li').eq(4).addClass('active').addClass('opened');
	$('#main-menu li.li').eq(4).find('ul li').eq(0).addClass('active');
	
	$('#shopStatus').optionSelect({
		compare:'${entity.shopStatus}',
		backFn : function(p) {
		}
	});
	$('#shopLevel').optionSelect({
		compare:'${entity.shopLevel}',
		backFn : function(p) {
		}
	});
	
	$('#submit').click(function(){
		$.fn.myAjaxSubmit(".form", "/backend/shop/doSave", "/backend/shop/list");
	});
	
});
</script>
</body>
</html>