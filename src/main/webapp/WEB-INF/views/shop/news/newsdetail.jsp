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
						阅读:${news.newsReaded } &nbsp;|&nbsp;
						<a class="zan" href="javascript:;" id="newsUp"> ${news.newsUp  }</a>
						<a class="mass" href="###"></a>
					</span>
				</div>
			</div>
			<div class="img"><img src="${image.imagePath }" alt=""></div>
			<div class="text">
				${news.newsContext}
			</div>
			
			<!-- 发表评论区域 -->
			<c:if test="${isLogin }">
				<div class="pl">
					<form method="POST" id="commentForm">
						<input type="hidden" name="newsId" value="${news.id }">
						<textarea class="ir-post-input" name="context" bindcursor="true" cols="0" placeholder="${t.tip_comment}"></textarea>
						<p><input type="button" onclick="postComment()" value="${t.t_post_comment}"></p>
					</form>
				</div>	
			</c:if>
			<%-- <div class="user">${user.username} <em>/</em> <a href="/logout">${t.sign_out}</a></div> --%>
			<!-- 发表评论区域 -->
			
			<!-- 评论列表区域 -->
			<c:if test="${not empty commentPage.list }">
				<div class="allpl"><a name="commentArea">${t.t_all_comment}</a></div>		
			</c:if>
			<ul>
				<c:forEach var="item" items="${commentPage.list }" varStatus="itemStatus">
					<li class="atl-con-bd clearfix">
						<div class="floor">${item.postName} : ${item.content}</div>
						<%-- <div class="floor">${item.content}</div> --%>
						<div class="floor_data">${item.date } <span class="reply" style="display:none;" onclick="showReplyForm(this)">${t.t_reply }</span></div>
						<!-- 回复评论 -->
						<div class="news_reply" style="display: none;">
							<form method="POST" class="commentReplyForm${itemStatus.index }">
								<input type="hidden" name="commentId" value="${item.commentId }">
								<input type="hidden" name="replyToUserId" value="${item.commentUserId }">
								<textarea class="editor" name="context" placeholder="${t.tip_comment_1 }"></textarea>
								<input type="button" onclick="postReplyComment(this)" value="${t.b_submit }">
								<input type="button" class="bg-grey" style="background-color:#aaa;" onclick="cancelComment(this)" value="${t.b_cancel }">
							</form>
						</div>
						<!-- 评论的回复列表 -->
						<c:if test="${not empty item.replyList }">
							<div class="item-reply-view">
								<c:forEach var="reply" items="${item.replyList }" varStatus="replyStatus">
									<div class="ir-list">
										<div>${reply.postName } ：${reply.content}</div>
										<%-- <div class="content">${reply.content}</div> --%>
										<div class="data floor_data">${reply.date } <span class="reply" onclick="showReplyForm(this)" style="display:none;">${t.t_reply }</span></div>
										<!-- 回复评论 -->
										<div class="news_reply" style="display: none;">
											<form method="POST" class="commentReplyForm${itemStatus.index }${replyStatus.index }">
												<input type="hidden" name="commentId" value="${item.commentId }">
												<input type="hidden" name="replyToUserId" value="${reply.postId }">
												<textarea class="editor" name="context" placeholder="${t.tip_comment_1 }"></textarea>
												<input type="button" onclick="postReplyComment(this)" value="${t.b_submit }">
												<input type="button" class="bg-grey" style="background-color:#aaa;"  onclick="cancelComment(this)" value="${t.b_cancel }">
											</form>
										</div>
									</div>								
								</c:forEach>	
							</div>
						</c:if>
					</li>
				</c:forEach>
			</ul>
			<br><br>
			<!-- 评论列表区域 -->
			<!-- <div class="more"><a href="###">查看全部评论&gt&gt</a></div> -->
			<!-- <div class="ads">
				<a href="###">
					<img src="images/ads.png" alt=""></a>
			</div> -->
		</div>
	<!-- 新闻end -->
	</div>

	<jsp:include page="../include/footer.jsp"></jsp:include>
	
<style>
.bg-grey{background-color:#666;}
</style>
<script type="text/javascript">
	$(function(){
		var newsId = '${news.id}';
		var isThumb=false;
		setTimeout("locationCommentArea();",400);
		
		//评论后的的回复鼠标放上去显示 移开隐藏
		/* $('li.atl-con-bd .floor_data').hover(function(){
			$(this).children('span').show();
		}, function(){
			$(this).children('span').hide();
		}); */
		
		
		//点击回复显示输框
		/* $('li.atl-con-bd .floor_data span.reply').click(function(){
			$(this).parent('.floor_data').next('.news_reply').show();
		}, function(){
			$(this).parent('.floor_data').next('.news_reply').hide();
		}); */
		
		//点赞
		$('#newsUp').click(function(){
			if(!isThumb){
				var currentNewsUp = $(this).text();
				currentNewsUp = parseInt(currentNewsUp) + 1;
				$(this).text(currentNewsUp);
				
				var parm = {};
				parm.newsId=newsId;
				jQuery.common.updateObjByParm(parm,'/news/doNewsUp',false,null);	
				
				isThumb = true;
			}
		});
	});
	
	function locationCommentArea(){
		//定位到评论区
		var hash = window.location.hash; 
		if(hash=="#commentArea"){
			var t = $("a[name='commentArea']").offset().top;
		    $(window).scrollTop(t);//滚动到锚点位置
		}  
		//window.location.hash = hash; 
	}; 
	
	//显示评论表单
	function showReplyForm(obj){
		$('.news_reply').hide();
		$(obj).parent('.floor_data').next('.news_reply').show();
	}
	
	//取消评论
	function cancelComment(obj){
		$('.news_reply').hide();
	}
	
	function postComment(){
		jQuery.common.saveObj("#commentForm","/news/saveComment",true,"/news/detail?newsId="+${news.id}+"&#commentArea");
	}
	
	function postReplyComment(obj){
		debugger;
		var elem = $(obj).parents('.news_reply form').attr('class');
		elem = '.'+elem;
		jQuery.common.saveObj(elem,"/news/saveComment",true,"");
	}
	
</script>
</body>
</html>