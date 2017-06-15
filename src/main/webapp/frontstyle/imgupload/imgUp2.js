 //下面用于多图片上传预览功能

function setImagePreviews2(source, target) {
    var docObj = document.getElementById(source);
    var dd = document.getElementById(target);
    dd.innerHTML = "";
    var fileList = docObj.files;
    for (var i = 0; i < fileList.length; i++) {            
        dd.innerHTML += "<div style='float:left' > <img id='img" + i + source + "'  /> </div>";
        var imgObjPreview = document.getElementById("img"+i+source); 
        if (docObj.files && docObj.files[i]) {
            //火狐下，直接设img属性
            imgObjPreview.style.display = 'block';
            imgObjPreview.style.width = '145px';
            imgObjPreview.style.height = '145px';
            //imgObjPreview.src = docObj.files[0].getAsDataURL();
            //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
            imgObjPreview.src = window.URL.createObjectURL(docObj.files[i]);
        }
        else {
            //IE下，使用滤镜
            docObj.select();
            var imgSrc = document.selection.createRange().text;
            var localImagId = document.getElementById("img" + i + source);
            //必须设置初始大小
            localImagId.style.width = "145px";
            localImagId.style.height = "145px";
            //图片异常的捕捉，防止用户修改后缀来伪造图片
            try {
                localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
            }
            catch (e) {
                alert("Formate error");
                return false;
            }
            imgObjPreview.style.display = 'none';
            document.selection.empty();
        }
    }  
    return true;
}

