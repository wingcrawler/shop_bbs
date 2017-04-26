<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>${t.title_news_comment }</title>
	<script src="/ue/ueditor.config.js"></script>
	<script src="/ue/ueditor.all.js"></script>
	<!-- <script type="text/javascript" charset="utf-8" src="/ue/lang/en/en.js"></script> -->
	<script type="text/javascript" charset="utf-8" src="/ue/lang/zh-cn/zh-cn.js"></script>
	<link href="/ue/themes/default/css/ueditor.css" rel="stylesheet">
	
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
							<a><i class="fa-location-arrow"> ${t.title_news_edit }</i></a> 
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-horizontal form" action="javascript:void(0);">
					        <div class="form-group">
					          	<div class="col-sm-6">
					          		<input type="hidden" value="${entity.id}" name="id" />
					          		<input type="hidden" value="${entity.newsId}" name="newsId" />
					          		<p>${t.t_title }</p>
					            	<input class="form-control" type="text"   value="${entity.title}">
					          	</div>
					        	<div class="col-sm-3">
					          		<p>${t.t_status }</p>
					          		<select class="form-control select" readonly="readonly" id="commentStatus">
										<%-- <option value="-1">-- ${t.t_select } --</option> --%>
										<option value="0">${t.t_off }</option>
										<option value="1">${t.t_on }</option>
									</select>
					          	</div>
					          	<div class="col-sm-3">
					        		<p>${t.t_username }</p>
					            	<input class="form-control" type="text" readonly="readonly" value="${entity.username}">
					    		</div>
					        </div>
					        <div class="form-group">
					        	<div class="col-sm-3">
					        		<p>${t.t_comment }</p>
					        		<p>${entity.context}</p>
					    		</div>
					        </div>
					        <div class="form-group">
					        	<div class="col-sm-12">
					        		<%-- <b style="display:none;">${code.content}</b> --%>
									<textarea id="myEditor" name="context"></textarea>
									<script type="text/javascript">
										var ue = UE.getEditor('myEditor',{
											toolbars: [
									           ['fullscreen', 'source', 'undo', 'redo', 'bold','pasteplain',
									            'removeformat','link','unlink','cleardoc',
									           'justifyleft','justifyright','justifycenter','justifyjustify',
									           'autotypeset', 'spechars','fontfamily','fontsize']
									       ],
									       initialFrameHeight:500,
									       initialFrameWidth:'100%'
									       /* autoHeightEnabled: true,
									       autoFloatEnabled: true */
										});
									</script>
									<span style="color:#f00">限制1000字</span>
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
	$('#main-menu li.li').eq(3).addClass('active').addClass('opened');
	$('#main-menu li.li').eq(3).find('ul li').eq(1).addClass('active');
	
	$('#commentStatus').optionSelect({
		compare:'${entity.status}',
		backFn : function(p) {
		}
	});
	
	$('#submit').click(function(){
	    var parm = $.fn.getFormJson('.form');
	    var id = '${entity.id}';
		$.fn.doSave(parm,'/backend/comment/doSave','/backend/comment/list?id='+id);
	});	
});
</script>
</body>
</html>