<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>${t.t_thread_edit }</title>
	<script src="/ue/ueditor.config.js"></script>
	<script src="/ue/ueditor.all.js"></script>
	<c:if test="${lang =='en' }">
	<script type="text/javascript" charset="utf-8" src="/ue/lang/en/en.js"></script>
	</c:if>
	<c:if test="${lang =='zh' }">
	<script type="text/javascript" charset="utf-8" src="/ue/lang/zh-cn/zh-cn.js"></script>
	</c:if>
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
							<a><i class="fa-location-arrow"> ${t.t_thread_edit }</i></a> 
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-horizontal form" action="javascript:void(0);">
					        <div class="form-group">
					          	<div class="col-sm-3">
					          		<input type="hidden" value="${entity.id}" name="id" />
					          		<p>${t.t_thread }<span style="color:#f00">*</span></p>
					            	<input class="form-control" type="text" name="threadTitle" value="${entity.threadTitle}">
					          	</div>
					        	<div class="col-sm-3">
									${t.t_select }${t.t_lang }
									<select class="form-control select" id="threadType" name="threadType">
										<option value="1">${t.t_zh}</option>
										<option value="0">${t.t_en}</option>
									</select>
								</div>
					          	<div class="col-sm-3">
					          		<p>${t.t_status }</p>
					          		<select class="form-control select" id="threadStatus" name="threadStatus">
										<option value="1">${t.t_on }</option>
										<option value="0">${t.b_close }</option>
									</select>
					          	</div>
					        </div>
					        <div class="form-group">
					        	<div class="col-sm-3">
					          		<p>${t.t_username }</p>
					          		<p>${entity.username }</p>
					          	</div>
					        	<div class="col-sm-3">
					          		<p>${t.t_section_1 }</p>
					          		<p>${entity.sectionOne }</p>
					          	</div>
					          	<div class="col-sm-3">
					          		<p>${t.t_section_2 }</p>
					          		<p>${entity.sectionTwo }</p>
					          	</div>
					          	<div class="col-sm-3">
					          		<p>${t.t_topic }</p>
					          		<p>${entity.topicTitle }</p>
					          	</div>
					        </div>
					        <div class="form-group">
					        	<div class="col-sm-12">
									<textarea id="myEditor" name="threadContext">${entity.threadContext}</textarea>
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
			<!-- 内容区结束 -->
			
		</div>
	</div>

<script type="text/javascript">
$(function(){
	$('#main-menu li.li').removeClass('active').removeClass('opened');
	$('#main-menu li.li').eq(6).addClass('active').addClass('opened');
	$('#main-menu li.li').eq(6).find('ul li').eq(0).addClass('active');
	
	$('#threadType').optionSelect({
		compare:'${entity.threadType}',
		backFn : function(p) {
		}
	});
	
	$('#threadStatus').optionSelect({
		compare:'${entity.threadStatus}',
		backFn : function(p) {
		}
	});
	
	$('#submit').click(function(){
	    var parm = $.fn.getFormJson('.form');
		$.fn.doSave(parm,'/backend/thread/doSave','/backend/thread/list');
	});	
});
</script>
</body>
</html>