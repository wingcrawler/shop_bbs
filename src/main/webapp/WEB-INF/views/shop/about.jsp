<!DOCTYPE html>
<html>
<head>
<jsp:include page="include/meta.jsp"></jsp:include>
<title>${t.t_aboutus }</title>
<link rel="stylesheet" type="text/css" href="/frontstyle/css/reset.css">
<link rel="stylesheet" type="text/css" href="/frontstyle/css/main.css">
</head>
<body>
	<jsp:include page="include/header.jsp"></jsp:include>

	<!--  stat page title  -->
	<section class="page_title">
		<h1 class="title">About Us</h1>
	</section>
	<!--  end page title  -->


	<!--  start work details section  -->
	<section class="work_details clearfix">
		<div class="wrapper">
			<p class="intro">Sachiel Connect is a Canadian Information
				Services Platform focused on the gathering and dissemination of
				Canadian health and healthy-living related data, information, foods
				and services.  Current export markets of focus include China,
				Africa, and New Zealand. Our Customer and Suppliers' portal can be
				located at www.sachielconnect.com We are based in the beautiful
				seaside city of Vancouver BC Canada.</p>
			<div class="columns">
				<img src="/frontstyle/images/work1.jpg" alt="" title="" class="col_left" /> <img
					src="/frontstyle/images/work2.jpg" alt="" title="" class="col_right" />
			</div>

		</div>
	</section>
	<section class="about clearfix">
		<div class="wrapper">


			<div class="columns clearfix">
				<div class="col_1 col_left">
					<h2>Tell us what you think!</h2>
					<p>Email: general@sachielconnectinc.com Telephone: 604-620-2092
					</p>
				</div>
				<div class="col_1 col_right">
					<h2>Address</h2>
					<p>2368-666 Burrard St Vancouver BC Canada</p>
				</div>
			</div>
		</div>
	</section>


	<!--  end footer  -->

	<jsp:include page="include/footer.jsp"></jsp:include>
</body>
</html>