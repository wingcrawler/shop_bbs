<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%><!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"><![endif]-->
<!--
        Zoom Template 
        http://www.templatemo.com/tm-414-zoom
        -->
<title>${t.t_sqe }</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- Google Web Font Embed -->

<link rel="stylesheet" href="/frontstyle/css/bootstrap.min.css">
<link rel="stylesheet" href="/frontstyle/css/font-awesome.min.css">
<link rel="stylesheet" href="/frontstyle/css/templatemo_main.css">
</head>
<body>
	<div id="main-wrapper">

		<div
			class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center templatemo-logo margin-top-20">
            <div class="templatemo-site-title">
                <a href="#"><img src="/frontstyle/images/wel_logo.png" alt=""></a>
            </div>  			
			<h1 class="templatemo-site-title">
				<a href="#">${t.t_sqe }</a>
			</h1>
            <div class="eg_zh"><a href="http://www.sachielconnect.com/">中文</a><a href="http://en.sachielconnect.com/">English</a></div>
        </div>
		<div class="image-section">
			<div class="image-container">
				<img src="/frontstyle/images/zoom-bg-1.jpg" id="menu-img"
					class="main-img inactive"
					alt="Zoom HTML5 Template by templatemo.com"> 
					<!-- <img
					src="/frontstyle/images/zoom-bg-2.jpg" id="products-img"
					class="inactive" alt="Product"> <img
					src="/frontstyle/images/zoom-bg-3.jpg" id="services-img"
					class="inactive" alt="Services"> <img
					src="/frontstyle/images/zoom-bg-4.jpg" id="about-img"
					class="inactive" alt="About"> <img
					src="/frontstyle/images/zoom-bg-5.jpg" id="contact-img"
					class="inactive" alt="Contact"> <img
					src="/frontstyle/images/zoom-bg-6.jpg" id="company-intro-img"
					class="main-img inactive" alt="Company Intro"> <img
					src="/frontstyle/images/zoom-bg-7.jpg" id="testimonials-img"
					class="main-img inactive" alt="Testimonials"> -->
			</div>
		</div>

		<div class="container">
			<div
				class="col-xs-12 col-sm-12 col-md-8 col-lg-8 col-md-offset-2 col-lg-offset-2 templatemo-content-wrapper">
				<div class="templatemo-content">

					<section id="menu-section" class="active">
						<div class="row">
							<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3 margin-bottom-20">
								<a href="/shopIndex" class="change-section">
									<div class="black-bg btn-menu">
										<i class="fa fa-cubes"></i>
										<h2>${t.t_mall }</h2>
									</div>
								</a>
							</div>
							<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3 margin-bottom-20">
								<a href="/news/list" class="change-section">
									<div class="black-bg btn-menu">
										<i class="fa fa-envelope"></i>
										<h2>${t.t_information }</h2>
									</div>
								</a>
							</div>
							<div class="col-xs-6 col-sm-3 col-md-3 col-lg-3 margin-bottom-20">
								<a href="/bbs/index" class="change-section">
									<div class="black-bg btn-menu">
										<i class="fa fa-users"></i>
										<h2>${t.t_bbs }</h2>
									</div>
								</a>
							</div>




						</div>
					</section>
					<!-- /.menu-section -->
					<!-- /.about-section -->

				</div>
				<!-- /.templatemo-content -->
			</div>
			<!-- /.templatemo-content-wrapper -->
		</div>
		<!-- /.row -->

		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 footer">
				<p class="footer-text">${t.t_sqe_copy_right }</p>
			</div>
			<!-- /.footer -->
		</div>

	</div>

	<script src="/frontstyle/js/jquery.min.js"></script>
	<script src="/frontstyle/js/jquery-ui.min.js"></script>
	<script src="/frontstyle/js/jquery.backstretch.min.js"></script>
	<script src="/frontstyle/js/templatemo_script.js"></script>

	<script type="text/javascript">
		$(function() {
			$(".change-section").click(function() {
				self.location = $(this).attr("href");
			});

		});
	</script>
</body>
</html>
