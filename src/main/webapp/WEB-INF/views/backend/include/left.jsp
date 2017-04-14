	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="sidebar-menu toggle-others fixed">
	<div class="sidebar-menu-inner">

		<header class="logo-env">
			<!-- logo -->
			<div class="logo">
				<a href="javascript:void(0);" class="logo-expanded"> <!-- <img src="/admin/images/logo@2x.png" width="80" alt="" /> -->
					<h2 style="color: #fff; padding: 0; margin: 0;">
						<i class="fa fa-cog"></i> Admin
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
					<span class="title">主页</span>
				</a>
			</li>
			<li class="li">
				<a href=""><i class="linecons-user"></i><span class="title">用户管理</span></a>
			</li>
			<li class="li">
				<a href="ui-panels.html"> 
					<i class="linecons-attach"></i> 
					<span class="title">广告管理</span>
				</a>
				<ul>
					<li><a href=""><span class="title">活动轮播图</span></a></li>
					<li><a href=""><span class="title">广告位管理</span></a></li>
				</ul>
			</li>
			<li class="li">
				<a href="ui-panels.html"> 
					<i class="linecons-attach"></i> 
					<span class="title">产品管理</span>
				</a>
				<ul>
					<li><a href=""><span class="title">类别管理</span></a></li>
					<li><a href=""><span class="title">点击量查看</span></a></li>
					<li><a href=""><span class="title">权重管理</span></a></li>
				</ul>
			</li>
			<li class="li">
				<a href="ui-panels.html"> 
					<i class="linecons-attach"></i> 
					<span class="title">新闻资讯管理</span>
				</a>
				<ul>
					<li><a href=""><span class="title">资讯管理</span></a></li>
					<li><a href=""><span class="title">阅读量</span></a></li>
					<li><a href=""><span class="title">点击计数管理</span></a></li>
					<li><a href=""><span class="title">评论管理</span></a></li>
				</ul>
			</li>
			<li class="li">
				<a href="ui-panels.html"> 
					<i class="linecons-attach"></i> 
					<span class="title">店家管理</span>
				</a>
				<ul>
					<li><a href=""><span class="title">编辑店家</span></a></li>
					<li><a href=""><span class="title">店家资质审核</span></a></li>
					<li><a href=""><span class="title">店家产品审核</span></a></li>
					<li><a href=""><span class="title">店家留言查看</span></a></li>
					<li><a href=""><span class="title">店家产品权重管理</span></a></li>
				</ul>
			</li>
			<li class="li">
				<a href=""><i class="linecons-clock"></i><span class="title">论坛管理</span></a>
			</li>
		</ul>

	</div>
</div>