<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

	<jsp:include page="include/meta.jsp"></jsp:include>
	<title>${t.m_bbs_plate }</title>
	
</head>
<body class="page-body">
	<div class="page-container">
		<!-- 左边栏 -->
		<jsp:include page="include/left.jsp"></jsp:include>
		<div class="main-content">
			<!-- 头部栏 -->
			<jsp:include page="include/header.jsp"></jsp:include>	
			
			<div class="row">
				<div class="col-md-6">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">${t.t_section_1 }</h3>
							<div class="panel-options">
								<a href="#" id="" name="" onclick="showMenuDialog(this)"><i class="fa-plus"></i></a>
								<a href="#" id="" data-toggle="reload" onclick="loadFirstSection(this)"><i class="fa-rotate-right"></i></a>
							</div>
						</div>
						<table class="table table-striped" url="/auth/codemenu/getCodeMenulist" id="datatable"> 
							<thead>
								<tr>
									<th field="index">${t.t_no }</th>
									<th field="sectionTitle">${t.t_section_name }</th>
									<th field="statusName">${t.t_status }</th>
									<th></th>
								</tr>
							</thead>
							<tbody></tbody>
						</table>
					</div>
				</div>
				<div class="col-md-6">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">${t.t_section_2 } <span class="father-menu-name"></span></h3>
							<div class="panel-options">
								<a href="#" id="" name="" onclick="edit(this)"><i class="fa-plus"></i></a>
								<a href="#" id="secondReload" data-toggle="reload" onclick="loadSecondSection(this)"><i class="fa-rotate-right"></i></a>
							</div>
						</div>
						<table class="table table-striped" id="table-sub"> 
							<thead>
								<tr>
									<th field="index">${t.t_no }</th>
									<th field="sectionTitle">${t.t_section_name }</th>
									<th field="statusName">${t.t_status }</th>
									<th field="op" field-role="201"></th>
								</tr>
							</thead>
							<tbody></tbody>
						</table>
					</div>
				</div>
			</div>
			
		</div>
	</div>
	
<script>
var fatherId = -1;
$(function(){
	$('#main-menu li.li').removeClass('active').removeClass('opened');
	$('#main-menu li.li').eq(7).addClass('active').addClass('opened');
	$('#main-menu li.li').eq(7).find('ul li').eq(1).addClass('active');	
	
	loadFirstSection();	
});

//一级版块
function loadFirstSection() {
	$.ajax({ 
		url: "/backend/section/getFirstList",
		data: {},  
		type: 'get',  
		cache: false,  
		dataType: 'json',
		success: function(data) {
			var html = '';
			for (var i=0; i<data.list.length; i++) {
				var item = data.list[i];
				html += '<tr>'
					+ ' <td>'+(i+1)+'</td>'	
					+ ' <td><a type="button" opid="'+item.id+'" onclick="loadSecondSection(this)" class="btn btn-white btn-xs">'+item.sectionTitle+'</a></td>'
					+ ' <td>'+item.statusName+'</td>'
					+ ' <td class="middle-align">'
					+ '  <a href="javascript:;" opid="'+item.id+'" status="'+item.sectionStatus+'" name="'+item.sectionTitle+'" onclick="showMenuDialog(this)" class="btn btn-secondary btn-sm btn-single"><i class="fa-edit"></i></a>'
					/* + ' <a href="#" class="btn btn-info btn-sm btn-single" onclick="moveUpOrDown('+item.id+',\'up\');"><i class="fa-chevron-up"></i></a>'
					+ ' <a href="#" class="btn btn-info btn-sm btn-single" onclick="moveUpOrDown('+item.id+',\'down\');"><i class="fa-chevron-down"></i></a>' */
					+ ' </td>'
					+ '</tr>';
			}
			$('#datatable tbody').html(html);
		}
	});
}
//一级版块弹出框
function showMenuDialog(obj){
	var id = $(obj).attr('opid');
	var name = $(obj).attr('name');
	var status = $(obj).attr('status');
	$('#menuStatus').optionSelect({
		compare:status,
		backFn : function(p) {
		}
	});
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
	var status = $('#modal-menu select[name="menuStatus"]').val();
	var _parm = {
			id:id,
			sectionTitle:name,
			sectionStatus:status,
			sectionType:0
		};
	var _url = '/backend/section/doSave';
	$.ajax({
		type: "POST",
		url: _url,
		dataType : "json",
		data: _parm,
		success: function(data) {
			if (data.errorNo != 0) {
				$.commonUtil.showTip(data.errorInfo);
			} else {
				$.commonUtil.showTip(data.errorInfo);
				loadFirstSection();
			}
		}
	});
}

//二级版块
function loadSecondSection(obj) {
	if($.commonUtil.isNotBlank($(obj).attr("opid"))){
		fatherId = $(obj).attr("opid");
		$('#datatable tbody tr').find('.btn-xs').removeClass('active');
		$(obj).addClass('active');
	} else {
		fatherId = fatherId;	
	}
	$('#table-sub').datatable({
		url_load : '/backend/section/getSecondList',
		parm :{
			sectionParentId : fatherId
		},
		backFn : function(p) {
			// console.log(p);
		}
	}); 
}
//二级版块弹出框
function edit(obj){
	if (fatherId == -1) {
		return;
	}
	var id = $(obj).attr('opid');
	var index = $(obj).attr('index');
	if($.commonUtil.isNotBlank(id)){
		var name = $('#table-sub tbody tr').eq(index).find('td').eq(1).text();
		var statusStr = $('#table-sub tbody tr').eq(index).find('td').eq(2).text();
		var status=0;
		if(statusStr=='正常' || statusStr=='Normal'){
			status=1;
		}
		$('#modal-submenu input[name="id"]').val(id);
		$('#modal-submenu input[name="name"]').val(name);
		$('#modal-submenu select[name="subMenuStatus"]').val(status);
		$('#subMenuStatus').optionSelect({
			compare:status,
			backFn : function(p) {
			}
		});
	} else {
		$('#modal-submenu input[name="id"]').val('');
		$('#modal-submenu input[name="name"]').val('');
		$('#modal-submenu select[name="subMenuStatus"]').val('');
		$('#subMenuStatus').optionSelect({
			compare:1,
			backFn : function(p) {
			}
		}); 
	}
	jQuery('#modal-submenu').modal('show', {backdrop: 'static'});
}
//save sub menu
function saveSubMenu(){
	debugger;
	var sectionParentId = $('#datatable tbody tr').find('.btn-xs.active').attr("opid");
	var id = $('#modal-submenu input[name="id"]').val();
	var name = $('#modal-submenu input[name="name"]').val();
	var status = $('#modal-submenu select[name="subMenuStatus"]').val();
	var parm = {
			id : id,
			fatherId : fatherId,
			sectionTitle : name,
			sectionStatus : status,
			sectionType:1,
			sectionParentId:sectionParentId
		};
	var url = '/backend/section/doSave';
	$.fn.doSaveAndReload(parm, url);
	$('#secondReload').trigger('click');
}

</script>
</body>
</html>