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
<!-- bootstarp-css -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<!--// bootstarp-css -->
<!-- css -->
<link rel="stylesheet" href="css/common.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/topic.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
<!--// css -->
<script src="js/jquery.min.js"></script>
<!--fonts-->
<!--/fonts-->
<!-- dropdown -->
<script src="js/jquery.easydropdown.js"></script>
<link href="css/nav.css" rel="stylesheet" type="text/css" media="all" />
<script src="js/scripts.js" type="text/javascript"></script>
<!--js-->
<!--/js-->
<script src="js/easyResponsiveTabs.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#horizontalTab').easyResponsiveTabs({
			type : 'default', //Types: default, vertical, accordion           
			width : 'auto', //auto or any width like 600px
			fit : true
		// 100% fit in a container
		});
	});
</script>
<!-- start-smoth-scrolling -->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event) {
			event.preventDefault();
			$('html,body').animate({
				scrollTop : $(this.hash).offset().top
			}, 1000);
		});
	});
</script>
<script src="/bbs/js/jquery.datatable.js"></script>
<script src="/backendstyle/js/common-util.js"></script>
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
				<a href="#" class="name">${user.username }</a> <span class="lv">LV6</span>
				<em class="isLz"></em>
				<div class="tmain">
					<p>
						<img src=${user.userImage } height="100" width="100" />
					</p>

					<h3>${thread.threadTitle }</h3>
					<p>${thread.threadContext }</p>
					<!--1楼的顶
					<div class="dwp">
						<a href="#" class="dbt bb"><span class="num">0</span>${t.t_bbs_up }</a>
					</div>-->
				</div>
				<a href="#bReply" class="rbt wb first">回复</a> <span class="msg">
					<fmt:formatDate value="${thread.threadTime }"
						pattern="yyyy-MM-dd HH:mm" />
				</span> <br class="c" />
			</div>
		</c:if>
		<div id="ajaxList">
			<!--楼层-->
			<li v-if="loaded==false">
				<div class="sk-circle">
							<div class="sk-circle1 sk-child"></div>
							<div class="sk-circle2 sk-child"></div>
							<div class="sk-circle3 sk-child"></div>
							<div class="sk-circle4 sk-child"></div>
							<div class="sk-circle5 sk-child"></div>
							<div class="sk-circle6 sk-child"></div>
							<div class="sk-circle7 sk-child"></div>
							<div class="sk-circle8 sk-child"></div>
							<div class="sk-circle9 sk-child"></div>
							<div class="sk-circle10 sk-child"></div>
							<div class="sk-circle11 sk-child"></div>
							<div class="sk-circle12 sk-child"></div>
						</div>
			</li>
			<li v-if="loaded==true && items.length==0">
				<div colspan="3" class="text-center">${t.t_bbs_DataNotFound }</div>
			</li>
			<!--楼层-->
			<div v-for="item in items" class="tItem cnt"
				quotedCommentId="45470767" id="">
				<a href="#" class="name">{{item.name}}</a> <span class="lv">LV6</span>
				<em class="isLz"></em>
				<div class="tmain">
					<p v-html="item.context"></p>
					<span class="msg">{{item.floor}}${t.t_bbs_floor }{{item.time}}</span>
					<!--楼中楼数据-->
					<ul v-if="item.replyList!=''" class="llist">
						<span v-for="item in item.replyList"> <br />
							<li>{{item.name}}:</li> <span class="msg">{{item.post_date|formatDate('dateTime')}}</span>
							<br />
							<p v-html="item.context"></p>
						</span>
					</ul>
				</div>
				<div class="rbt wb" @click="toggle(item)">${t.t_bbs_reploy }</div>

				<br class="c" />
				<!--楼中楼内容-->
				<div class="lzl" v-show="item.show">

					<div class="reply1">
						<form @submit.prevent="submit">
							<textarea class="editor" placeholder="......"
								v-model="post.postContext"></textarea>
							<input type="hidden" value="" v-model="post.postId"> <input
								type="hidden" v-bind:value="item.id" v-model="post.postId">
							<input type="submit" value="${t.b_submit }">
						</form>
					</div>

				</div>
				<!--END 楼中楼内容-->
			</div>
		</div>
	</div>
	</div>
	<!-- 回帖框 -->

	<c:if test="${isLogin }">
		<div v-if="${isLogin }==true" class="container"
			style="margin-top: 1em">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						<a><i class="fa-location-arrow"> ${t.t_bbs_reploy }</i></a>
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

		<!-- 内容区结束 -->
	</c:if>
	<c:if test="${!isLogin }">
		<div class="panel panel-default">
			<p class="navbar-text navbar-right">
				${t.sign_in } <a href="/user/login" class="navbar-link">${t.t_bbs_login_re }</a>
			</p>
		</div>
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
	<!-- footer -->
	<script type="text/javascript">
		var isLogin = ${isLogin	};
		var playTableVue = new Vue({
			el : "#ajaxList",
			data : {
				items : [],
				loaded : false,
				post : {
					threadId : '${thread.id }',
					postContext : '',
					postId : '',
				},

			},
			methods : {
				toggle : function(item) {
					let
					inner = this.post
					inner.postId = item.id

					if (isLogin) {
						item.show = !item.show;
					} else {
						alert("${t.t_bbs_login_re}");
					}
				},
				submit : function() {
					console.log(this.post)

					var formData = this.post; // 这里才是你的表单数
					$.fn.doSave(formData, '/bbs/post/doSave',
							'/bbs/thread?threadId=${thread.id }');
				}
			}

		});

		function responseHandle(json) {
			if (!json)
				json = [];
			json.list.map(function(item) {
				item.show = false;
			});
			playTableVue.items = json.list;
			playTableVue.loaded = true;
		}

		function demo(curr) {
			$.getJSON('/bbs/post/getList', {
				threadId : '${thread.id }',
				pageNo : curr || 1,
				pageSize : 10, //向服务端传的参数，此处只是演示
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
					prev : '<', //若不显示，设置false即可  
		            next: '>', //若不显示，设置false即可 
					pages : json.page.pageCount, //通过后台拿到的总页数
					jump : function(e) { //触发分页后的回调  
						$.getJSON('/bbs/post/getList', {
							threadId : '${thread.id }',
							pageNo : e.curr,//当前页  
							pageSize : 10,
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
		function formatNumber(n) {
			//n为整数
			n = n.toString();
			return n[1] ? n : '0' + n
		}

		//注册全局过滤器
		Vue
				.filter(
						"formatDate",
						function(value, formatType) {
							//value：时间毫秒值
							var date = new Date(value);
							var year = date.getFullYear(), month = formatNumber(date
									.getMonth() + 1), day = formatNumber(date
									.getDate());
							var dateStr = year + '-' + month + '-' + day;
							if (formatType == 'dateTime') {
								var hour = date.getHours(), minute = date
										.getMinutes(), second = date
										.getSeconds();
								dateStr += ' '
										+ [ hour, minute, second ].map(
												formatNumber).join(':');
							}
							return dateStr;
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

			$('.rbt').toggle(function(e) {
				$(this).parent(".tItem").find(".hid").css('display', 'block')
			}, function(e) {
				$(this).parent(".tItem").find(".hid").css('display', 'none')
			});
		});
	</script>
</body>
</html>