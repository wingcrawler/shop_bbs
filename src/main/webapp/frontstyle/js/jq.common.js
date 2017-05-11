jQuery.common = {
		
	/**
	 * 删除
	 */
	deleteById: function(_id, _deleteUrl, _isRefrush, _jumpUrl) {
		$.ajax({
			type: "GET",
			url: _deleteUrl,
			dataType : "json",
			data: {
				idList : _id
			},
			success: function(data) {
				if (data.errorNo != 0) {
					alert(data.errorInfo);
				} else {
					if(_isRefrush){
						self.location.href=_jumpUrl;
					} else {
						alert(data.errorInfo);
					}
				}
			}
		});
	},
	
	updateObj: function(_obj, _updateUrl, _isRefrush, _jumpUrl) {
		$.ajax({
			type: "POST",
			url: _updateUrl,
			dataType : "json",
			data: _obj,
			success: function(data) {
				if (data.errorNo != 0) {
					alert(data.errorInfo);
				} else {
					if(_isRefrush){
						self.location.href=_jumpUrl;
					} else {
						alert(data.errorInfo);
					}
				}
			}
		});
	},
	
	/* 含文件的表单提交 */
	ajaxFileSubmit : function(elem ,_submitUrl,_isRefrush, _jumpUrl) {
		$(elem).ajaxSubmit({  
            type:'post',  
            cache: false,  
            url: _submitUrl, 
            dataType : 'json', //返回值类型 一般设置为json  
            success : function(data, status) {  
	        	if(data.errorNo==0){
	        		if(_isRefrush){
	        			self.location= _jumpUrl;
	        		}
				} else {
					alert(data.errorInfo);
				}
	        },  
	        error : function(data, status, e) {  
	        	alert(data.errorInfo); 
	        }   
        });
	},
	
	/* 联动刷新select */
	refreshSelect : function(_currentElem, _targetElem, _getUrl){
		var parentId = $(_currentElem).val();
		$.ajax({
			type: "GET",
			url: _getUrl,
			dataType : "json",
			data: {
				id : parentId 
			},
			success: function(data) {
				if (data.errorNo == 0) {
					if(data.list==undefined){
						return;
					}
					var html = '';
					var len = data.list.length;
					for (var i = 0; i < len; i++) {
						var item = data.list[i];
						html += '<option value="'+item.id+'">'+item.typeName+'</option>';
					}
					$(_targetElem).html(html);
				} 
			}
		});
	}
	
}
