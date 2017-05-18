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
				</div>
				<div class="page-toolbar">
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
				</div>
			</div>
			<!-- //companyData -->
		</div>
		<!-- //container -->
	</div>
	<!-- //main -->

</body>
</html>