<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="include/meta.jsp"></jsp:include>
<script src="/ue/ueditor.config.js"></script>
<script src="/ue/ueditor.all.js"></script>
<c:if test="${t.lang =='en' }">
	<script type="text/javascript" charset="utf-8" src="/ue/lang/en/en.js"></script>
</c:if>
<c:if test="${t.lang =='zh' }">
	<script type="text/javascript" charset="utf-8"
		src="/ue/lang/zh-cn/zh-cn.js"></script>
</c:if>
<link href="/ue/themes/default/css/ueditor.css" rel="stylesheet">

<!-- css -->
<link rel="stylesheet" href="bbs/css/common.css" type="text/css" media="all" />
<link rel="stylesheet" href="bbs/css/style.css" type="text/css" media="all" />
<!--// css -->
<script src="bbs/js/jquery.min.js"></script>
<!--fonts-->
<!--/fonts-->
<!-- dropdown -->
<script src="bbs/js/jquery.easydropdown.js"></script>
<link href="bbs/css/nav.css" rel="stylesheet" type="text/css" media="all"/>
<script src="bbs/js/scripts.js" type="text/javascript"></script>
</head>
<script src="/bbs/js/jquery.datatable.js"></script>
<script src="/backendstyle/js/common-util.js"></script>
<body>
	<!-- header -->
	<jsp:include page="include/header.jsp"></jsp:include>
	<!-- //header -->
	<!-- 新增的圈子和精彩 -->
	<div class="tj_t_q">
		<div class="container">

			<!--圈子信息-->
			<div id="qms">
				<c:if test="${not empty sectionindex }">
					<img src="images/xzQ.gif" class="ico" />
					<span class="m1">${sectionindex.sectionTitle }</span>
					<span class="m2">帖子总数${thread.list.size() }</span>
					<!--是圈圈公务圈,则不显示加入和签到按钮-->


					<a class="b1 collect" href="javascript:void(0);"
						data-url="http://m.100bt.com/JoinToCategory.action?ttqId=20"></a>
					<a href="javascript:void(0);"
						data-url="http://m.100bt.com/CheckIn.action?ttqId=20"
						class="b1 sign hid"></a>
				</c:if>
			</div>
			<!--话题筛选-->

			<div id="op">
				<!--展开的内容-->
				<div class="h2">
					<c:if test="${not empty section.list }">
						<c:forEach var="item" items="${section.list }">
							<a href="sectionindex?sectionId=${item.id }"
								class="tab">${item.sectionTitle }</a>
						</c:forEach>
						<br class="c" />
					</c:if>
					
				</div>
				<!--END 展开的内容-->
			</div>
			<!--数据列表,通过AJAX请求回来-->
			<div id="ajaxList">
				<ul class="list">
					<li v-for=" item in items"><em v-if="item.thread_identify==2"
						class="t">${t.t_bbs_top }</em> <a v-bind:href='"/bbs/thread?threadId="+item.id'
						class="tx">{{item.threadTitle}}</a><em
						v-if="item.thread_identify==1" class="jh">${t.t_bbs_recommendation }</em> <br />
						<div class="tR">
							<span class="qq l">{{item.thread_view}}</span> <a class="sd"
								href="#">{{item.username}}</a> &nbsp;&nbsp; <a href="#"></a>
							&nbsp;|&nbsp; <span class="tm">{{item.timeAgo}}</span>

						</div></li>
					<li v-if="loaded==false">
						<div>正在加载数据......</div>
					</li>
					<li v-if="loaded==true && items.length==0">
						<div colspan="3" class="text-center">暂无数据</div>
					</li>

				</ul>
			</div>

		</div>



		<!-- 内容区 -->
		<c:if test="${isLogin }">
			<div v-if="${isLogin }==true" class="container" style="margin-top: 1em;">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<a><i class="fa-location-arrow"> 发帖</i></a>
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-horizontal form" action="javascript:void(0);">
							<div class="form-group">
								<div class="col-sm-3">
									<input type="hidden" value="" name="id" />
									<p>${t.t_thread }<span style="color: #f00">*</span>
									</p>
									<input class="form-control" type="text" name="threadTitle"
										value="${entity.threadTitle}">
								</div>
								<div class="col-sm-3">
									${t.t_select }${t.t_lang } <select class="form-control select"
										id="threadType" name="threadType">
										<option value="1">${t.t_zh}</option>
										<option value="0">${t.t_en}</option>
									</select>
								</div>
								<div class="col-sm-3">
									${t.t_select } 板块 <select class="form-control select"
										id="threadType" name="sectionId">
										<c:if test="${not empty section.list }">
											<c:forEach var="item" items="${section.list }">
												<option value="${item.id }">${item.sectionTitle }</option>

											</c:forEach>
										</c:if>
										<c:if test="${empty section.list }">

											<option value="${sectionindex.id }">${sectionindex.sectionTitle }</option>

										</c:if>


									</select>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-12">
									<textarea id="myEditor" name="threadContext"> </textarea>
									<script type="text/javascript">
										var ue = UE
												.getEditor(
														'myEditor',
														{
															toolbars : [ [
																	'source',
																	'undo',
																	'redo',
																	'bold',
																	'pasteplain',
																	'removeformat',
																	'link',
																	'unlink',
																	'cleardoc',
																	'simpleupload',
																	'insertimage',
																	'imagecenter',
																	'justifyleft',
																	'justifyright',
																	'justifycenter',
																	'justifyjustify',
																	'insertrow',
																	'insertcol',
																	'mergeright',
																	'mergedown',
																	'deleterow',
																	'deletecol',
																	'splittorows',
																	'splittocols',
																	'splittocells',
																	'deletecaption',
																	'inserttitle',
																	'mergecells',
																	'deletetable',
																	'insertparagraphbeforetable',
																	'edittable',
																	'edittd',
																	'inserttable',
																	'autotypeset',
																	'customstyle',
																	'spechars',
																	'fontfamily',
																	'fontsize' ] ],
															initialFrameHeight : 340,
															initialFrameWidth : 1060,
															autoHeightEnabled : true,
															autoFloatEnabled : true
														});
									</script>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-12">
									<a id="submit" class="btn btn-info" href="javascript:void(0);">${t.b_submit }</a>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- 内容区结束 -->
		</c:if>
		<c:if test="${!isLogin }">
			<div>请登录后发帖</div>

		</c:if>
		<div id="page" style="text-align: center;"></div>
		<!--END 数据列表-->


		<script type="text/javascript">
			$(function() {
				$('#main-menu li.li').removeClass('active').removeClass(
						'opened');
				$('#main-menu li.li').eq(6).addClass('active').addClass(
						'opened');
				$('#main-menu li.li').eq(6).find('ul li').eq(0).addClass(
						'active');

				$('#submit')
						.click(
								function() {
									var parm = $.fn.getFormJson('.form');
									$.fn
											.doSave(parm, '/bbs/thread/doSave',
													'/bbs/sectionindex?sectionId=${sectionindex.id }');
								});
			});
		</script>





		<!-- 新增的圈子和精彩end -->
		<!-- footer --><jsp:include page="include/footer.jsp"></jsp:include>
		<!-- //footer -->

		<script type="text/javascript">
		var playTableVue = new Vue({
			el : "#ajaxList",
			data : {
				items : [],
				loaded : false
			}
		});

		function  responseHandle(json){		
			if (!json)
				json = [];
			playTableVue.items = json.list;
			playTableVue.loaded = true;
		}
		
		function demo(curr) {
			$.getJSON('thread/getSectionList', {
				sectionId : '${sectionindex.id }',
				pageNo : curr || 1,
				pageSize : 10, //向服务端传的参数
			}, function(json) {
				//显示分页
				laypage({
					cont : 'page', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>  
					curr : 1, //初始化当前页  
					skin : '#429842',//皮肤颜色  
					groups : 5, //连续显示分页数  
					skip : true, //是否开启跳页  
					first : '首页', //若不显示，设置false即可  
					last : '尾页', //若不显示，设置false即可  
					prev: '<', //若不显示，设置false即可  
		            next: '>', //若不显示，设置false即可 
					pages : json.page.pageCount, //通过后台拿到的总页数
					jump : function(e) { //触发分页后的回调  
						$.getJSON('thread/getSectionList', {
							sectionId : '${sectionindex.id }',
							pageNo : e.curr,//当前页  
							pageSize : 10,
						}, function(json) {
							e.pages = e.last = json.page.pageCount; //重新获取总页数，一般不用写  
							console.log(e.pages);
							//渲染  
							responseHandle(json);
							//var view = document.getElementById('page1'); //你也可以直接使用jquery  
							//解析数据  
							//var resultHtml = PackagData(json);
							//view.innerHTML = resultHtml;
						});
					}
				});
			});
		};
			$(document).ready(function() {
				$(function() {
					$.getJSON("thread/getSectionList", {
						sectionId : '${sectionindex.id }',
						pageNo : '1',
					}, function(json) {
						if (!json)
							json = [];
						playTableVue.items = json.list;
						playTableVue.loaded = true;
					});
				});
				demo();
			});
		</script>
</body>
</html>