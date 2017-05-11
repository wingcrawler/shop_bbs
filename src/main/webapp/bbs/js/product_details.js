function showBigImg(imgEv) {
	var alertView = document.getElementById("alertView");
	alertView.style.display = "block"
	var bigImgC = document.getElementById("bigImgC");
	bigImgC.src = imgEv.src;
}

function hideBigImg() {
	var alertView = document.getElementById("alertView");
	alertView.style.display = "none"
	var bigImgC = document.getElementById("bigImgC");
	bigImgC.src = "";
}