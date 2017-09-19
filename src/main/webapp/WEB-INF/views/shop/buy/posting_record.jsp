<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>${t.t_post_record }</title>
	<link href="/frontstyle/buy/css/posting_record.css" rel="stylesheet" type="text/css">
</head>
<body>
	<jsp:include page="../include/header.jsp"></jsp:include>
	
	<!-- main -->
	<div class="main">
		<!-- container -->
		<div class="container">
			<jsp:include page="include/left.jsp"></jsp:include>
			
			<!-- companyData -->
			<div class="companyData">
				<div class="header"><span>${t.t_user_center } >  ${t.t_post_record }</span></div>
				<div class="product_message">
					<div class="ulbox">
						<div class="g-tabWrap">
							<div class="g-tabHdWrap">
								<ul class="g-tabHd">
									<li class="f-active"><span>${t.t_post_record }</span></li>
								</ul>
							</div>
							<div class="g-tabMn">
								<ul class="g-tabMnItem f-active">
								<c:forEach var="item" items="${page.list }">
									<li>
										<div class="invitation">
											<span>${t.t_at}</span>
											<span class="red">${item.topicTitle}</span>
											<span>${t.t_post}</span>
											<span class="red"><a href="/bbs/thread?threadId=${item.id}">${item.threadTitle}</a></span>
											<span>${t.t_reply}(${item.count})</span>
										</div>
										<div class="time">
											<span>${item.time}</span>
										</div>
									</li>
								</c:forEach>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<div class="pagebar"></div>
			</div>
			<!-- //companyData -->
		</div>
		<!-- //container -->
	</div>
	<!-- //main -->

<script type="text/javascript">
$(function(){
	$('.menu_list ul li a').eq(2).addClass('active');
	
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
	self.location="/front/buy/postRecord"+args;
}
</script>

</body>
</html>