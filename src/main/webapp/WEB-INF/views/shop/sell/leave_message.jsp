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
							</ul>
						</div>
						<div class="g-tabMn">
							<div class="g-tabMnItem f-active">
								<ul>
								<c:forEach var="item" items="${page.list }">
									<li class="list">
										<div class="item">
											<div class="headline">
												<div class="head_text">${item.content }</div>
												<div class="head_portrait">
													<div class="img_head_portrait">
														<img src="images/head_portrait.jpg">
													</div>
													<div class="name_time">
														<p class="name">${item.postName }</p>
														<p class="time">${item.date }</p>
													</div>

												</div>
											</div>
											<div class="content">
												<div class="img_box">
													<img src="images/img1001.jpg" alt="">
												</div>
												<div class="introduce">
													<p class="name"><span>${item.productName }</span> <!-- <span>25</span>kg/盒 --></p>
													<p class="referral">${item.desc }</p>
													<p class="tariff">${t.t_price }：＄<span>${item.price }</span></p>
												</div>
											</div>
											<div class="date_reply">
												<!-- <span>2016-06-27 17:55</span> -->
												<span class="reply">${t.t_reply }</span>
											</div>	
											<div class="edit">
												<textarea></textarea>
												<div class="edit_btn">
													<span class="cancel">${t.b_cancel }</span>
													<span class="affirm">${t.b_confirm }</span>
												</div>
											</div>
										</div>
										<div class="write_back_box">
											<ul class="write_back">
												<li>
													<p class="quietly">
														<span class="red">${t.t_me }</span>
														<span>${t.t_reply }</span>
														<span class="red">等一个孩子的归来：</span>
														<span>绝对可以的，您买了我们产品肯定会见效的，感谢对本产品的支持。</span>
													</p>
													<p class="reply_box">
														<span>2016-06-27 17:55</span>
														<span class="reply">${item.productName }</span>
													</p>
												</li>
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
	$(".pagebar").createPage({
		pageCount : '${page.pageCount}',
		current : '${page.currentPage}',
		fnName : 'nextPage',
		backFn : function(p) {
			// console.log(p);
		}
	});	
});
var type="${type}";//1留言 2私信
function nextPage(pageNo){
	var parm = "";
	parm = parm + "type="+type;
	parm = parm + "&pageNo="+pagerNo;
	parm = parm + "&pageSize="+10;
	self.location.href="/front/sell/messagePage?"+parm;
}

</script>
</body>
</html>