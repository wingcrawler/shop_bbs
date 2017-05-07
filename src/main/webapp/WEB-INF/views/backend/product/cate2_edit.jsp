<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>${t.t_product_type_two_edit }</title>
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
							<a><i class="fa-location-arrow"> ${t.t_product_type_two_edit }</i></a> 
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-horizontal form" action="javascript:void(0);">
					        <div class="form-group">
					          	<div class="col-sm-3">
					          		<input type="hidden" value="${entity.id}" name="id" />
					          		<p>${t.t_weight }</p>
					            	<input class="form-control" type="text" name="typeRank" value="${entity.typeRank}">
					          	</div>
					          	<div class="col-sm-3">
									${t.t_select_type }
									<select class="form-control select" name="parentId" id="parentId">
										<c:forEach items="${typeList}" var="item">
										<option value="${item.id}">${item.typeNameCh}/${item.typeName}</option>
										</c:forEach>
									</select>
								</div>
					        </div>
					        <div class="form-group">
					          	<div class="col-sm-6">
					          		<p>${t.t_type_name }(${t.t_zh })<span style="color:#f00">*</span></p>
					            	<input class="form-control" type="text" name="typeNameCh" value="${entity.typeNameCh}">
					          	</div>
					          	<div class="col-sm-6">
					          		<p>${t.t_type_name }(${t.t_en })<span style="color:#f00">*</span></p>
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
	$('#main-menu li.li').eq(2).addClass('active').addClass('opened');
	$('#main-menu li.li').eq(2).find('ul li').eq(2).addClass('active');
	
	$('#parentId').optionSelect({
		compare:'${entity.parentId}',
		backFn : function(p) {
		}
	});
	
	$('#submit').click(function(){
        var parm = $.fn.getFormJson('.form');
		$.fn.doSave(parm,'/backend/cate2/doSave','/backend/cate2/list');
	});
});

</script>
</body>
</html>