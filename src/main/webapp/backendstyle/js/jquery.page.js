/**
2016-04-05
**/
(function($){
	var ms = {
		init:function(obj,args){
			return (function(){
				ms.fillHtml(obj,args);
				ms.bindEvent(obj,args);
			})();
		},
		fillHtml:function(obj,args){
			return (function(){
				obj.empty();
				if(args.current > 1){
					obj.append('<a href="javascript:;" class="prevPage" onClick="'+args.fnName+'('+(args.current-1)+')">'+args.prevPage+'</a>');
				}else{
					obj.remove('.prevPage');
					obj.append('<span class="disabled">'+args.prevPage+'</span>');
				}
				if(args.current != 1 && args.current >= 4 && args.pageCount != 4){
					obj.append('<a href="javascript:;" class="tcdNumber" onClick="'+args.fnName+'(1)">'+1+'</a>');
				}
				if(args.current-2 > 2 && args.current <= args.pageCount && args.pageCount > 5){
					obj.append('<span>...</span>');
				}
				var start = args.current -2,end = args.current+2;
				if((start > 1 && args.current < 4)||args.current == 1){
					end++;
				}
				if(args.current > args.pageCount-4 && args.current >= args.pageCount){
					start--;
				}
				for (;start <= end; start++) {
					if(start <= args.pageCount && start >= 1){
						if(start != args.current){
							obj.append('<a href="javascript:;" class="tcdNumber" onClick="'+args.fnName+'('+start+')">'+ start +'</a>');
						}else{
							obj.append('<span class="current">'+ start +'</span>');
						}
					}
				}
				if(args.current + 2 < args.pageCount - 1 && args.current >= 1 && args.pageCount > 5){
					obj.append('<span>...</span>');
				}
				if(args.current != args.pageCount && args.current < args.pageCount -2  && args.pageCount != 4){
					obj.append('<a href="javascript:;" class="tcdNumber" onClick="'+args.fnName+'('+args.ageCount+')">'+args.pageCount+'</a>');
				}
				if(args.current < args.pageCount){
					obj.append('<a href="javascript:;" class="nextPage" onClick="'+args.fnName+'('+(parseInt(args.current)+parseInt(1))+')">'+args.nextPage+'</a>');
				}else{
					obj.remove('.nextPage');
					obj.append('<span class="disabled">'+args.nextPage+'</span>');
				}
			})();
		},
		bindEvent:function(obj,args){
			return (function(){
				obj.on("click","a.tcdNumber",function(){
					var current = parseInt($(this).text());
					ms.fillHtml(obj,{"current":current,"pageCount":args.pageCount,"fnName":args.fnName});
					if(typeof(args.backFn)=="function"){
						args.backFn(current);
					}
				});
				obj.on("click","a.prevPage",function(){
					var current = parseInt(obj.children("span.current").text());
					ms.fillHtml(obj,{"current":current-1,"pageCount":args.pageCount,"fnName":args.fnName});
					if(typeof(args.backFn)=="function"){
						args.backFn(current-1);
					}
				});
				obj.on("click","a.nextPage",function(){
					var current = parseInt(obj.children("span.current").text());
					ms.fillHtml(obj,{"current":current+1,"pageCount":args.pageCount,"fnName":args.fnName});
					if(typeof(args.backFn)=="function"){
						args.backFn(current+1);
					}
				});
			})();
		}
	}
	
	$.fn.createPage = function(options){
		var args = $.extend({
			pageCount : 10,
			current : 1,
			fnName : '',
			prevPage:'<',
			nextPage:'>',
			backFn : function(){}
		},options);
		ms.init(this,args);
	}
})(jQuery);

