<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="include/meta.jsp"></jsp:include>
<script src="/ue/ueditor.config.js"></script>
<script src="/ue/ueditor.all.js"></script>
<c:if test="${lang =='en' }">
	<script type="text/javascript" charset="utf-8" src="/ue/lang/en/en.js"></script>
</c:if>
<c:if test="${lang =='zh' }">
	<script type="text/javascript" charset="utf-8"
		src="/ue/lang/zh-cn/zh-cn.js"></script>
</c:if>
<link href="/ue/themes/default/css/ueditor.css" rel="stylesheet">
</head>
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
							<li><a href="sectionindex?sectionId=${item.id }"
								class="qb wb">${item.sectionTitle }</a></li>
						</c:forEach>
					</c:if>
					<br class="c" />
				</div>
				<!--END 展开的内容-->
			</div>
			<!--数据列表,通过AJAX请求回来-->
			<div id="ajaxList">
			<ul class="list">
				<li v-for=" item in items"> <em v-if="item.thread_identify==2"
					class="t">置顶</em> <a v-bind:href='"${contextPath}/bbs/thread?threadId="+item.id'
					class="tx">{{item.threadTitle}}</a><em
					v-if="item.thread_identify==1" class="jh">精</em> <br />
					<div class="tR">
					<span class="qq l">{{item.thread_view}}</span> 
						<a class="sd"
							href="../Home/1808520.html">{{item.username}}</a> &nbsp;&nbsp;


							<a href="../Home/1686708.html"></a> &nbsp;|&nbsp; <span
							class="tm">{{item.timeAgo}}</span>
							
					</div></li>
				<li v-if="loaded==false">
					<div>正在加载数据......</div>
				</li>
				<li v-if="loaded==true && items.length==0">
					<div colspan="3" class="text-center">暂无数据</div>
				</li>

				<li><em class="t">置顶</em> <a href="zhengwen.html" class="tx">[水泽]新人孩子们不要顾着排名,和我一起了解下家史可好</a>



					<br />
					<div class="tR">
						<span class="qq l">406</span> <a class="sd"
							href="../Home/1808520.html">水泽</a> &nbsp;|&nbsp; <span class="tm">


							<a href="../Home/1686708.html">高端大气上档次</a> &nbsp;&nbsp;1小时前
						</span>
					</div></li>

				<li><em class="t">置顶</em> <a href="zhengwen.html" class="tx">【圈务】12月申精&amp;举报贴</a>



					<br />
					<div class="tR">
						<span class="qq l">25</span> <a class="sd"
							href="../Home/1806745.html">小编燕子</a> &nbsp;|&nbsp; <span
							class="tm"> <a href="../Home/2372933.html">smile、夏弥&deg;</a>
							&nbsp;&nbsp;2小时前
						</span>
					</div></li>

				<li><a href="zhengwen.html" class="tx">十二星座那些事.......</a> <br />
					<div class="tR">
						<span class="qq l">4</span> <a class="sd"
							href="../Home/755355.html">95912180</a> &nbsp;|&nbsp; <span
							class="tm"> <a href="../Home/755355.html">95912180</a>
							&nbsp;&nbsp;刚刚
						</span>
					</div></li>

				<li><a href="zhengwen.html" class="tx">来测测你的动漫形象~~0 0</a> <br />
					<div class="tR">
						<span class="qq l">14</span> <a class="sd"
							href="../Home/29429949.html">迷路、</a> &nbsp;|&nbsp; <span
							class="tm"> <a href="../Home/29429949.html">迷路、</a>
							&nbsp;&nbsp;刚刚
						</span>
					</div></li>

				<li><a href="zhengwen.html" class="tx">【莫子】二次元</a> <br />
					<div class="tR">
						<span class="qq l">24</span> <a class="sd"
							href="../Home/255222.html">嗲妹</a> &nbsp;|&nbsp; <span class="tm">


							<a href="../Home/1692542.html">☪我是阿起</a> &nbsp;&nbsp;刚刚
						</span>
					</div></li>

				<li><a href="zhengwen.html" class="tx">趣味测试：手指测试你心中最要的人？</a> <br />
					<div class="tR">
						<span class="qq l">83</span> <a class="sd"
							href="../Home/3040110.html">小编町子</a> &nbsp;|&nbsp; <span
							class="tm"> <a href="../Home/8294252.html">229132183</a>
							&nbsp;&nbsp;刚刚
						</span>
					</div></li>

				<li><a href="zhengwen.html" class="tx">【月弦】你认为你的星座是最顶尖的吗？一戳见分晓。</a>


					<em class="jh">精</em> <br />
					<div class="tR">
						<span class="qq l">481</span> <a class="sd"
							href="../Home/1682135.html">Baek Hyun</a> &nbsp;|&nbsp; <span
							class="tm"> <a href="../Home/2377431.html">初々『柔』</a>
							&nbsp;&nbsp;5分钟前
						</span>
					</div></li>

				<li><a href="zhengwen.html" class="tx">十二星座专属的物品</a> <br />
					<div class="tR">
						<span class="qq l">298</span> <a class="sd"
							href="../Home/14320118.html">习惯社、冰蓝</a> &nbsp;|&nbsp; <span
							class="tm"> <a href="../Home/6168949.html">113227002</a>
							&nbsp;&nbsp;6分钟前
						</span>
					</div></li>

			</ul>
</div>

		</div>



		<!-- 内容区 -->
		<c:if test="${isLogin }">
			<div v-if="${isLogin }==true" class="row">
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
									${t.t_select }${t.t_lang }
									 <select class="form-control select"
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
															initialFrameWidth : 1150,
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
请登录

</c:if>
		<br>
		<div class="page-toolbar">
			<div class="ajax-page">
				<ul class="page-pagination">
					<li class="first-page"><span>首页</span></li>
					<li class="previous-page"><span>上一页</span></li>
					<li class="active"><span>1</span></li>
					<li><span>2</span></li>
					<li><span>3</span></li>
					<li><span>4</span></li>
					<li><span>5</span></li>
					<li><span>6</span></li>
					<li><span>7</span></li>
					<li><span>8</span></li>
					<li><span>9</span></li>
					<li><span>10</span></li>
					<li class="omit"><span><i>...</i>15</span></li>
					<li class="skip"><input type="text" value="1">/ <span>15</span>页
					</li>
					<li class="next-page"><span>下一页</span></li>
				</ul>
			</div>
		</div>
		<!--END 数据列表-->


		<script type="text/javascript">
			$(function() {
				$('#main-menu li.li').removeClass('active').removeClass(
						'opened');
				$('#main-menu li.li').eq(6).addClass('active').addClass(
						'opened');
				$('#main-menu li.li').eq(6).find('ul li').eq(0).addClass(
						'active');						

				$('#submit').click(
						function() {
							var parm = $.fn.getFormJson('.form');
							$.fn.doSave(parm, '/bbs/thread/doSave',
									'/bbs/sectionindex?sectionId=${sectionindex.id }');
						});
			});
		</script>





		<!-- 新增的圈子和精彩end -->
		<!-- footer --><jsp:include page="include/footer.jsp"></jsp:include>
		<!-- //footer -->
		
		<script type="text/javascript">
			$(document).ready(function() {
				var playTableVue = new Vue({
					el : "#ajaxList",
					data : {
						items : [],
						loaded : false
					}
				});
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

			});
		</script>
</body>
</html>