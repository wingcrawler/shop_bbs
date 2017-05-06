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
			url_edit : '', //编辑或添加的页面接口
			url_remove : '', //删除
			url_move_up : '', //上移
			url_move_down : '', //下移
			url_move_show : '', //查看
			url_load : '', //加载
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
	
	/* 搜索 */
	$.fn.doAutoSearch = function() {
		var _parm = $.fn.getFormJson('.form');
		_args.parm = _parm;
		action.load(_obj, _args);
	}
	
	/* 进入编辑页  */
	$.fn.edit = function(obj){
		var url= _args.url_edit + "?id=" + $(obj).attr("opid");
		window.open(url);
	}

	/* 弹出删除确认框  */
	$.fn.showDeleteDialog = function(obj) {
		var _id = $(obj).attr("opid");
		var _url = _args.url_remove;
		$("input[name='hidden-url']").val(_url);
		$("input[name='hidden-id']").val(_id);
		jQuery("#modal-delete").modal("show", {backdrop: "fade"});
	}
	
	/* 删除 */
	$.fn.doDelete = function() {
		/*var _url = $('input[name="hidden-url"]').val();*/
		var _url = _args.url_remove;
		var _id = $('input[name="hidden-id"]').val();
		$.ajax({
			type: "GET",
			url: _url,
			dataType : "json",
			data: {
				id : _id
			},
			success: function(data) {
				if (data.errorNo != 0) {
					$.commonUtil.showTip(data.errorInfo);
				} else {
					$.commonUtil.showTip(data.errorInfo);
					action.load(_obj, _args);
				}
			}
		});
	}
	
	/* 保存 */
	$.fn.doSave = function(_parm, _submitUrl, _jumpUrl) {
		$.ajax({
			type: "POST",
			url: _submitUrl,
			dataType : "json",
			data: _parm,
			success: function(data) {
				if (data.errorNo != 0) {
					$.commonUtil.showTip(data.errorInfo);
				} else {
					$.commonUtil.showTip(data.errorInfo);
					self.location= _jumpUrl;
				}
			}
		});
	}
	
	/* 保存并刷新  */
	$.fn.doSaveAndReload = function(_parm, _url) {
		$.ajax({
			type: "POST",
			url: _url,
			dataType : "json",
			data: _parm,
			success: function(data) {
				if (data.errorNo != 0) {
					$.commonUtil.showTip(data.errorInfo);
				} else {
					$.commonUtil.showTip(data.errorInfo);
					action.load(_obj, _args);
				}
			}
		});
	}
	
	/* 移除加载地址后面的参数 */
	$.fn.removeUrlLoadArgs = function() {
		var lastIndex =_args.url_load.indexOf('?'); 
		_args.url_load = _args.url_load.substring(0,lastIndex);
	}
	
	/* 含文件的表单提交 */
	$.fn.myAjaxSubmit = function(elem ,_submitUrl, _jumpUrl) {
		$(elem).ajaxSubmit({  
            type:'post',  
            cache: false,  
            url: _submitUrl, 
            dataType : 'json', //返回值类型 一般设置为json  
            success : function(data, status) {  
	        	if(data.errorNo==0){
	        		self.location= _jumpUrl;
				} else {
					$.commonUtil.showTip(data.errorInfo);
				}
	        },  
	        error : function(data, status, e) {  
	        	$.commonUtil.showTip(data.errorInfo); 
	        }   
        });
	}
	
	 $.fn.getFormJson = function(elem) {
        var o = {};
        var a = $(elem).serializeArray();
        $.each(a, function () {
            if (o[this.name] !== undefined) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
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
					if(data.list==undefined){
						return;
					}
					var len = data.list.length;
					for (var i = 0; i < len; i++) {
						var item = data.list[i];
						html += '<tr>';
						$.each(fields, function(index) {
							var field = fields.eq(index).attr('field');//获取当前列的字段
							if (field == 'index') {
								html += '<td>' + (i + 1) + '</td>';
							}else if (field == 'op') {
								//操作 : 0:删除; 1:添加; 2:编辑; 3:查看; 4:上移; 5:下移;6:标记为已读;
								var opHtml = '';
								var field_role =  fields.eq(index).attr("field-role");//获取当前列op字段的field-role值
								var arr = field_role.split(',');
								for(var r=0;r<arr.length;r++){
									if (arr[r]==1) {										
										opHtml += '<a href="javascript:;" index="'+i+'" opid="'+item.id+'" onclick="add(this);" class="btn btn-secondary btn-single btn-sm"><i class="fa-plus"></i></a>';
									}
									if (arr[r]==2) {
										opHtml += '<a href="javascript:;" index="'+i+'" opid="'+item.id+'" onclick="$.fn.edit(this);" class="btn btn-secondary btn-single btn-sm"><i class="fa-edit"></i></a>';
									}
									if (arr[r]==201) {
										opHtml += '<a href="javascript:;" index="'+i+'" opid="'+item.id+'" onclick="edit(this);" class="btn btn-secondary btn-single btn-sm"><i class="fa-edit"></i></a>';
									}
									if (arr[r]==3) {
										if(item.status==0){
											opHtml += '<a href="javascript:;" index="'+i+'" opid="'+item.id+'" onclick="viewDetail(this);" class="btn btn-secondary btn-single btn-sm"><i class="fa-eye"></i></a>';
										}
									}
									if (arr[r]==4) {
										opHtml += '<a href="javascript:;" index="'+i+'" opid="'+item.id+'" onclick="moveUp(this);" class="btn btn-secondary btn-single btn-sm"><i class="fa-chevron-up"></i></a>';
									}
									if (arr[r]==5) {
										opHtml += '<a href="javascript:;" index="'+i+'" opid="'+item.id+'" onclick="moveDown(this);" class="btn btn-secondary btn-single btn-sm"><i class="fa-chevron-down"></i></a>';
									}
									if (arr[r]==6) {
										opHtml += '<a href="javascript:;" index="'+i+'" opid="'+item.id+'" onclick="doRead(this);" class="btn btn-secondary btn-single btn-sm"><i class="fa-toggle-off"></i></a>';										
									}
									if (arr[r]==0) {
										opHtml += '<a href="javascript:;" index="'+i+'" opid="'+item.id+'" onclick="$.fn.showDeleteDialog(this);" class="btn btn-danger btn-single btn-sm"><i class="fa-minus"></i></a>';
									}
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
								//字段读取的链接地址 一般存于数据库
								if ($.commonUtil.isNotBlank(fields.eq(index).attr('url-field'))) {
									text = '<a href="' + item[fields.eq(index).attr("url-field")] + '" target="_blank">' + text + '</a>';
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
  
