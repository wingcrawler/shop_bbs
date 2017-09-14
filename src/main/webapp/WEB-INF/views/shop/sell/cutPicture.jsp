<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String ctx = request.getContextPath();
%>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="x-ua-compatible" content="ie=edge">
  <title>头像上传</title>
  <link rel="stylesheet" href="<%=ctx%>/frontstyle/sell/cropper/assets/css/font-awesome.min.css">
  <link rel="stylesheet" href="<%=ctx%>/frontstyle/sell/cropper/assets/css/bootstrap.min.css">
  <link rel="stylesheet" href="<%=ctx%>/frontstyle/sell/cropper/dist/cropper.css">
  <link rel="stylesheet" href="<%=ctx%>/frontstyle/sell/cropper/demo/css/main.css">
 <link rel="stylesheet" href="<%=ctx%>/frontstyle/sell/uploadTool/webuploader/webuploader.css">
  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body>
  <!--[if lt IE 8]>
    <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
  <![endif]-->
  <!-- Content -->
  <div class="container" style="margin-left:0px;">
    <div class="row">
      <div class="col-md-3">
        <!-- <h3 class="page-header">Demo:</h3> -->
        <div class="img-container">
          <img id="image"  src="" alt="Picture">
        </div>
      </div>
    </div>
    <div class="col-md-3 docs-buttons">
    	<div class="btn-group btn-group-crop" style="boder:0px">
       		<button type="button" class="btn btn-primary" data-method="getCroppedCanvas" style="z-index: 100;boder:0px;height:40px;width:86px;margin-bottom: 5px;background:#00b7ee">
	          <span class="docs-tooltip" data-toggle="tooltip">
	            	${t.b_confirm }</span>
	        </button>
       </div>
       <div id="uploader" class="btn-group btn-group-crop">
		    <!--用来存放文件信息-->
		    <div id="thelist" class="uploader-list"></div>
		    <div class="btns">
		        <div id="picker">${t.t_select }</div>
		    </div>
		</div>
  </div>
          
  <!-- Scripts -->
  <script src="<%=ctx%>/frontstyle/sell/cropper/assets/js/jquery.min.js"></script>
  <script src="<%=ctx%>/frontstyle/sell/cropper/assets/js/bootstrap.min.js"></script>
  <script src="<%=ctx%>/frontstyle/sell/cropper/dist/cropper.js"></script>
  <script src="<%=ctx%>/frontstyle/sell/cropper/demo/js/main.js"></script>
  <script src="<%=ctx%>/frontstyle/sell/uploadTool/webuploader/webuploader.js"></script>
  <script type="text/javascript">
  	var uploader = WebUploader.create({
		
		    // swf文件路径
		    swf: 'uploadTool/webuploader/Uploader.swf',
		
		    // 文件接收服务端。
		    server: 'http://webuploader.duapp.com/server/fileupload.php',
		
		    // 选择文件的按钮。可选。
		    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
		    pick: '#picker',
		
		    // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
		    resize: false,
		    // 只允许选择图片文件。
		    accept: {
		        title: 'Images',
		        extensions: 'gif,jpg,jpeg,bmp,png',
		        mimeTypes: 'image/*'
		    }
		});
		uploader.on( 'fileQueued', function( file ) {
		    // 创建缩略图
		    // 如果为非图片文件，可以不用调用此方法。
		    // thumbnailWidth x thumbnailHeight 为 100 x 100
		    uploader.makeThumb( file, function( error, src ) {
		    	var $img=$("#image");
		        if ( error ) {
		            $img.replaceWith('<span>不能预览</span>');
		            return;
		        }
		         $img.attr( 'src', src );
		         var options1 = {
			        aspectRatio:1/1,
			        resizable:true,
			        crop: function (e) {
			          $dataX.val(Math.round(e.x));
			          $dataY.val(Math.round(e.y));
			          $dataHeight.val(Math.round(e.height));
			          $dataWidth.val(Math.round(e.width));
			          $dataRotate.val(e.rotate);
			          $dataScaleX.val(e.scaleX);
			          $dataScaleY.val(e.scaleY);
			        }
			      };
			       $img.cropper('destroy').cropper(options1);
		    uploader.reset();
		    },0.8,0.8);
		   
		});
  </script>
</body>
</html>
