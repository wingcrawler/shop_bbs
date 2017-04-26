<div class="row">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">${t.t_list }</h3>
			<div class="panel-options">
				<a href="#" data-toggle="reload" onclick="$.fn.reload()"><i class="fa-rotate-right"></i></a>
			</div>
		</div>
		<div class="panel-body">
			<table class="table table-bordered table-striped" id="datatable">
				<thead>
				<tr>
					<th width="60" field="index">${t.t_no }</th>
					<th field="username">${t.t_msg }</th>
					<th field="messageContext">${t.t_content }</th>
					<th field="statusName">${t.t_status }</th>
					<th field="createTimeStr">${t.t_createtime }</th>
					<th field="op" field-role="3,0" width="110"></th>
				</tr>
				</thead>
				<tbody class="middle-align"></tbody>
			</table>
			<div class="pagebar"></div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(function(){
	$('#main-menu li.li').removeClass('active').removeClass('opened');
	$('#main-menu li.li').eq(4).addClass('active').addClass('opened');
	$('#main-menu li.li').eq(4).find('ul li').eq(2).addClass('active');
	
	$('#datatable').datatable({
		url_load : '/backend/shop/getMsgList',
		url_remove : '/backend/shop/doDeleteMsg',
		backFn : function(p) {
			// console.log(p);
		}
	}); 
});

function viewDetail(obj){
	var id=$(obj).attr("opid")
	$.ajax({
		type: "POST",
		url: '/backend/message/read',
		dataType : "json",
		data: {id:id},
		success: function(data) {
			if (data.errorNo != 0) {
				$.commonUtil.showTip(data.errorInfo);
			} else {
				var messageCount = $("#messageCount").text();
				messageCount--;
				if(messageCount==0){
					$("#messageCount").hide();
				} else {
					$("#messageCount").text(messageCount);
				}
				$.commonUtil.showTip(data.errorInfo);
				$.fn.reload();
			}
		}
	});
}
</script>
