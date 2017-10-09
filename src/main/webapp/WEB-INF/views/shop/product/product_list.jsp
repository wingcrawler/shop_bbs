<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>${title }</title>
</head>
<body>
	<jsp:include page="../include/header.jsp"></jsp:include>
	
	<!-- men -->
	<div class="men">
		<!-- container -->
		<div class="container">
			<div class="col-md-9 fashions">
				<div class="title">
					<h3>${t.m_product_list }</h3>
				</div>
				<div class="fashion-section">
					<div class="fashion-grid1">
					<c:forEach var="item" items="${page.list }" varStatus="status">
						 <div class="col-md-3 fashion-grid" style="height: 225px;">
							 <a href="single.html"><img src="${item.imagePath }" alt=""/>
								 <div class="product <c:if test="${item.productCount==0 }">not-avaliable</c:if>">
									 <h3>${item.productName }</h3>
									 <p>
									 	<c:if test="${item.productCount==0 }">
											<del><span></span> ¥ ${item.productPrice }</del>										 	
									 	</c:if>
									 	<c:if test="${item.productCount!=0 }">
									 		<span></span> ¥ ${item.productPrice } 
									 	</c:if>
									 </p>
								 </div>
							 </a>
							 
							 <div class="fashion-view"><span></span>
								<div class="clearfix"></div>
								<c:if test="${item.productCount==0 }">
									<h4 class="sold-out"><a href="/product/single?productId=${item.id }" target="_blank"><del>Sold Out</del></a></h4>									 	
							 	</c:if>
							 	<c:if test="${item.productCount!=0 }">
							 		<h4><a href="/product/single?productId=${item.id }" target="_blank">Quick View</a></h4>
							 	</c:if>
							 </div>
						 </div>
						 <c:if test="${status.count/4==1 }">
							<div class="clearfix"></div>
							<br>
						</c:if>
					</c:forEach>
					</div>
				
				</div>
			</div>
		
			<div class="col-md-3 side-bar">
				<div class="categories">
					<h3>${t.t_product_type }</h3>
					<ul>
						<li><a href="/product/list">
								<c:if test="${empty parentType  }">
									<b>${t.t_all }</b>
								</c:if>
								<c:if test="${not empty parentType  }">
									${t.t_all }
								</c:if>
							</a></li>
						<c:forEach var="item" items="${productTypeList }">
							<li>
							<a href="/product/list?parentType=${item.key.id }">
								<c:if test="${parentType==item.key.id  }">
									<b>${item.key.typeName }</b>
								</c:if>
								<c:if test="${parentType!=item.key.id  }">
									${item.key.typeName }
								</c:if>
							</a>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>

			<div class="clearfix"> </div>
			<div class="pagebar"></div>
			
		</div>
		<!-- //container -->
	</div>
	<!-- //men -->
	<jsp:include page="../include/footer.jsp"></jsp:include>
	
<script type="text/javascript">
var parentType = '${parentType}';
$(function(){
	$(".pagebar").createPage({
		pageCount : '${page.pageCount}',
		current : '${page.currentPage}',
		fnName : 'nextPage',
		backFn : function(p) {
			// console.log(p);
		}
	});	
});

function nextPage(pageNo){
	var args = '?pageNo='+pageNo;
	if(parentType!=''){
		args += '&parentType='+parentType;
	}
	var searchText = $('input[name="searchText"]').val();
	args += '&searchText='+encodeURIComponent(searchText);
	/* args += '&pageSize=3'; */
	
	self.location="/product/list"+args;
}
</script>
</body>
</html>