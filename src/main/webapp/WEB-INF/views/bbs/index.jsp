<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="${t.lang}">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="include/common.jsp"%>
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
				<span class="ico ico_qztj txt">${t.t_bbs_circle }</span> <a
					href="#" class="more">${t.t_bbs_more_circle }&gt;</a> <br
					class="c" />
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
				<span class="ico ico_jctj txt">${t.t_bbs_Wonderful_recommendation }</span> <br class="c" />
			</div>
			<div id="jc_l">
				<div id="ajaxdata">
					<!--10条数据-->
					<ul class="list">
						<li v-for="item in items"><em v-if="item.thread_identify==2"
							class="t">${t.t_bbs_top }</em> <a
							v-bind:href='"/bbs/thread?threadId="+item.id' class="tx">{{item.threadTitle}}</a><em
							v-if="item.thread_identify==1" class="jh">${t.t_bbs_recommendation }</em> <br />
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
					<a button v-on:click="change" id="getchange" class="changeb wb">${t.t_bbs_change }</a>
				</div>
			</div>
			<!--END 精彩推荐-->
		</div>
	</div>
	<!-- 新增的圈子和精彩end -->
	<!-- footer -->
	<c:if test="${t.lang =='en' }">
		<jsp:include page="include/footer.jsp"></jsp:include>
	</c:if>
	<c:if test="${t.lang =='zh' }">
		<jsp:include page="include/zhfooter.jsp"></jsp:include>
	</c:if>
	<!-- //footer -->
	<script type="text/javascript">
		var playTableVue = new Vue({
			el : "#ajaxdata",
			data : {
				items : [],
				loaded : false
			}
		});
		var Totla = 0;
		var section = new Array();
		$(document).ready(function() {
			$(function() {
				$.getJSON("/bbs/threads", {
					playid : '${sectionId.sectionId}'
				}, function(json) {
					if (!json)
						json = [];
					playTableVue.items = json.list;
					playTableVue.loaded = true;
				});
			});

			$.ajax({
				url : '/bbs/section/getSecondSection',
				type : 'get',
				dataType : 'json',
				success : function(result) {
					if (result.page.totalRecords > 0) {
						Totla = result.page.totalRecords;
						for (var i = 0; i < result.list.length; i++) {
							section.push(result.list[i].id)
						}
					}
				},
				error : function() {
					alert(register_failed);
				}

			});
			var num = 1;
			var sectionId;
			$("#getchange").click(function() {
				if (num < Totla) {
					sectionId = section[num];
					num++;
				} else {
					num = 1
				}

				$(function() {
					$.getJSON("/bbs/threads", {
						sectionId : sectionId
					}, function(json) {
						if (!json)
							json = [];
						playTableVue.items = json.list;
						playTableVue.loaded = true;
					});
				})
			})

		});
	</script>
	<script>
		
	</script>

</body>
</html>
