<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>${t.m_order_list }</title>
	
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
									${t.t_username }
									<input type="text" class="form-control input" name="username" value="">
								</div>
								<div class="col-sm-3">
									${t.t_status }
									<select class="form-control select" name="orderStatus">
										<option value="-1">-- ${t.t_select } --</option>
										<option value="0">${t.t_order_paying }</option>
										<option value="1">${t.t_order_payed }</option>
										<option value="2">${t.t_order_shipped }</option>
										<option value="3">${t.t_order_deleted }</option>
										<option value="4">${t.t_order_ended }</option>
										<option value="5">${t.t_order_back }</option>
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
							<%-- <button class="btn btn-purple btn-icon btn-icon-standalone btn-xs" style="margin-bottom:0;" id="export">
								<i class="fa-download"></i>
								<span>${t.t_export }</span>
							</button> --%>
							<a href="/backend/shop/edit" target="_blank"><i class="fa-plus"></i></a>
							<a href="#" data-toggle="reload" onclick="$.fn.reload()"><i class="fa-rotate-right"></i></a>
						</div>
					</div>
					<div class="panel-body">
						<table class="table table-bordered table-striped" id="datatable">
							<thead>
								<tr>
									<th width="60" field="index">${t.t_no }</th>
									<th field="productName">${t.t_product_name }</th>
									<th field="username">${t.t_username }</th>
									<th field="statusName">${t.t_status }</th>
									<th field="createTimrStr">${t.t_createtime }</th>
									<th field="orderAddress">${t.t_address }</th>
									<th field="op" field-role="2" width="80"></th>
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
	$('#main-menu li.li').eq(5).addClass('active').addClass('opened');
	$('#main-menu li.li').eq(5).find('ul li').eq(0).addClass('active');
	
	$('#datatable').datatable({
		url_load : '/backend/order/getList',
		url_edit : '/backend/order/edit',
		backFn : function(p) {
			// console.log(p);
		}
	}); 
	
	/* $('#export').click(function(){
		debugger;
		var shopTitle=$('input[name="shopTitle"]').val();
		var shopStatus=$('select[name="shopStatus"]').val();
		self.location = '/backend/shop/export?shopTitle='+shopTitle+'&shopStatus='+shopStatus+'&date='+new Date().getTime();
	}); */
});
</script>
</body>
</html>