<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>${t.t_post_reply }</title>
	<link href="/frontstyle/sell/css/posted_reply.css" rel="stylesheet" type="text/css">
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
				<div class="header"><span>${t.m_bbs } >  ${t.t_post_reply }</span></div>
				<div class="product_message">
					<div  class="ulbox">
						<div class="g-tabWrap">
							<div class="g-tabHdWrap">
								<ul class="g-tabHd">
									<li class="f-active"><span>${t.t_mypost }</span></li>
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
												<span class="red">${item.threadTitle}</span>
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
					<div class="pagebar"></div>
				</div>

			</div>
		</div>
			<!-- //companyData -->
	</div>
	<!-- //main -->
	
	<jsp:include page="../include/footer.jsp"></jsp:include>
	
<script type="text/javascript">
$(function(){
	$('.menu_box .menu_list div.module a').eq(6).addClass('active');
	
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
	var parm = "";
	parm = parm + "pageNo="+pagerNo;
	parm = parm + "&pageSize="+10;
	self.location.href="/front/sell/postReply?"+parm;
}

</script>
</body>
</html>