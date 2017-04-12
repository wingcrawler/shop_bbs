/**
 * From 2016.04.12
 */
/* plugin definition */    
(function($) {
	var _obj; //页面选择的对象
	var _args; //参数
	
	// 扩展方式1
	/* Extend our default options with those provided.
	 *html: $.fn.hilight.defaults.foreground = 'blue';  
	 */    
	/*var opts = $.extend({}, $.fn.datatable.defaults, options);     
	  $.fn.datatable.defaults = {    
			foreground: 'red',    
			background: 'yellow'    
	};*/
	
	// 扩展方式2
	$.fn.datatable = function(options) {
		var args = $.extend({
			url_edit : '',
			url_remove : '',
			url_move_up : '',
			url_move_down : '',
			url_move_show : '',
			url_load : '',
			parm : {
				pageNum : 1,
				pageSize : 10
			},
			cache : false,
			type : 'get',
			dataType : 'json',
			edit_fn : '',
			backFn : function() {}
		},options);
		_obj = this;
		_args = args;
		action.load(this, _args);
	}
	
	/* 重新加载 */
	$.fn.reload = function() {
		action.load(_obj, _args);
	}
	
	/* 翻页 */
	$.fn.nextPage = function(_pageNum) {
		_args.parm.pageNum = _pageNum;
		action.load(_obj, _args);
	}
	
	/* 搜索 */
	$.fn.doSearch = function(_parm) {
		_args.parm = _parm;
		action.load(_obj, _args);
	}
	
	/* 删除 */
	$.fn.doDelete = function() {
		var _url = $('input[name="hidden-url"]').val();
		var _id = $('input[name="hidden-id"]').val();
		$.ajax({
			type: "GET",
			url: _url,
			dataType : "json",
			data: {
				id : _id
			},
			success: function(data) {
				if (data.errorNo != 200) {
					$.commonUtil.showTip(data.errorInfo);
				} else {
					$.commonUtil.showTip("删除成功");
					action.load(_obj, _args);
				}
			}
		});
	}
	
	/* 保存 */
	$.fn.doSave = function(_parm, _url) {
		$.ajax({
			type: "POST",
			url: _url,
			dataType : "json",
			data: _parm,
			success: function(data) {
				if (data.errorNo != 200) {
					$.commonUtil.showTip(data.errorInfo);
				} else {
					$.commonUtil.showTip("提交成功");
					action.load(_obj, _args);
				}
			}
		});
	}
	
	var action = {
		init: function(obj,args){
			return (function() {
				action.load(obj, args);
			})();
		},
		load: function(obj, args) {
			var fields = $(obj).find('thead tr th');
			$.ajax({
				url : args.url_load,
				data : args.parm,
				type : args.type,
				cache : args.cache,
				dataType : args.dataType,
				success : function(data) {
					var html = '';
					var len = data.list.length;
					for (var i = 0; i < len; i++) {
						var item = data.list[i];
						html += '<tr>';
						$.each(fields, function(index) {
							var field = fields.eq(index).attr('field');//获取当前列的字段
							if (field == 'index') {
								html += '<td>' + (i + 1) + '</td>';
							}else if (field == 'op') {
								//操作 : 0:删除; 1:添加; 2:编辑; 3:查看; 4:上移; 5:下移;
								var opHtml = '';
								var field_role =  fields.eq(index).attr("field-role");//获取当前列op字段的field-role值
								if (field_role.indexOf('1') >= 0) {										
									opHtml += '<a href="javascript:;" index="'+i+'" id="'+item.id+'" onclick="add(this);" class="btn btn-secondary btn-single btn-sm"><i class="fa-plus"></i></a>';
								}
								if (field_role.indexOf('2') >= 0) {
									opHtml += '<a href="javascript:;" index="'+i+'" id="'+item.id+'" onclick="edit(this);" class="btn btn-secondary btn-single btn-sm"><i class="fa-edit"></i></a>';
								}
								if (field_role.indexOf('3') >= 0) {
									opHtml += '<a href="javascript:;" index="'+i+'" id="'+item.id+'" onclick="viewDetail(this);" class="btn btn-secondary btn-single btn-sm"><i class="fa-chevron-eye"></i></a>';
								}
								if (field_role.indexOf('4') >= 0) {
									opHtml += '<a href="javascript:;" index="'+i+'" id="'+item.id+'" onclick="moveUp(this);" class="btn btn-secondary btn-single btn-sm"><i class="fa-chevron-up"></i></a>';
								}
								if (field_role.indexOf('5') >= 0) {
									opHtml += '<a href="javascript:;" index="'+i+'" id="'+item.id+'" onclick="moveDown(this);" class="btn btn-secondary btn-single btn-sm"><i class="fa-chevron-down"></i></a>';
								}
								if (field_role.indexOf('0') >= 0) {
									opHtml += '<a href="javascript:;" index="'+i+'" id="'+item.id+'" onclick="showDeleteDialog(this);" class="btn btn-danger btn-single btn-sm"><i class="fa-minus"></i></a>';
								}
								text = opHtml;
								html += '<td>' + text + '</td>';
							} else {
								var text = item[field];
								text = $.commonUtil.convertBlank(text);//空检查
								//是否有超链接
								if ($.commonUtil.isNotBlank(fields.eq(index).attr('url'))) {
									text = '<a href="' + fields.eq(index).attr("url") + item[fields.eq(index).attr("parm")] + '" target="_blank">' + text + '</a>';
								}
								html += '<td>' + text + '</td>';
							}
						});
						html += '</tr>';
					}
					$(obj).find('tbody').html(html);
					if(data.page!=null){
						$(".pagebar").createPage({
							pageCount : data.page.pageCount,
							current : data.page.currentPage,
							fnName : '$.fn.nextPage',
							backFn : function(p) {
								// console.log(p);
							}
						});
					}
				}
			});
		},
		nextPage: function(pageNum) {
			console.log(11);
		}
	}
	
})(jQuery);
  
