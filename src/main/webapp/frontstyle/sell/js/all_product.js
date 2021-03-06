
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
	 if(confirm("Confirm to delete?")) {
		 jQuery.common.deleteById(id, "/front/sell/deleteProductById", true, window.location.href);
	 }
}

/**
 * 删除勾选的产品
 */
function deleteAll(){
	if(confirm("Confirm to delete?")) {
		var id = getSelectedItem();
		if(id!=''){
			jQuery.common.deleteById(id, "/front/sell/deleteProductById", true, window.location.href);	
		}
	}
}

/**
 * 单个产品下架
 * @param id
 */
function productOff(id){
	var obj = {};
	obj["idList"]=id;
	jQuery.common.updateObjByParm(obj, "/front/sell/offProductById", true, window.location.href);
}

/**
 * 下架勾选的商品
 * @param id
 */
function productOffAll(){
	var id = getSelectedItem();
	var obj = {};
	obj["idList"]=id;
	jQuery.common.updateObjByParm (obj, "/front/sell/offProductById", true, window.location.href);
}

/**
 * 单个产品上架
 * @param id
 */
function productOn(id){
	var obj = {};
	obj["idList"]=id;
	jQuery.common.updateObjByParm(obj, "/front/sell/onProductById", true, window.location.href);
}

/**
 * 上架勾选的商品
 * @param id
 */
function productOnAll(){
	var id = getSelectedItem();
	var obj = {};
	obj["idList"]=id;
	jQuery.common.updateObjByParm(obj, "/front/sell/onProductById", true,window.location.href);
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
	if(idList==''){
		alert("Please select product");
	} else {
		return idList;	
	}
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
function selectAllCheckbox(){
	var allCheckbox = document.getElementsByName("all-ipt-checkbox");
	allCheckbox.checked = !allCheckbox.checked;
}
