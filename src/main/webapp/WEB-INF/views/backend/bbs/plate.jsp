<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>${t.m_bbs_plate }</title>
	
</head>
<body class="page-body">
	<div class="page-container">
		<!-- 左边栏 -->
		<jsp:include page="../include/left.jsp"></jsp:include>
		<div class="main-content">
			<!-- 头部栏 -->
			<jsp:include page="../include/header.jsp"></jsp:include>	
			
			<div class="row">
				<div class="col-md-6">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">菜单列表</h3>
							<div class="panel-options">
								<a href="#" id="" name="" onclick="showMenuDialog(this)"><i class="fa-plus"></i></a>
								<a href="#" id="" data-toggle="reload" onclick="loadMenu(this)"><i class="fa-rotate-right"></i></a>
							</div>
						</div>
						<table class="table table-striped" url="/auth/codemenu/getCodeMenulist" id="datatable"> 
							<thead>
								<tr>
									<th field="orderBy">编号</th>
									<th field="name">菜单名称</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody></tbody>
						</table>
					</div>
				</div>
				<div class="col-md-6">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">子菜单管理 <span class="father-menu-name"></span></h3>
							<div class="panel-options">
								<a href="#" id="" name="" onclick="edit(this)"><i class="fa-plus"></i></a>
								<a href="#" data-toggle="reload" onclick="loadSubMenu(this)"><i class="fa-rotate-right"></i></a>
							</div>
						</div>
						<table class="table table-striped" id="table-sub"> 
							<thead>
								<tr>
									<th field="id">ID</th>
									<th field="name">子菜单名称</th>
									<th field="status">状态</th>
									<th field="op" field-role="2">操作</th>
								</tr>
							</thead>
							<tbody></tbody>
						</table>
					</div>
				</div>
			</div>
			
		</div>
	</div>
	
	<%-- <jsp:include page="../dialog/dialog_menu_code.jsp"></jsp:include> --%>
	
<script>
$(function(){
	$('#main-menu li.li').removeClass('active').removeClass('opened');
	$('#main-menu li.li').eq(6).addClass('active').addClass('opened');
	$('#main-menu li.li').eq(6).find('ul li').eq(1).addClass('active');	
});

//menu
function loadMenu() {
	$.ajax({ 
		url: "/auth/codemenu/getList",
		data: {},  
		type: 'get',  
		cache: false,  
		dataType: 'json',
		success: function(data) {
			var html = '';
			for (var i=0; i<data.list.length; i++) {
				var item = data.list[i];
				html += '<tr>'
					+ ' <td>'+item.orderBy+'</td>'	
					+ ' <td><a type="button" id="'+item.id+'" onclick="loadSubMenu(this)" class="btn btn-white btn-xs">'+item.name+'</a></td>'
					+ ' <td class="middle-align">'
					+ ' <a href="javascript:;" id="'+item.id+'" name="'+item.name+'" onclick="showMenuDialog(this)" class="btn btn-secondary btn-sm btn-single"><i class="fa-edit"></i></a>'
					+ ' <a href="#" class="btn btn-info btn-sm btn-single" onclick="moveUpOrDown('+item.id+',\'up\');"><i class="fa-chevron-up"></i></a>'
					+ ' <a href="#" class="btn btn-info btn-sm btn-single" onclick="moveUpOrDown('+item.id+',\'down\');"><i class="fa-chevron-down"></i></a>'
					+ ' </td>'
					+ '</tr>';
			}
			$('#datatable tbody').html(html);
		}
	});
}
// menu dialog
function showMenuDialog(obj){
	var id = $(obj).attr('id');
	var name = $(obj).attr('name');
	if(id!=''){
		$('#modal-menu input[name="id"]').val(id);
		$('#modal-menu input[name="name"]').val(name);	
	} else {
		$('#modal-menu input[name="id"]').val('');
		$('#modal-menu input[name="name"]').val('');
	}
	jQuery('#modal-menu').modal('show', {backdrop: 'static'});
}
//save menu
function saveMenu(){
	var id = $('#modal-menu input[name="id"]').val();
	var name = $('#modal-menu input[name="name"]').val();
	var _parm = {id:id,name:name};
	var _url = '/auth/codemenu/saveMenu';
	$.ajax({
		type: "POST",
		url: _url,
		dataType : "json",
		data: _parm,
		success: function(data) {
			if (data.errorNo != 200) {
				$.commonUtil.showTip(data.errorInfo);
			} else {
				$.commonUtil.showTip("提交成功");
				loadMenu();
			}
		}
	});
}
//move up or down
function moveUpOrDown(id, type){
	$.ajax({
		type: 'GET',
		url: '/auth/codemenu/doMenuMove',
		dataType : "json",
		data: {
			menuId: id,
			type: type
		},
		success: function(data) {
			if (data.errorNo != 200) {
				$.commonUtil.showTip(data.errorInfo);
			} else {
				$.commonUtil.showTip("操作成功");
				loadMenu();
			}
		}
	});
}


//sub meun
function loadSubMenu(obj) {
	if($.commonUtil.isNotBlank($(obj).attr("id"))){
		fatherId = $(obj).attr("id");
		$('#datatable tbody tr').find('.btn-xs').removeClass('active');
		$(obj).addClass('active');
	} else {
		fatherId = fatherId;	
	}
	$('#table-sub').datatable({
		url_load : '/auth/codemenu/getSubMenuByFatherId',
		parm :{
			fatherId : fatherId
		},
		backFn : function(p) {
			// console.log(p);
		}
	}); 
}
//sub menu dialog
function edit(obj){
	if (fatherId == -1) {
		return;
	}
	var id = $(obj).attr('id');
	var index = $(obj).attr('index');
	if($.commonUtil.isNotBlank(id)){
		var name = $('#table-sub tbody tr').eq(index).find('td').eq(1).text();
		var status = $('#table-sub tbody tr').eq(index).find('td').eq(2).text();
		$('#modal-submenu input[name="id"]').val(id);
		$('#modal-submenu input[name="name"]').val(name);
		$('#modal-submenu select[name="status"]').val(status);
		$('#subMenuStatus').onSelect({
			compare:status,
			backFn : function(p) {
			}
		});
	} else {
		$('#modal-submenu input[name="id"]').val('');
		$('#modal-submenu input[name="name"]').val('');
		$('#modal-submenu select[name="status"]').val('');
		$('#subMenuStatus').onSelect({
			backFn : function(p) {
			}
		});
	}
	jQuery('#modal-submenu').modal('show', {backdrop: 'static'});
}
//save sub menu
function saveSubMenu(){
	var id = $('#modal-submenu input[name="id"]').val();
	var name = $('#modal-submenu input[name="name"]').val();
	var status = $('#modal-submenu select[name="status"]').val();
	var parm = {
			id : id,
			fatherId : fatherId,
			name : name,
			status : status
		};
	var url = '/auth/codemenu/saveSubMenu';
	$.fn.doSave(parm, url);
}

</script>
</body>
</html>