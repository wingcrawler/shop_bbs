<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>${t.t_import }</title>
	
</head>
<body class="page-body">
	<div class="page-container">
		<!-- 左边栏 -->
		<jsp:include page="../include/left.jsp"></jsp:include>
		<div class="main-content">
			<!-- 头部栏 -->
			<jsp:include page="../include/header.jsp"></jsp:include>	
			
			<div class="row">
				<div class="col-sm-12 panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">${t.t_import }</h3>
					</div>
					<div class="panel-body">
						<form class="form-horizontal importform" id="form" method="post" enctype="multipart/form-data" action="javascript:void(0);">
							<div class="form-group">
								<div class="col-sm-6">
									<input type="file" name="txtFile" id="txtFile" accept="text/plain"/>
								</div>
								<div class="col-sm-3">
									<a class="btn btn-info btn-icon" id="import">
										<span>${t.b_submit }</span>
									</a>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			
		</div>
	</div>
	
<jsp:include page="../dialog/dialog_tip.jsp"></jsp:include>
<script type="text/javascript">
$(function(){
	$('#main-menu li.li').removeClass('active').removeClass('opened');
	$('#main-menu li.li').eq(5).addClass('active').addClass('opened');
	$('#main-menu li.li').eq(5).find('ul li').eq(1).addClass('active');
	
	$('#import').click(function(){
		$(".importform").ajaxSubmit({  
            type:'post',  
            cache: false,  
            url: '/backend/user/doImport', 
            dataType : 'json',   
            success : function(data, status) {  
	        	if(data.errorNo==0){
	        		$("#modal-tip .modal-body").text(data.errorInfo);
	        		jQuery("#modal-tip").modal("show", {backdrop: "fade"});
				} else {
					$.commonUtil.showTip(data.errorInfo);
				}
	        },  
	        error : function(data, status, e) {  
	        	$.commonUtil.showTip(data.errorInfo); 
	        }   
        });
	});
});
</script>
</body>
</html>