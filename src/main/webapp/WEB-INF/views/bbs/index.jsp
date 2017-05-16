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
						<c:if test="${not empty thread.list }">
							<c:forEach var="item" items="${thread.list }">
								<li><a href="thread?threadId=${item.id }" class="tx">${item.threadTitle }</a>
									<c:if test="${not empty item.identify }">
										<em class="jh">${item.identify }</em>
									</c:if> <br />
									<div class="tR">
										<a href="sectionindex?sectionId=${item.section_id}"
											class="qq l">${item.section_title }</a> <span class="rp">${item.thread_view }</span>
										&nbsp;|&nbsp; <span class="tm">${item.timeAgo }</span>
									</div></li>
							</c:forEach>
						</c:if>
						<li><a href="zhengwen.html" class="tx">【造句活动】若你喜欢怪人，其实我很美。</a>



							<br />
							<div class="tR">
								<a href="liebiao.html" class="qq l">小说圈</a> <span class="rp">235</span>
								&nbsp;|&nbsp; <span class="tm">1小时前</span>
							</div></li>
						<li><a href="zhengwen.html" class="tx">【香槟】当你准备放弃的时候，看看这个吧</a>



							<br />
							<div class="tR">
								<a href="liebiao.html" class="qq l">田田圈</a> <span class="rp">102</span>
								&nbsp;|&nbsp; <span class="tm">37分钟前</span>
							</div></li>
						<li><a href="zhengwen.html" class="tx">【忧桑】如果你回到家发现窝睡在你家床上，你会说什么</a>



							<br />
							<div class="tR">
								<a href="liebiao.html" class="qq l">奥雅之光粉丝圈</a> <span class="rp">667</span>
								&nbsp;|&nbsp; <span class="tm">5分钟前</span>
							</div></li>
						<li><a href="zhengwen.html" class="tx">【彤彤】明星们的撞脸</a> <br />
							<div class="tR">
								<a href="zn/TTQInfo/23_0_0_0.html" class="qq l">明星圈</a> <span
									class="rp">55</span> &nbsp;|&nbsp; <span class="tm">1小时前</span>
							</div></li>
						<li><a href="zhengwen.html" class="tx">韩国SM旗下艺人的悲惨内幕 【2】</a>



							<br />
							<div class="tR">
								<a href="liebiao.html" class="qq l">明星圈</a> <span class="rp">42</span>
								&nbsp;|&nbsp; <span class="tm">7小时前</span>
							</div></li>
						<li><a href="zhengwen.html" class="tx">【男神】致吾辈最爱的男神们？有你的他么！</a>



							<br />
							<div class="tR">
								<a href="liebiao.html" class="qq l">动漫圈</a> <span class="rp">286</span>
								&nbsp;|&nbsp; <span class="tm">13分钟前</span>
							</div></li>
						<li><a href="zhengwen.html" class="tx">你的腹黑指度有多少</a> <em
							class="jh">精</em> <br />
							<div class="tR">
								<a href="liebiao.html" class="qq l">星座圈</a> <span class="rp">324</span>
								&nbsp;|&nbsp; <span class="tm">28分钟前</span>
							</div></li>
					</ul>
				</div>
				<!--相关按钮-->
				<div class="info hyh">
					<a href="javascript:void(0);"
						data-url="http://m.100bt.com/zn/LoadIndexTopics.html?limit=10"
						class="changeb wb">换一换</a>
				</div>
			</div>
			<!--END 精彩推荐-->
		</div>
	</div>
	<!-- 新增的圈子和精彩end -->
	<!-- footer -->
	<jsp:include page="include/footer.jsp"></jsp:include>

	<!-- //footer -->
</body>
</html>