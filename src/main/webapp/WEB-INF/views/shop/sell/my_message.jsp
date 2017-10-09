<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>${t.t_product_msg }</title>
	<link href="/frontstyle/sell/css/leave_message.css" rel="stylesheet" type="text/css">
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
				<div class="header"><span>${t.m_product }>  ${t.t_product_msg }</span></div>
				<div class="product_message">
					<div class="g-tabWrap">
						<div class="g-tabHdWrap">
							<ul class="g-tabHd">
								<li class="<c:if test="${type==1 }">f-active</c:if>">
									<span><a href="/front/sell/messagePage?type=1">${t.t_mymsg}</a></span>
								</li>
								<li class="<c:if test="${type==2 }">f-active</c:if>">
									<span><a href="/front/sell/messagePage?type=2">${t.t_myletter}</a></span>
								</li>
								<li class="<c:if test="${type==3 }">f-active</c:if>">
									<span><a href="/front/sell/messagePage?type=3">${t.t_my_news_comment}</a></span>
								</li>
							</ul>
						</div>
						<div class="g-tabMn">
							<div class="g-tabMnItem f-active">
								<ul>
								<c:forEach var="item" items="${page.list }">
									<l  i class="list">
										<div class="item">
											<div class="headline">
												<div class="head_text">${item.content }</div>
												<div class="head_portrait">
													<div class="img_head_portrait">
														<img src="${item.receiveImg }">
													</div>
													<div class="name_time">
														<p class="name">${item.postName }</p>
														<p class="time">${item.date }</p>
													</div>

												</div>
											</div>
											<div class="content">
												<div class="img_box">
													<img src="${item.imagePath }" alt="">
												</div>
												<div class="introduce">
													<p class="name"><span>${item.productName }</span> <!-- <span>25</span>kg/盒 --></p>
													<p class="referral">${item.desc }</p>
													<p class="tariff">${t.t_price }：¥<span>${item.price }</span></p>
												</div>
											</div>
											<div class="date_reply">
												<!-- <span>2016-06-27 17:55</span> -->
												<span class="reply">${t.t_reply }</span>
											</div>	
											<div class="edit" style="display:none;">
												<textarea id="msgContent"></textarea>
												<div class="edit_btn">
													<span class="cancel">${t.b_cancel }</span>
													<span class="affirm" productId="${item.productId }" commentId="${item.commentId }" replyToId="${item.postId }" messageId="${item.messageId }">${t.b_confirm }</span>
												</div>
											</div>
										</div>
										<div class="write_back_box">
											<ul class="write_back">
												<c:forEach var="reply" items="${item.replyList }">
													<li>
														<p class="quietly">
															<span class="red">${reply.postName }</span>
															<span>${t.t_reply }</span>
															<span class="red">${reply.receiveName }：</span>
															<span>${reply.content }</span>
														</p>
														<%-- <p class="reply_box">
															<span>2016-06-27 17:55</span>
															<span class="reply">${item.t_reply }</span>
														</p> --%>
													</li>												
												</c:forEach>
											</ul>
											<!-- <div class="unfold red">
												<div class="spread">
													<span>展开更多</span>
													<i><img src="images/drop-down-arrow.jpg" alt=""></i>
												</div>
											</div> -->
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
	
	<jsp:include page="../include/footer.jsp"></jsp:include>
	
<script type="text/javascript">
$(function(){
	$('.menu_box .menu_list div.module a').eq(7).addClass('active');
	
	$(".pagebar").createPage({
		pageCount : '${page.pageCount}',
		current : '${page.currentPage}',
		fnName : 'nextPage',
		backFn : function(p) {
			// console.log(p);
		}
	});	
	
	//显示输入框
	$('.reply').click(function(){
		$('.edit').hide();
		$(this).parent(".date_reply").next('.edit').show();
	});
	//回复
	$('.affirm').click(function(){
		var parm={};
		var productId = $(this).attr('productId');
		var commentId = $(this).attr('commentId');
		var messageId = $(this).attr('messageId');
		var replyToId = $(this).attr('replyToId');
		
		parm.type=2;
		parm.productId=productId;
		parm.msgContent=$(this).parent().prev().val();
		parm.messageId=messageId;
		parm.replyToId=replyToId;
		
		jQuery.common.updateObjByParm(parm,'/front/sell/messageReply',true,'/front/sell/messagePage?type=2');
	});
	//取消回复
	$('.cancel').click(function(){
		$('.edit').hide();
	});
});
//1产品评论 2私信3新闻评论
function nextPage(pageNo){
	var parm = "";
	parm = parm + "type=2";
	parm = parm + "&pageNo="+pagerNo;
	parm = parm + "&pageSize="+10;
	self.location.href="/front/sell/messagePage?"+parm;
}

</script>
</body>
</html>