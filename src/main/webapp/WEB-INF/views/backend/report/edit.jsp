<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>${t.title_report_edit }</title>
	
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
							<a><i class="fa-location-arrow"> ${t.title_report_edit }</i></a> 
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-horizontal form" action="javascript:void(0);">
					        <div class="form-group">
					          	<div class="col-sm-6">
					          		<input type="hidden" value="${entity.id}" name="id" />
					          		<p>${t.t_title }</p>
					            	<input class="form-control" type="text" readonly="readonly"  value="${entity.informTitle}">
					          	</div>
					        	<div class="col-sm-3">
					          		<p>${t.t_status }</p>
					          		<select class="form-control select" id="infromStatus" name="infromStatus">
										<%-- <option value="-1">-- ${t.t_select } --</option> --%>
										<option value="0">${t.t_undone }</option>
										<option value="1">${t.t_done }</option>
									</select>
					          	</div>
					        </div>
					        <div class="form-group">
					        	<div class="col-sm-3">
					        		<p>${t.t_report }</p>
					            	<input class="form-control" type="text" readonly="readonly" value="${entity.reporter}">
					    		</div>
					        	<div class="col-sm-3">
					        		<p>${t.t_reported }</p>
					        		<input class="form-control" type="text" readonly="readonly" value="${entity.beReported}">
					    		</div>
					        </div>
					        <div class="form-group">
					        	<div class="col-sm-12">
					        		<p>${t.t_report_content }</p>
					        		<p>${entity.informContext}</p>
					    		</div>
					        </div>
					        <div class="form-group">
					        	<div class="col-sm-12">
					        		<p>${t.t_remark }</p>
									<textarea id="myEditor" name="informRemark" cols="80" rows="6">${entity.informRemark }</textarea>
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
	$('#main-menu li.li').eq(7).addClass('active').addClass('opened');
	$('#main-menu li.li').eq(7).find('ul li').eq(0).addClass('active');
	
	$('#infromStatus').optionSelect({
		compare:'${entity.status}',
		backFn : function(p) {
		}
	});
	
	$('#submit').click(function(){
	    var parm = $.fn.getFormJson('.form');
	    var id = ${entity.id};
		$.fn.doSave(parm,'/backend/inform/doSave','/backend/inform/list');
	});	
});
</script>
</body>
</html>