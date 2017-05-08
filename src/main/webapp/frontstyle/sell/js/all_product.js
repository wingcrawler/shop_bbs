$(function(){
	$(".pagebar").createPage({
		pageCount : '${productPage.pageCount}',
		current : '${productPage.currentPage}',
		fnName : 'nextPage',
		backFn : function(p) {
			// console.log(p);
		}
	});	
});

/**
 * 翻页
 */
function nextPage(pageNo){
	var parm = "";
	parm = parm + "pageNo="+pagerNo;
	parm = parm + "&pageSize="+10;
	self.location.href="/front/sell/productListPage?"+parm;
}

/**
 * 删除单个产品
 */
function deleteOne(id){
	jq.common.deleteObj(id, "/front/sell/deleteProductById", true, "");
}

/**
 * 删除勾选的产品
 */
function deleteAll(){
	var id = getSelectedItem();
	jq.common.deleteObj(id, "/front/sell/deleteProductById", true, "");
}

/**
 * 单个产品下架
 * @param id
 */
function productOff(id){
	var obj = {};
	obj["idList"]=id;
	jq.common.updateObj(obj, "/front/sell/offProductById", true, "");
}

/**
 * 下架勾选的商品
 * @param id
 */
function productOffAll(){
	var id = getSelectedItem();
	var obj = {};
	obj["idList"]=id;
	jq.common.updateObj(obj, "/front/sell/offProductById", true, "");
}

/**
 * 单个产品上架
 * @param id
 */
function productOn(id){
	var obj = {};
	obj["idList"]=id;
	jq.common.updateObj(obj, "/front/sell/onProductById", true, "");
}

/**
 * 上架勾选的商品
 * @param id
 */
function productOnAll(){
	var id = getSelectedItem();
	var obj = {};
	obj["idList"]=id;
	jq.common.updateObj(obj, "/front/sell/onProductById", true, "");
}


/**
 * 获取选择项
 */
function getSelectedItem(){
	var obj=document.getElementsByName('item');
	var idList=''; 
	for(var i=0; i<obj.length; i++){ 
		if(obj[i].checked){
			idList+=obj[i].value+',';
		}
	} 
	return idList;
}

/**
 * 全选与反选
 */
function selectAll() {
	var checkboxs = document.getElementsByName("item");
	for (var i = 0; i < checkboxs.length; i++) {
		var e = checkboxs[i];
		e.checked = !e.checked;
	}
}
