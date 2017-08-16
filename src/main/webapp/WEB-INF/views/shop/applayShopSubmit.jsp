<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<style type="text/css">


center {color:red}

</style>

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
		<!-- 			<img src="../frontstyle/images/register.png" class="reg_bg"> -->
		<div class="register1">
			<div class="box">
<!-- 				<p>Seller Register</p> -->
			<!-- 	<div class="input">
				
					<span name="seller_pload" style="margin:-2px auto ">
						

					</span>
				
					<input hidden="" type="text" name="userRole" class="key1" id="role" vaule="2">
				</div> -->
				<!-- 	<span name="seller_pload" style="margin:-2px auto ">
						eqrewee

					</span> -->
					 <p class="margin:-2rem ">
					${t.shop_applay_return }
					</p>				
					<input id="onsubmit" type="botton" class="seller_pload" value="${t.t_user_login}" onclick=window.open('/user/login')>
			</div>
		</div>
	</form>
</html>