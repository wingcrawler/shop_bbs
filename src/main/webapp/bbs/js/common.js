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

	// $.get("template/header.html", function(data) {　　　　
	// 	$("body").prepend(data);　　
	// });
	// $.get("template/footer.html", function(data) {　　　　
	// 	$("body").append(data);　　
	// });
	// $.get("template/menu.html", function(data) {　　　　
	// 	$(".main .container").prepend(data);　　
	// });
})(document, window);