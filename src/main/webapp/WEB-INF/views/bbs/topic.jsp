<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

<jsp:include page="include/meta.jsp"></jsp:include>


</head>
<body class="page-body">

	<table class="table table-bordered table-striped table-condensed"
		id="recentPlayTable">
		<tr>
			<th>名称</th>
			<th>时间</th>
			<th>人数</th>
			<th>人数</th>
			<th>人数</th>
		</tr>
		<tr v-for="item in items">
			<td>{{item.date}}</td>
			<td>{{item.threadTitle}}</td>
			<td>{{item.createTimeStr}}</td>
			<td>{{item.username}}</td>
		</tr>
		<tr v-if="loaded==false">
			<td colspan="3" class="text-center">正在加载数据......</td>
		</tr>
		<tr v-if="loaded==true && items.length==0">
			<td colspan="3" class="text-center">暂无数据</td>
		</tr>
	</table>
	<!-- 分页测试 -->
	<div id="page1" style="margin-top: 5px; text-align: center;"></div>
	<ul id="biuuu_list"></ul>
	<ul id="biuuu_city_list"></ul>
	<div id="biuuu_city"></div>
	<script type="text/javascript">
		var playTableVue = new Vue({
			el : "#recentPlayTable",
			data : {
				items : [],
				loaded : false
			}
		});
		$(function() {
			$.getJSON("test", {
				playid : '${play.playid}'
			}, function(json) {
				if (!json)
					json = [];
				playTableVue.items = json.list;
				playTableVue.loaded = true;
			});
		});
	</script>

	<script type="text/javascript">
		//以下将以jquery.ajax为例，演示一个异步分页
		function demo(curr) {
			$.getJSON('test', {
				playid : '${play.playid}'
			}, function(json) {
				if (!json)
					json = [];
				//显示分页
				laypage({
					cont : 'page1',
					pages : json.page.totalRecords, //可以叫服务端把总页数放在某一个隐藏域，再获取。假设我们获取到的是18
					curr : function() { //通过url获取当前页，也可以同上（pages）方式获取
						var page = location.search.match(/pageNo=(\d+)/);
						return page ? page[1] : 1;
					}(),
					jump : function(e, first) { //触发分页后的回调
						if (!first) { //一定要加此判断，否则初始时会无限刷新
							location.href = '?pageNo=' + e.curr;
						}
					}
				});
			});
		};
		//运行
		demo();
	</script>

</body>
</html>