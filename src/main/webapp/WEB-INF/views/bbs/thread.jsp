<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="include/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${t.lang} }">
<head>
<link rel="stylesheet" href="//res.layui.com/layui/src/css/layui.css" media="all">
<link rel="stylesheet" href="//res.layui.com/css/global.css" media="all">

<link href="demo.css" type="text/css" rel="stylesheet">

<script src="//res.layui.com/lay/lib/laypage/laypage.js?v=1.3" tips="使用时，只要这一个js即可"></script>

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
<title>${t.m_bbs_list }</title>
</head>
<body>
	<!-- header -->
	<jsp:include page="include/header.jsp"></jsp:include>
	<!-- //header -->
	<!-- 新增的樓層-->
	<div class="container">
		<section class="m" id="content"> <!--楼层--> <!--楼层--> <c:if
			test="${not empty user }">
			<div class="tItem cnt" quotedCommentId="45470309" id="45470309">
				<a href="#" class="name">${user.username }</a> <em class="isLz"></em>
				<div class="tmain">
					<p>
						<img src="${user.userImage }" />
					</p>
		</c:if>

		<p>${thread.threadTitle }</p>

		<p>${thread.threadContext }</p>
		<!--1楼的顶-->
		<div class="dwp">
			<a href="#ajax#" class="dbt bb"><span class="num">0</span>${t.t_bbs_up }</a>
		</div>

		<span class="msg">1楼<fmt:formatDate
				value="${thread.threadTime }" pattern="yyyy-MM-dd HH:mm" />
			</p></span> <br class="c" />
		<div id="ajaxList">
			<!--楼层-->t
			<div v-for="item in items" class="tItem cnt"
				quotedCommentId="45470737" id="45470737">
				<a href="#" class="name">{{item.name}}</a> <em class="isLz"></em>

				<div class="tmain">
					<p v-html="item.context"></p>
				</div>
				<!-- <a href="javascript:void(0);" class="rbt wb" notClick="true">回复(0)</a> -->
				<span class="msg">{{item.floor}}${t.t_bbs_floor }{{item.time}}</span> <br class="c" />
				<!--楼中楼内容-->
				<div class="lzl hid">
					<em class="ico"></em>
					<!--刷新和收起-->
					<div class="op">
						<a href="javascript:;" class="cl">收起回复</a> <a
							href="javascript:void(0);"
							data-url="http://m.100bt.com/zn/LoadReply.html?topicId=10899981&commentId=45470737"
							class="rf">刷新</a> <br class="c" />
					</div>
					<!--楼中楼数据-->
					<ul class="llist">
						<li>唐伯虎回顧秋香：</li>
						<li>這是什麽a唐伯虎回顧秋香這是什麽a唐伯虎回顧秋香這是什麽a唐伯虎回顧秋香這是什麽a唐伯虎回顧秋香這是什麽a唐伯虎回顧秋香這是什麽a唐伯虎回顧秋香</li>
					</ul>
				</div>
				<!--END 楼中楼内容-->

			</div>
			<!--楼层-->
			<li v-if="loaded==false">
				<div>正在加载数据......</div>
			</li>
			<li v-if="loaded==true && items.length==0">
				<div colspan="3" class="text-center">暂无数据</div>
			</li>

			<!--楼层-->
			<div class="tItem cnt" quotedCommentId="45470923" id="45470923">


				<a href="../Home/755355.html" class="name">95912180</a> <span
					class="lv">LV6</span> <em class="isLz"></em>

				<div class="tmain">
					<p></p>
					<p
						style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">【12星座最大的缺点是什么?】</p>
					
					<p
						style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">~(≥◇≤)~</p>

				</div>
				<!--  <a href="javascript:void(0);" class="rbt wb" notClick="true">回复(0)</a>-->

				<span class="msg">5楼2013-12-08 15:35</span> <br class="c" />

				<!--楼中楼内容  隐藏掉-->
				<!--  <div class="lzl hid">
			<em class="ico"></em>-->
				<!--刷新和收起-->
				<!-- <div class="op">
			<a href="javascript:;" class="cl">收起回复</a> <a
				href="javascript:void(0);"
				data-url="http://m.100bt.com/zn/LoadReply.html?topicId=10899981&commentId=45470923"
				class="rf">刷新</a> <br class="c" />
		</div>-->
				<!--楼中楼数据-->
				<!--<ul class="llist">
			<li>唐伯虎回顧秋香：</li>
			<li>這是什麽a唐伯虎回顧秋香這是什麽a唐伯虎回顧秋香這是什麽a唐伯虎回顧秋香這是什麽a唐伯虎回顧秋香這是什麽a唐伯虎回顧秋香這是什麽a唐伯虎回顧秋香</li>
		</ul>
	</div>
	-->
				<!--END 楼中楼内容-->

				<!--</div> -->
			</div>


		</div>
	</div>
	<!-- 回帖框 -->

	<c:if test="${isLogin }">
		<div v-if="${isLogin }==true" class="row">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						<a><i class="fa-location-arrow"> 跟帖</i></a>
					</h3>
				</div>
				<form class="form-horizontal form" action="javascript:void(0);">
					<div class="panel-body">
						<div class="form-group">
							<div class="col-sm-12">
								<input type="hidden" value="${thread.id }" name="threadId" /> <input
									type="hidden" value="${user.id }" name="replyToUserId" />
								<textarea id="myEditor" name="postContext"> </textarea>
								<script type="text/javascript">
									var ue = UE.getEditor('myEditor', {
										toolbars : [ [ 'source', 'undo',
												'redo', 'bold', 'pasteplain',
												'removeformat', 'link',
												'unlink', 'cleardoc',
												'justifyleft', 'justifyright',
												'justifycenter',
												'justifyjustify', 'insertrow',
												'insertcol', 'mergeright',
												'mergedown', 'deleterow',
												'deletecol', 'splittorows',
												'splittocols', 'splittocells',
												'deletecaption', 'inserttitle',
												'mergecells', 'deletetable',
												'insertparagraphbeforetable',
												'edittable', 'edittd',
												'inserttable', 'autotypeset',
												'customstyle', 'spechars',
												'fontfamily', 'fontsize' ] ],
										initialFrameHeight : 340,
										initialFrameWidth : 1100,
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
${t.t_bbs_login_re }

</c:if>
	<br>






	<!--跟帖分页-->
	<div id="page3" style="text-align: center;"></div>
	</section>
	</div>
	</div>
	<!-- 新增的圈子和精彩end -->
	<!-- footer -->
	<jsp:include page="include/footer.jsp"></jsp:include>
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
			$.getJSON('/bbs/post/getList', {
				threadId : '${thread.id }',
				pageNo : curr || 1,
				pageSize : 5, //向服务端传的参数，此处只是演示
			}, function(json) {
				//显示分页
				laypage({
					cont : 'page3', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>  
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
						$.getJSON('/bbs/post/getList', {
							threadId : '${thread.id }',
							pageNo : e.curr,//当前页  
							pageSize : 5,
						}, function(json) {
							e.pages = e.last = json.page.pageCount; //重新获取总页数，一般不用写  
							console.log(e.pages)
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
				$.getJSON("/bbs/post/getList", {
					threadId : '${thread.id }'
				}, function(json) {
					if (!json)
						json = [];
					//playTableVue.items = json.list;
					//playTableVue.loaded = true;
				});
			});
			demo();
		});
	</script>
	<script type="text/javascript">
		$(function() {
			$('#main-menu li.li').removeClass('active').removeClass('opened');
			$('#main-menu li.li').eq(6).addClass('active').addClass('opened');
			$('#main-menu li.li').eq(6).find('ul li').eq(0).addClass('active');

			$('#submit').click(
					function() {
						var parm = $.fn.getFormJson('.form');
						$.fn.doSave(parm, '/bbs/post/doSave',
								'/bbs/thread?threadId=${thread.id }');
					});
		});
	</script>
</body>
</html>