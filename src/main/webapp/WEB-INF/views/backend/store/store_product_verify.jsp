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
					<th field="productName">${t.t_product_name }</th>
					<th field="shopName">${t.t_shop_name }</th>
					<th field="productTypeName">${t.t_product_type }</th>
					<th field="productPrice">${t.t_price }</th>
					<th field="productCount">${t.t_product_count }</th>
					<th field="productStatusStr">${t.t_product_status }</th>
					<th field="productView">${t.t_product_view }</th>
					<th field="productRank">${t.t_weight }</th>
					<th field="op" field-role="2,0" width="110"></th>
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
	$('#main-menu li.li').eq(4).find('ul li').eq(1).addClass('active');
	
	var dt = $('#datatable').datatable({
		url_load : '/backend/product/getList?shopId=${shopId}',
		url_edit : '/backend/product/edit',
		url_remove : '/backend/product/doDelete',
		backFn : function(p) {
			// console.log(p);
		}
	}); 
	
	var shopId = '${shopId}';
	if(shopId!=''){
		$('#datatable').datatable({
			url_load : '/backend/product/getList?shopId=${shopId}',
			url_edit : '/backend/product/edit',
			url_remove : '/backend/product/doDelete',
			backFn : function(p) {
				// console.log(p);
			}
		}); 
		$.fn.removeUrlLoadArgs();
	}else {
		$('#datatable').datatable({
			url_load : '/backend/product/getList',
			url_edit : '/backend/product/edit',
			url_remove : '/backend/product/doDelete',
			backFn : function(p) {
				// console.log(p);
			}
		}); 
	}
});
</script>
