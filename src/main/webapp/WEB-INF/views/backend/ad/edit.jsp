<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>${t.title_ad_edit }</title>
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
							<a><i class="fa-location-arrow"> ${t.title_ad_edit }</i></a> 
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-horizontal form" method="post" action="/backend/ad/doEdit" enctype="multipart/form-data">
					        <div class="form-group">
					          	<div class="col-sm-6">
					          		<input type="hidden" value="${entity.id}" name="id" />
					          		<p>${t.t_weight }</p>
					            	<input class="form-control" type="number" name="sort" value="${entity.sort}">
					          	</div>
					          	<div class="col-sm-6">
					          		<p>${t.t_select_type }<span style="color:#f00">*</span></p>
									<select class="form-control select" name="type" id="typeSelect">
										<option value="-1">-- ${t.t_select } --</option>
										<option value="0">${t.t_ad_lb }</option>
										<option value="1">${t.t_ad_ggw }</option>
									</select>
					          	</div>
					        </div>
					        <div class="form-group">
					        	<div class="col-sm-6">
					          		<p>${t.t_img_link }<span style="color:#f00">*</span></p>
					            	<input class="form-control" type="text" name="imageUrl" value="${entity.imageUrl}">
					          	</div>
					          	<div class="col-sm-6">
					          		<p>${t.t_desc }</p>
					            	<input class="form-control" type="text" name="description" value="${entity.description}">
					          	</div>
					        </div>
					        <div class="form-group">
					        	<div class="col-sm-12">
					        		<%-- <c:if test="${fn:length(entity.imageUrl)>0}">
					        			 <a href="${entity.imageUrl}" target="_blank"><img border="0" width="100%" src="${entity.imagePath}" /></a>
					        		</c:if>
    				        		<c:if test="${fn:length(entity.imageUrl)<=0}">
					        			<input type="file" name="attachFile" id="attachFile" accept="image/*"/><span style="color:#f00">* ${t.t_advice_size }：1349*716</span>
					        		</c:if> --%>
					        		<input type="file" name="attachFile" id="attachFile" accept="image/*"/><span style="color:#f00">*</span>
					        		<a href="${entity.imageUrl}" target="_blank"><img border="0" width="100%" src="${entity.imagePath}" /></a>
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
	$('#main-menu li.li').eq(1).addClass('active').addClass('opened');
	$('#main-menu li.li').eq(1).find('ul li').eq(0).addClass('active');
	
	$('#typeSelect').optionSelect({
		compare:'${entity.type}',
		backFn : function(p) {
		}
	});
	
	//提交
	$('#submit').click(function(){
		$.fn.myAjaxSubmit(".form", "/backend/ad/doSave", "/backend/ad/list");
	});
	/* $('#submit').click(function(){
		$(".form").ajaxSubmit({ 
            type:'post',  
            cache: false,  
            url: "/backend/ad/doSave", 
            dataType : 'json', //返回值类型 一般设置为json  
            success : function(data, status) {  
            	debugger;
	        	if(data.errorNo==0){
	        		self.location='/backend/ad/list';
				} else {
					$.commonUtil.showTip(data.errorInfo);
				}
	        },  
	        error : function(data, status, e) {  
	        	$.commonUtil.showTip(data.errorInfo); 
	        }   
        });
	}); */
});


</script>
</body>
</html>