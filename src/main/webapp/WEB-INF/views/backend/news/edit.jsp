<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>${t.title_news_edit }</title>
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
			
			<!-- 编辑区 -->
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
					          		<p>${t.t_title }<span style="color:#f00;">*</span></p>
					            	<input class="form-control" type="text" name="newsTitle" value="${entity.newsTitle}">
					          	</div>
					        </div>
					        <div class="form-group">
					        	<div class="col-sm-3">
					          		<p>${t.t_select }<span style="color:#f00;">*</span></p>
					          		<select class="form-control select" name="newsType" id="newsType">
										<%-- <option value="-1">-- ${t.t_select } --</option> --%>
										<option value="0">${t.t_zh }</option>
										<option value="1">${t.t_en }</option>
									</select>
					          	</div>
					          	<div class="col-sm-3">
					        		<p>${t.t_read_count }</p>
					            	<input class="form-control" type="text" name="newsReaded" value="${entity.newsReaded}">
					    		</div>
					        	<div class="col-sm-3">
					        		<p>${t.t_thumup }</p>
					            	<input class="form-control" type="text" name="newsUp" value="${entity.newsUp}">
					    		</div>
					        </div>
					        <div class="form-group">
					        	<div class="col-sm-12">
					        		<%-- <b style="display:none;">${code.content}</b> --%>
									<textarea id="myEditor" name="newsContext">${entity.newsContext}</textarea>
									<script type="text/javascript">
										var ue = UE.getEditor('myEditor',{
											toolbars: [
									           ['fullscreen', 'source', 'undo', 'redo', 'bold','pasteplain','removeformat','link','unlink','cleardoc',
									           'simpleupload','insertimage','imagecenter','justifyleft','justifyright','justifycenter','justifyjustify',
									           'insertrow', 'insertcol', 'mergeright','mergedown', 'deleterow', 'deletecol', 'splittorows', 'splittocols', 
									           'splittocells', 'deletecaption','inserttitle', 'mergecells', 'deletetable',  
									           'insertparagraphbeforetable','edittable','edittd','inserttable','autotypeset','customstyle',
									           'spechars','fontfamily','fontsize']
									       ],
									       initialFrameHeight:500,
									       initialFrameWidth:'100%'
									       /* autoHeightEnabled: true,
									       autoFloatEnabled: true */
										});
									</script>
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
	$('#main-menu li.li').eq(3).addClass('active').addClass('opened');
	$('#main-menu li.li').eq(3).find('ul li').eq(0).addClass('active');
	
	$('#newsType').optionSelect({
		compare:'${entity.newsType}',
		backFn : function(p) {
		}
	});
	
	$('#submit').click(function(){
	    var parm = $.fn.getFormJson('.form');
		$.fn.doSave(parm,'/backend/news/doSave','/backend/news/list');
	});	
});
</script>
</body>
</html>