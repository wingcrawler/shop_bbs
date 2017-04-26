<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>${t.m_product }</title>
	
</head>
<body class="page-body">
	<div class="page-container">
		<!-- 左边栏 -->
		<jsp:include page="../include/left.jsp"></jsp:include>
		<div class="main-content">
			<!-- 头部栏 -->
			<jsp:include page="../include/header.jsp"></jsp:include>	
			
			<!-- 搜索区 -->
			<div class="row">
				<div class="col-sm-12 panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">${t.search_box }</h3>
					</div>
					<div class="panel-body">
						<form class="form-horizontal form" id="form" action="javascript:void(0);">
							<div class="form-group">
								<div class="col-sm-3">
									${t.t_product_name }:
									<input type="text" class="form-control input" name="productName" value="">
								</div>
								<div class="col-sm-3">
									${t.t_product_status }:
									<select class="form-control select" name="productStatus" id="StatusSelect">
										<option value="">-- ${t.t_select } --</option>
										<option value="0">${t.t_pending }</option>
										<option value="1">${t.t_product_on }</option>
										<option value="2">${t.t_product_off }</option>
									</select>
								</div>
								<div class="col-sm-3">
									${t.t_select_store }:
									<select class="form-control select" name="shopId">
										<option value="-1">-- ${t.t_select } --</option>
										<c:forEach items="${shopList}" var="item">
										<option value="${item.id}">${item.shopTitle}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-sm-2">
									<br>
									<button class="btn btn-info btn-icon" onclick="$.fn.doAutoSearch()">
										<i class="fa-search"></i>
										<span>${t.t_search }</span>
									</button>
								</div>
							</div>
							<div class="form-group">
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- 搜索区结束 -->
			
			<!-- 列表区 -->
			<div class="row">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">${t.t_list }</h3>
						<div class="panel-options">
							<a href="#" data-toggle="reload" onclick="$.fn.reload()"><i class="fa-rotate-right"></i></a>
						</div>
					</div>
					<div class="panel-body">
						<table class="table table-bordered table-striped" id="datatable">
							<thead>
								<tr>
									<th width="60" field="index">${t.t_no }</th>
									<th field="productName">${t.t_product_name }</th>
									<th field="shopName">${t.t_shop_name }</th>
									<th field="productTypeName">${t.t_product_type }</th>
									<th field="productPrice">${t.t_price }</th>
									<th field="productCount">${t.t_product_count }</th>
									<th field="productStatusStr">${t.t_product_status }</th>
									<th field="productView">${t.t_product_view }</th>
									<th field="productRank">${t.t_weight }</th>
									<th field="op" field-role="2,0" width="110"></th>
								</tr>
							</thead>
							<tbody class="middle-align"></tbody>
						</table>
						<div class="pagebar"></div>
					</div>
				</div>
			</div>
			<!-- 列表区结束 -->
			
		</div>
	</div>
<jsp:include page="../dialog/dialog_delete.jsp"></jsp:include>	
<script type="text/javascript">
$(function(){
	$('#main-menu li.li').removeClass('active').removeClass('opened');
	$('#main-menu li.li').eq(2).addClass('active').addClass('opened');
	$('#main-menu li.li').eq(2).find('ul li').eq(0).addClass('active');
	
	$('#datatable').datatable({
		url_load : '/backend/product/getList',
		url_edit : '/backend/product/edit',
		url_remove : '/backend/product/doDelete',
		backFn : function(p) {
			// console.log(p);
		}
	}); 
	
});
</script>
</body>
</html>