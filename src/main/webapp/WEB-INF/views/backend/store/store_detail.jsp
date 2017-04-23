<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>${t.m_store_detail }</title>
	
</head>
<body class="page-body">
	<div class="page-container">
		<!-- 左边栏 -->
		<jsp:include page="../include/left.jsp"></jsp:include>
		<div class="main-content">
			<!-- 头部栏 -->
			<jsp:include page="../include/header.jsp"></jsp:include>	
			
			<!-- 搜索区 -->
			<div class="row">
				<div class="col-sm-12 panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">${t.search_box }</h3>
					</div>
					<div class="panel-body">
						<form class="form-horizontal form" id="form" action="javascript:void(0);">
							<div class="form-group">
								<c:if test="${pageflag==1 }">
								<div class="col-sm-6">
									${t.t_select_store }:
									<select class="form-control select" name="shopId" id="shopId">
										<option value="-1">-- ${t.t_select } --</option>
										<c:forEach items="${shopList}" var="item">
										<option value="${item.id}">${item.shopTitle}</option>
										</c:forEach>
									</select>
								</div>
								</c:if>
								<c:if test="${pageflag==2 }">
								<div class="col-sm-2">
									${t.t_select }${t.t_status }:
									<select class="form-control select" name="messageStatus" id="messageStatus">
										<option value="-1">-- ${t.t_select } --</option>
										<option value="0">${t.t_unreaded }</option>
										<option value="1">${t.t_readed }</option>
									</select>				
								</div>				
								</c:if>
								<div class="col-sm-2">
									<br>
									<button class="btn btn-info btn-icon" onclick="$.fn.doAutoSearch()">
										<i class="fa-search"></i>
										<span>${t.t_search }</span>
									</button>
								</div>
							</div>
							<div class="form-group">
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- 搜索区结束 -->
			
			<div class="row">
				<div class="col-sm-12">
					<ul class="nav nav-tabs">
						<li class="<c:if test="${pageflag==1 }">active</c:if>">
							<a href="/backend/shop/product?id=${shopId }">
								<span class="hidden-xs">${t.t_store_product }</span>
							</a>
						</li>
						<li class="<c:if test="${pageflag==2 }">active</c:if>">
							<a href="/backend/shop/msg?id=${shopId }">
								<span class="hidden-xs">${t.t_store_msg }</span>
							</a>
						</li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active">
							<c:if test="${pageflag==1 }">
								<jsp:include page="store_product_verify.jsp"></jsp:include>
							</c:if>
							<c:if test="${pageflag==2 }">
								<jsp:include page="store_msg.jsp"></jsp:include>
							</c:if>
						</div>
					</div>			
				</div>
			</div>
			
		</div>
	</div>
	
<jsp:include page="../dialog/dialog_delete.jsp"></jsp:include>
<script type="text/javascript">
$('#shopId').optionSelect({
	compare:'${shopId}',
	backFn : function(p) {
	}
});
</script>
</body>
</html>