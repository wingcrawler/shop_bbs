<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <link rel="stylesheet" href="../frontstyle/css/base.css" type="text/css" />
  <link rel="stylesheet" href="../frontstyle/css/registerstyle.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<div class="register1">
<form name="form1" method="post" action="/user/doRegister" onsubmit="return check()">
  <img src="../frontstyle/images/register.png" class="reg_bg">
  <div class="box">
    <p>用户注册</p>
    <div class="input">
      <div class="margin"><span>*</span>账号：<input type="text" name="username" class="user" id="rename" /></div>
      <span>*</span>请设置密码：<input type="password" name="password" class="key"/ id="key"><br/>
      <span>*</span>请确认密码：<input type="password" name="repassword" class="key1"/ id="key1"><br/>
      <div class="margin"><span>*</span>邮箱：<input type="text" name="userMail" class="email" id="email" /></div>
    </div> 
    <label><input type="checkbox" name="checked" class="check" checked="checked">我已阅读并同意<a href="###" class="ftp">《赛奇尔服务协议》</a></label> 
    <input id="btn "type="submit" class="pload" value="立即注册" />   

  </div>
</form>
</div>

<script language="javascript">
pload.onclick=function(){
   function check()
    {
      var pattern=/^[\u4e00-\u9fa5 a-zA-Z0-9]{2,}$/; 
      var 
    rename=document.getElementById("rename");
    key=document.getElementById("key");
    key1=document.getElementById("key1");
    email=document.getElementById("email");
    if (rename.value=="")
        {
            alert("提示：用户名不能为空！")
            rename.focus() 
            return false
        }

    if (key.value=="")
        {
            alert("提示： 密码不能为空！")
            key.focus()
            return false
        }
    if (key1.value=="")
        {
            alert("提示： 密码不能为空！")
            key1.focus()
            return false
        }

     if (email.value=="")
        {
            alert("提示:邮箱不能为空！")
            email.focus()
            return false
        }    
    }
}
</script>

</body>
</html>