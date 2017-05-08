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
	}
}

