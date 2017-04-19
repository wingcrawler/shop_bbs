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
							<a><i class="fa-location-arrow"> 编辑新闻资讯</i></a> 
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-horizontal form" action="javascript:void(0);">
					        <div class="form-group">
					          	<div class="col-sm-6">
					          		<input type="hidden" value="${code.id}" name="id" />
					          		<p>输入标题</p>
					            	<input class="form-control" type="text" name="item" value="${code.item}" placeholder="输入标题">
					          	</div>
					          	<div class="col-sm-6">
					          		<p>选择是否是节点</p>
										<input type="radio" name="isNode" class="cbr" value="node">
										节点
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio" name="isNode" class="cbr" value="notnode">
										非节点
					          	</div>
					        </div>
					        <div class="form-group">
					        	<div class="col-sm-3">
					        		<p>选择主菜单</p>
					        		<select class="form-control menuSelect" name="menuId" onchange="loadMenus()">
					        			<option value="0">-- 选择主菜单 --</option>
					        		</select>
					    		</div>
					        </div>
					        <div class="form-group">
					        </div>
					        <div class="form-group">
					        	<div class="col-sm-12">
					        		<%-- <b style="display:none;">${code.content}</b> --%>
									<textarea id="myEditor">${code.content}</textarea>
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
					        		<a id="codeSubmit" class="btn btn-info" href="javascript:void(0);">提交</a>
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
	$('#main-menu li.li').eq(4).addClass('active').addClass('opened');
	$('#main-menu li.li').eq(4).find('ul li').eq(0).addClass('active');
});
</script>
</body>
</html>