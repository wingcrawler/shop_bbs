<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- menu -->
<div class="menu">
	<div class="menu_title">
		<p class="title_img"><img src="${img}" alt=""></p>
		<p class="title_text">${user.username}</p>
	</div>
	<div class="menu_list">
		<ul>
			<li><a href="#">${t.t_user_center }</a></li>
			<li><a href="/front/buy/basicInfo">${t.t_basic_info }</a></li>
			<li><a href="/front/buy/postRecord">${t.t_post_record }</a></li>
			<li><a href="/front/buy/messagePage?type=1">${t.t_my_product_comment }</a></li>
			<li><a href="/front/buy/messagePage?type=2">${t.t_my_message }</a></li>
			<li><a href="/front/buy/messagePage?type=3">${t.t_my_news_comment }</a></li>
			<li><a href="/front/buy/changePwd">${t.t_change_pwd }</a></li>
			<c:if test="${isSellLogin }">
				<li><a href="/front/sell/productListPage">${t.t_my_shop }</a></li>
			</c:if>
			<c:if test="${isBuyLogin }">
				<li><a href="/front/buy/applayShop">${t.t_goto_open_shop }</a></li>	
			</c:if>
			<%-- <li><a href="#">${t.t_want_report }</a></li>       --%>
		</ul>
	</div>
</div> 
<!-- //menu -->

<style>
.active{color:#f00}
</style>