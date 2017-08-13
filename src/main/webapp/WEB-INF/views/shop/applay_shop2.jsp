<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- saved from url=(0046)http://en.sachielconnect.com/user/sellregister -->
<html style="background-image: url(/frontstyle/images/register.png); background-position: initial; background-repeat: no-repeat; background-attachment: initial; background-origin: initial; background-clip: initial; background-color: initial; background-size: 100% !important; font-size: 18.6803px;"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta name="keywords" content="Biruang Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design">

<script type="application/x-javascript"> 
	addEventListener("load", function() { 
		setTimeout(hideURLbar, 0); 
	}, false); 
	function hideURLbar(){ 
		window.scrollTo(0,1); 
	} 
</script>

<link href="/frontstyle/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="/frontstyle/css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href="/frontstyle/css/nav.css" rel="stylesheet" type="text/css" media="all"/>
<link href="/frontstyle/css/common.css" rel="stylesheet" type="text/css" media="all"/>
<link rel="stylesheet" href="/backendstyle/css/jquery.page.css">

<script src="/frontstyle/js/jquery.min.js"></script>
<script src="/frontstyle/js/jquery.easydropdown.js"></script>
<script src="/frontstyle/js/scripts.js" type="text/javascript"></script>
<script src="/frontstyle/js/easyResponsiveTabs.js" type="text/javascript"></script>
<script type="text/javascript">
 $(document).ready(function () {
     $('#horizontalTab').easyResponsiveTabs({
         type: 'default', //Types: default, vertical, accordion           
         width: 'auto', //auto or any width like 600px
         fit: true   // 100% fit in a container
     });
 });
</script>	
<!-- start-smoth-scrolling -->
<script type="text/javascript" src="/frontstyle/js/move-top.js"></script>
<script type="text/javascript" src="/frontstyle/js/easing.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event){		
			event.preventDefault();
			$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
		});
	});
</script>

<!-- pop-up-box -->
<script type="text/javascript" src="/frontstyle/js/modernizr.custom.min.js"></script>    
<link href="/frontstyle/css/popuo-box.css" rel="stylesheet" type="text/css" media="all" />
<script src="/frontstyle/js/jquery.magnific-popup.js" type="text/javascript"></script>
<!--//pop-up-box -->

<script src="/backendstyle/js/jquery.form.js"></script>
<script src="/frontstyle/js/common.js"></script>
<script src="/backendstyle/js/jquery.page.js"></script>
<script src="/backendstyle/js/jquery.select.js"></script>
<script src="/frontstyle/js/jq.common.js"></script>

<!-- img upload -->
<script type="text/javascript" src="/frontstyle/imgupload/imgUp.js"></script>
<script type="text/javascript" src="/frontstyle/imgupload/imgUp2.js"></script>
<link rel="stylesheet" href="/frontstyle/css/base.css" type="text/css" />
<link rel="stylesheet" href="/frontstyle/css/registerstyle.css"
	type="text/css" />

<title>Insert title here</title>
</head>
<body>

	<form name="form1" method="post" action="http://en.sachielconnect.com/user/sellregister" onsubmit="return check()">
		<!-- 			<img src="/frontstyle/images/register.png" class="reg_bg"> -->
		<div class="register1">
			<div class="box">
<!-- 				<p>Seller Register</p> -->
				<div class="input">
					<div class="margin">
					<input type="hidden" value="${param.name}" name="name">
						<span><b>*</b>${t.t_shop_name }：</span><input type="text" name="username" class="user" id="rename">
					</div>
					<div class="margin" style="margin-top:20px;">
						<span><b>*</b>${t.t_shop_logo }:</span>							
						<div class="upload" id="upload">
							<span style="position:relative;left:0%;top:10px;margin-left: 0;width:100%; display:block;text-align: center;">+选择图片</span>
							<div style="margin:10px; width:100px;">
								<input type="file"  name="file" id="doc" multiple="multiple" onchange="javascript:setImagePreviews();" accept="image/*" />
								<div id="dd" style="height:60px;margin-top:-46px;margin-left:-15px;z-index:99999"></div>
							</div>
						</div>

					</div>
					<div class="margin">
						<span><b>*</b>${t.t_mobile }<em>:</em></span><input type="password" name="repassword" class="dianhua" id="key1">
					</div>
					<div class="margin">
						<span><b>*</b>${t.t_work_time }<em>:</em></span><input type="text" class="work_time" name="workFrom" value=""> - 
						<input type="text" name="workTo" value="" class="work_time">
						<span class="gray">7*24h</span>
					</div>					
					<div class="margin">
						<span><b>*</b>${t.t_qq }<em>:</em></span><input type="text" name="userMail" class="qq" id="email">
					</div>
					<div class="margin">
						<span><b>*</b>${t.t_address }<em>:</em></span><input type="text" name="userMail" class="dizhi" id="email">
					</div>
					<input hidden="" type="text" name="userRole" class="key1" id="role" vaule="2">
				</div>
				<input type="button" onclick="jQuery.common.ajaxFileSubmit('#form','/user/doApplayShop',true,'/user/applayShopSubmit')">
								${t.b_submit }
								</div>
			</div>
		</div>
	</form>



	<script type="text/javascript">
$(function(){
	$('.menu_box .menu_list div.module a').eq(4).addClass('active');
	
	$('#upload').click(function(){
		$('input[name="file"]').trigger('click');
	});
	
	$('#showImg').hover(function(){
		$('.deleteImg').show();	
	},function(){
		$('.deleteImg').hide();
	});
})
function deleteImg(id){
	if(confirm('${t.t_confirm_delete}')){
		debugger;
		var parm = {};
		parm.id=id;
		parm.type="logo";
		jQuery.common.updateObjByParm(parm,'/front/sell/deleteImg',true,window.location.href);
	}
}

var stats=${shop.shopStatus };
if(stats==2|stats==1){
	$('.save').attr("style", "display:none;");
}
</script>	

</body></html>