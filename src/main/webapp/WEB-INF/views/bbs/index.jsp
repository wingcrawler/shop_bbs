<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="include/meta.jsp"></jsp:include>
</head>
<body>
	<!-- header -->
	<jsp:include page="include/header.jsp"></jsp:include>
	<!-- //header -->
	<!-- 新增的圈子和精彩 -->
	<div class="tj_t_q">
		<div class="container">
			<!--圈子推荐-->
			<div id="tj_t" class="h2">
				<span class="ico ico_qztj txt">圈子推荐</span> <a href="liebiao.html"
					class="more">更多圈子&gt;</a> <br class="c" />
			</div>
			<ul id="tj_m">

				<c:if test="${not empty section.list }">
					<c:forEach var="item" items="${section.list }">
						<li><a href="sectionindex?sectionId=${item.id }"
							class="qb wb">${item.sectionTitle }</a></li>
					</c:forEach>
				</c:if>

			</ul>
			<!--END 圈子推荐-->
			<!--精彩推荐-->
			<div class="h2">
				<span class="ico ico_jctj txt">精彩推荐</span> <br class="c" />
			</div>
			<div id="jc_l">
				<div id="ajaxdata">
					<!--10条数据-->
					<ul class="list">
						<li v-for="item in items"><em v-if="item.thread_identify==2"
							class="t">置顶</em> <a v-bind:href='"thread?threadId="+item.id'
							class="tx">{{item.threadTitle}}</a><em
							v-if="item.thread_identify==1" class="jh">精</em> <br />
							<div class="tR">
								<a href="#" class="qq l">{{item.section_title}}</a> <span
									class="rp">{{item.thread_view}}</span> &nbsp;|&nbsp; <span
									class="tm">{{item.timeAgo}}</span>
							</div></li>
						<li v-if="loaded==false">
							<div>正在加载数据......</div>
						</li>
						<li v-if="loaded==true && items.length==0">
							<div colspan="3" class="text-center">暂无数据</div>
						</li>
					</ul>
				</div>
				<!--相关按钮-->
				<div class="info hyh">
					<a id="getchange" class="changeb wb">换一换</a>
				</div>
			</div>
			<!--END 精彩推荐-->
		</div>
	</div>
	<!-- 新增的圈子和精彩end -->
	<!-- footer -->
	<jsp:include page="include/footer.jsp"></jsp:include>

	<!-- //footer -->
	<script type="text/javascript">
		$(document).ready(function() {
			var playTableVue = new Vue({
				el : "#ajaxdata",
				data : {
					items : [],
					loaded : false
				}
			});
			$(function() {
				$.getJSON("threads", {
					playid : '${sectionId.sectionId}'
				}, function(json) {
					if (!json)
						json = [];
					playTableVue.items = json.list;
					playTableVue.loaded = true;
				});
			});
			
			
		});
		
		
	</script>
	<script>
		$("#getchange").click(function() {
			console.log('click change begin');
			var playTableVue2 = new Vue({
				el : '#ajaxdata',
				data : {
					items : [],
					loaded : false
				}
			});

			$(function() {
				$.getJSON("threads", {
					sectionId : '2'
				}, function(json) {
					if (!json)
						json = [];
					playTableVue2.items = json.list;
					playTableVue2.loaded = true;
				});

			})
			
		  console.log('click change end');
			$(function() {
				var section2;
				var list;
				$.getJSON("section/getSecondSection", {
					sectionId : '2'
				}, function(json) {
					if (!json)
						json = [];
					section2 = json.list;
					console.log('#'+section2);
				});
				for(var p in section2){
				    str = str+obj[p]+',';
				    console.log('#'+str);
				}

			}) 
		});
	</script>

</body>
</html>