<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
							<a><i class="fa-location-arrow"> ${t.title_ad_edit }</i></a> 
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-horizontal form" action="javascript:void(0);">
					        <div class="form-group">
					          	<div class="col-sm-6">
					          		<input type="hidden" value="${entity.id}" name="id" />
					          		<p>${t.t_weight }</p>
					            	<input class="form-control" type="text" name="typeRank" value="${entity.typeRank}">
					          	</div>
					        </div>
					        <div class="form-group">
					          	<div class="col-sm-6">
					          		<p>${t.t_type_name }(${t.t_zh })</p>
					            	<input class="form-control" type="text" name="typeNameCh" value="${entity.typeNameCh}">
					          	</div>
					          	<div class="col-sm-6">
					          		<p>${t.t_type_name }(${t.t_en })</p>
					            	<input class="form-control" type="text" name="typeName" value="${entity.typeName}">
					          	</div>
					        </div>
					        <div class="form-group">
					        	<div class="col-sm-12">
					          		<p>${t.t_desc }(${t.t_zh })</p>
					            	<input class="form-control" type="text" name="typeDescriptionCh" value="${entity.typeDescriptionCh}">
					          	</div>
					        </div>
					        <div class="form-group">
					        	<div class="col-sm-12">
					          		<p>${t.t_desc }(${t.t_en })</p>
					            	<input class="form-control" type="text" name="typeDescription" value="${entity.typeDescription}">
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
	$('#main-menu li.li').eq(1).addClass('active').addClass('opened');
	$('#main-menu li.li').eq(1).find('ul li').eq(0).addClass('active');
	
	$('#submit').click(function(){
        var parm = $.fn.getFormJson('.form');
		$.fn.doSave(parm,'/backend/cate/doEdit','/backend/cate/list');
	});
});

</script>
</body>
</html>