/**
 * From 2016.04.12
 *
 * e.g. $.commonUtil.isNotBlank();
 */
jQuery.commonUtil = {
	
	/* 内容为空 返回"" */
	convertBlank: function(value) {
		if (value == undefined) {
			return "";
		} else if (value == null) {
			return "";
		}
		return value;
	},

	/* 判断是否为空 */ 
	isNotBlank: function(value) {
		if (value == undefined) {
			return false;
		} else if (value == null) {
			return false;
		} else if (value == '') {
			return false;
		} 
		return true;
	},
	
	//显示提示
	showTip: function(text) {
		setTimeout(function() {			
			var opts = {
				"closeButton": true,
				"debug": false,
				"positionClass": "toast-top-right toast-default",
				"toastClass": "black",
				"onclick": null,
				"showDuration": "100",
				"hideDuration": "1000",
				"timeOut": "2000",
				"extendedTimeOut": "1000",
				"showEasing": "swing",
				"hideEasing": "linear",
				"showMethod": "fadeIn",
				"hideMethod": "fadeOut"
			};
			toastr.info(text, "提示/Tip", opts);
		}, 400);
	}
	
}