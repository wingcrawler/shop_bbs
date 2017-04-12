<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <%@ include file="/common/global.jsp" %>
    <title>主页</title>
    <%@ include file="/common/meta.jsp" %>
    <%@ include file="/common/include-base-styles.jsp" %>
    <link type="text/css" rel="stylesheet" href="${ctx}/css/index.css"/>
</head>

<body>
<%@include file="/common/header-nav.jsp" %>
<div class="container">

    <div class="jumbotron">
        <h1>
            小小商店
        </h1>

        <p>
            Spring MVC + Spring Data JPA + Bootstrap 3
        </p>

        <p>
            <a class="btn btn-primary btn-large" href="http://github.com/vito16/shop">Github</a>
        </p>
    </div>

</div>
<!-- /container -->
<%@include file="/common/footer.jsp" %>
<%@ include file="/common/include-base-js.jsp" %>
</body>
</html>