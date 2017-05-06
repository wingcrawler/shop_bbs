<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>${t.m_bbs_topic }</title>
	
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
							<a><i class="fa-location-arrow"> ${t.m_bbs_topic }</i></a> 
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-horizontal form" action="javascript:void(0);">
					        <div class="form-group">
					          	<div class="col-sm-3">
					          		<input type="hidden" value="${entity.id}" name="id" />
					          		<p>${t.t_title }<span style="color:#f00">*</span></p>
					            	<input class="form-control" type="text" name="topicTitle" value="${entity.topicTitle}">
					          	</div>
					        	<div class="col-sm-3">
					          		<p>${t.t_select_section }</p>
					          		<select class="form-control select" id="sectionId" name="sectionId">
										<c:forEach items="${secondSectionList}" var="item">
										<option value="${item.id}">${item.sectionTitle}</option>
										</c:forEach>
									</select>
					          	</div>
					          	<div class="col-sm-3">
					          		<p>${t.t_status }</p>
					          		<select class="form-control select" id="topicStatus" name="topicStatus">
										<option value="1">${t.t_on }</option>
										<option value="0">${t.b_close }</option>
									</select>
					          	</div>
					        </div>
					        <div class="form-group">
					        	<div class="col-sm-12">
					        		<p>${t.t_desc }<span style="color:#f00">*</span></p>
					        		<textarea rows="6" cols="80" name="topicDescription">${entity.topicDescription }</textarea>
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

<jsp:include page="../dialog/dialog_delete.jsp"></jsp:include>		
<script type="text/javascript">
$(function(){
	$('#main-menu li.li').removeClass('active').removeClass('opened');
	$('#main-menu li.li').eq(6).addClass('active').addClass('opened');
	$('#main-menu li.li').eq(6).find('ul li').eq(2).addClass('active');
	
	$('#sectionId').optionSelect({
		compare:'${entity.sectionId}',
		backFn : function(p) {
		}
	});
	
	$('#topicStatus').optionSelect({
		compare:'${entity.topicStatus}',
		backFn : function(p) {
		}
	});
	
	$('#submit').click(function(){
	    var parm = $.fn.getFormJson('.form');
		$.fn.doSave(parm,'/backend/topic/doSave','/backend/topic/list');
	});	
});
</script>
</body>
</html>