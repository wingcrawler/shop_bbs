<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>${t.t_user_login}</title>
  	<link rel="stylesheet" href="/frontstyle/css/base.css" type="text/css" />
  	<link rel="stylesheet" href="/frontstyle/css/registerstyle.css" type="text/css" />
  	<script src="/frontstyle/js/jquery.min.js"></script>
  	<script src="/backendstyle/js/jquery.form.js"></script>
<script src="/frontstyle/js/jq.common.js"></script>
</head>
<body>
<div class="register1">
	<form name="form1" method="post" class="form">
		<img src="/frontstyle/images/register.png" class="reg_bg">
	  	<div class="onload1">
	    	<div class="load1">
	      		<p class="userload">${t.t_user_forget_passwd}</p>
		      		<input type="text" placeholder="${t.t_username}"  class="user1" name="username" />
		      		<div class="margin">
						<input type="text"
							name="userMail" placeholder="${t.register_email }" class="email" id="email" />
					</div>
		      		<!--<input type="password" placeholder="${t.t_pwd}"  class="key1" name="password" />-->
		      		<input type="text" placeholder="验证码"  class="code" id="code" />
		      		<input type="button" class="pload" value="${t.t_user_forget_passwd}" onclick="jQuery.common.login('.form','/user/forget_password')" />
		      		<label class="span">
		      		</label> 
		      		
	      </div>
	  </div>
	</form>
</div>

</body>
</html>


