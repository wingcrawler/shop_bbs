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
					          		<p>${t.t_input_title }</p>
					            	<input class="form-control" type="text" name="title" value="${entity.title}">
					          	</div>
					          	<div class="col-sm-6">
					          		<p>${t.t_select_menu }</p>
									<select class="form-control select" name="category">
										<option value="-1">-- ${t.t_select } --</option>
										<option value="1">${t.m_ad_active }</option>
										<option value="2">${t.m_ad_position }</option>
									</select>
					          	</div>
					        </div>
					        <div class="form-group">
					        	<div class="col-sm-6">
					          		<p>${t.t_img_link }</p>
					            	<input class="form-control" type="text" name="imgLink" value="${entity.imgLink}">
					          	</div>
					        </div>
					        <div class="form-group">
					        	<div class="col-sm-12">
					        		<a id="codeSubmit" class="btn btn-info" href="javascript:void(0);">${t.b_submit }</a>
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
});
</script>
</body>
</html>