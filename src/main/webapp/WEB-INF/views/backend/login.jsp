<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="include/meta.jsp"></jsp:include>
	<title>${t.sign_in }</title>
</head>
<body class="page-body login-page">

	<div class="login-container">
		<div class="row">
		
			<div class="col-sm-6">
			
				<script type="text/javascript">
					jQuery(document).ready(function($) {
						// Reveal Login form
						setTimeout(function(){ $(".fade-in-effect").addClass('in'); }, 1);
						// Validation and Ajax action
						$("form#login").validate({
							rules: {
								username: {
									required: true
								},
								passwd: {
									required: true
								}
							},
							messages: {
								username: {
									required: 'Please enter your username.'
								},
								
								passwd: {
									required: 'Please enter your password.'
								}
							},
							// Form Processing via AJAX
							submitHandler: function(form)
							{
								show_loading_bar(70); // Fill progress bar to 70% (just a given value)
								var opts = {
									"closeButton": true,
									"debug": false,
									"positionClass": "toast-top-full-width",
									"onclick": null,
									"showDuration": "300",
									"hideDuration": "1000",
									"timeOut": "5000",
									"extendedTimeOut": "1000",
									"showEasing": "swing",
									"hideEasing": "linear",
									"showMethod": "fadeIn",
									"hideMethod": "fadeOut"
								};
								$.ajax({
									url: "/back/doLogin",
									method: 'POST',
									dataType: 'json',
									data: {
										username: $(form).find('#username').val(),
										password: $(form).find('#passwd').val()
									},
									success: function(resp) {
										show_loading_bar({
											delay: .5,
											pct: 100,
											finish: function(){
												// Redirect after successful login page (when progress bar reaches 100%)
												if(resp.errorNo == 0) {
													window.location.href = resp.url;
												} else {
													toastr.error(resp.errorInfo, "Invalid Login!", opts);
													//$passwd.select();
												}
											}
										});
									}
								});
							}
						});
						// Set Form focus
						$("form#login .form-group:has(.form-control):first .form-control").focus();
					});
				</script>
				
				<!-- Errors container -->
				<div class="errors-container">
				
				</div>
				
				<!-- Add class "fade-in-effect" for login form effect -->
				<form method="post" role="form" id="login" class="login-form fade-in-effect">
					<div class="login-header">
						<h1><a href="javascript:;" class="" style="color:#fff;">${t.sign_in }</a></h1>
						<p>Dear user, log in to access the admin area!</p>
					</div>
					<div class="form-group">
						<label class="control-label" for="username">${t.t_username }</label>
						<input type="text" class="form-control input-dark" name="username" id="username" autocomplete="off" />
					</div>
					<div class="form-group">
						<label class="control-label" for="passwd">${t.t_pwd }</label>
						<input type="password" class="form-control input-dark" name="passwd" id="passwd" autocomplete="off" />
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-gray  btn-block text-left">
							<i class="fa-lock"></i>${t.sign_in }
						</button>
					</div>
					<div class="login-footer">
						<a href="#">${t.t_forget_pwd }</a>
					</div>
				</form>
				
			</div>
			
		</div>
	</div>
	
		<!-- Bottom Scripts -->
	<script src="/backendstyle/js/bootstrap.min.js"></script>
	<script src="/backendstyle/js/TweenMax.min.js"></script>
	<script src="/backendstyle/js/resizeable.js"></script>
	<script src="/backendstyle/js/joinable.js"></script>
	<script src="/backendstyle/js/xenon-api.js"></script>
	<script src="/backendstyle/js/xenon-toggles.js"></script>
	<script src="/backendstyle/js/jquery-validate/jquery.validate.min.js"></script>
	<script src="/backendstyle/js/toastr/toastr.min.js"></script>


	<!-- JavaScripts initializations and stuff -->
	<script src="/backendstyle/js/xenon-custom.js"></script>
	

</body>
</html>