<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>${t.t_store_edit }</title>
	
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
						<form class="form-horizontal form" action="javascript:void(0);">
					        <div class="form-group">
					          	<div class="col-sm-6">
					          		<input type="hidden" value="${entity.id}" name="id" />
					          		<p>${t.t_title }<span style="color:#f00">*</span></p>
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
					        		<p>${t.t_weight }<span style="color:#f00">*</span></p>
					            	<input class="form-control" type="text" name="shopRank" value="${entity.shopRank}">
					    		</div>
					        </div>
					        <div class="form-group">
					        	<div class="col-sm-12">
					        		<p>${t.t_desc }</p>
					        		<textarea id="myEditor" name="shopDescription" cols="80" rows="6">${entity.shopDescription }</textarea>
					    		</div>
					        </div>
					        <div class="form-group">
					        	<div class="col-sm-12">
					        		上传
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
	$('#main-menu li.li').eq(4).addClass('active').addClass('opened');
	$('#main-menu li.li').eq(4).find('ul li').eq(0).addClass('active');
	
	$('#shopStatus').optionSelect({
		compare:'${entity.shopStatus}',
		backFn : function(p) {
		}
	});
	
	$('#submit').click(function(){
	    var parm = $.fn.getFormJson('.form');
		$.fn.doSave(parm,'/backend/shop/doSave','/backend/shop/list');
	});	
});
</script>
</body>
</html>