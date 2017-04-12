<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

	<jsp:include page="include/meta.jsp"></jsp:include>
	<title>Admin</title>
	
<style>
.linecons-cloud:before{position:relative;top:10px;}
.xe-icon .linecons-user:before{position:relative;top:10px;}
.linecons-desktop:before{position:relative;top:10px;}
.linecons-cup:before{position:relative;top:10px;}
</style>
</head>
<body class="page-body">
	<div class="page-container">
		
		<jsp:include page="include/left.jsp"></jsp:include>
		
		<div class="main-content">
					
			<jsp:include page="include/header.jsp"></jsp:include>	
			
			<div class="row">
				<div class="col-sm-3">
					<div class="xe-widget xe-counter" data-count=".num" data-from="0" data-to="${userCount}" data-suffix="" data-duration="4">
						<div class="xe-icon">
							<i class="linecons-user"></i>
						</div>
						<div class="xe-label">
							<strong class="num">1k</strong>
							<span>用户数量</span>
						</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="xe-widget xe-counter xe-counter-purple" data-count=".num" data-from="0" data-to="${blogCount}" data-duration="4" data-easing="false">
						<div class="xe-icon">
							<i class="linecons-cloud"></i>
						</div>
						<div class="xe-label">
							<strong class="num">0</strong>
							<span>数量1</span>
						</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="xe-widget xe-counter xe-counter-info" data-count=".num" data-from="0" data-to="${picBlogCount}" data-duration="4" data-easing="true">
						<div class="xe-icon">
							<i class="linecons-desktop"></i>
						</div>
						<div class="xe-label">
							<strong class="num">0</strong>
							<span>数量2</span>
						</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="xe-widget xe-counter xe-counter-orange" data-count=".num" data-from="0" data-to="${handbookCount}" data-duration="4" data-easing="true">
						<div class="xe-icon">
							<i class="linecons-cup"></i>
						</div>
						<div class="xe-label">
							<strong class="num">0</strong>
							<span>数量3</span>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="panel-title">
							<i class="fa fa-check-square-o"></i> 待办事项
						</div>
					</div>
					<div class="panel-body">
						<div class="col-sm-12">
					
					<div class="xe-widget xe-todo-list xe-todo-list-turquoise">
						<div class="xe-header">
							<div class="xe-icon">
								<i class="fa-file-text-o"></i>
							</div>
							<div class="xe-label">
								<span>to do list</span>
								<strong>Tasks</strong>
							</div>
						</div>
						<div class="xe-body">
							
							<ul class="list-unstyled">
								<li class="">
									<label>
										<input type="checkbox" class="cbr" />
										<span>mina(IoSession)</span>
									</label>
								</li>
								<li class="">
									<label>
										<input type="checkbox" class="cbr" />
										<span>Java中间件，消息中间件（AvtiveMQ）</span>
									</label>
								</li>
								<li>
									<label>
										<input type="checkbox" class="cbr" />
										<span>多线程（Executor）深入学习 锁</span>
									</label>
								</li>
								<li>
									<label>
										<input type="checkbox" class="cbr" />
										<span>mysql各种功能及存储过程</span>
									</label>
								</li>
								<li class="">
									<label>
										<input type="checkbox" class="cbr" />
										<span>Spring深入学习</span>
									</label>
								</li>
								<li>
									<label>
										<input type="checkbox" class="cbr" />
										<span>分布式框架（dubbo）</span>
									</label>
								</li>
								<li class="">
									<label>
										<input type="checkbox" class="cbr" />
										<span>reactjs</span>
									</label>
								<li class="">
									<label>
										<input type="checkbox" class="cbr" />
										<span>http tcp udp</span>
									</label>
								</li>
								</li>
							</ul>
						</div>
						<div class="xe-footer">
							<input type="text" class="form-control" placeholder="Add task..." />
						</div>
					</div>
					
				</div>
					</div>
				</div>
			</div>
			
		</div>
		
	</div>
	
	
	<div class="page-loading-overlay">
		<div class="loader-2"></div>
	</div>
	

</body>
</html>