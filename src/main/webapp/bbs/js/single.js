function clickCancel(event) {
    $("#textArea").val("");
}

function clickSure(event) {
    var newValue = $("#textArea").val();
    var messageList = $("#messageList");
    var newLi = `
					<li class="list">
                        <div class="item">
                            <div class="headline">
                                <div class="head_text">
                                <div class="one">${newValue}</div>
                                <div class="single_reply">回复：您好，关于您提到的音质的问题，建议您享受音乐的时候，选择高品质的声源哦，这样视听效果会更加棒呢！如果您使用高品质音乐之后，觉得问题仍然存在的话，您都可以马上联系我们，我们会第一时间帮您处理，给您一个满意的答复！ （沫沫）</div>
                                </div>
                                <div class="head_portrait">
                                    <div class="img_head_portrait">
                                        <img src="images/head_portrait.jpg"/>
                                    </div>
                                    <div class="name_time">
                                        <p class="name">等一个孩子的归来</p>
                                        <p class="time">06:12</p>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </li>
                        `;
    $("#messageList").prepend(newLi);
    $("#textArea").val("");

}