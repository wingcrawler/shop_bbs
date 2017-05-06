<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

	<jsp:include page="../include/meta.jsp"></jsp:include>
	<title>${t.title_view_user }</title>
</head>
<body class="page-body">
	<div class="page-container">
		<!-- 左边栏 -->
		<jsp:include page="../include/left.jsp"></jsp:include>
		<div class="main-content">
			<!-- 头部栏 -->
			<jsp:include page="../include/header.jsp"></jsp:include>	
			
			<!-- 用户信息区 -->
			<section class="profile-env">
				<div class="row">
				
					<!-- 用户头像部分 -->
					<div class="col-sm-3">
						<div class="user-info-sidebar">
							<a href="#" class="user-img">
								<img src="${entity.userImage }" alt="user-img" class="img-cirlce img-responsive img-thumbnail">
							</a>
							<a href="#" class="user-name">
								${entity.username }
							</a>
							<span class="user-title">
								<c:if test="${entity.userRole==1 }"><strong>${t.t_buy }</strong></c:if>
								<c:if test="${entity.userRole==2 }"><strong>${t.t_sell }</strong></c:if>
								<c:if test="${entity.userStatus==0 }"><span style="color:#f00;">${t.t_off }</span></c:if>
								<c:if test="${entity.userStatus==1 }">${t.t_on }</c:if>
							</span>
							<hr>
							<ul class="list-unstyled user-info-list">
								<li><i class="fa fa-home"></i>${entity.userAddress }</li>
								<li><i class="fa fa-envelope-o fa-fw"></i>${entity.userMail }</li>
								<li><i class="fa fa-phone"></i>${entity.userPhone }</li>
							</ul>	
							<!-- <hr>
							<ul class="list-unstyled user-friends-count">
								<li>
									<span>643</span>
									followers
								</li>
								<li>
									<span>108</span>
									following
								</li>
							</ul> -->
							<c:if test="${entity.userStatus==1 }">
								<button type="button" class="btn btn-danger btn-block text-left" id="offUser">
									${t.t_off }<i class="fa-remove pull-right"></i>
								</button>
							</c:if>
							<c:if test="${entity.userStatus==0 }">
								<button type="button" class="btn btn-success btn-block text-left" id="onUser">
									${t.t_cancel_off }<i class="fa-check pull-right"></i>
								</button>
							</c:if>
						</div>
					</div>
					<!-- 用户头像部分 结束-->
					
					<!-- 商家介绍 -->
					<div class="col-sm-9">
						<section class="user-timeline-stories">
							<article class="timeline-story">
								<i class="fa-paper-plane-empty block-icon"></i>
								<header>
									<!-- <a href="#" class="user-img">
										<img src="assets/images/user-4.png" alt="user-img" class="img-responsive img-circle">
									</a> -->
									<div class="user-details">
										${t.t_desc }:
									</div>
								</header>
								<div class="story-content">
									<c:if test="${empty entity.userIntroduce }">
										No Introduce!
									</c:if>
									<c:if test="${not empty entity.userIntroduce }">
										<p>${entity.userIntroduce}</p>
									</c:if>
								</div>
							</article>
						</section>
					</div>
					
				</div>
			</section>
			<!-- 用户信息结束 -->
			
		</div>
	</div>
<script type="text/javascript">
$(function(){
	$('#main-menu li.li').removeClass('active').removeClass('opened');
	$('#main-menu li.li').eq(5).addClass('active').addClass('opened');
	$('#main-menu li.li').eq(5).find('ul li').eq(0).addClass('active');
	
	var id='${entity.id}';
	$('#offUser').click(function(){
		if(confirm('${t.t_confirm_user_off}')){
			$.ajax({
				type: "POST",
				url: "/backend/user/onOroffUser",
				dataType : "json",
				data: {userId:id,status:0},
				success: function(data) {
					if (data.errorNo != 0) {
						$.commonUtil.showTip(data.errorInfo);
					} else {
						self.location='';
					}
				}
			});
		}
	});
	$('#onUser').click(function(){
		if(confirm('${t.t_confirm_user_on}')){
			$.ajax({
				type: "POST",
				url: "/backend/user/onOroffUser",
				dataType : "json",
				data: {userId:id,status:1},
				success: function(data) {
					if (data.errorNo != 0) {
						$.commonUtil.showTip(data.errorInfo);
					} else {
						self.location='';
					}
				}
			});
		}
	});
});
</script>
</body>
</html>