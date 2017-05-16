//点击回复按钮
//注:这里代码没做封装,等到真实逻辑时再看情况封装
function clickLeaveMessage(event) {
	//查看点击的是第几个cell
	var cellNum = event.id.split("_")[2];

	//tab标识
	var tabSign = event.id.split("_")[1];

	//设置该cell对应的输入框为显示
	$("#answerArea_"+tabSign+"_"+cellNum).css("display", "block");

	//设置该cell对应的回复内容是否显示(这里通过网络查看回复内容是否有,如果有就显示,暂定一直有)
	if(1) {
		$("#answerHistory_"+tabSign+"_"+cellNum).css("display", "block");

		//设置显示更多文字是否显示,同样这里设置显示(如果已经显示全部数据那么不应该显示)
		if(1) {
			$("#loadMore_"+tabSign+"_"+cellNum).css("display", "block");
		}
	}
}

//点击取消按钮
function clickCancel(event) {
	//查看点击的是第几个cell
	var cellNum = event.id.split("_")[2];

	//tab标识
	var tabSign = event.id.split("_")[1];

	//设置该cell对应的输入框为影藏
	$("#answerArea_"+tabSign+"_"+cellNum).css("display", "none");

	//设置该cell对应的回复内容为影藏
	$("#answerHistory_"+tabSign+"_"+cellNum).css("display", "none");

	//设置显示更多文字是否显示,同样这里设置显示(如果已经显示全部数据那么不应该显示)
	$("#loadMore_"+tabSign+"_"+cellNum).css("display", "none");

	//清空textArea上面的内容
	var answerValue = $("#textArea_"+tabSign+"_"+cellNum).val("");
}

//点击确定按钮
function clickSure(event) {
	//查看点击的是第几个cell
	var cellNum = event.id.split("_")[2];

	//tab标识
	var tabSign = event.id.split("_")[1];

	//为回复历史里面加上自己回复的内容
	var answerValue = $("#textArea_"+tabSign+"_"+cellNum).val();

	//想历史里面加上自己回复的,这里除了回复的内容,其他的都是假的
	var answerHistoryList = $("#answerHistoryList_"+tabSign+"_"+cellNum);
	var newLi = `
					<li>
						<p class="quietly">
							<span class="red">我</span>
							<span>悄悄回复 </span>
							<span class="red">等一个孩子的归来：</span>
							<span>${answerValue}</span>
						</p>
						<p class="reply_box">
							<span>2016-06-27 17:55</span>
							<span class="reply">回复</span>
						</p>
					</li>
                        `;
	$("#answerHistoryList_"+tabSign+"_"+cellNum).append(newLi);

	//清空textArea上面的内容
	var answerValue = $("#textArea_"+tabSign+"_"+cellNum).val("");
}

//点击加载更多
function clickLoadMore(event) {
	//查看点击的是第几个cell
	var cellNum = event.id.split("_")[2];

	//tab标识
	var tabSign = event.id.split("_")[1];

	var newLi = `
					<li>
						<p class="quietly">
							<span class="red">我</span>
							<span>悄悄回复 </span>
							<span class="red">等一个孩子的归来：</span>
							<span>这是点击展开更多添加的数据</span>
						</p>
						<p class="reply_box">
							<span>2016-06-27 17:55</span>
							<span class="reply">回复</span>
						</p>
					</li>
                        `;
	$("#answerHistoryList_"+tabSign+"_"+cellNum).append(newLi);

	//设置显示更多文字是否显示,同样这里设置显示(如果已经显示全部数据那么不应该显示)
	$("#loadMore_"+tabSign+"_"+cellNum).css("display", "none");
}