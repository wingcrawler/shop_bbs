<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>${t.t_news}</title>
	<link rel="stylesheet" href="/frontstyle/css/topic.css" type="text/css" media="all" />
</head>
<body>
	<jsp:include page="../include/header.jsp"></jsp:include>
	
	<!-- 新闻-->
	<div class="news">
		<div class="container">
			<div class="hot_news"><span>${t.t_hot_news}</span></div>
			<ul>
				<c:forEach var="item" items="${list }">
					<li>
						<a href="/news/detail?newsId=${item.id}" class="liA">
							<c:if test="${not empty item.imagePath }">
								<div class="news_pic"><img src="${item.imagePath}"></div>
								<div class="word">
									<h1>${item.newsTitle}</h1>
									<p>${item.newsContent}</p>
								</div></a>
								<div class="tR">
									${item.dateStr}&nbsp;|&nbsp;
									<span class="tm">
										${t.t_readed }:${item.newsRead } &nbsp;|&nbsp;
										${t.t_praise }:${item.newsUp }
										<%-- <a class="zan" href="###"> ${item.newsRead }</a>
										<a class="mass" href="###"> </a> --%>
									</span>
								</div>	
							</c:if>
							<c:if test="${empty item.imagePath }">
								<h1>${item.newsTitle}</h1>
								<p>${item.newsShotDesc}</p>
								<div class="tR">
									${item.dateStr}&nbsp;|&nbsp;
									<span class="tm">
										阅读:${item.newsRead } &nbsp;|&nbsp;
										点赞:${item.newsUp }
										<%-- <a class="zan" href="###"> ${item.newsRead }</a>
										<a class="mass" href="###"> </a> --%>
									</span>
								</div>
							</c:if>
						</a>
					</li>	
				</c:forEach>
			</ul>
			
			<div class="pagebar"></div>
			
			<!-- <div class="ads"><a href="###"><img src="images/ads.png" alt=""></a></div> -->
		</div>
	</div>
	<!-- 新闻end -->

	<jsp:include page="../include/footer.jsp"></jsp:include>
	
<script type="text/javascript">
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
	var searchText = $('input[name="searchText"]').val();
	args += '&searchText='+encodeURIComponent(searchText);
	self.location="/news/list"+args;
}
</script>
</body>
</html>