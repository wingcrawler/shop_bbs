<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>${t.m_product_list }</title>
	<link href="/frontstyle/sell/css/allProduct.css" rel="stylesheet" type="text/css">
	<script src="/frontstyle/js/common.js"></script>
	<script src="/frontstyle/sell/js/all_product.js"></script>
</head>
<body>
	<jsp:include page="../include/header.jsp"></jsp:include>
	
	<div class="main">
		<div class="container">
			<jsp:include page="include/left.jsp"></jsp:include>
		
			<!-- companyData -->
			<div class="companyData">
				<div class="header"><span>${t.m_product } >  ${t.m_product_list }</span></div>
				<div class="product_message">
					<div class="m-table">
							<table>
								<thead>
									<tr>
										<td class="td1"></td>
										<td class="td2">${t.t_product_name }</td>
										<td class="td3">${t.t_product_type }</td>
										<td class="td4">${t.t_release_time }</td>
										<td class="td5">${t.t_product_status }</td>
										<!-- <td class="td6">标签</td> -->
										<td class="td7"></td>
									</tr>
								</thead>
								<tbody>
									<c:if test="${not empty productPage.list }">
										<c:forEach var="item" items="${productPage.list }">
											<tr class="tr-td selected">
												<td class="td1">
													<div class="u-checkboxitem">
														<input type="checkbox" class="u-ipt-checkbox" name="" id="table-checkbox3" >
														<label for="table-checkbox3">
															<div class="i-checkbox">
																<i></i>
															</div>
														</label>
													</div>
												</td>
												<td class="td2">
													<span class="imgBox">
														<img src="images/img1001.jpg">
													</span>
													<span>${item.productName }</span>
												</td>
												<td class="td3">${item.productTypeName }</td>
												<td class="td4">${item.createTimeStr }</td>
												<td class="td5">${item.productStatusStr }</td>
												<%-- <td class="td6">${${item.tag }</td> --%>
												<td class="td7">
													<div class="td_btn">
														<p><span class="red edit">${t.b_edit }</span>|<span class="red del">${t.b_delete }</span></p>
														<p>
															<span class="cancel">${t.t_product_off }</span>
														</p>
													</div>
												</td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
					</div>
					<div class="operation">
						<input type="checkbox" class="u-ipt-checkbox" id="all-ipt-checkbox">
						<label class="all-ipt-checkbox" for="all-ipt-checkbox">${t.b_select_all }</label>
						<span class="table-btn">${t.b_delete }</span>
						<span class="table-btn">${t.t_product_on }</span>
						<span class="table-btn">${t.t_product_off }</span>
					</div>
					<div class="pagebar"></div>
					<!-- <div class="page-toolbar">
						<div class="ajax-page">
							<ul class="page-pagination">
								<li class="first-page">
									<span>首页</span>
								</li>
								<li class="previous-page">
									<span>上一页</span>
								</li>
								<li class="active">
									<span>1</span>
								</li>
								<li>
									<span>2</span>
								</li>
								<li>
									<span>3</span>
								</li>
								<li>
									<span>4</span>
								</li>
								<li>
									<span>5</span>
								</li>
								<li>
									<span>6</span>
								</li>
								<li>
									<span>7</span>
								</li>
								<li>
									<span>8</span>
								</li>
								<li>
									<span>9</span>
								</li>
								<li>
									<span>10</span>
								</li>
								<li class="omit">
									<span><i>...</i>15</span>
								</li>
								<li class="skip">
									<input type="text" value="1">/
									<span>15</span>页
								</li>
								<li class="next-page">
									<span>下一页</span>
								</li>
							</ul>
						</div>
					</div> -->
				</div>

			</div>
		</div>
			<!-- //companyData -->
		
	</div>
	
	<jsp:include page="../include/footer.jsp"></jsp:include>

<script type="text/javascript">
$(function(){
	$(".pagebar").createPage({
		pageCount : '${productPage.pageCount}',
		current : '${productPage.currentPage}',
		fnName : 'nextPage',
		backFn : function(p) {
			// console.log(p);
		}
	});	
})
</script>
</body>
</html>