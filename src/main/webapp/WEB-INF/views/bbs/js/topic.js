
//必须给最外层，加一个id
var root = $("#ajaxList");
//收起|展开回复
root.on("click", ".lzl .cl", function(){
	lzl.getItem(this).hide();
	return false;
}).on("click", ".tItem .rbt:not(.first)", function(){
	var LZ = $(this).siblings(".lzl");
	LZ.show();
	return false;
});

	
