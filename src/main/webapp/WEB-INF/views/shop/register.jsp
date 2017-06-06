<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="include/meta.jsp"></jsp:include>
<link rel="stylesheet" href="../frontstyle/css/base.css" type="text/css" />
<link rel="stylesheet" href="../frontstyle/css/registerstyle.css"
	type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<div class="register1">
		<form name="form1" method="post" action="" onsubmit="return check()">
			<img src="../frontstyle/images/register.png" class="reg_bg">
			<div class="box">
				<p>${t.register }</p>
				<div class="input">
					<div class="margin">
						<span>*</span>${t.register_account }：<input type="text" name="username" class="user"
							id="rename" />
					</div>
					<span>*</span>${t.register_password }：<input type="password" name="password"
						class="key" / id="key"><br /> <span>*</span>${t.register_confirm  }：<input
						type="password" name="repassword" class="key1" / id="key1"><br />
					<div class="margin">
						<span>*</span>${t.register_email }：<input type="text" name="userMail" class="email"
							id="email" />
					</div>
				</div>
				<label><input type="checkbox" name="checked" class="check"
					checked="checked">我已阅读并同意<a href="###" class="ftp">《赛奇尔服务协议》</a></label>
				<input id="onsubmit" type="submit" class="pload" value="立即注册" />

			</div>
		</form>
	</div>


	<script type="text/javascript">
	var error_user_empty='${t.error_empty_username}';
	var error_password_empty='${t.error_empty_pwd}';
	var register_sucess='${t.register_succee}';
	var register_failed='${t.register_failed}';
		$(function() {
			$(" #onsubmit").on('click', function() {
				var $username = $("#rename").val(), 
				$pass = $("#key").val();
				$pass2 = $("#key1").val();
				$mail = $("#email").val();
				if ($username == '' || $pass == '') {
					alert(error_user_empty);
					return false;
				} else {
					var datas = {
						username : $username,
						password : $pass,
						repassword : $pass2,
						userMail : $mail,
					};
					$.ajax({
						url : '/user/doRegister',
						type : 'post',
						dataType : 'json',
						data : datas,
						success : function(result) {
							if (result.errorNo == -1) {
								alert(result.errorInfo);
							} else {
								alert(register_sucess);
								window.location.href = '/user/login';//登录成功跳转
							}
						},
						error : function() {
							alert(register_failed);
						}
					})
				}
				return false;
			})
			$("#getsubmit").click(
					function() {
						var $username = $("#getusername").val(), $pass = $(
								"#getpass").val();
						if ($username == '' || $pass == '') {
							alert("用户名及密码不能为空");
							return false;
						} else {
							var datas = {
								username : $username,
								pass : $pass
							}
							$.ajax({
								url : 'adduser.php',
								type : 'post',
								data : datas,
								dataType : 'json',
								success : function(reslut) {
									if (reslut == "repeat") {
										alert("该用户名已存在");
									} else if (reslut == 'success') {
										alert("注册成功");
										//注册成功后立即切换至登录表单,并记住用户名及密码;
										$("#form2").hide();
										$("#form1").show();
										$("#username").val($username);
										$("#pass").val($pass);
									} else {
										alert('false');
									}
								}
							})
						}
						return false;
					})
		})
	</script>
</body>
</html>