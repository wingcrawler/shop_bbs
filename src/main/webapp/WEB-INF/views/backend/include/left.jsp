	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="sidebar-menu toggle-others fixed">
	<div class="sidebar-menu-inner">

		<header class="logo-env">
			<div class="logo">
				<a href="javascript:void(0);" class="logo-expanded"> 
					<h2 style="color: #fff; padding: 0; margin: 0;">
						<i class="fa fa-cog"></i> ${t.backend_name }
					</h2>
				</a>
			</div>
			<!-- <div class="mobile-menu-toggle visible-xs">
				<a href="#" data-toggle="user-info-menu"> <i class="fa-bell-o"></i>
					<span class="badge badge-success">7</span>
				</a> <a href="#" data-toggle="mobile-menu"> <i class="fa-bars"></i>
				</a>
			</div> -->
		</header>

		<ul id="main-menu" class="main-menu">
			<li class="li active opened">
				<a href="/backend/index">
					<i class="linecons-globe"></i>
					<span class="title">${t.home}</span>
				</a>
			</li>
			<!-- 广告信息管理 -->
			<li class="li">
				<a href="/backend/ad/list"><i class="linecons-tag"></i><span class="title">${t.m_ad }</span></a>
			</li>
			<!-- 产品管理 -->
			<li class="li">
				<a href="ui-panels.html"> 
					<i class="linecons-attach"></i> 
					<span class="title">${t.m_product }</span>
				</a>
				<ul>
					<li><a href="/backend/product/list"><span class="title">${t.m_product_list }</span></a></li>
					<li><a href="/backend/product/category"><span class="title">${t.m_product_cate }</span></a></li>
				</ul>
			</li>
			<!-- 新闻资讯管理 -->
			<li class="li">
				<a href="ui-panels.html"> 
					<i class="linecons-note"></i> 
					<span class="title">${t.m_news }</span>
				</a>
				<ul>
					<li><a href="/backend/news/list"><span class="title">${t.m_news_info }</span></a></li>
					<li><a href=""><span class="title">${t.m_news_read }</span></a></li>
					<li><a href=""><span class="title">${t.m_news_click }</span></a></li>
					<li><a href=""><span class="title">${t.m_news_comment }</span></a></li>
				</ul>
			</li>
			<!-- 店家管理 -->
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
			<!-- 用户管理 -->
			<li class="li">
				<a href=""><i class="linecons-user"></i><span class="title">${t.m_user }</span></a>
			</li>
			<!-- 论坛管理 -->
			<li class="li">
				<a href=""><i class="linecons-comment"></i><span class="title">${t.m_bbs }</span></a>
			</li>
			<!-- 举报信息 -->
			<li class="li">
				<a href=""><i class="linecons-star"></i><span class="title">${t.m_report }</span></a>
			</li>
		</ul>

	</div>
</div>