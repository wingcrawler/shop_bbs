<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>${t.title_ad_list }</title>
	
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
									${t.t_menu }:
									<select class="form-control select" name="type">
										<option value="-1">-- ${t.t_select } --</option>
										<option value="0">${t.m_ad_active }</option>
										<option value="1">${t.m_ad_position }</option>
									</select>
								</div>
								<div class="col-sm-2">
									<br>
									<button class="btn btn-info btn-icon" onclick="search()">
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
							<a href="/backend/ad/edit" target="_blank"><i class="fa-plus"></i></a>
							<a href="#" data-toggle="reload" onclick="$.fn.reload()"><i class="fa-rotate-right"></i></a>
						</div>
					</div>
					<div class="panel-body">
						<table class="table table-bordered table-striped" id="datatable">
							<thead>
								<tr>
									<th field="index" width="60">${t.t_no }</th>
									<th field="imageUrl">${t.t_img_link }</th>
									<th field="typeName">${t.t_type }</th>
									<th field="sort">${t.t_weight }</th>
									<th field="description">${t.t_desc }</th>
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
	$('#main-menu li.li').eq(1).addClass('active').addClass('opened');
	$('#main-menu li.li').eq(1).find('ul li').eq(0).addClass('active');
	
	$('#datatable').datatable({
		url_load : '/backend/ad/getList',
		url_edit : '/backend/ad/edit',
		url_remove : '/backend/ad/doDelete',
		backFn : function(p) {
			// console.log(p);
		}
	}); 
});

function search(){
	var type=$('select[name="type"]').val();
	var parm = {
		type:type
	}
	$.fn.doSearch(parm);
}
</script>
</body>
</html>