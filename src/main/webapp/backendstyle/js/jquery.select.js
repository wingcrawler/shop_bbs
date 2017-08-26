(function($) {
	$.fn.itemSelect = function(options) {
		var args = $.extend({
			elem : '',
			property : '',
			compare : '',
			backFn : function() {
			}
		}, options);
		if (args.elem == '' || args.property == '' || args.compare == '') {
			return;
		}
		$(args.elem).removeClass('active');
		$(args.elem).each(function (index, domElem){
			var objAttr = $(args.elem).eq(index).attr(args.property);
			if(objAttr != undefined && objAttr  == args.compare){
				$(args.elem).eq(index).addClass('active');
				return;
			}
		});
	},
	
	$.fn.optionSelect = function(options) {
		var args = $.extend({
			compare : '-1',
			backFn : function() {
			}
		}, options);
		$(this).children('option[value="'+args.compare+'"]').attr("selected","selected");
	}

}(jQuery));
