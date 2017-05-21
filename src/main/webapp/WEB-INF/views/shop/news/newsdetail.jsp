<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>${news.newsTitle}</title>
	<jsp:include page="../include/meta.jsp"></jsp:include>
	<link rel="stylesheet" href="/frontstyle/css/topic.css" type="text/css" media="all" />
</head>
<body>
	<jsp:include page="../include/header.jsp"></jsp:include>
	
	<!-- 新闻-->
	<div class="newsdetail">
		<div class="container">
			<div class="hot_news"></div>
			<div class="title">
				<h1>${news.newsTitle}</h1>
				<div class="tR">
					<span class="qq">${news.createTimeStr}</span>
					<span class="tm">
						<a class="zan" href="###">${news.newsUp }</a>
						<a class="mass" href="###"></a>
					</span>
				</div>
			</div>
			<!-- <div class="img"><img src="images/newsdetail.png" alt=""></div> -->
			<div class="text">
				${news.newsContext}
			</div>
			
			<div class="pl">
				<textarea class="ir-post-input" bindcursor="true" cols="0" placeholder="${t.tip_comment}"></textarea>
				<p><input type="submit" value="${t.t_post_comment}"></p>
			</div>
			<div class="user">${user.username} <em>/</em> <a href="/logout">${t.sign_out}</a>
			<div class="allpl">${t.t_all_comment}</div>
			<ul>
				<c:forEach var="item" items="${commentPage.list }">
					<li class="atl-con-bd clearfix">
						<div class="bbs-content">${item.postName}</div>
						<div class="floor">${item.content}</div>
						<div class="floor_data">${item.date }
							<span class="reply">回复</span>
							<div class="news_reply" style="display: block;">
								<textarea class="editor" placeholder="在这里输入你要发表的内容..."></textarea>
								<input type="submit" value="发表">
							</div>
						</div>
						
						<div class="item-reply-view">
							<c:forEach var="reply" items="${item.replyList }">
								<div class="ir-list">
									<div>
										<a class="ir-user" href="###" target="_blank">${reply.postName }</a>
									</div>
									<div class="content">${reply.content}</div>
									<div class="data">${reply.date }</div>
								</div>								
							</c:forEach>	
						</div>
					</li>
				</c:forEach>
			</ul>
			<!-- <div class="more"><a href="###">查看全部评论&gt&gt</a></div> -->
			<!-- <div class="ads">
				<a href="###">
					<img src="images/ads.png" alt=""></a>
			</div> -->
		</div>
	</div>
	<!-- 新闻end -->

	<jsp:include page="../include/footer.jsp"></jsp:include>
	
<script type="text/javascript">
			jQuery(document).ready(function($) {
				$(".scroll").click(function(event){		
					event.preventDefault();
					$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
				});
				$('.reply').toggle(function(e) {
					$(this).parent(".floor_data").find(".news_reply").css('display','block')
				},function(e){
					$(this).parent(".floor_data").find(".news_reply").css('display','none')
				});			
			});

		</script>
</body>
</html>