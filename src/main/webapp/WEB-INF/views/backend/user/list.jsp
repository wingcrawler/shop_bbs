<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>${t.m_user }</title>
	
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
									${t.t_mobile }
									<input type="text" class="form-control input" name="userPhone" value="">
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
							<button class="btn btn-purple btn-icon btn-icon-standalone btn-xs" style="margin-bottom:0;" id="export">
								<i class="fa-download"></i>
								<span>${t.t_export }</span>
							</button>
							<a href="#" data-toggle="reload" onclick="$.fn.reload()"><i class="fa-rotate-right"></i></a>
						</div>
					</div>
					<div class="panel-body">
						<table class="table table-bordered table-striped" id="datatable">
							<thead>
								<tr>
									<th width="60" field="index">${t.t_no }</th>
									<th field="username" url="/backend/user/detail?id=" parm="id">${t.t_username }</th>
									<th field="userPhone">${t.t_mobile }</th>
									<th field="userRole">${t.t_user_role }</th>
									<th field="userEmail">${t.t_email }</th>
									<th field="statusName">${t.t_status }</th>
									<th field="createTimeStr">${t.t_createtime }</th>
									<th field="op" field-role="0" width="80"></th>
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
		url_load : '/backend/user/getList',
		url_remove : '/backend/inform/doDelete',
		backFn : function(p) {
			// console.log(p);
		}
	}); 
	
	$('#export').click(function(){
		var username=$('input[name="username"]').val();
		var userPhone=$('input[name="userPhone"]').val();
		self.location='/backend/user/export?username='+username+'&userPhone='+userPhone+'&time='+new Date().getTime();
	});
});
</script>
</body>
</html>