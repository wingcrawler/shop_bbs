<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- menu -->
<div class="menu">
	<div class="menu_title">
		<p class="title_img"><img src="${user.userImage}" alt=""></p>
		<p class="title_text">${user.username}</p>
	</div>
	<div class="menu_list">
		<ul>
			<li>${t.t_user_center }</li>
			<li>${t.t_basic_info }</li>
			<li>${t.t_post_record }</li>
			<li>${t.t_my_comment }</li>
			<li>${t.t_my_message }</li>
			<li>${t.t_change_pwd }</li>
			<li>${t.t_want_report }</li>      
		</ul>
	</div>
</div> 
<!-- //menu -->

<style>
.active{color:red}
</style>