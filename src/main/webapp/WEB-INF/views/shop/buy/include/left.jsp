<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- menu -->
<div class="menu">
	<div class="menu_title">
		<p class="title_img"><img src="${user.userImage}" alt=""></p>
		<p class="title_text">${user.username}</p>
	</div>
	<div class="menu_list">
		<ul>
			<li><a href="#">${t.t_user_center }</a></li>
			<li><a href="/front/buy/basicInfo">${t.t_basic_info }</a></li>
			<li><a href="/front/buy/postRecord">${t.t_post_record }</a></li>
			<li><a href="#">${t.t_my_comment }</a></li>
			<li><a href="#">${t.t_my_message }</a></li>
			<li><a href="/front/buy/changePwd">${t.t_change_pwd }</a></li>
			<li><a href="#">${t.t_want_report }</a></li>      
		</ul>
	</div>
</div> 
<!-- //menu -->

<style>
.active{color:#f00}
</style>