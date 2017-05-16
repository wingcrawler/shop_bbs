(function(doc, win) {
	var docEl = doc.documentElement,
		resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
		recalc = function() {
			var clientWidth = docEl.clientWidth;
			if (!clientWidth) return;
			docEl.style.fontSize = 16 * (clientWidth / 1170) + 'px';
		};

	if (!doc.addEventListener) return;
	win.addEventListener(resizeEvt, recalc, false);
	doc.addEventListener('DOMContentLoaded', recalc, false);


})(document, window);

(function() {
	common = {
		init: function() {
			var self = this;
			//tab切换
			self.tab();
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
		}
	};
})();

$(function() {
	common.init();
});