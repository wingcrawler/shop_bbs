<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="include/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${t.lang} }">
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
			<a href="#ajax#" class="dbt bb"><span class="num">0</span>顶</a>
		</div>

		<span class="msg">1楼<fmt:formatDate
				value="${thread.threadTime }" pattern="yyyy-MM-dd HH:mm" />
			</p></span> <br class="c" />
		<div id="ajaxList">
			<!--楼层-->
			<div v-for="item in items" class="tItem cnt"
				quotedCommentId="45470737" id="45470737">
				<a href="#" class="name">{{item.name}}</a> <em class="isLz"></em>

				<div class="tmain">
					<p v-html="item.context"></p>
				</div>
				<!-- <a href="javascript:void(0);" class="rbt wb" notClick="true">回复(0)</a> -->
				<span class="msg">{{item.floor}}楼{{item.time}}</span> <br class="c" />
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
						style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">白羊座-心太软</p>
					<p
						style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">金牛座-不太现实</p>
					<p
						style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">双子座-不够坚强</p>
					<p
						style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">巨蟹座-脾气不太好</p>
					<p
						style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">狮子座-容易相信他</p>
					<p
						style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">处女座-太依赖别人</p>
					<p
						style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">天秤座-心肠太好</p>
					<p
						style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">天蝎座-太追求完美</p>
					<p
						style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">射手座-有点任性</p>
					<p
						style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">摩羯座-性格刚烈</p>
					<p
						style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">水瓶座-对自己不够好</p>
					<p
						style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;">双鱼座-太过于追求时尚</p>
					<p
						style="font-family: 宋体, tahoma, helvetica, arial, sans-serif; line-height: 21px; background-color: #ffffff;"></p>
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
请登录

</c:if>
	<br>



	<div id="page1" style="text-align: center;">
	</div>


	<!--分页-->
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

		function demo(curr){
			  $.getJSON('/bbs/post/getList', {
				threadId : '${thread.id }',
				pageNo: curr || 1 ,
				pageSize : 2, //向服务端传的参数，此处只是演示
			  }, function(json) {
					if (!json)
						json = [];
					playTableVue.items = json.list;
					playTableVue.loaded = true;
			    //显示分页
			    laypage({
			      cont: 'page1', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<code><</code>div id="page1"><code><</code>/div>
			      pages: json.pageCount, //通过后台拿到的总页数
			      curr: curr || json.currentPage, //当前页
			      jump: function(obj, first){ //触发分页后的回调
			        if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
			          demo(obj.curr);
			        }
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