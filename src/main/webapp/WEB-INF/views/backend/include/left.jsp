	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="sidebar-menu toggle-others fixed">
	<div class="sidebar-menu-inner">

		<header class="logo-env">
			<!-- logo -->
			<div class="logo">
				<a href="javascript:void(0);" class="logo-expanded"> <!-- <img src="/admin/images/logo@2x.png" width="80" alt="" /> -->
					<h2 style="color: #fff; padding: 0; margin: 0;">
						<i class="fa fa-cog"></i> ${t.backend_name }
					</h2>
				</a>
			</div>
			<div class="mobile-menu-toggle visible-xs">
				<a href="#" data-toggle="user-info-menu"> <i class="fa-bell-o"></i>
					<span class="badge badge-success">7</span>
				</a> <a href="#" data-toggle="mobile-menu"> <i class="fa-bars"></i>
				</a>
			</div>
		</header>

		<ul id="main-menu" class="main-menu">
			<!-- add class "multiple-expanded" to allow multiple submenus to open -->
			<!-- class "auto-inherit-active-class" will automatically add "active" class for parent elements who are marked already with class "active" -->
			<li class="li active opened">
				<a href="/backend/index">
					<i class="linecons-globe"></i>
					<span class="title">${t.home}</span>
				</a>
			</li>
			<li class="li">
				<a href=""><i class="linecons-user"></i><span class="title">${t.m_user }</span></a>
			</li>
			<li class="li">
				<a href="ui-panels.html"> 
					<i class="linecons-tag"></i> 
					<span class="title">${t.m_ad }</span>
				</a>
				<ul>
					<li><a href=""><span class="title">${t.m_ad_active }</span></a></li>
					<li><a href=""><span class="title">${t.m_ad_position }</span></a></li>
				</ul>
			</li>
			<li class="li">
				<a href="ui-panels.html"> 
					<i class="linecons-attach"></i> 
					<span class="title">${t.m_product }</span>
				</a>
				<ul>
					<li><a href=""><span class="title">${t.m_product_cate }</span></a></li>
					<li><a href=""><span class="title">${t.m_product_click }</span></a></li>
					<li><a href=""><span class="title">${t.m_product_weight }</span></a></li>
				</ul>
			</li>
			<li class="li">
				<a href="ui-panels.html"> 
					<i class="linecons-note"></i> 
					<span class="title">${t.m_news }</span>
				</a>
				<ul>
					<li><a href=""><span class="title">${t.m_news_info }</span></a></li>
					<li><a href=""><span class="title">${t.m_news_read }</span></a></li>
					<li><a href=""><span class="title">${t.m_news_click }</span></a></li>
					<li><a href=""><span class="title">${t.m_news_comment }</span></a></li>
				</ul>
			</li>
			<li class="li">
				<a href="ui-panels.html"> 
					<i class="linecons-shop"></i> 
					<span class="title">${t.m_store }</span>
				</a>
				<ul>
					<li><a href=""><span class="title">${t.m_store_list }</span></a></li>
					<li><a href=""><span class="title">${t.m_store_product }</span></a></li>
					<li><a href=""><span class="title">${t.m_store_msg }</span></a></li>
					<li><a href=""><span class="title">${t.m_store_weight }</span></a></li>
				</ul>
			</li>
			<li class="li">
				<a href=""><i class="linecons-comment"></i><span class="title">${t.m_bbs }</span></a>
			</li>
		</ul>

	</div>
</div>