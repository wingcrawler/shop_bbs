<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>${t.m_store_msg }</title>
	
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
						<h3 class="panel-title">搜索框</h3>
					</div>
					<div class="panel-body">
						<form class="form-horizontal form" id="form" action="javascript:void(0);">
							<div class="form-group">
								<div class="col-sm-3">
									标题:
									<input type="text" class="form-control input" name="title" value="" placeholder="输入标题">
								</div>
								<div class="col-sm-3">
									菜单：
									<select class="form-control select" name="menuId">
										<option value="-1">-- 选择菜单 --</option>
										<option value="1">item1</option>
										<option value="2">item2</option>
									</select>
								</div>
								<div class="col-sm-2">
									<br>
									<button class="btn btn-info btn-icon" onclick="search()">
										<i class="fa-search"></i>
										<span>搜索</span>
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
						<h3 class="panel-title">列表</h3>
						<div class="panel-options">
							<a href="/backend/report/edit" target="_blank"><i class="fa-plus"></i></a>
							<a href="#" data-toggle="reload" onclick="$.fn.reload()"><i class="fa-rotate-right"></i></a>
						</div>
					</div>
					<div class="panel-body">
						<table class="table table-bordered table-striped" id="datatable">
							<thead>
								<tr>
									<th width="55" field="index">编号</th>
									<th field="title" url="http://my.blog/blog/detail?id=" parm="id">标题</th>
									<th field="username">用户名</th>
									<th field="menuName">菜单</th>
									<th field="click">点击</th>
									<th field="createTime">创建时间</th>
									<th field="updateTime">更新时间</th>
									<th field="op" field-role="0"></th>
								</tr>
							</thead>
							<tbody class="middle-align">
								<tr>
									<td>1</td>
									<td>sgarsgarw</td>
									<td>sgarsgarw</td>
									<td>sgarsgarw</td>
									<td>sgarsgarw</td>
									<td>sgarsgarw</td>
									<td>sgarsgarw</td>
									<td><a class="btn btn-info" href="/backend/news/edit">编辑</a></td>
								</tr>
							</tbody>
						</table>
						<div class="pagebar"></div>
					</div>
				</div>
			</div>
			<!-- 列表区结束 -->
			
		</div>
	</div>
	
<script type="text/javascript">
$(function(){
	$('#main-menu li.li').removeClass('active').removeClass('opened');
	$('#main-menu li.li').eq(4).addClass('active').addClass('opened');
	$('#main-menu li.li').eq(4).find('ul li').eq(3).addClass('active');
});
</script>
</body>
</html>