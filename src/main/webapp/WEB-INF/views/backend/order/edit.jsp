<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>${t.t_order_detaial }</title>
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
							<a><i class="fa-location-arrow"> ${t.t_order_detaial }</i></a> 
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-horizontal form" action="javascript:void(0);">
					        <div class="form-group">
					          	<div class="col-sm-12">
					          		<input type="hidden" value="${entity.id}" name="id" />
					          		<p>${t.t_product_name }: <b>${entity.productName}</b></p>
					          	</div>
					        </div>
					        <div class="form-group">
					        	<div class="col-sm-12">
					        		<p>${t.t_store_name }: <b>${entity.shopName}</b></p>
					        	</div>
					        </div>
					        <div class="form-group">
					        	<div class="col-sm-3">
					          		${t.t_status }:
									<select class="form-control select" name="orderStatus" id="orderStatus">
										<option value="0">${t.t_order_paying }</option>
										<option value="1">${t.t_order_payed }</option>
										<option value="2">${t.t_order_shipped }</option>
										<option value="3">${t.t_order_deleted }</option>
										<option value="4">${t.t_order_ended }</option>
										<option value="5">${t.t_order_back }</option>
									</select>
					          	</div>
					        </div>
					        <div class="form-group">
					        	<div class="col-sm-12">
					        		<p>${t.t_buy }: <b>${entity.username}</b></p>
					        	</div>
					        </div>
					        <div class="form-group">
					        	<div class="col-sm-12">
					        		<p>${t.t_order_createtime }: <b>${entity.createTimeStr}</b></p>
					        	</div>
					        </div>
					        <div class="form-group">
					        	<div class="col-sm-12">
					        		<p>${t.t_address }: <b>${entity.orderAddress}</b></p>
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
			<!-- 内容区结束 -->
			
		</div>
	</div>
	
<script type="text/javascript">
$(function(){
	$('#main-menu li.li').removeClass('active').removeClass('opened');
	$('#main-menu li.li').eq(5).addClass('active').addClass('opened');
	$('#main-menu li.li').eq(5).find('ul li').eq(0).addClass('active');
	
	$('#orderStatus').optionSelect({
		compare:'${entity.orderStatus}',
		backFn : function(p) {
		}
	});
	
	$('#submit').click(function(){
	    var parm = $.fn.getFormJson('.form');
		$.fn.doSave(parm,'/backend/order/doSave','/backend/order/list');
	});	
});
</script>
</body>
</html>