(function() {
	upload_product = {
		init: function() {
			var self = this;

			//tab切换
			self.tab();

			//模拟radio
			self.radio();

			//模拟checkbox
			self.checkbox();

		},
		/**
		 * tab切换
		 */
		tab: function() {
			$('.g-tabHd').on('click', 'li', function(ev) {
				var $this = $(this),
					$tabWrap = $this.closest('.g-tabWrap'),
					index = $this.index();

				$this.addClass('f-active').siblings('li').removeClass('f-active');
				$tabWrap.find('.g-tabMn .g-tabMnItem').eq(index).addClass('f-active').siblings('.g-tabMnItem').removeClass('f-active');

				ev.preventDefault();
			});

			$('.g-tabHd').on('click', 'li a', function(ev) {
				ev.preventDefault();
			});
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