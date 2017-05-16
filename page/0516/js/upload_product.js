(function() {
	upload_product = {
		init: function() {
			var self = this;

			//模拟radio
			self.radio();

			//模拟checkbox
			self.checkbox();

		},
		/*
		 * 模拟radio事件
		 */
		radio: function() {
			var self = this;
			$('body').on('click', '.u-ipt-radio', function(e) {
				var $this = $(this),
					$group = $this.closest('.m-radiogroup'),
					$item = $this.closest('.u-radioitem');

				$group.find('.i-radio').removeClass('z-checked');
				$item.find('.i-radio').addClass('z-checked');
			});
		},
		/*
		 * 模拟checkbox事件
		 */
		checkbox: function() {
			var self = this;
			$('body').on('click', '.u-ipt-checkbox', function(ev) {
				var $this = $(this),
					$item = $this.closest('.u-checkboxitem'),
					isChecked = $this.is(':checked');

				if (isChecked) {
					$item.find('.i-checkbox').addClass('z-checked');
				} else {
					$item.find('.i-checkbox').removeClass('z-checked');
				}
			});
		}
	};
})();

$(function() {
	upload_product.init();
});